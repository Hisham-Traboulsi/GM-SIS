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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class RemoveCustomerController implements Initializable {

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
    
     private ObservableList<Customers> selected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
            
            customerTable.setItems(customerData);
        }   
        catch(SQLException ex)
        {
            ex.printStackTrace();
        } 
    } 
    
    public void remove() throws SQLException
    {
        selected = customerTable.getSelectionModel().getSelectedItems();   
        System.out.println(selected.get(0).getID());
        Database.getInstance().removeCustomer(selected.get(0).getID());
        refresh();
    }
    
    public void refresh()
    {
        try
        {   
            URL editUserUrl = getClass().getResource("/customers/gui/RemoveCustomer.fxml");
            AnchorPane editUserPane = FXMLLoader.load(editUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(editUserPane);
            
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
