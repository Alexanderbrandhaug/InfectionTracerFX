package gr2181.smittesporer;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class ScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMain(ActionEvent event, String username) throws IOException {
        System.out.println( getClass().getResource(getClass().getSimpleName() + ".class") );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        root = loader.load();
        MainController mainController = loader.getController();
        mainController.setUsername(username);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}