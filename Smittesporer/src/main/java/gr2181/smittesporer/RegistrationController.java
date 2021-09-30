package gr2181.smittesporer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrationController extends AbstractController {

    final FileHandler file_handler = new FileHandler();

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
    void CancelRegisterBtn(ActionEvent event) {
        App.changeScene("login.fxml");
    }

    @FXML
    void RegisterBtn(ActionEvent event) {

        try {
            User new_user = new User(forename_txt.getText(), lastname_txt.getText(), email_txt.getText(),
                    password_txt.getText());
            file_handler.insertUser(new_user);
            App.changeScene("login.fxml");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
