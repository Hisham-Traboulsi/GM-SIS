/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.database.Database;
import common.logic.SystemUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class AddCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<SystemUser> customerTable = new TableView<SystemUser>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<SystemUser, String> fullNameCol;

    @FXML
    private TableColumn<SystemUser, String> vehicleRegCol;

    @FXML
    private TableColumn<SystemUser, String> addressCol;

    @FXML
    private TableColumn<SystemUser, String> postCodeCol;
    
    @FXML
    private TableColumn<SystemUser, String> phoneCol;
    
    @FXML
    private TableColumn<SystemUser, String> emailCol;
     
    @FXML
    private TableColumn<SystemUser, String> typeCol;
    
    @FXML
    private TextField idBox;
    
    @FXML
    private TextField fullNameBox;
    
    @FXML
    private TextField vehicleRegBox;
    
    @FXML
    private TextField addressBox;
    
    @FXML
    private TextField postCodeBox;
    
    @FXML
    private TextField phoneBox;
    
    @FXML
    private TextField emailBox;
    
    @FXML
    private RadioButton privateRadio;
    
    @FXML
    private RadioButton businessRadio;
    
    final ToggleGroup group = new ToggleGroup();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        privateRadio.setToggleGroup(group);
        businessRadio.setToggleGroup(group);
        
        try
        {
            ObservableList<Customers> customerData = Database.getInstance().getAllCustomers();
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            fullNameCol.setCellValueFactory(new PropertyValueFactory<>("FULLNAME"));
            vehicleRegCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLE_REG"));
            //idCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            //idCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            //idCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            //idCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
       
    }    
    
    
    public void clear()
    {
        idBox.clear();
        fullNameBox.clear();
        vehicleRegBox.clear();
        addressBox.clear();
        postCodeBox.clear();
        phoneBox.clear();
        emailBox.clear();
        
        privateRadio.setSelected(false);
        businessRadio.setSelected(false);
    }
    
}
