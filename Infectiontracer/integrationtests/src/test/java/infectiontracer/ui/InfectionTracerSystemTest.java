package infectiontracer.ui;

import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
import infectiontracer.rest.InfectionTracerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@ContextConfiguration(classes = {InfectionTracerApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfectionTracerSystemTest extends ApplicationTest {

  @LocalServerPort private int port;

  private final FileHandler fileHandler = new FileHandler();
  private List<User> actualUsersList;

  @BeforeAll
  public void setupFile() throws IOException {
    actualUsersList = fileHandler.getUsers();
    AbstractController.setMyUrl(String.valueOf(port));
    List<User> testUsers = new ArrayList<>();
    fileHandler.writeUsersToFile(testUsers);
  }

  @AfterAll
  public void restoreFile() throws IOException {
    fileHandler.writeUsersToFile(actualUsersList);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  public void systemTest() {
    registerUsers();
    login();
    addCloseContact();
    changeHealthStatus();
    deleteCloseContact();
    editProfile();
    deleteAccount();
    //retrieveForgottenPassword();
  }

  public void registerUsers() {
    clickOn("#registerBtnId");
    clickOn("#forenameTxt").write("test");
    clickOn("#lastnameTxt").write("test");
    clickOn("#emailTxt").write("test@test.com");
    clickOn("#passwordTxt").write("Passord123");
    clickOn("#verifyPasswordTxt").write("Passord123");
    clickOn("#registerBtnId");
    clickOn("#okButton");

    clickOn("#registerBtnId");
    clickOn("#forenameTxt").write("testersen");
    clickOn("#lastnameTxt").write("testersen");
    clickOn("#emailTxt").write("testersen@test.com");
    clickOn("#passwordTxt").write("Passord123");
    clickOn("#verifyPasswordTxt").write("Passord123");
    clickOn("#registerBtnId");
    clickOn("#okButton");
  }

  public void login() {
    clickOn("#emailTxt").write("test@test.com");
    clickOn("#passwordTxt").write("Passord123");
    clickOn("#loginBtnId");
  }

  public void addCloseContact() {
    clickOn("#contactNameTxt").write("testersen@test.com");
    clickOn("#addContactBtnId");
    clickOn("#okButton");
  }
  public void changeHealthStatus() {
    clickOn("#infectedBtnId");
    clickOn("#okButton");
    clickOn("#healthyBtnId");
    clickOn("#okButton");
  }

  public void deleteCloseContact() {
    clickOn("#deleteBtn");
    clickOn("#okButton");
  }

  public void editProfile() {
    clickOn("#profileBtnCss");
    doubleClickOn("#firstNameTxt").write("testing");
    doubleClickOn("#lastNameTxt").write("testing");
    doubleClickOn("#newPasswordTxt").write("testing123");
    doubleClickOn("#verifyPasswordTxt").write("testing123");
    clickOn("#saveBtn");
    clickOn("#okButton");
  }

  public void deleteAccount() {
    clickOn("#deleteUserBtnId");
    clickOn("#okButton");
    verifyThat("#loginSceneId", isVisible());
  }

  //TODO Fix test if possible
  public void retrieveForgottenPassword() {
    clickOn("#emailTxt").write("testersen@test.com");
    clickOn("#retrievePasswordBtnId");
    clickOn("#okButton");
  }
}
