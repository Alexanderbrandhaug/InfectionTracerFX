package infectiontracer.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.List;

import java.io.IOException;
import java.time.LocalDate;
import infectiontracer.core.*;

public class MainController extends AbstractController {

    MainController(String username) {
        this.username = username;
    }

    private final InfectionTracer infectionTracer = new InfectionTracer();

    @FXML
    private Label username_lbl;

    @FXML
    private Label infectionStatus;

    @FXML
    private Label numberOfContacts;

    @FXML
    private TableView<User> contactTable;
    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> lastnameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> healthstatusColumn;

    @FXML
    private TableColumn<User, String> dateOfInfectionColumn;

    @FXML
    private TextField contactNameTxt;

    @FXML
    private Button fireHealthyUser;

    @FXML
    private Button fireInfectedUser;
    ObservableList<User> contactList = FXCollections.observableArrayList();

    // Filehandler filehandler = new FileHandler();

    @FXML
    void addContact(ActionEvent event) throws IOException {
        try {
            System.out.println(username);
            infectionTracer.addCloseContact2(username, contactNameTxt.getText());
            List<User> currentMap = infectionTracer.getRelevantMap(username);
            contactList.clear();
            for (User user : currentMap) {

                System.out.println(contactList.toString());
                User closeContact = new User(user.getForname(), user.getLastname(), user.getEmail(), user.getPassword(),
                        user.getHealthStatus(), user.getDateOfInfection());

                contactList.add(closeContact);
                numberOfContacts.setText(String.valueOf(currentMap.size()));

            }
            contactTable.setItems(contactList);
            System.out.println(contactList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to refresh tableview after every insertion of a close contact
    @FXML
    private void initialize() {
        try {

            List<User> currentMap = infectionTracer.getRelevantMap(username);
            username_lbl.setText(username);
            infectionStatus.setText(infectionTracer.getActiveUser(username).getHealthStatus());
            


            nameColumn.setCellValueFactory(new PropertyValueFactory<>("forname"));
            lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            healthstatusColumn.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
            dateOfInfectionColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInfection"));
            if (currentMap == null) {
                return;
            }
            for (User user : currentMap) {
                numberOfContacts.setText(String.valueOf(currentMap.size()));
                User closeContact = new User(user.getForname(), user.getLastname(), user.getEmail(), user.getPassword(),
                        user.getHealthStatus(), user.getDateOfInfection());
                contactList.add(closeContact);
                contactTable.setItems(contactList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
