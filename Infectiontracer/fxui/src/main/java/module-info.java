module infectiontracer.ui {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires infectiontracer.core;
  requires infectiontracer.rest;
  requires com.fasterxml.jackson.databind;
  requires java.net.http;



  opens infectiontracer.ui to javafx.graphics, javafx.fxml;
}