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
public class InfectionTracerMainUnitTest extends ApplicationTest {

  Scene scene;

  @Test
  public void checkBtnsAndTxtFields() {
    verifyThat("#usernameLbl", isVisible());
    verifyThat("#infectionStatus", isVisible());
    verifyThat("#contactNameTxt", isVisible());
    verifyThat("#addContactBtn", isVisible());
    verifyThat("#fireHealthyBtnId", isVisible());
    verifyThat("#fireInfectedBtnId", isVisible());
    verifyThat("#contactTable", isVisible());
    verifyThat("#numberOfContacts", isVisible());
    verifyThat("#logOutBtnId", isVisible());
  }

  @Test
  public void mainToLogin() {
    clickOn("#logOutBtnId");
    verifyThat("#loginSceneId", isVisible());
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainTest.fxml")));
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
