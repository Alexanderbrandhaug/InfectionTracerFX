module infectiontracer.core {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires com.google.gson;
  

  exports infectiontracer.core;
  opens infectiontracer.core;
}