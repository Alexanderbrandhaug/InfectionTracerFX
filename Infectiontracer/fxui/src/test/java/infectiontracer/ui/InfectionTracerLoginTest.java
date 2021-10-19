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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import infectiontracer.core.FileHandler;
import infectiontracer.core.User;
import infectiontracer.core.InfectionTracer;
import infectiontracer.ui.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfectionTracerLoginTest extends ApplicationTest {

    private final FileHandler fileHandler = new FileHandler();
    private List<User> actualUsersList;

    @BeforeAll
    public void setupFile() {
        actualUsersList = fileHandler.getUsers();
        User testUser = new User("test", "test","test@gmail.com", "Passord123","", "");
        List<User> testUsers = new ArrayList<>();
        testUsers.add(testUser);
        fileHandler.writeUsersToFile(testUsers);
    }

    @AfterAll
    public void restoreFile() {
        fileHandler.writeUsersToFile(actualUsersList);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testValidLogin() {
        clickOn("#email_txt").write("test@gmail.com");
        clickOn("#password_txt").write("Passord123");
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
        verifyThat("#loginSceneID", isVisible());

    }
}
