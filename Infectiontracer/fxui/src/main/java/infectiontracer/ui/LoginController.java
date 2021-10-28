package infectiontracer.ui;

import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Controller for the login screen of the application. */
public class LoginController extends AbstractController {

  private final FileHandler fileHandler = new FileHandler();
  private final ScreenController screencontroller = new ScreenController();

  @FXML
  private Button closeBtnLogin;

  @FXML
  private TextField emailTxt;

  @FXML
  private PasswordField passwordTxt;

  @FXML
  void loginBtn(ActionEvent event) {

    try {

      for (User ele : fileHandler.getUsers()) {
        if (emailTxt.getText().equals(ele.getEmail()) && passwordTxt.getText().equals(ele.getPassword())) {
          screencontroller.switchToMain(event, emailTxt.getText());
          return;
        }
      }
      createErrorDialogBox("Error Dialog", "Invalid Email/Password combination",
          "Oops, the Email and password combination does not exist");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void registerBtn(ActionEvent event) {
    screencontroller.switchToRegistration(event);
  }

  @FXML
  void closeLogin(ActionEvent event) {
    Stage stage = (Stage) closeBtnLogin.getScene().getWindow();
    stage.close();
  }
}
