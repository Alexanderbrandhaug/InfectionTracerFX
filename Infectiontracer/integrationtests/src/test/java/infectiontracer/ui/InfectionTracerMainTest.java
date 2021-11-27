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
public class InfectionTracerMainTest extends ApplicationTest {

  @LocalServerPort private int port;

  private final FileHandler fileHandler = new FileHandler();
  private List<User> actualUsersList;
  final User testUser = new User("test", "test", "test123@gmail.com", "Passord123", "", "");
  final User testUser2 = new User("test", "test", "test123456@gmail.com", "Passord123", "", "");
  final User testUser3 = new User("test", "test", "test321456@gmail.com", "Passord123", "", "");
  private Stage stage;
  private Scene scene;

  @BeforeAll
  public void setupFile() throws IOException {
    actualUsersList = fileHandler.getUsers();
    AbstractController.setMyUrl(String.valueOf(port));
    List<User> testUsers = new ArrayList<>();
    testUsers.add(testUser);
    testUsers.add(testUser2);
    testUsers.add(testUser3);
    fileHandler.writeUsersToFile(testUsers);
  }

  @AfterAll
  public void restoreFile() throws IOException {
    fileHandler.writeUsersToFile(actualUsersList);
  }

  @Override
  public void start(Stage stage) throws Exception {
    MainController maincontroller = new MainController(testUser);
    FXMLLoader loader = new FXMLLoader();
    loader.setController(maincontroller);
    loader.setLocation(getClass().getResource("Main.fxml"));
    Parent root = loader.load();
    this.scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
  }

  @Test
  public void testAddValidCloseContact() throws IOException {
    clickOn("#contactNameTxt").write(testUser2.getEmail());
    clickOn("#addContactBtnId");
    verifyThat("#okButton", isVisible());
    clickOn("#okButton");
    clickOn("#contactNameTxt").write(testUser3.getEmail());
    clickOn("#addContactBtnId");
    verifyThat("#okButton", isVisible());
    clickOn("#okButton");
    InfectionTracer tracer = new InfectionTracer();
    assertArrayEquals(
        tracer.getActiveUser(testUser.getEmail()).getAllCloseContacts().toArray(),
        new String[] {testUser2.getEmail(), testUser3.getEmail()});
  }

  @Test
  public void testChangeHealthStatus() {
    clickOn("#infectedBtnId");
    verifyThat("#okButton", isVisible());
    clickOn("#okButton");
    System.out.println(scene.lookup("#infectionStatus").toString());
    assertEquals(
        "Label[id=infectionStatus, styleClass=label]'Infected'",
        scene.lookup("#infectionStatus").toString());
    clickOn("#healthyBtnId");
    verifyThat("#okButton", isVisible());
    clickOn("#okButton");
    assertEquals(
        "Label[id=infectionStatus, styleClass=label]'Covid-19 Negative'",
        scene.lookup("#infectionStatus").toString());
  }

  @Test
  public void testLogOut() {
    clickOn("#logOutBtnId");
    verifyThat("#loginSceneId", isVisible());
  }

  // TODO fix test
  /*@Test
  public void testDeleteCloseContact() {
      clickOn("#contactNameTxt").write(testUser2.getEmail());
      clickOn("#addContactBtn");
      clickOn("#okButton");
      clickOn("#contactTable");

      sleep(3000);
  }*/

}
