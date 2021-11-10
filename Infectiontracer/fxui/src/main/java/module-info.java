module infectiontracer.ui {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires infectiontracer.core;
  requires infectiontracer.rest;
  requires java.net.http;
  requires com.google.gson;


    opens infectiontracer.ui to javafx.graphics, javafx.fxml;
}