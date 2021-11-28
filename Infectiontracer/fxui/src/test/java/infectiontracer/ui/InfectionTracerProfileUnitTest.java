package infectiontracer.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.Objects;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfectionTracerProfileUnitTest extends ApplicationTest {

    Scene scene;

    @Test
    public void checkBtnsAndTxtFields() {
        verifyThat("#emailTxt", isVisible());
        verifyThat("#firstNameTxt", isVisible());
        verifyThat("#lastNameTxt", isVisible());
        verifyThat("#newPasswordTxt", isVisible());
        verifyThat("#verifyPasswordTxt", isVisible());
        verifyThat("#saveBtn", isVisible());
        verifyThat("#deleteUserBtnId", isVisible());
        verifyThat("#backToMainBtn", isVisible());
    }

    @Test
    public void profileToMain() {
        clickOn("#backToMainBtn");
        sleep(200);
        verifyThat("#mainSceneId", isVisible());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProfileTest.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

