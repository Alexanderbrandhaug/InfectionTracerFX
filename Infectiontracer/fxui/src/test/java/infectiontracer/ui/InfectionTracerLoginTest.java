package infectiontracer.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import infectiontracer.core.User;
import infectiontracer.json.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testValidLogin() {
        clickOn("#emailTxt").write("test@gmail.com");
        clickOn("#passwordTxt").write("Passord123");
        clickOn("#loginBtnID");
        verifyThat("#mainSceneID", isVisible());

    }

    @Test
    void testInvalidLogin() {
        String username = "test";
        String password = "";
        clickOn("#emailTxt").write(username);
        clickOn("#passwordTxt").write(password);
        clickOn("#loginBtnID");
        verifyThat("#errorButton", isVisible());

    }
}
