package infectiontracer.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.List;

import java.io.IOException;

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

    @FXML
    private Button closeBtnMain;
    
    ObservableList<User> contactList = FXCollections.observableArrayList();
    ScreenController screencontroller = new ScreenController();

    @FXML
    void addContact(ActionEvent event) {
        try {
            infectionTracer.addCloseContact(username, contactNameTxt.getText());
            List<User> currentMap = infectionTracer.getUsersCloseContacts(username);
            contactList.clear();
            contactList.addAll(currentMap);
            numberOfContacts.setText(String.valueOf(contactList.size()));
            contactTable.setItems(contactList);
        } catch (IllegalArgumentException e) {
            createErrorDialogBox("Error", null, e.getMessage());
        }
    }

    // Function to refresh tableview after every insertion of a close contact
    @FXML
    private void initialize() {
        List<User> usersCloseContacts = infectionTracer.getUsersCloseContacts(username);
        username_lbl.setText(username);
        infectionStatus.setText(infectionTracer.getActiveUser(username).getHealthStatus());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        healthstatusColumn.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
        dateOfInfectionColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInfection"));
        if (usersCloseContacts == null) {
            return;
        }
        contactList.addAll(usersCloseContacts);
        numberOfContacts.setText(String.valueOf(usersCloseContacts.size()));
        contactTable.setItems(contactList);
    }


    @FXML
    void MainToLogin(ActionEvent event) {
        screencontroller.switchToLogin(event);
    }

    @FXML
    void closeMain(ActionEvent event) {
        Stage stage = (Stage)closeBtnMain.getScene().getWindow();
        stage.close();
    }

}
