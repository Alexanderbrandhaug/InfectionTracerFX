package gr2181.smittesporer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    @Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Smittesporer");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("login.fxml"))));
		primaryStage.show();
		
		
		
	}
	

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	}