package gr2181.smittesporer;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationController {


    private Stage window1 = new Stage();

    @FXML
    void CancelRegisterBtn(ActionEvent event) {

        try {
            
            App.changeScene("login.fxml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void RegisterBtn(ActionEvent event) {
           
  
        try {
            
            App.changeScene("login.fxml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }

}


