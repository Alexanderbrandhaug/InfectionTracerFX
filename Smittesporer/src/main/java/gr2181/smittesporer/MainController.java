package gr2181.smittesporer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Label username;

    @FXML
    private Label infectionStatus;

    @FXML
    private Label numberOfContacts;

    @FXML
    private TableView<?> contactTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> DateColumn;

    @FXML
    private TextField contactNameTxt;

    @FXML
    private Button fireHealthyUser;

    @FXML
    private Button fireInfectedUser;

    @FXML
    void addContact(ActionEvent event) {

    }

}
