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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class EditCustomerController implements Initializable {

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
    private ObservableList<Customers> customerData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    { 
       searchByName.setPromptText("Search By Name or Vehicle");
       try
        {
            customerData = Database.getInstance().getAllCustomers();
            
            customerTable.setEditable(true);
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            firstNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFirstName(t.getNewValue());
                }
            }
            );
            
            surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
            surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            surnameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSurname(t.getNewValue());
                }
            }
            );
            
            
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAddress(t.getNewValue());
                }
            }
            );
            
            postCodeCol.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            postCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            postCodeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPostCode(t.getNewValue());
                }
            }
            );
            
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPhone(t.getNewValue());
                }
            }
            );
            
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
            emailCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEmail(t.getNewValue());
                }
            }
            );
            
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            typeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setType(t.getNewValue());
                }
            }
            );
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
    
    public void edit() throws SQLException
    {
        boolean dataCheck;
        for(int i = 0; i<customerData.size(); i++)
        {
            //checks on first name
            if(customerData.get(i).getFirstName().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "First Name is required");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }
            else if((customerData.get(i).getFirstName().chars().allMatch(Character::isLetter)) == false)
            {
                JOptionPane.showMessageDialog(null, "First Name must only consist of letters (no numbers and no spaces)");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }

            //checks on surname
            if(customerData.get(i).getSurname().isEmpty())
            {
                 JOptionPane.showMessageDialog(null, "Surname is required");
                 JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }
            else if((customerData.get(i).getSurname().chars().allMatch(Character::isLetter)) == false)
            {
                JOptionPane.showMessageDialog(null, "First Name must only consist of letters (no numbers and no spaces)");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }

            //checks on address
            if(customerData.get(i).getAddress().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Address is required");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }

            //checks on postcode
            if(customerData.get(i).getPostCode().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Post Code is required");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }

            //checks on the phone number
            if(customerData.get(i).getPhone().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Phone is required");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }
            else if(!checkIsInteger(customerData.get(i).getPhone()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a correct phone number");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }

            //checks on the email
            if(customerData.get(i).getEmail().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Email is required");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }
            if(customerData.get(i).getType().equals("P") || customerData.get(i).getType().equals("B"))
            {
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please enter either a P (for Private) or B (for Business) for the type");
                JOptionPane.showMessageDialog(null, "Customer wasn't edited");
                return;
            }
        }
        Database.getInstance().editCustomer();
        refresh();
    }
    
    public void refresh()
    {
        try
        {            
            URL addCustomerUrl = getClass().getResource("/customers/gui/EditCustomer.fxml");
            AnchorPane addUserPane = FXMLLoader.load(addCustomerUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addUserPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public boolean checkIsInteger(String number)
    {
       int length = number.length();
       if(length == 0 || length < 11)
       {
           JOptionPane.showMessageDialog(null, "The number's length you entered was less than 11 digits or you entered no digits at all");
           return false;
       }
       
       for(int i = 0; i<length; i++)
       {
           if(number.charAt(i) < '0' || number.charAt(i) > '9')
           {
               JOptionPane.showMessageDialog(null, "Please enter a number WITHOUT any letters");
               return false;
           }
       }
       return true;
    }
    
    public void displayHelp() throws IOException
    {
        Stage stage = new Stage();                                
        Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/EditHelpMessage.fxml"));                                                                
        stage.setScene(new Scene(root));
        stage.setTitle("Help Message");  
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/customers/gui/help-icon.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
}
