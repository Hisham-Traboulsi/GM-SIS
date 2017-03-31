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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    
     private ObservableList<Customers> selected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         searchByName.setPromptText("Search By Name or Vehicle");
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
                    int customerID = Database.getInstance().getCustomerVehicles(searchByName.getText());
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
                    else if(customer.getID() == customerID)
                    {
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
        if(selected.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please select a customer that you want to remove");
        }
        else
        {
            Object [] options = {"Yes", "No"};
                            int selection = JOptionPane.showOptionDialog(null,
                            "Are you sure you want to remove the selected customer",
                            "",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.DEFAULT_OPTION,
                            null,
                            options,
                            null); 
            if(selection == 0)
            {    
                System.out.println(selected.get(0).getID());
                Database.getInstance().removeCustomer(selected.get(0).getID());
                refresh();
            }
        }
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
    
    public void displayHelp() throws IOException
    {
        Stage stage = new Stage();                                
        Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/RemoveHelpMessage.fxml"));                                                                
        stage.setScene(new Scene(root));
        stage.setTitle("Help Message");   
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/customers/gui/help-icon.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
