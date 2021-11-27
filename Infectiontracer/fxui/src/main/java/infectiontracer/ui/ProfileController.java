package infectiontracer.ui;

import infectiontracer.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Controller for the profile screen of the application. */
public class ProfileController extends AbstractController {

  @FXML private TextField emailTxt;

  @FXML private TextField firstNameTxt;

  @FXML private TextField lastNameTxt;

  @FXML private Button saveBtn;

  @FXML private PasswordField verifyPasswordTxt;

  @FXML private PasswordField newPasswordTxt;

  @FXML private Button closeBtnProfile;

  ProfileController(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  @FXML
  void closeProfile(ActionEvent event) {
    Stage stage = (Stage) closeBtnProfile.getScene().getWindow();
    stage.close();
  }

  @FXML
  void saveChanges(ActionEvent event) {
    try {
      if (!firstNameTxt.getText().isEmpty()
          && !firstNameTxt.getText().equals(loggedInUser.getForename())) {
        loggedInUser.setForename(firstNameTxt.getText());
      }
      if (!lastNameTxt.getText().isEmpty()
          && !lastNameTxt.getText().equals(loggedInUser.getLastname())) {
        loggedInUser.setLastname(lastNameTxt.getText());
      }
      if (!newPasswordTxt.getText().equals(verifyPasswordTxt.getText()) && (!verifyPasswordTxt.getText().isEmpty() || !newPasswordTxt.getText().isEmpty())) {
        throw new IllegalArgumentException("Passwords must be identical!");
      }
      if (!newPasswordTxt.getText().isEmpty()) {
        loggedInUser.setPassword(verifyPasswordTxt.getText());
      }
      String updatedUserJson = fileHandler.userToJson(loggedInUser);
      String putUrl = myUrl + "user/" + loggedInUser.getEmail();
      if (createPutRequest(putUrl, updatedUserJson)) {
        createInformationDialogBox(
            "Changes saved", null, "Changes to user profile successfully saved.");
      }
    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Can't save changes to users profile", null, e.getMessage());
    }
  }

  @FXML
  void deleteUserBtn(ActionEvent event) {
    String deleteUrl = myUrl + "user/" + loggedInUser.getEmail();
    if (createDeleteRequest(deleteUrl)) {
      createInformationDialogBox(
          "User deleted", null, "Your profile has been successfully deleted.");
      screenController.switchToLogin(event);
    }
  }

  @FXML
  void backToMain(MouseEvent event) {
    screenController.switchToMainFromProfile(event, loggedInUser);
  }

  @FXML
  private void initialize() {
    emailTxt.setText(loggedInUser.getEmail());
    firstNameTxt.setText(loggedInUser.getForename());
    lastNameTxt.setText(loggedInUser.getLastname());
  }
}
