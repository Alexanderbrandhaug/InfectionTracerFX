package infectiontracer.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import infectiontracer.core.*;

public class ScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    

    public void switchToMain(ActionEvent event, String username) throws IOException {
        // root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        MainController mainController = new MainController(username);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(mainController);
        loader.setLocation(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        root = loader.load();
        /*
         * MainController mainController = loader.getController();
         * mainController.setUsername(username);
         */

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}