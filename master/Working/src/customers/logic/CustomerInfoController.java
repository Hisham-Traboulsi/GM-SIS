/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.Main;
import common.database.Database;
import static customers.logic.AddCustomerController.rowData;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
public class CustomerInfoController implements Initializable {

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
    
    protected static Customers rowData;
    
    private ObservableList<Customers> selected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            
            customerTable.setRowFactory( tv -> {
                TableRow<Customers> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        rowData = row.getItem();
                        Object [] options = {"Display Info", "Past & Future Bookings with Bills"};
                        int selection = JOptionPane.showOptionDialog(null,
                        "Would you like to",
                        "Customers Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        null); 
                        
                        System.out.println(selection);
                        if(selection == 0)
                        {
                            try {                
                                System.out.println(rowData.getAddress());
                                Stage stage = new Stage();                                
                                Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/DisplayInfo.fxml"));                                                                
                                stage.setScene(new Scene(root));
                                stage.setTitle("Customer Info");                                
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }   
                        }
                        else if(selection == 1)
                        {
                            try {                                
                                Stage stage = new Stage();                                
                                Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/DisplayAllBookings.fxml"));                                                                
                                stage.setScene(new Scene(root));
                                stage.setTitle("Past & Future Bookings with Bills");                                
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }         
                        }
                    }
                });
                return row ;
            });
            
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
    
   public void displayHelp() throws IOException
    {
        Stage stage = new Stage();                                
        Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/CustomerInfoHelp.fxml"));                                                                
        stage.setScene(new Scene(root));
        stage.setTitle("Help Message");   
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/customers/gui/help-icon.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
