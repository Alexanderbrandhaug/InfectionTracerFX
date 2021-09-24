package gr2181.smittesporer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    void loginBtn(ActionEvent event) {
        App.changeScene("main.fxml");
    }

    @FXML
    void registerBtn(ActionEvent event) {
        App.changeScene("registration.fxml");
    }

}
