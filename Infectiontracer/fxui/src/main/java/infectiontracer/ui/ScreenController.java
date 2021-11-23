package infectiontracer.ui;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
/** Controller to change between the different scenes in the application. */
public class ScreenController extends AbstractController {
  private Stage stage;
  private Scene scene;
  private Parent root;

  /**
   * Method to switch scene to main screen.
   *
   * @param event Event from pressing button used to get current stage.
   * @param username Username passed to the main controller, so that the application knows which
   *     user is currently logged in.
   */
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
      stage.show();
    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
    }
  }


  public void switchToProfile(MouseEvent event){
    try {
      root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Profile.fxml")));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      createErrorDialogBox("Scene error", null, "Error when changing scenes");
    }
  }


}