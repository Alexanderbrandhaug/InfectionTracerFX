package infectiontracer.ui;

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
public class InfectionTracerRegistrationUnitTest extends ApplicationTest {

  @Test
  public void checkBtnsAndTxtFields() {
    verifyThat("#forenameTxt", isVisible());
    verifyThat("#lastnameTxt", isVisible());
    verifyThat("#emailTxt", isVisible());
    verifyThat("#passwordTxt", isVisible());
    verifyThat("#verifyPasswordTxt", isVisible());
    verifyThat("#registerBtnId", isVisible());
    verifyThat("#registrationToLoginBtnId", isVisible());
    verifyThat("#registrationCloseBtnId", isVisible());
  }

  /*@Test // TODO Fix test
  public void checkCloseBtn() {
    clickOn("#registrationCloseBtnId");
    System.out.println(rootNode(scene).isVisible());
    //assertFalse(button.isVisible());
   verifyThat("#registratonCloseBtnId", isVisible());
  }*/

  @Test
  public void registrationToLogin() {
    clickOn("#registrationToLoginBtnId");
    verifyThat("#loginSceneId", isVisible());
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Registration.fxml")));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
