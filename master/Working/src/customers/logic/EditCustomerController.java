/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.Main;
import common.database.Database;
import common.logic.SystemUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    private TextField searchCustomer;
    
    @FXML
    private ObservableList<Customers> customerData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       try
        {
            customerData = Database.getInstance().getAllCustomers();
            
            customerTable.setEditable(true);
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            
            fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            fullNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            fullNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Customers, String> t) {
                    ((Customers) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFullName(t.getNewValue());
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
            
            customerTable.setItems(customerData);
        }   
        catch(SQLException ex)
        {
            ex.printStackTrace();
        } 
    }    
    
    public void edit() throws SQLException
    {
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
    
}
