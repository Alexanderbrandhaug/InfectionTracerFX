package infectiontracer.ui;

import infectiontracer.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/* Controller for the profile screen of the application*/

public class ProfileController extends AbstractController {

  @FXML
  private TextField emailTxt;

  @FXML
  private TextField firstnameTxt;

  @FXML
  private TextField lastnameTxt;

  @FXML
  private Button saveBtn;

  @FXML
  private PasswordField verifyPasswordTxt;

  @FXML
  private PasswordField newPasswordTxt;

  @FXML
  private Button closeBtnProfile;

  @FXML
  private Button deleteUserbtnID;

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
        if (newPasswordTxt.getText().equals(verifyPasswordTxt.getText())) {
          URI endpointBaseUri = new URI(myUrl+"user/"+loggedInUser.getEmail());
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build()
                    .send(request,HttpResponse.BodyHandlers.ofString());

            User originalUser = fileHandler.jsonToUser(response.body());
          originalUser.setForename(firstnameTxt.getText());
          originalUser.setLastname(lastnameTxt.getText());
          originalUser.setPassword(verifyPasswordTxt.getText());

          String updatedUserJson = fileHandler.userToJson(originalUser);
          System.out.println(updatedUserJson);
            URI endpointBaseUri2 = new URI(myUrl+"user/"+loggedInUser.getEmail());
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
  void deleteUserBtn(ActionEvent event) {
    if (fileHandler.deleteUserFromFile(loggedInUser.getEmail())){
      Stage stage = (Stage) deleteUserbtnID.getScene().getWindow();
      stage.close();
    } else {
      createErrorDialogBox("Error", null, "Error when trying to delete your account");
    }
  }



  @FXML
    void backToMain(MouseEvent event) {
      screenController.switchToMainFromProfile(event, loggedInUser);
    }


  @FXML
  private void initialize() {
    emailTxt.setText(loggedInUser.getEmail());
    firstnameTxt.setText(loggedInUser.getForename());
    lastnameTxt.setText(loggedInUser.getLastname());
    
  }

}