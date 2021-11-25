package infectiontracer.ui;

import infectiontracer.core.User;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** Controller for the main screen of the application. */
public class MainController extends AbstractController {

  MainController(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  private final ObservableList<User> contactList = FXCollections.observableArrayList();

  @FXML private Label usernameLbl;

  @FXML private Label infectionStatus;

  @FXML private Label numberOfContacts;

  @FXML private TableView<User> contactTable;
  @FXML private TableColumn<User, String> nameColumn;

  @FXML private TableColumn<User, String> lastnameColumn;

  @FXML private TableColumn<User, String> emailColumn;

  @FXML private TableColumn<User, String> healthstatusColumn;

  @FXML private TableColumn<User, String> dateOfInfectionColumn;

  @FXML private TextField contactNameTxt;

  @FXML private Button closeBtnMain;

  @FXML
  void fireHealthyUser(ActionEvent event) {
    String getUrl = myUrl + "user/" + loggedInUser.getEmail();
    String json = createGetRequest(getUrl);
    String putUrl = myUrl + "user/" + loggedInUser.getEmail() + "/healthstatus/makehealthy";
    if (createPutRequest(putUrl, json)) {
      infectionStatus.setText("Covid-19 Negative");
      createInformationDialogBox(
          "Health status changed", null, "Health status successfully changed.");
    } else {
      createErrorDialogBox(
          "Status change failed", null, "Failed to change infection status to 'Covid-19 Negative'");
    }
  }

  @FXML
  void fireInfectedUser(ActionEvent event) {

    String getUrl = myUrl + "user/" + loggedInUser.getEmail();
    String userJson = createGetRequest(getUrl);
    String putUrl = myUrl + "user/" + loggedInUser.getEmail() + "/healthstatus/makesick";
    if (createPutRequest(putUrl, userJson)) {
      infectionStatus.setText("Infected");
      createInformationDialogBox(
          "Email sent", null, "Email notification was sent to your closecontacts");
    } else {
      createErrorDialogBox("Error", null, "Error when updating healthstatus to sick");
    }
  }

  @FXML
  void addContact(ActionEvent event) {

    if (contactNameTxt.getText().isEmpty()) {
      createErrorDialogBox(
          "Invalid closecontact", null, "Please insert email of an existing user in the textfield");
      return;
    }
    String getUrl = myUrl + "user/" + contactNameTxt.getText();
    String closeContactJson = createGetRequest(getUrl);
    String postUrl = myUrl + "user/" + loggedInUser.getEmail() + "/closecontacts";
    if (createPostRequest(postUrl, closeContactJson)) {
      createInformationDialogBox(
          "New close contact added", null, "New close contact successfully added.");
      contactList.add(fileHandler.jsonToUser(closeContactJson));
      refreshInfo();
      contactNameTxt.setText("");
    } else {
      createErrorDialogBox("Failed to add", null, "Error when attempting to add close contact.");
    }
  }

  @FXML
  void removeCloseContact(ActionEvent event) {
    String postUrl = myUrl + "user/" + loggedInUser.getEmail() + "/closecontacts/removecontact";
    User closeContact = contactTable.getSelectionModel().getSelectedItem();
    String userJson = fileHandler.userToJson(closeContact);

    if (createPostRequest(postUrl, userJson)) {
      createInformationDialogBox(
          "Close contact removed",
          null,
          "The selected close contact has successfully been removed.");
      contactList.remove(closeContact);
      refreshInfo();
    } else {
      createErrorDialogBox("Deletion failed", null, "Failed to remove selected close contact.");
    }
  }

  @FXML
  private void initialize() {
    usernameLbl.setText(loggedInUser.getEmail());
    infectionStatus.setText(loggedInUser.getHealthStatus());

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    healthstatusColumn.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
    dateOfInfectionColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInfection"));

    String getUrl = myUrl + "user/" + loggedInUser.getEmail() + "/closecontacts";
    String closeContactsJson = createGetRequest(getUrl);
    List<User> userList = fileHandler.jsonToUserList(closeContactsJson);
    if (userList == null) {
      return;
    }
    contactList.addAll(userList);
    refreshInfo();
  }

  @FXML
  void mainToLogin(ActionEvent event) {
    screenController.switchToLogin(event);
  }

  @FXML
  void closeMain(ActionEvent event) {
    Stage stage = (Stage) closeBtnMain.getScene().getWindow();
    stage.close();
  }

  private void refreshInfo() {
    numberOfContacts.setText(String.valueOf(contactList.size()));
    contactTable.setItems(contactList);
  }
}
