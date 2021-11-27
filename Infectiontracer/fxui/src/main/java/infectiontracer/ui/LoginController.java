package infectiontracer.ui;

import infectiontracer.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Controller for the login screen of the application. */
public class LoginController extends AbstractController {

  @FXML private Button closeBtnLogin;

  @FXML private TextField emailTxt;

  @FXML private PasswordField passwordTxt;

  @FXML
  void loginBtn(ActionEvent event) {
    if (emailTxt.getText().isEmpty()) {
      createErrorDialogBox(
          "Login information is incorrect", null, "Email/password combination is not valid.");
      return;
    }
    String url = myUrl + "user/" + emailTxt.getText();
    String userJson = createGetRequest(url);
    User user = fileHandler.jsonToUser(userJson);
    if (user != null) {
      if (user.getPassword() != null && user.getPassword().equals(passwordTxt.getText())) {
        screenController.switchToMain((Node) event.getSource(), user);
      }
    } else {
      createErrorDialogBox(
          "Login information is incorrect", null, "Email/password combination is not valid.");
    }
  }

  @FXML
  void forgotPasswordBtn(ActionEvent event) {
    if (emailTxt.getText().isEmpty()) {
      createErrorDialogBox("Error", null, "Please insert your email in the textfield.");
      return;
    }

    String getUrl = myUrl + "/user/" + emailTxt.getText();
    String userJson = createGetRequest(getUrl);
    if (userJson == null) {
      return;
    }
    String putUrl = myUrl + "user/" + emailTxt.getText();
    if (createPutRequest(putUrl, userJson)) {
      createInformationDialogBox(
          "Email sent", null, "Email was successfully sent with your new password.");
    }
  }

  @FXML
  void registerBtn(ActionEvent event) {
    screenController.switchToRegistration(event);
  }

  @FXML
  void closeLogin(ActionEvent event) {
    Stage stage = (Stage) closeBtnLogin.getScene().getWindow();
    stage.close();
  }
}
