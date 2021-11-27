package infectiontracer.ui;

import infectiontracer.rest.InfectionTracerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testfx.framework.junit5.ApplicationTest;
import infectiontracer.ui.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import infectiontracer.core.User;
import infectiontracer.json.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {InfectionTracerApplication.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfectionTracerLoginTest extends ApplicationTest {

  @LocalServerPort private int port;

  private final FileHandler fileHandler = new FileHandler();
  private List<User> actualUsersList;

  @BeforeAll
  public void setupFile() throws IOException {

    actualUsersList = fileHandler.getUsers();
    AbstractController.setMyUrl(String.valueOf(port));
    User testUser = new User("test", "test", "test@gmail.com", "Passord123", "", "");
    List<User> testUsers = new ArrayList<>();
    testUsers.add(testUser);
    fileHandler.writeUsersToFile(testUsers);
  }

  @AfterAll
  public void restoreFile() throws IOException {
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
    clickOn("#loginBtnId");
    verifyThat("#mainSceneId", isVisible());
  }

  @Test
  void testInvalidLogin() {
    String username = "test";
    String password = "";
    clickOn("#emailTxt").write(username);
    clickOn("#passwordTxt").write(password);
    clickOn("#loginBtnId");
    verifyThat("#errorButton", isVisible());
  }
}
