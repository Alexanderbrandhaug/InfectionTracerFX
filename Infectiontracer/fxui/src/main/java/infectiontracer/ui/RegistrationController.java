package infectiontracer.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import infectiontracer.core.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class RegistrationController extends AbstractController {

    final FileHandler file_handler = new FileHandler();
    ScreenController screencontroller = new ScreenController();

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
    void RegisterToLogin(ActionEvent event) throws IOException {
        screencontroller.switchToLogin(event);
    }

    @FXML
    void RegisterBtn(ActionEvent event) {

        try {
            // boolean test = false;
            String test1 = "";
                    
                    if(password_txt.getText().equals(verify_password_txt.getText())){
                        User new_user = new User(forename_txt.getText(), lastname_txt.getText(), email_txt.getText(),
                        password_txt.getText(), test1, test1);
                        file_handler.insertUser(new_user);
                        screencontroller.switchToLogin(event);
                    }
                    else{
                        createErrorDialogBox("Error", "Error", "Error");
                    }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void closeRegistration(ActionEvent event) {
        Stage stage = (Stage) closeBtnRegistration.getScene().getWindow();
        stage.close();
    }

    @FXML
    void BackToLoginBtn(ActionEvent event) throws IOException {
        screencontroller.switchToLogin(event);
    }

}
