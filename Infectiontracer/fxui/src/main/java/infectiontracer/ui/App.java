package infectiontracer.ui;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** JavaFX App. */
public class App extends Application {

  @Override
  public void start(Stage stage) throws IOException {

    Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Login.fxml"))));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
