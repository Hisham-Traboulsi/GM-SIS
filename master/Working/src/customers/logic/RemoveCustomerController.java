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
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Customers, String> firstNameCol;
    
    @FXML
    private TableColumn<Customers, String> surnameCol;

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
    private TextField searchByName;
    
    @FXML
    private TextField searchByVehicle;
    
     private ObservableList<Customers> selected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        searchByName.setPromptText("Search By Name");
        searchByVehicle.setPromptText("Search By Vehicle");
        try
        {
            ObservableList<Customers> customerData = Database.getInstance().getAllCustomers();
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postCodeCol.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            
            FilteredList<Customers> filteredData=new FilteredList<>(customerData,e->true);
            searchByName.textProperty().addListener((observableValue,oldValue,newValue)->{
		filteredData.setPredicate((Predicate<? super Customers>)customer->{
			if(newValue==null||newValue.isEmpty()){
				return true;
                        }
			String lowerCaseFilter=newValue.toLowerCase();
			if(customer.getFirstName().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			else if(customer.getSurname().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			return false;
		});
            });
            SortedList<Customers> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(customerTable.comparatorProperty());
            customerTable.setItems(sortedData);
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
