package gr2181.smittesporer;

import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController extends AbstractController {

    private FileHandler fileHandler = new FileHandler();

    @FXML
    private TextField email_txt;

    @FXML
    private PasswordField password_txt;

    @FXML
    void loginBtn(ActionEvent event) {
        App.changeScene("main.fxml");
        // try {
        // for (User ele : fileHandler.getUsers()) {
        // if (email_txt.getText().equals(ele.getEmail()) &&
        // password_txt.getText().equals(ele.getPassword())) {
        // App.changeScene("main.fxml");
        // }
        // }
        // } catch (Exception FileNotFoundException) {
        // createErrorDialogBox("Error Dialog", "Invalid Email/Password combination",
        // "Oops, the Email and password combination does not exist");
        // }

    }

    @FXML
    void registerBtn(ActionEvent event) {
        App.changeScene("registration.fxml");
    }

}
