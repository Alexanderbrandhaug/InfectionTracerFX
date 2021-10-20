module infectiontracer.ui {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires infectiontracer.core;

  opens infectiontracer.ui to javafx.graphics, javafx.fxml;
}