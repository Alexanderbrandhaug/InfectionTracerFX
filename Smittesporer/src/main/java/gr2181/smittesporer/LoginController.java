package gr2181.smittesporer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends AbstractController {

    private FileHandler fileHandler;

    @FXML
    private TextField email_txt;

    @FXML
    private TextField password_txt;

    @FXML
    void loginBtn(ActionEvent event) {
        username = email_txt.getText();
        App.changeScene("main.fxml");
    }

    @FXML
    void registerBtn(ActionEvent event) {
        App.changeScene("registration.fxml");
    }

}
