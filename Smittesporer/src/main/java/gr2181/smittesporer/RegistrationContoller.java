package gr2181.smittesporer;


public class RegistrationController {


    private Stage window1 = new Stage();

    @FXML
    void CancelRegisterBtn(ActionEvent event) {
           
           Stage stage = (Stage) closeButton.getScene().getWindow();
           stage.close();


    }

    @FXML
    void RegisterBtn(ActionEvent event) {
           
  
        try {
            
            window1.setScene(new Scene(FXMLLoader.load(getClass().getResource("login.fxml"))));
            window1.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }

    }

}
