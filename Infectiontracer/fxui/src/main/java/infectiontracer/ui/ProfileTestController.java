package infectiontracer.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Controller for the profile screen of the application. */
public class ProfileTestController extends AbstractController {

  @FXML private TextField emailTxt;

  @FXML private TextField firstNameTxt;

  @FXML private TextField lastNameTxt;

  @FXML private Button saveBtn;

  @FXML private PasswordField verifyPasswordTxt;

  @FXML private PasswordField newPasswordTxt;

  @FXML private Button closeBtnProfile;

  @FXML
  void closeProfile(ActionEvent event) {
    Stage stage = (Stage) closeBtnProfile.getScene().getWindow();
    stage.close();
  }

  @FXML
  void saveChanges(ActionEvent event) {}

  @FXML
  void deleteUserBtn(ActionEvent event) {}

  @FXML
  void backToMain(MouseEvent event) {
    screenController.switchToMainTest((Node) event.getSource());
  }

  @FXML
  void initialize() {
    emailTxt.setText("test@test.com");
    firstNameTxt.setText("test");
    lastNameTxt.setText("test");
    newPasswordTxt.setText("test");
    verifyPasswordTxt.setText("test");
  }
}
