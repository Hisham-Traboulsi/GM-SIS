
package diagrep.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ernes
 */
public class BookingRepairsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Bookings> BookingTable= new TableView<Bookings>();

    @FXML
    private TableColumn BookingCol;

    @FXML
    private TableColumn<Bookings, String> MechanicCol;

    @FXML
    private TableColumn<Bookings, String> fullNameCol;

    @FXML
    private TableColumn<Bookings, String> LicensePlateCol;
    
    @FXML
    private TableColumn<Bookings, String> MilageCol;
    
    @FXML
    private TableColumn<Bookings, String> DateCol;
     
    @FXML
    private TableColumn<Bookings, String> TimeCol;
    
    @FXML
    private TextField RegNum;
    
    @FXML
    private TextField FullName;
    
    @FXML
    private TextField VechicleMileage;
    
    @FXML
    private TextField MechanicID;
    
    @FXML
    private TextField DateID;
    
    @FXML
    private TextField TimeID;
    
    final ToggleGroup group = new ToggleGroup();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*try
        {
            ObservableList<Bookings> customerData = Database.getInstance().getAllCustomers();
            
           BookingCol.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
            fullNameCol.setCellValueFactory(new PropertyValueFactory<>("FullName"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            LicensePlateCol.setCellValueFactory(new PropertyValueFactory<>("RegNum"));
            MilageCol.setCellValueFactory(new PropertyValueFactory<>("VechicleMileage"));
            DateCol.setCellValueFactory(new PropertyValueFactory<>("DateID"));
            TimeCol.setCellValueFactory(new PropertyValueFactory<>("TimeID"));
            
            BookingTable.setItems(customerData);
        }   
        catch(SQLException ex)
        {
            ex.printStackTrace();
        } */
    }  
    
    
    
}

