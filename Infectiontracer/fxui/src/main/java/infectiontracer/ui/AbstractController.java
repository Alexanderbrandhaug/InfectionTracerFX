package infectiontracer.ui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * This class is meant to be inherited by all controllers in the project. This will make it easier
 * to preserve information across scenes with different controllers. Also, some javafx dialogs
 * methods in this class will make it easier to provide feedback to the user.
 */
public class AbstractController {

  // this will be email for now
  String username;

  protected void createErrorDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  protected void createInformationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    okButton.setId("okButton");
    alert.showAndWait();
  }

  protected boolean createConfirmationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    Optional<ButtonType> result = alert.showAndWait();
    return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
  }
}
