package infectiontracer.ui;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.jfr.Timestamp;
import javafx.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import infectiontracer.ui.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;




public class InfectionTracerRegisterTest extends ApplicationTest {
    
    private ScreenController screencontroller = new ScreenController();
    // private MainController maincontroller = new MainController();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Test
    public void testValidLogin() {
        String forename = "Test";
        String lastname = "Testersen";
        String username = "test@gmail.com";
        String password = "Passord123";
        clickOn("#forename_txt").write(forename);
        clickOn("#lastname_txt").write(lastname);
        clickOn("#email_txt").write(username);
        clickOn("#password_txt").write(password);
        clickOn("#verify_password_txt").write(password);
        clickOn("#registerBtnID");
        verifyThat("#loginSceneID", isVisible());

    }

    @Test
    public void testInValidConfirmationPassword() { //This test should fail in realese 2 due to not implementing it yet
        String forename = "Ola";
        String lastname = "Svenskman";
        String username = "ola@outlook.com";
        String password = "Passord1234";
        String passwordConfirmation = "pasSord123456";
        clickOn("#forename_txt").write(forename);
        clickOn("#lastname_txt").write(lastname);
        clickOn("#email_txt").write(username);
        clickOn("#password_txt").write(password);
        clickOn("#verify_password_txt").write(passwordConfirmation);
        clickOn("#registerBtnID");
        verifyThat("#registrationSceneID", isVisible()); //should fail

    }

    @Test
    public void testInValidEmail() { //This test is to be changed after realese 2
        String forename = "Ola";
        String lastname = "Svenskman";
        String username = "ola&outlook.com";
        String password = "Passord1234";
        clickOn("#forename_txt").write(forename);
        clickOn("#lastname_txt").write(lastname);
        clickOn("#email_txt").write(username);
        clickOn("#password_txt").write(password);
        clickOn("#verify_password_txt").write(password);
        clickOn("#registerBtnID");
        verifyThat("#registrationSceneID", isVisible()); //should still be in registration menu

    }

    @Test
    public void testInvalidConfirmationPassword() { //This test should fail in realese 2 due to not implementing it yet
        String forename = "Ola";
        String lastname = "Svenskman";
        String username = "ola@outlook.com";
        String password = "P12P";
        clickOn("#forename_txt").write(forename);
        clickOn("#lastname_txt").write(lastname);
        clickOn("#email_txt").write(username);
        clickOn("#password_txt").write(password);
        clickOn("#verify_password_txt").write(password);
        clickOn("#registerBtnID");
        verifyThat("#registrationSceneID", isVisible()); //should fail

    }

  
  
}