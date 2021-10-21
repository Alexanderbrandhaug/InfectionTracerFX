package infectiontracer.ui;

import infectiontracer.core.InfectionTracer;
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

  MainController(String username) {
    this.username = username;
  }

  private final InfectionTracer infectionTracer = new InfectionTracer();
  final ObservableList<User> contactList = FXCollections.observableArrayList();
  final ScreenController screencontroller = new ScreenController();

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
    try {
      infectionTracer.makeUserHealthy(username);
      infectionStatus.setText("Covid-19 Negative");
    } catch (IllegalArgumentException e) {
      createInformationDialogBox("Can't change health status", null, e.getMessage());
    }
  }

  @FXML
  void fireInfectedUser(ActionEvent event) {
    try {
      infectionTracer.makeUserInfected(username);
      infectionStatus.setText("Infected");
    } catch (IllegalArgumentException e) {
      createInformationDialogBox("Can't change health status", null, e.getMessage());
    }
  }

  @FXML
  void addContact(ActionEvent event) {
    try {
      infectionTracer.addCloseContact(username, contactNameTxt.getText());
      List<User> currentMap = infectionTracer.getUsersCloseContacts(username);
      contactList.clear();
      contactList.addAll(currentMap);
      numberOfContacts.setText(String.valueOf(contactList.size()));
      contactTable.setItems(contactList);
    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Error", null, e.getMessage());
    }
  }

  @FXML
  private void initialize() {
    usernameLbl.setText(username);
    infectionStatus.setText(infectionTracer.getActiveUser(username).getHealthStatus());

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    healthstatusColumn.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
    dateOfInfectionColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInfection"));
    List<User> usersCloseContacts = infectionTracer.getUsersCloseContacts(username);
    if (usersCloseContacts == null) {
      return;
    }
    contactList.addAll(usersCloseContacts);
    numberOfContacts.setText(String.valueOf(usersCloseContacts.size()));
    contactTable.setItems(contactList);
  }

  @FXML
  void mainToLogin(ActionEvent event) {
    screencontroller.switchToLogin(event);
  }

  @FXML
  void closeMain(ActionEvent event) {
    Stage stage = (Stage) closeBtnMain.getScene().getWindow();
    stage.close();
  }
}
