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
import javax.swing.JOptionPane;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author ernes
 */
public class AddBookings implements Initializable {

    @FXML
    private TextField MechTxt;
    @FXML
    private TextField PART_NAME;
    @FXML
    private TextField CUSTOMER_ID;
    @FXML
    private TextField RegNumTxt;
    @FXML
    private TextField ManufactureTxt;
    @FXML
    private TextField MileageTxt;
    @FXML
    private TextField TimeTxt;
    @FXML
    private TextField Typetxt;
    @FXML
    private DatePicker DateTxt;
    @FXML
    private ComboBox regComb;
    @FXML
    private ComboBox IDcomb;
    
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    
    @FXML
    private TableView<Bookings> BookTable = new TableView<Bookings>();
    
    @FXML
    private TableColumn<Bookings, String> BookingCol; 
    @FXML
    private TableColumn<Bookings, String> MechanicCol;
    @FXML
    private TableColumn<Bookings, String> PartCol;
    @FXML
    private TableColumn<Bookings, Integer> CustomerCol;
    @FXML
    private TableColumn<Bookings, String> RegNumCol;
    @FXML
    private TableColumn<Bookings, String> ManufactureCol;
    @FXML
    private TableColumn<Bookings, String> MileageCol;
    @FXML
    private TableColumn <Bookings, String>DateCol;
    @FXML
    private TableColumn <Bookings, String>TimeCol;
    @FXML
    private TableColumn <Bookings, String>TypeCol;
  
  
    

    private ObservableList<Bookings> list=FXCollections.observableArrayList();
        private ObservableList<Bookings> selected = null;
    
   @FXML
   public boolean addBooking() 
    {
       DateFormat df = new SimpleDateFormat("dd/MM/yy");
       Date dateobj = new Date();
       
       Calendar cal = Calendar.getInstance();
       Date today = cal.getTime();
       cal.add(Calendar.DATE, 364); 
       Date nextYear = cal.getTime();
       
       
       
       
       
       
      BookTable.getItems().clear();
      boolean added=false;
      
     
            String BookingMechanicID = (MechTxt.getText());
            String PARTNAME = (String) regComb.getValue();
            int CUSTOMERID = (Integer)(IDcomb.getValue());
            String BookingRegNum=(RegNumTxt.getText());
            String BookingManufacture=(ManufactureTxt.getText());
            String BookingMileage= (MileageTxt.getText());
            String BookingDate = (df.format(dateobj));          
            String BookingTime =(TimeTxt.getText());
            String BookingType=(Typetxt.getText());
            
         if(MechTxt.getText().isEmpty() || RegNumTxt.getText().isEmpty()|| ManufactureTxt.getText().isEmpty() || ManufactureTxt.getText().isEmpty() || MileageTxt.getText().isEmpty()
        || TimeTxt.getText().isEmpty() || Typetxt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields are required");
        }
         else{
     
          
      added = Database.getInstance().addBookings( BookingMechanicID,PARTNAME,
             CUSTOMERID, BookingRegNum, BookingManufacture,BookingMileage ,BookingDate,
             BookingTime, BookingType);
         }
      
           
      
      RefreshPage();
            
      return added;
    }
   public void enterPressed(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                  addBooking();
                }
            }
   
   public void partBooking(ActionEvent event)
    {
        try
        {
            //addMenuBar();
            
            URL addPartUrl = getClass().getResource("/parts/gui/partBooking.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
  
   public void updateAmount() 
    {
        String partname=PART_NAME.getText();
        Database.getInstance().updateStock(partname);
        Database.getInstance().partBelowZero();
        //return ID;
    }
   
   
   
   
   public void removeBooking() throws SQLException
    {
        selected = BookTable.getSelectionModel().getSelectedItems();   
        
        int ID = selected.get(0).getIDnum();
        
        Database.getInstance().removeBookings(ID);
        RefreshPage();
        
    }
   
   public void clearFields()
    {
        MechTxt.clear();
        
        RegNumTxt.clear();
        ManufactureTxt.clear();
        MileageTxt.clear();
        
        TimeTxt.clear();
        Typetxt.clear();
        
    }
   
   
   
   public void editBooking() throws SQLException
    {
        Database.getInstance().editBookings();
        RefreshPage();
    }
   
   public void partBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        //regComb = new ComboBox();
        regComb.setItems(regComb1);
        
    }
    public void idcombBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <Integer> regComb1=Database.getInstance().fillIDcombo();
        //regComb = new ComboBox();
        IDcomb.setItems(regComb1);
        
    }
   
   public void RefreshPage(){
       try {
            ObservableList<Bookings> BookingsData = Database.getInstance().getBookings();

            BookTable.setEditable(true);

            BookingCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MechID"));
            PartCol.setCellValueFactory(new PropertyValueFactory<>("PART_NAME"));
            CustomerCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
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
       partBox();
       idcombBox();
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
            PartCol.setCellValueFactory(new PropertyValueFactory<>("PART_NAME"));
            /*PartCol.setCellFactory(TextFieldTableCell.forTableColumn());
            PartCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_FNAME(t.getNewValue());
                }
            }
            );*/
            CustomerCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            /*SNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            SNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_SNAME(t.getNewValue());
                }
            }
            );*/
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
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TYPE"));
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

