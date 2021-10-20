package infectiontracer.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import infectiontracer.core.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class RegistrationController extends AbstractController {

    final FileHandler file_handler = new FileHandler();
    final ScreenController screencontroller = new ScreenController();

    @FXML
    private TextField forename_txt;

    @FXML
    private TextField lastname_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private TextField password_txt;

    @FXML
    private TextField verify_password_txt;

    @FXML
    private Button closeBtnRegistration;
    @FXML
    private Button registerBtnID;

    @FXML
    void RegisterToLogin(ActionEvent event) {
        screencontroller.switchToLogin(event);
    }

    @FXML
    void RegisterUser(ActionEvent event) {

        try {
            if(password_txt.getText().equals(verify_password_txt.getText())){
                User new_user = new User(forename_txt.getText(), lastname_txt.getText(), email_txt.getText(),
                        password_txt.getText(), "", "");
                file_handler.insertUser(new_user);
                createInformationDialogBox("Successful registration", null, "The registration was successful");
                screencontroller.switchToLogin(event);
            }
        }catch (IllegalArgumentException e) {
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
