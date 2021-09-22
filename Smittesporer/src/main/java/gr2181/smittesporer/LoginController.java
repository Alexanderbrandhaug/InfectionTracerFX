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
            
            window1.setScene(new Scene(FXMLLoader.load(getClass().getResource("main.fxml"))));
            window1.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }

    @FXML
    void registerBtn(ActionEvent event) {
           
  
        try {
            
            window1.setScene(new Scene(FXMLLoader.load(getClass().getResource("registration.fxml"))));
            window1.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
