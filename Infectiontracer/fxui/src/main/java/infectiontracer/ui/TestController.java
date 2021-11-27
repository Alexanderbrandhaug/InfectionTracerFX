package infectiontracer.ui;

import infectiontracer.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** Controller for testing the main screen of the application. */
public class TestController extends AbstractController {

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
  void fireHealthyUser(ActionEvent event) {}

  @FXML
  void fireInfectedUser(ActionEvent event) {}

  @FXML
  void addContact(ActionEvent event) {}

  @FXML
  void removeCloseContact(ActionEvent event) {}

  @FXML
  void profileBtn(ActionEvent event) {}

  @FXML
  void mainToLogin(ActionEvent event) {
    screenController.switchToLogin(event);
  }

  @FXML
  void closeMain(ActionEvent event) {
    Stage stage = (Stage) closeBtnMain.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void initialize() {
    usernameLbl.setText("test");
    infectionStatus.setText("test");
    numberOfContacts.setText("test");

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    healthstatusColumn.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
    dateOfInfectionColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInfection"));
  }
}
