package infectiontracer.ui;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.google.gson.reflect.TypeToken;

import infectiontracer.core.EmailService;
import infectiontracer.core.InfectionTracer;
import infectiontracer.core.User;
import infectiontracer.json.FileHandler;
//import infectiontracer.rest.InfectionTracerApiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import infectiontracer.rest.*;
import com.google.gson.Gson;


/** Controller for the login screen of the application. */
public class LoginController extends AbstractController {

  private final FileHandler fileHandler = new FileHandler();
  private final ScreenController screencontroller = new ScreenController();
  private final String myUrl = "http://localhost:8080/infectiontracer/";
  private Gson gson = new Gson();
 // private EmailService emailService = new EmailService();
  private InfectionTracer infectionTracer = new InfectionTracer();
 
  @FXML
  private Button closeBtnLogin;

  @FXML
  private TextField emailTxt;

  @FXML
  private PasswordField passwordTxt;

  @FXML
  void loginBtn(ActionEvent event) {
    String url = myUrl+"user/"+emailTxt.getText();
    String JsonUser = createGetRequest(url);
    User user = gson.fromJson(JsonUser, new TypeToken<User>() {}.getType() /*User.class*/);
    if (user != null && user.getPassword().equals(passwordTxt.getText())) {
      screencontroller.switchToMain(event, user.getEmail());
    }
    else {
      createErrorDialogBox("Login information is incorrect", null, "Email/password combination is not valid.");
    }
  }


  @FXML
  void forgotPasswordBtn(ActionEvent event) {
  
    try {
    URI endpointBaseUri = new URI(myUrl+"user/"+emailTxt.getText());
    String json = gson.toJson(infectionTracer.getActiveUser(emailTxt.getText()));
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build()
              .send(request,HttpResponse.BodyHandlers.ofString());
      System.out.println(response);

     

      
    } catch (IllegalArgumentException e) {
      createErrorDialogBox("Error", null, e.getMessage());
    
  } catch (Exception e) {
    createErrorDialogBox("Error", null, "Error when updating healthstatus to sick");
  }
  }
      
  



  @FXML
  void registerBtn(ActionEvent event) {
    screencontroller.switchToRegistration(event);
  }

  @FXML
  void closeLogin(ActionEvent event) {
    Stage stage = (Stage) closeBtnLogin.getScene().getWindow();
    stage.close();
  }
}
