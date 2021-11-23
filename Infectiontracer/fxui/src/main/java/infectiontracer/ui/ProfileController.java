package infectiontracer.ui;

import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import com.google.gson.Gson;
import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import infectiontracer.core.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/* Contoller for the profile screen of the application*/

public class ProfileController extends AbstractController {

    @FXML
    private Label emailLbl;

    @FXML
    private Label firstNameLbl;

    @FXML
    private Label lastNameLbl;

    @FXML
    private Label passwordLbl;

    @FXML
    private Button closeBtnProfile;

    @FXML
    private TextField newFirstNameTxt;

    @FXML
    private TextField newLastNameTxt;

    @FXML
    private TextField newPasswordTxt;

    @FXML
    private TextField newVerifyPasswordTxt;

    @FXML
    private Button saveBtn;

    private final InfectionTracer infectionTracer = new InfectionTracer();
    final ScreenController screencontroller = new ScreenController();
    private final String myUrl = "http://localhost:8080/infectiontracer/";
    private Gson gson = new Gson();

    ProfileController(String username) {
      this.username = username;
  }

    @FXML
    void closeProfile(ActionEvent event) {
        Stage stage = (Stage) closeBtnProfile.getScene().getWindow();
        stage.close();
    }

    @FXML
    void profileToMain(ActionEvent event) {
        screencontroller.switchToMain(event, username);
    }

    @FXML
    void saveChanges(ActionEvent event) {
      try {
        if (newPasswordTxt.getText().equals(newVerifyPasswordTxt.getText())) {
          URI endpointBaseUri = new URI(myUrl+"user/"+username);
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build()
                    .send(request,HttpResponse.BodyHandlers.ofString());

            User originalUser = gson.fromJson(response.body(), new TypeToken<User>() {}.getType() /*User.class*/);
          originalUser.setForename(newFirstNameTxt.getText());
          originalUser.setLastname(newLastNameTxt.getText());
          originalUser.setPassword(newPasswordTxt.getText());

          String updatedUserJson = gson.toJson(originalUser);
          System.out.println(updatedUserJson);
            URI endpointBaseUri2 = new URI(myUrl+"user/"+username);
          HttpRequest request2 = HttpRequest.newBuilder(endpointBaseUri2)
                  .header("Accept", "application/json")
                  .header("Content-Type", "application/json")
                  .PUT(HttpRequest.BodyPublishers.ofString(updatedUserJson))
                  .build();
          final HttpResponse<String> response2 = HttpClient.newBuilder().build()
                  .send(request2,HttpResponse.BodyHandlers.ofString());
                  System.out.println(response2);
        }
      } catch (IllegalArgumentException e) {
        createInformationDialogBox("Can't save changes to users profile", null, e.getMessage());
    } catch (Exception e) {
      createErrorDialogBox("Error", null, "Error when updating users profile");
    }
  }

  @FXML
  private void initialize() {
    emailLbl.setText(username);
    firstNameLbl.setText(infectionTracer.getActiveUser(username).getForename());
    lastNameLbl.setText(infectionTracer.getActiveUser(username).getLastname());
    passwordLbl.setText(infectionTracer.getActiveUser(username).getPassword());
  }

}