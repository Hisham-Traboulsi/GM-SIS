/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.Popup;

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
    private TableView<Customers> customerTable = new TableView<Customers>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<Customers, String> fullNameCol;

    @FXML
    private TableColumn<Customers, String> addressCol;

    @FXML
    private TableColumn<Customers, String> postCodeCol;
    
    @FXML
    private TableColumn<Customers, String> phoneCol;
    
    @FXML
    private TableColumn<Customers, String> emailCol;
     
    @FXML
    private TableColumn<Customers, String> typeCol;
    
    @FXML
    private TextField fullNameBox;
    
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
    
    private static BorderPane root = new BorderPane();
    
    Stage stage = new Stage();
 
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        privateRadio.setToggleGroup(group);
        businessRadio.setToggleGroup(group);
        
        try
        {
            ObservableList<Customers> customerData = Database.getInstance().getAllCustomers();
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postCodeCol.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            
            customerTable.setRowFactory( tv -> {
                TableRow<Customers> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        Customers rowData = row.getItem();
                        Object [] options = {"Add Vehicle", "Book Appointment"};
                        int selection = JOptionPane.showOptionDialog(null,
                        "Would you like to",
                        "Customers Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        options[1]);       
                    }
                });
                return row ;
            });
            
            customerTable.setItems(customerData);
        }   
        catch(SQLException ex)
        {
            ex.printStackTrace();
        } 
    }  
    
    public void addCustomer()
    {
        boolean added = false;
        
        String type = "";
        
        if(privateRadio.isSelected())
        {
            type = "P";
        }
        else if(businessRadio.isSelected())
        {
            type = "B";
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select a type");
            return;
        }
        
        if(fullNameBox.getText().isEmpty() || addressBox.getText().isEmpty() || postCodeBox.getText().isEmpty() || phoneBox.getText().isEmpty() || emailBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields are required: \n FirstName\n Surname\n Password\n Admin ");
        }
        else
        {
            added = Database.getInstance().addCustomer(fullNameBox.getText(), addressBox.getText(), postCodeBox.getText(), phoneBox.getText(), type, emailBox.getText());
        }
        
        if(added)
        {
            JOptionPane.showMessageDialog(null, "Customer was added");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Customer was not added");
        }
        refresh();
        clear();
    }
    
    public void refresh()
    {
        try
        {            
            URL addUserUrl = getClass().getResource("/customers/gui/AddCustomer.fxml");
            AnchorPane addUserPane = FXMLLoader.load(addUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addUserPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void clear()
    {
        fullNameBox.clear();
        addressBox.clear();
        postCodeBox.clear();
        phoneBox.clear();
        emailBox.clear();
        
        privateRadio.setSelected(false);
        businessRadio.setSelected(false);
    }
    
}
