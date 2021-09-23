package gr2181.smittesporer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    private Stage window1 = new Stage();

    @FXML
    void loginBtn(ActionEvent event) {

        try {

            App.changeScene("main.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void registerBtn(ActionEvent event) {

        try {

            App.changeScene("registration.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
