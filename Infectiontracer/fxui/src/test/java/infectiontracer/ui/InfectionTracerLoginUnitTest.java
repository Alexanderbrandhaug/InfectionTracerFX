package infectiontracer.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.framework.junit5.ApplicationTest;
import java.util.Objects;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfectionTracerLoginUnitTest extends ApplicationTest {

  Scene scene;

  @Test
  public void checkBtnsAndTxtFields() {
    verifyThat("#emailTxt", isVisible());
    verifyThat("#passwordTxt", isVisible());
    verifyThat("#registerBtnId", isVisible());
    verifyThat("#loginBtnId", isVisible());
    verifyThat("#retrievePasswordBtnId", isVisible());
  }

  @Test
  public void loginToRegistration() {
    clickOn("#registerBtnId");
    verifyThat("#registrationSceneId", isVisible());
  }

  @Test
  public void loginToMain() {
    moveTo("#loginBtnId");
    ScreenController screenController = new ScreenController();
    Platform.runLater(
        () -> screenController.switchToMainTest(rootNode(scene).lookup("#loginBtnId")));
    sleep(500);
    verifyThat("#mainSceneId", isVisible());
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
