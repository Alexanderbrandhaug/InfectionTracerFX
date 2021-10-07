module infectiontracer.ui {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires com.google.gson;
  requires infectiontracer.core;
  
  
    opens infectiontracer.ui to javafx.graphics, javafx.fxml;
}