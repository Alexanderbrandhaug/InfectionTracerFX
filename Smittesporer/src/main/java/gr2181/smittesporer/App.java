package gr2181.smittesporer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Stage primaryStage;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;
		primaryStage.setTitle("Smittesporer");
		primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")))));
		primaryStage.show();
	}

	public static void changeScene(String fxml) {
		try {
			Parent pane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
			primaryStage.getScene().setRoot(pane);
			primaryStage.sizeToScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}