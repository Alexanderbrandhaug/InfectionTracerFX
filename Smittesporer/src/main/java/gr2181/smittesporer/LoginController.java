package gr2181.smittesporer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController extends AbstractController {

    private FileHandler fileHandler = new FileHandler();
    private ScreenController screencontroller = new ScreenController();

    @FXML
    private TextField email_txt;

    @FXML
    private PasswordField password_txt;

    @FXML
    void loginBtn(ActionEvent event) {

        try {
            for (User ele : fileHandler.getUsers()) {
                if (email_txt.getText().equals(ele.getEmail()) && password_txt.getText().equals(ele.getPassword())) {
                    username = email_txt.getText();
                    screencontroller.switchToMain(event);
                    return;
                }

            }
            createErrorDialogBox("Error Dialog", "Invalid Email/Password combination",
                    "Oops, the Email and password combination does not exist");
        } catch (Exception FileNotFoundException) {

        }

    }

    @FXML
    void registerBtn(ActionEvent event) throws IOException {
        screencontroller.switchToRegistration(event);
    }

}
