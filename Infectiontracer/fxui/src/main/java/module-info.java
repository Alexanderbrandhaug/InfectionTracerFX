module infectiontracer.ui {
  requires com.google.gson;
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires infectiontracer.core;
  requires java.net.http;


    opens infectiontracer.ui to javafx.graphics, javafx.fxml;
}