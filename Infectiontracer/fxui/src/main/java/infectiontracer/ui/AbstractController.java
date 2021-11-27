package infectiontracer.ui;

import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * This class is meant to be inherited by all controllers in the project. This will make it easier
 * to preserve information across scenes with different controllers. Also, some javafx dialogs
 * methods in this class will make it easier to provide feedback to the user.
 */
public abstract class AbstractController {

  // this will be email for now
  User loggedInUser;
  static final String port = "8080";
  static String myUrl = "http://localhost:" + port + "/infectiontracer/";
  final FileHandler fileHandler = new FileHandler();
  final ScreenController screenController = new ScreenController();

  // TODO find methods such that this is not static
  static void setMyUrl(String port) {
    myUrl = "http://localhost:" + port + "/infectiontracer/";
  }

  /**
   * Method that creates an error JavaFX dialog.
   *
   * @param title Title of the dialog.
   * @param header Header of the dialog.
   * @param content Content of the dialog.
   */
  void createErrorDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    errorButton.setId("errorButton");
    alert.showAndWait();
  }

  /**
   * Method that creates an information JavaFX dialog.
   *
   * @param title Title of the dialog.
   * @param header Header of the dialog.
   * @param content Content of the dialog.
   */
  void createInformationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
    okButton.setId("okButton");
    alert.showAndWait();
  }

  /**
   * Method that creates a confirmation JavaFX dialog.
   *
   * @param title Title of the dialog.
   * @param header Header of the dialog.
   * @param content Content of the dialog.
   */
  boolean createConfirmationDialogBox(String title, String header, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    Optional<ButtonType> result = alert.showAndWait();
    return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
  }

  /**
   * Method that creates a post request using a URL and a JSON object.
   *
   * @param url Url that is applicable in for the API-controller.
   * @param json Json-string that is to be sent with HTTPS.
   * @return boolean, to know if request failed or not.
   */
  boolean createPostRequest(String url, String json) {
    try {
      URI endpointBaseUri = new URI(url);
      HttpRequest request =
          HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        createErrorDialogBox(String.valueOf(response.statusCode()), null, response.body());
        return false;
      }
      return true;
    } catch (URISyntaxException e) {
      createErrorDialogBox("URL error", null, e.getMessage());
      return false;
    } catch (Exception e) {
      createErrorDialogBox("Http error", null, e.getMessage());
      return false;
    }
  }

  /**
   * Method that creates a put request using a URL and a JSON object.
   *
   * @param url Url for the relevant REST-method
   * @param json JSON-object to be sent
   * @return True if request succeeded.
   */
  boolean createPutRequest(String url, String json) {
    try {
      URI endpointBaseUri = new URI(url);
      HttpRequest request =
          HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        createErrorDialogBox(String.valueOf(response.statusCode()), null, response.body());
        return false;
      }
      return true;
    } catch (URISyntaxException e) {
      createErrorDialogBox("URL error", null, e.getMessage());
      return false;
    } catch (Exception e) {
      createErrorDialogBox("Http error", null, e.getMessage());
      return false;
    }
  }

  /**
   * Method that creates a get request using a URL.
   *
   * @param url Url for the relevant REST-method
   * @return Response body of the ResponseEntity received.
   */
  String createGetRequest(String url) {
    try {
      URI endpointBaseUri = new URI(url);
      HttpRequest request =
          HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .GET()
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 404) {
        createErrorDialogBox("404 error", null, "Object not found");
        return null;
      } else if (response.statusCode() == 500) {
        createErrorDialogBox("500 error", null, "Internal server error");
        return null;
      }
      return response.body();
    } catch (URISyntaxException e) {
      createErrorDialogBox("URL error", null, e.getMessage());
      return null;
    } catch (Exception e) {
      createErrorDialogBox("Http error", null, e.getMessage());
      return null;
    }
  }

  Boolean createDeleteRequest(String url) {
    try {
      URI endpointBaseUri = new URI(url);
      HttpRequest request =
              HttpRequest.newBuilder(endpointBaseUri)
                      .header("Accept", "application/json")
                      .DELETE()
                      .build();
      final HttpResponse<String> response =
              HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        createErrorDialogBox(String.valueOf(response.statusCode()), null, response.body());
        return false;
      }
      return true;
    } catch (URISyntaxException e) {
      createErrorDialogBox("URL error", null, e.getMessage());
      return false;
    } catch (Exception e) {
      createErrorDialogBox("Http error", null, e.getMessage());
      return false;
    }
  }
}
