package gr2181.smittesporer;

import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

// This class is meant to be inherited by all controllers in the project
// This will make it easier to preserve information across scenes with different controllers
// Also some javafx dialogs methods in this class will make it easier to provide feedback to the user
public class AbstractController {

    // this will be email for now
    protected static String username;

    protected void createErrorDialogBox(String setTitle, String setHeaderText, String setContentText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.setContentText(setContentText);
        alert.showAndWait();
    }
}