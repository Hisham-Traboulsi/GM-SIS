/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;/**

/**
 * FXML Controller class
 *
 * @author ernes
 */
public class AddBookings implements Initializable {

    @FXML
    private TextField MechTxt;
    @FXML
    private TextField FNameTxt;
    @FXML
    private TextField SNameTxt;
    @FXML
    private TextField RegNumTxt;
    @FXML
    private TextField ManufactureTxt;
    @FXML
    private TextField MileageTxt;
    @FXML
    private TextField DateTxt;
    @FXML
    private TextField TimeTxt;
    @FXML
    private TextField Typetxt;
    
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    
    @FXML
    private TableView<Bookings> BookTable = new TableView<Bookings>();
    
    @FXML
    private TableColumn BookingCol; 
    @FXML
    private TableColumn<Bookings, String> MechanicCol;
    @FXML
    private TableColumn FNameCol;
    @FXML
    private TableColumn SNameCol;
    @FXML
    private TableColumn RegNumCol;
    @FXML
    private TableColumn ManufactureCol;
    @FXML
    private TableColumn MileageCol;
    @FXML
    private TableColumn DateCol;
    @FXML
    private TableColumn TimeCol;
    @FXML
    private TableColumn TypeCol;
    
    @FXML
    private TextField searchByName;
    
    @FXML
    private TextField searchByVehicle;
    
    protected static Bookings rowData;
    

    private ObservableList<Bookings> list=FXCollections.observableArrayList();
        private ObservableList<Bookings> selected = null;
    
   @FXML
   public boolean add() throws SQLException
    {
      
      boolean added=false;
      
      
            String BookingMechanicID = (MechTxt.getText());
            String BookingFName = (FNameTxt.getText());
            String BookingSName = (SNameTxt.getText());
            String BookingRegNum = (RegNumTxt.getText());
            String BookingManufacture = (ManufactureTxt.getText());
            String BookingMileage = (MileageTxt.getText());
            String BookingDate = (DateTxt.getText());
            String BookingTime = (TimeTxt.getText());
            String BookingType = (Typetxt.getText());
 
            
  
      added = Database.getInstance().addBookings( BookingMechanicID, BookingFName,
              BookingSName,BookingRegNum,BookingManufacture, BookingMileage,
              BookingDate,BookingTime, BookingType);
      reload();
            
      return added;
    }
   public void remove() throws SQLException
    {
        selected = BookTable.getSelectionModel().getSelectedItems();   
        
        int ID = selected.get(0).getIDnum();
        
        Database.getInstance().removeBookings(ID);
        reload();
        
    }
   
   public void clearFields()
    {
        MechTxt.clear();
        FNameTxt.clear();
        SNameTxt.clear();
        RegNumTxt.clear();
        ManufactureTxt.clear();
        MileageTxt.clear();
        DateTxt.clear();
        TimeTxt.clear();
        Typetxt.clear();
        
    }
   
   
   
   public void edit() throws SQLException
    {
        Database.getInstance().editBookings();
        reload();
    }
   
   public void reload(){
       try {
            ObservableList<Bookings> BookingsData = Database.getInstance().getBookings();

            BookTable.setEditable(true);

            BookingCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MechID"));
            FNameCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_FNAME"));
            SNameCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_SNAME"));
            RegNumCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_REGNUM"));
            ManufactureCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MANUFACTURE"));
            MileageCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MILEAGE"));
            DateCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_DATE"));
            TimeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TIME"));
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TYPE"));
          
            

            BookTable.setItems(BookingsData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
   }
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Bookings> BookingsData = Database.getInstance().getBookings();

            BookTable.setEditable(true);

            BookingCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MechID"));
            MechanicCol.setCellFactory(TextFieldTableCell.forTableColumn());
            MechanicCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_MechID(t.getNewValue());
                }
            } 
            );
            FNameCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_FNAME"));
            FNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            FNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_FNAME(t.getNewValue());
                }
            }
            );
            SNameCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_SNAME"));
            SNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            SNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_SNAME(t.getNewValue());
                }
            }
            );
            RegNumCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_REGNUM"));
            RegNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
            RegNumCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_REGNUM(t.getNewValue());
                }
            }
            );
            ManufactureCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MANUFACTURE"));
            ManufactureCol.setCellFactory(TextFieldTableCell.forTableColumn());
            ManufactureCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_MANUFACTURE(t.getNewValue());
                }
            }
            );
            MileageCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MILEAGE"));
            MileageCol.setCellFactory(TextFieldTableCell.forTableColumn());
            MileageCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_MILEAGE(t.getNewValue());
                }
            }
            );
            DateCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_DATE"));
            DateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            DateCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_DATE(t.getNewValue());
                }
            }
            );
            TimeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TIME"));
            TimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            TimeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_TIME(t.getNewValue());
                }
            }
            );
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TIME"));
            TypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            TypeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_TYPE(t.getNewValue());
                }
            }
            );

            BookTable.setItems(BookingsData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    

}
