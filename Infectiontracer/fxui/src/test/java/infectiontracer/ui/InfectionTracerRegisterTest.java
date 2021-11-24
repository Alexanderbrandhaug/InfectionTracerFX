package infectiontracer.ui;
import infectiontracer.core.User;
import java.util.List;
import java.util.Objects;

import infectiontracer.rest.InfectionTracerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import infectiontracer.json.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { InfectionTracerApplication.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfectionTracerRegisterTest extends ApplicationTest {

    @LocalServerPort
    private int port;

    private final FileHandler fileHandler = new FileHandler();
    private List<User> actualUsersList;

    @BeforeAll
    public void setupFile() {
        actualUsersList = fileHandler.getUsers();
        AbstractController abstractController = new AbstractController();
        abstractController.setMyUrl(String.valueOf(port));
    }

    @AfterAll
    public void restoreFile() {
        fileHandler.writeUsersToFile(actualUsersList);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Registration.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Test
    public void testValidRegistration() {
        String forename = "Test";
        String lastname = "Testersen";
        String username = "test99299@gmail.com";
        String password = "Passord123";
        clickOn("#forenameTxt").write(forename);
        clickOn("#lastnameTxt").write(lastname);
        clickOn("#emailTxt").write(username);
        clickOn("#passwordTxt").write(password);
        clickOn("#verifyPasswordTxt").write(password);
        clickOn("#registerBtnId");
        verifyThat("#okButton", isVisible());
    }

    /** 
    @Test
    public void testInValidConfirmationPassword() { //This test should fail in realese 2 due to not implementing it yet
        String forename = "Ola";
        String lastname = "Svenskman";
        String username = "ola@outlook.com";
        String password = "Passord1234";
        String passwordConfirmation = "pasSord123456";
        clickOn("#forenameTxt").write(forename);
        clickOn("#lastnameTxt").write(lastname);
        clickOn("#emailTxt").write(username);
        clickOn("#passwordTxt").write(password);
        clickOn("#verifyPasswordTxt").write(passwordConfirmation);
        clickOn("#registerBtnId");
        verifyThat("#registrationSceneID", isVisible());
    }

    @Test
    public void testInValidEmail() { //This test is to be changed after realese 2
        String forename = "Ola";
        String lastname = "Svenskman";
        String username = "ola&outlook.com";
        String password = "Passord1234";
        clickOn("#forenameTxt").write(forename);
        clickOn("#lastnameTxt").write(lastname);
        clickOn("#emailTxt").write(username);
        clickOn("#passwordTxt").write(password);
        clickOn("#verifyPasswordTxt").write(password);
        clickOn("#registerBtnId");
        verifyThat("#registrationSceneID", isVisible());
     

    }
 
    @Test
    public void testInvalidConfirmationPassword() { //This test should fail in realese 2 due to not implementing it yet
        String forename = "Ola";
        String lastname = "Svenskman";
        String username = "ola@outlook.com";
        String password = "P12P";
        clickOn("#forenameTxt").write(forename);
        clickOn("#lastnameTxt").write(lastname);
        clickOn("#emailTxt").write(username);
        clickOn("#passwordTxt").write(password);
        clickOn("#verifyPasswordTxt").write(password);
        clickOn("#registerBtnId");
        verifyThat("#registrationSceneID", isVisible());
    }*/
}