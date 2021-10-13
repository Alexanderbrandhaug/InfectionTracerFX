package infectiontracer.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import infectiontracer.core.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class LoginController extends AbstractController {

    private FileHandler fileHandler = new FileHandler();
    private ScreenController screencontroller = new ScreenController();
    

    @FXML
    private Button closeBtnLogin;
    @FXML
    private TextField email_txt;

    @FXML
    private PasswordField password_txt;

    @FXML
    void loginBtn(ActionEvent event) {

        try {
            for (User ele : fileHandler.getUsers()) {
                if (email_txt.getText().equals(ele.getEmail()) && password_txt.getText().equals(ele.getPassword())) {
                    screencontroller.switchToMain(event, email_txt.getText());
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
    void registerBtn(ActionEvent event) throws IOException {
        screencontroller.switchToRegistration(event);
    }

    @FXML
    void closeLogin(ActionEvent event) {
        Stage stage = (Stage)closeBtnLogin.getScene().getWindow();
        stage.close();
    }
    
}
