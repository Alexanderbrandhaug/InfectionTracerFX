package infectiontracer.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import infectiontracer.core.*;

public class RegistrationController extends AbstractController {

    final FileHandler file_handler = new FileHandler();
    ScreenController screencontroller = new ScreenController();

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
    void CancelRegisterBtn(ActionEvent event) throws IOException {
        screencontroller.switchToLogin(event);
    }

    @FXML
    void RegisterBtn(ActionEvent event) {

        try {
            boolean test = false;
            String test1 = "";
            User new_user = new User(forename_txt.getText(), lastname_txt.getText(), email_txt.getText(),
                    password_txt.getText(), test, test1);
            file_handler.insertUser(new_user);
            screencontroller.switchToLogin(event);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
