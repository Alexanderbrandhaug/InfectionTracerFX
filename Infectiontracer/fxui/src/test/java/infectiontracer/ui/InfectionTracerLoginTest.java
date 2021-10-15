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

public class InfectionTracerLoginTest extends ApplicationTest {

    private ScreenController screencontroller = new ScreenController();
    // private MainController maincontroller = new MainController();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testValidLogin() {
        String username = "test@gmail.com";
        String password = "Passord123";
        clickOn("#email_txt").write(username);
        clickOn("#password_txt").write(password);
        clickOn("#loginBtnID");
        verifyThat("#mainSceneID", isVisible());

    }

    @Test
    void testInvalidLogin() {

        String username = "test";
        String password = "";
        clickOn("#email_txt").write(username);
        clickOn("#password_txt").write(password);
        clickOn("#loginBtnID");
        verifyThat("OK", isVisible());

    }
}
