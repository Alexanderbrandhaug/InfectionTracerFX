package infectiontracer.ui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import infectiontracer.core.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * This class is meant to be inherited by all controllers in the project. This will make it easier
 * to preserve information across scenes with different controllers. Also, some javafx dialogs
 * methods in this class will make it easier to provide feedback to the user.
 */
public class AbstractController {

  // this will be email for now
  String username;
  final static String myUrl = "http://localhost:8080/infectiontracer/";
  final Gson gson = new Gson();

  protected void createErrorDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    errorButton.setId("errorButton");
    alert.showAndWait();
  }

  protected void createInformationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    okButton.setId("okButton");
    alert.showAndWait();
  }

  protected boolean createConfirmationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    Optional<ButtonType> result = alert.showAndWait();
    return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
  }

  /**
   * @param url Url that is applicable in for the API-controller
   * @param json Json-string that is to be sent with HTTPS
   * @return boolean, to know if request failed or not
   */
  protected boolean createPostRequest(String url, String json) {
    try {
      URI endpointBaseUri = new URI(url);
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build()
              .send(request,HttpResponse.BodyHandlers.ofString());
      System.out.println(response);
      return response.statusCode() == 200;

    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Error", null, e.getMessage());
      return false;

    } catch (Exception e) {
      createErrorDialogBox("Error", null, "Error when updating healthstatus to sick");
      return false;
    }
  }

  protected boolean createPutRequest(String url, String json) {
    try {
      URI endpointBaseUri = new URI(url);
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build()
              .send(request,HttpResponse.BodyHandlers.ofString());
      System.out.println(response);
      return response.statusCode() == 200;
    } catch (IllegalArgumentException e) {
      createInformationDialogBox("Can't change health status", null, e.getMessage());
      return false;
    } catch (Exception e) {
      createErrorDialogBox("Error", null, "Error when updating healthstatus to sick");
      return false;
    }
  }

  protected String createGetRequest(String url) {
    try{
      URI endpointBaseUri = new URI(url);
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .GET()
              .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build()
              .send(request,HttpResponse.BodyHandlers.ofString());
      System.out.println(response);

      return response.body();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
