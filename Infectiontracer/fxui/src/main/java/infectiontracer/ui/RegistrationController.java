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
  private final String myUrl = "http://localhost:8080/infectiontracer/";
  final Gson gson = new Gson();

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
          URI endpointBaseUri = new URI(myUrl+"users");
          String json = gson.toJson(newUser);
          System.out.println(json);
          HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
                  .header("Accept", "application/json")
                  .header("Content-Type", "application/json")
                  .POST(HttpRequest.BodyPublishers.ofString(json))
                  .build();
          final HttpResponse<String> response = HttpClient.newBuilder().build()
                  .send(request,HttpResponse.BodyHandlers.ofString());
          System.out.println(response);
          if(newUser.getPassword().equals(passwordTxt.getText())){
            screencontroller.switchToMain(event, newUser.getEmail());
          }
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
