package infectiontracer.ui;

import infectiontracer.core.InfectionTracer;
import infectiontracer.core.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import infectiontracer.ui.*;
import infectiontracer.rest.InfectionTracerApplication;
import infectiontracer.json.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {InfectionTracerApplication.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfectionTracerProfileTest extends ApplicationTest {

  @LocalServerPort private int port;

  private final FileHandler fileHandler = new FileHandler();
  private final InfectionTracer infectionTracer = new InfectionTracer();
  private List<User> actualUsersList;
  private final User testUser = new User("test", "test", "test123@gmail.com", "Passord123", "", "");
  private final User testUser2 = new User("test", "test", "test@gmail.com", "Passord123", "", "");

  @BeforeAll
  public void setupFile() throws IOException {
    actualUsersList = fileHandler.getUsers();
    AbstractController.setMyUrl(String.valueOf(port));
    List<User> testUsers = new ArrayList<>();
    testUsers.add(testUser);
    testUsers.add(testUser2);
    fileHandler.writeUsersToFile(testUsers);
  }

  @AfterAll
  public void restoreFile() throws IOException {
    fileHandler.writeUsersToFile(actualUsersList);
  }

  @Override
  public void start(Stage stage) throws Exception {
    ProfileController profileController = new ProfileController(testUser);
    FXMLLoader loader = new FXMLLoader();
    loader.setController(profileController);
    loader.setLocation(getClass().getResource("Profile.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
  }

  @Test
  public void testEditUser() throws IOException {
    doubleClickOn("#firstNameTxt").write("Testersen");
    doubleClickOn("#lastNameTxt").write("Testersen");
    doubleClickOn("#newPasswordTxt").write("Test1234");
    doubleClickOn("#verifyPasswordTxt").write("Test1234");
    clickOn("#saveBtn");
    clickOn("#okButton");
    Assertions.assertEquals(
        infectionTracer.getActiveUser(testUser.getEmail()),
        new User("Testersen", "Testersen", "test123@gmail.com", "Test1234", "", ""));
    doubleClickOn("#firstNameTxt").write("Test");
    doubleClickOn("#lastNameTxt").write("Test");
    doubleClickOn("#newPasswordTxt").write("");
    doubleClickOn("#verifyPasswordTxt").write("");
    clickOn("#saveBtn");
    clickOn("#okButton");
    doubleClickOn("#newPasswordTxt").write("Passord123");
    doubleClickOn("#verifyPasswordTxt").write("Passord123");
    clickOn("#saveBtn");
    clickOn("#okButton");
    Assertions.assertEquals(infectionTracer.getActiveUser(testUser.getEmail()), testUser);
  }

  @Test
  public void testInvalidEdit() {
    doubleClickOn("#newPasswordTxt").write("Test1234");
    clickOn("#saveBtn");
    clickOn("#errorButton");
    doubleClickOn("#newPasswordTxt").write("");
    doubleClickOn("#firstNameTxt").write("test123");
    clickOn("#saveBtn");
    clickOn("#errorButton");
  }

  @Test
  public void testDeleteUser() throws IOException {
    infectionTracer.addCloseContact(testUser.getEmail(), testUser2.getEmail());
    assertEquals(infectionTracer.getUsersCloseContacts(testUser.getEmail()).get(0), testUser2);
    clickOn("#deleteUserBtnId");
    clickOn("#okButton");
    verifyThat("#loginSceneId", isVisible());
    assertFalse(infectionTracer.checkUserList(testUser.getEmail(), infectionTracer.getUsers()));
    assertFalse(
        infectionTracer
            .getActiveUser(testUser2.getEmail())
            .getAllCloseContacts()
            .contains(testUser.getEmail()));
  }
}
