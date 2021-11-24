package infectiontracer.ui;

import com.google.gson.Gson;
import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/** Controller for the registration screen of the application. */
public class RegistrationController extends AbstractController {

  final FileHandler fileHandler = new FileHandler();
  final ScreenController screencontroller = new ScreenController();
  //final Gson gson = new Gson();

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
    String url = myUrl+"users";
    if (!passwordTxt.getText().equals(verifyPasswordTxt.getText())) {
      createErrorDialogBox("Not identical passwords", null, "The two passwords entered are different.");
      return;
    }
    try {
      User newUser = new User(forenameTxt.getText(), lastnameTxt.getText(), emailTxt.getText(),
              passwordTxt.getText(), "", "");
      String json = gson.toJson(newUser);
      if (createPostRequest(url, json)) {
        createInformationDialogBox(
                "Successful registration", null, "The registration was successful");
        screencontroller.switchToLogin(event);
      }
    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Unsuccessful registration", null, e.getMessage());
    } catch (Exception e) {
      createErrorDialogBox("Unsuccessful registration", null, e.getMessage());
    }
  }


  @FXML
  void closeRegistration(ActionEvent event) {
    Stage stage = (Stage) closeBtnRegistration.getScene().getWindow();
    stage.close();
  }
}
