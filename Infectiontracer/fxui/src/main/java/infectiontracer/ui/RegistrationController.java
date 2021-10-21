package infectiontracer.ui;

import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Controller for the registration screen of the application. */
public class RegistrationController extends AbstractController {

  final FileHandler fileHandler = new FileHandler();
  final ScreenController screencontroller = new ScreenController();

  @FXML private TextField forenameTxt;

  @FXML private TextField lastnameTxt;

  @FXML private TextField emailTxt;

  @FXML private TextField passwordTxt;

  @FXML private TextField verifyPasswordTxt;

  @FXML private Button closeBtnRegistration;
  @FXML private Button registerBtnId;

  @FXML
  void registerToLogin(ActionEvent event) {
    screencontroller.switchToLogin(event);
  }

  @FXML
  void registerUser(ActionEvent event) {

    try {
      if (passwordTxt.getText().equals(verifyPasswordTxt.getText())) {
        User newUser =
            new User(
                forenameTxt.getText(),
                lastnameTxt.getText(),
                emailTxt.getText(),
                passwordTxt.getText(),
                "",
                "");
        fileHandler.insertUser(newUser);
        createInformationDialogBox(
            "Successful registration", null, "The registration was successful");
        screencontroller.switchToLogin(event);
      }
    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Error", null, e.getMessage());
    } catch (Exception e) {
      createErrorDialogBox("Error", null, "Error when creating user");
    }
  }

  @FXML
  void closeRegistration(ActionEvent event) {
    Stage stage = (Stage) closeBtnRegistration.getScene().getWindow();
    stage.close();
  }
}
