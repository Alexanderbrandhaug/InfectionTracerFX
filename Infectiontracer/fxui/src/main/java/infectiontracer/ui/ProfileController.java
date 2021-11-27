package infectiontracer.ui;

import infectiontracer.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        if (newPasswordTxt.getText().equals(verifyPasswordTxt.getText()) && !newPasswordTxt.getText().isEmpty()) {
          loggedInUser.setForename(firstnameTxt.getText());
          loggedInUser.setLastname(lastnameTxt.getText());
          loggedInUser.setPassword(verifyPasswordTxt.getText());

          String updatedUserJson = fileHandler.userToJson(loggedInUser);
          String endpointBaseUri = myUrl+"user/"+loggedInUser.getEmail();
          createPutRequest(endpointBaseUri, updatedUserJson);            
        } 
        if(newPasswordTxt.getText().isEmpty() && verifyPasswordTxt.getText().isEmpty()){
          loggedInUser.setForename(firstnameTxt.getText());
          loggedInUser.setLastname(lastnameTxt.getText());
          
          String updatedUserJson = fileHandler.userToJson(loggedInUser);
          String endpointBaseUri = myUrl+"user/"+loggedInUser.getEmail();
          createPutRequest(endpointBaseUri, updatedUserJson); 
        }
      } catch (IllegalArgumentException e) {
        createInformationDialogBox("Can't save changes to users profile", null, e.getMessage());
    } catch (Exception e) {
      createErrorDialogBox("Error", null, "Error when updating users profile");
    }
  }



  @FXML
  void deleteUserBtn(ActionEvent event) {
   /* if (fileHandler.deleteUserFromFile(loggedInUser.getEmail())){
      Stage stage = (Stage) deleteUserbtnID.getScene().getWindow();
      stage.close();
    } */
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