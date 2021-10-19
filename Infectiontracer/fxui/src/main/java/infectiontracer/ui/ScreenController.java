package infectiontracer.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import infectiontracer.core.*;

public class ScreenController extends AbstractController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMain(ActionEvent event, String username) {
        try {
            MainController mainController = new MainController(username);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(mainController);
            loader.setLocation(getClass().getResource("Main.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            createErrorDialogBox("Scene error", null, "Error when changing scenes");
        }
    }

    public void switchToRegistration(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            createErrorDialogBox("Scene error", null, "Error when changing scenes");
        }
    }

    public void switchToLogin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            createErrorDialogBox("Scene error", null, "Error when changing scenes");
        }
    }
}