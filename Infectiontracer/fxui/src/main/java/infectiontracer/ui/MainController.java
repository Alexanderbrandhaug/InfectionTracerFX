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

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.SortedMap;
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
    private TableView<CloseContact> contactTable;
    @FXML
    private TableColumn<CloseContact, String> nameColumn;
    @FXML
    private TableColumn<CloseContact, LocalDate> DateColumn;

    @FXML
    private TableColumn<CloseContact, Boolean> healthStatusColumn;

    @FXML
    private TableColumn<CloseContact, String> dateOfInfectionColumn;

    @FXML
    private TextField contactNameTxt;

    @FXML
    private Button fireHealthyUser;

    @FXML
    private Button fireInfectedUser;
    boolean healthStatus = false;
    ObservableList<CloseContact> contactList = FXCollections.observableArrayList();
    
  //  Filehandler filehandler = new FileHandler();

    @FXML
    void addContact(ActionEvent event)throws IOException {
        try {
            infectionTracer.addCloseContact(username, contactNameTxt.getText());
            SortedMap<String, String> currentMap = infectionTracer.getRelevantMap(username);
            contactList.clear();
            for (Map.Entry<String, String> entry : currentMap.entrySet()) {
              
                System.out.println(contactList.toString());
                CloseContact closeContact = new CloseContact(entry.getKey(), LocalDate.parse(entry.getValue()), healthStatus, "test");
                
                contactList.add(closeContact);
                
                }
                contactTable.setItems(contactList);
                System.out.println(contactList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to refresh tableview after every insertion of a close contact
    @FXML
    private void initialize()throws IOException {
        SortedMap<String, String> currentMap = infectionTracer.getRelevantMap(username);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        healthStatusColumn.setCellValueFactory(new PropertyValueFactory<>("infected"));
        dateOfInfectionColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInfected"));
            if (currentMap == null) {
                return;
            }
            
            for (Map.Entry<String, String> entry : currentMap.entrySet()) {
                //user.setEmail(entry.getKey());
                
                CloseContact closeContact = new CloseContact(entry.getKey(), LocalDate.parse(entry.getValue()),healthStatus,"test");
                contactList.add(closeContact);
                contactTable.setItems(contactList);
    }

}
}
