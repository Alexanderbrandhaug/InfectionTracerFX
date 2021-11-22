package infectiontracer.ui;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import infectiontracer.core.EmailService;
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
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

/** Controller for the main screen of the application. */
public class MainController extends AbstractController {

  MainController(String username) {
    this.username = username;
  }

  private final InfectionTracer infectionTracer = new InfectionTracer();
  final ObservableList<User> contactList = FXCollections.observableArrayList();
  final ScreenController screencontroller = new ScreenController();
  private final String myUrl = "http://localhost:8080/infectiontracer/";
  private Gson gson = new Gson();
  private EmailService emailservice = new EmailService();


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
    String url = myUrl+"user/"+username+"/healthstatus/makehealthy";
    String json = gson.toJson(infectionTracer.getActiveUser(username));
    if(createPutRequest(url, json)) {;
      infectionStatus.setText("Covid-19 Negative");
    }
    else {
      createErrorDialogBox("Status change failed", null, "Failed to change infection status to 'Covid-19 Negative'");
    }

  }

  @FXML
  void fireInfectedUser(ActionEvent event) {
    try {
      URI endpointBaseUri = new URI(myUrl+"user/"+username+"/healthstatus/makesick");
      String json = gson.toJson(infectionTracer.getActiveUser(username));
      System.out.println(json);
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build()
              .send(request,HttpResponse.BodyHandlers.ofString());
              infectionStatus.setText("Infected");
              System.out.println(response);
              List<User> currentMap = infectionTracer.getUsersCloseContacts(username);
              createInformationDialogBox(
                "Email sent", null, "Email notification was sent to your closecontacts");
              emailservice.sendEmail(username, currentMap);
              
           
              
      
    } catch (IllegalArgumentException e) {
      createInformationDialogBox("Can't change health status", null, e.getMessage());
      
    } catch (Exception e) {
      createErrorDialogBox("Error", null, "Error when updating healthstatus to sick");
    }
  }


  @FXML
  void addContact(ActionEvent event) {
    String url = myUrl+"user/"+username+"/closecontacts";
    String json = gson.toJson(infectionTracer.getActiveUser(contactNameTxt.getText()));

    if (createPostRequest(url, json)) {
      createInformationDialogBox("New close contact added", null, "New close contact successfully added.");
      List<User> currentMap = infectionTracer.getUsersCloseContacts(username);
      refreshInfo(currentMap);
    }
    else {
      createErrorDialogBox("Failed to add close contact", null, "Failed to add new close contact.");
    }
  }

  @FXML
  void removeCloseContact(ActionEvent event) {
    String url = myUrl+"user/"+username+"/closecontacts/removeContact";
    User closeContact = contactTable.getSelectionModel().getSelectedItem();
    String json = gson.toJson(closeContact);

    if (createPostRequest(url, json)) {
      createInformationDialogBox("Close contact removed", null, "The selected close contact has successfully been removed.");
      List<User> currentMap = infectionTracer.getUsersCloseContacts(username);
      refreshInfo(currentMap);
    }
    else {
      createErrorDialogBox("Deletion failed", null, "Failed to remove selected close contact.");
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

    String url = myUrl+"user/"+username+"/closecontacts";
    String closecontacts = createGetRequest(url);
    List<User> userList = gson.fromJson(closecontacts, new TypeToken<List<User>>() {}.getType());
    if (userList == null) {
      return;
    }
    refreshInfo(userList);
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

  private void refreshInfo(List<User> userList) {
    contactList.clear();
    contactList.addAll(userList);
    numberOfContacts.setText(String.valueOf(userList.size()));
    contactTable.setItems(contactList);
  }
}