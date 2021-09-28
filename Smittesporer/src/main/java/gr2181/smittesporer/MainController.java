package gr2181.smittesporer;

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

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.SortedMap;

public class MainController extends AbstractController {

    private final InfectionTracer infectionTracer = new InfectionTracer();

    @FXML
    private Label username_lbl;

    @FXML
    private Label infectionStatus;

    @FXML
    private Label numberOfContacts;

    @FXML
    private TableView<CloseContact> contactTable;
    @FXML private TableColumn<CloseContact, String> nameColumn;
    @FXML private TableColumn<CloseContact, LocalDate> DateColumn;

    @FXML
    private TextField contactNameTxt;

    @FXML
    private Button fireHealthyUser;

    @FXML
    private Button fireInfectedUser;

    @FXML
    void addContact(ActionEvent event) {
        try {
            infectionTracer.addCloseContact(username, contactNameTxt.getText());
            refreshContactTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeContactTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        refreshContactTable();
    }
    // Function to refresh tableview after every insertion of a close contact
    private void refreshContactTable() {
        try {
            SortedMap<String, String> currentMap = infectionTracer.getRelevantMap(username);
            if (currentMap == null) {
                return;
            }
            ObservableList<CloseContact> contactList = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : currentMap.entrySet()) {
               CloseContact closeContact = new CloseContact(entry.getKey(), LocalDate.parse(entry.getValue()));
               contactList.add(closeContact);
            }
            contactTable.setItems(contactList);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        initializeContactTable();
    }

}
