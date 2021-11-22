package infectiontracer.ui;
import infectiontracer.core.InfectionTracer;
import infectiontracer.core.User;
import java.util.ArrayList;
import java.beans.Transient;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import infectiontracer.json.*;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfectionTracerMainTest extends ApplicationTest{

    private final FileHandler fileHandler = new FileHandler();
    private List<User> actualUsersList;
    private ScreenController screencontroller = new ScreenController();
    private MainController maincontroller;
    User testUser = new User("test", "test","test@gmail.com", "Passord123","", "");
    User testUser2 = new User("test", "test","test123@gmail.com", "Passord123","", "");
    User testUser3 = new User("test", "test","test321@gmail.com", "Passord123","", "");
    private Parent root;
    private Stage stage;
    private Scene scene;
    

    @BeforeAll
    public void setupFile() {
        actualUsersList = fileHandler.getUsers();
        List<User> testUsers = new ArrayList<>();
        testUsers.add(testUser);
        testUsers.add(testUser2);
        testUsers.add(testUser3);
        fileHandler.writeUsersToFile(testUsers);
       
    }

    @AfterAll
    public void restoreFile() {
        fileHandler.writeUsersToFile(actualUsersList);
    }


    @Override
    public void start(Stage stage) throws Exception {
      maincontroller = new MainController("test@gmail.com");
      FXMLLoader loader = new FXMLLoader();
      loader.setController(maincontroller);
      loader.setLocation(getClass().getResource("Main.fxml"));
      root = loader.load();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    }

    @Test
    public void testAddValidCloseContact(){
        clickOn("#contactNameTxt").write(testUser2.getEmail());
        clickOn("#addContactBtn");
        verifyThat("#okButton", isVisible());
        clickOn("#okButton");
        clickOn("#contactNameTxt").write(testUser3.getEmail());
        clickOn("#addContactBtn");
        verifyThat("#okButton", isVisible());
        clickOn("#okButton");
        InfectionTracer tracer = new InfectionTracer();
        assertNotNull(tracer.getUsersCloseContacts(testUser.getEmail()));
    }





























}