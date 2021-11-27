package infectiontracer.ui;

import infectiontracer.core.User;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
/** Controller to change between the different scenes in the application. */
public class ScreenController {
  private Stage stage;
  private Scene scene;
  private Parent root;

  /**
   * Method to switch scene to main screen.
   *
   * @param node The node (button) used to get current stage.
   * @param user User passed to the main controller, so that the application knows which
   *     user is currently logged in.
   */
  public void switchToMain(Node node, User user) {
    try {
      MainController mainController = new MainController(user);
      FXMLLoader loader = new FXMLLoader();
      loader.setController(mainController);
      loader.setLocation(getClass().getResource("Main.fxml"));
      stage = (Stage) node.getScene().getWindow();
      root = loader.load();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
      e.printStackTrace();
    }
  }




  /**
   * Method to switch scene to main screen. Used for testing in fxui module
   *
   * @param node The node (button) used to get current stage.
   */
  public void switchToMainTest(Node node) {
    try {
      root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainTest.fxml")));
      stage = (Stage) node.getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
    }
  }

  /**
   * Method to switch scene to main screen. Used for testing in fxui module
   *
   * @param node The node (button) used to get current stage.
   */
  public void switchToProfileTest(Node node) {
    try {
      root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProfileTest.fxml")));
      stage = (Stage) node.getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
    }
  }

  /**
   * Method to switch to the registration scene.
   *
   * @param event Event from pressing button used to get current stage.
   */
  public void switchToRegistration(ActionEvent event) {
    try {
      root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Registration.fxml")));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();
    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
    }
  }

  /**
   * Method to switch to the login scene.
   *
   * @param event Event from pressing button used to get current stage.
   */
  public void switchToLogin(ActionEvent event) {
    try {
      root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();
    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
    }
  }


  public void switchToProfile(ActionEvent event, User loggedInUser){
    try {
      ProfileController profileController = new ProfileController(loggedInUser);
      FXMLLoader loader = new FXMLLoader();
      loader.setController(profileController);
      loader.setLocation(getClass().getResource("Profile.fxml"));
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



  void createErrorDialogBox(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    errorButton.setId("errorButton");
    alert.showAndWait();
  }

  void createInformationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    okButton.setId("okButton");
    alert.showAndWait();
  }

  boolean createConfirmationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    Optional<ButtonType> result = alert.showAndWait();
    return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
  }


  public void switchToMainFromProfile(MouseEvent event, User loggedInUser) {
    try {
      MainController mainController = new MainController(loggedInUser);
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

}
