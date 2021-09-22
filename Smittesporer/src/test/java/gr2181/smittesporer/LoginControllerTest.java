package test.java.gr2181.smittesporer;

import java.io.IOException;

import org.testfx.framework.junit5.ApplicationTest;

import gr2181.smittesporer.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginControllerTest extends ApplicationTest {

    private LoginController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("login.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
