package infectiontracer.ui;

import infectiontracer.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/** Controller for the registration screen of the application. */
public class RegistrationController extends AbstractController {

  @FXML private TextField forenameTxt;

  @FXML private TextField lastnameTxt;

  @FXML private TextField emailTxt;

  @FXML private PasswordField passwordTxt;

  @FXML private PasswordField verifyPasswordTxt;

  @FXML private Button registrationCloseBtnId;

  @FXML private Button registerBtnId;

  @FXML
  void registerToLogin(ActionEvent event) {
    screenController.switchToLogin(event);
  }

  @FXML
  void registerUser(ActionEvent event) {
    String postUrl = myUrl + "users";
    if (!passwordTxt.getText().equals(verifyPasswordTxt.getText())) {
      createErrorDialogBox(
          "Not identical passwords", null, "The two passwords entered are different.");
      return;
    }
    try {
      User newUser =
          new User(
              forenameTxt.getText(),
              lastnameTxt.getText(),
              emailTxt.getText(),
              passwordTxt.getText(),
              "",
              "");
      String userJson = fileHandler.userToJson(newUser);
      if (createPostRequest(postUrl, userJson)) {
        createInformationDialogBox(
            "Successful registration", null, "The registration was successful");
        screenController.switchToLogin(event);
      }
    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Unsuccessful registration", null, e.getMessage());
    }
  }

  @FXML
  void closeRegistration(ActionEvent event) {
    Stage stage = (Stage) registrationCloseBtnId.getScene().getWindow();
    stage.close();
  }
}
