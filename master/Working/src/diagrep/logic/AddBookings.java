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
    private TableView<Mec> MechTable = new TableView<Mec>();
    @FXML
    private TableColumn<Mec, Double> MechanicRate;
    @FXML
    private TextField PART_NAME;
    @FXML
    private TextField ManufactureTxt;
    @FXML
    private TextField MileageTxt;
    @FXML
    private TextField CostTxt;
    @FXML
    private ComboBox regComb;
    @FXML
    private ComboBox IDcomb;
    @FXML
    private ComboBox MechCombReg;
    @FXML
    private ComboBox regCombReg;
    @FXML
    private ComboBox comboType;
    @FXML
    private ComboBox comboTime;
    @FXML
    private ComboBox comboManufacture;
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
    @FXML
    private TableColumn <Bookings, Double> CostCol;
 
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
      if(Fill())
      { 
    String BookingMechanicID = (String) MechCombReg.getValue();
    String PARTNAME = (String) regComb.getValue();
    int CUSTOMERID = (Integer)(IDcomb.getValue());
    String BookingRegNum = (String) regCombReg.getValue();
    String BookingManufacture = (String) comboManufacture.getValue();
    String BookingMileage= (MileageTxt.getText());
    String BookingDate = (df.format(dateobj));          
    String BookingTime = (String) comboTime.getValue();
    String BookingType = (String) comboType.getValue();
    Double BookingTotal= Double.parseDouble(CostTxt.getText());
            
    
    added = Database.getInstance().addBookings( BookingMechanicID,PARTNAME,CUSTOMERID, 
                                                BookingRegNum, BookingManufacture,BookingMileage,
                                                BookingDate, BookingTime, BookingType,BookingTotal);
      }
      RefreshPage();
      return added;
    }
    public void enterPressed(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                  addBooking();
                }
            }
    public boolean Fill(){
            
    boolean check=true;
        
    boolean BookingMechanicID = MechCombReg.getSelectionModel().isEmpty();
    boolean PARTNAME = regComb.getSelectionModel().isEmpty();
    boolean CUSTOMERID = IDcomb.getSelectionModel().isEmpty();
    boolean BookingRegNum = regCombReg.getSelectionModel().isEmpty();
    boolean BookingType = comboType.getSelectionModel().isEmpty();
    boolean BookingManufacture = comboManufacture.getSelectionModel().isEmpty();
    boolean BookingTime = comboTime.getSelectionModel().isEmpty();
    boolean BookingMileage=  MileageTxt.getText().isEmpty();
    boolean BookingTotal = CostTxt.getText().isEmpty();
            
            if (BookingMechanicID)
        {
            JOptionPane.showMessageDialog(null,"Please select a Mechanic");
            check=false;
        }
        else if(PARTNAME)
        {
            JOptionPane.showMessageDialog(null,"Please select a Part Name");
            check=false;
        }
        
        else if(CUSTOMERID)
        {
            JOptionPane.showMessageDialog(null,"Please select a customer ID");
            check=false;
        }
        else if(BookingRegNum)
        {
            JOptionPane.showMessageDialog(null,"Please select Registration Number");
            check=false;
        }
            else if(BookingType)
        {
            JOptionPane.showMessageDialog(null,"Please select Booking Type");
            check=false;
        }
             else if(BookingManufacture)
        {
            JOptionPane.showMessageDialog(null,"Please select Car Manufature");
            check=false;
        }
             else if(BookingTime)
        {
            JOptionPane.showMessageDialog(null,"Please select Booking Time");
            check=false;
        }
            else if(BookingMileage)
        {
            JOptionPane.showMessageDialog(null,"Enter Mileage(Numbers ONLY e.g. 20000)");
            check=false;
        }
             else if(BookingTotal)
        {
            JOptionPane.showMessageDialog(null,"Enter The Duration Of Repair In Hours(e.g. 3)");
            check=false;
        }
        return check;
    }
   
   public void partBooking(ActionEvent event)
    {
        try
        {
            //addMenuBar();
            
            URL addPartUrl = getClass().getResource("/diagrep/gui/AddBookings.fxml");
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
       
    }
   
   
   
   
   public void removeBooking() throws SQLException
    {
        
        
        Object [] options = {"Yes", "No"};
        int selection = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to remove this booking?",
                        "CONFIRM",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        null); 

                        if(selection == 0)
                        {
                             selected = BookTable.getSelectionModel().getSelectedItems();   
                             int ID = selected.get(0).getIDnum();
                             Database.getInstance().removeBookings(ID);
                              RefreshPage();
                        }
                        else if(selection == 1)
                        {
                         
                        }
        
    }
   
   public void clearFields()
    {   regComb.getItems().clear();
        regCombReg.getItems().clear();
        IDcomb.getItems().clear();
        MechCombReg.getItems().clear();
        comboTime.getItems().clear();
        comboManufacture.getItems().clear();
        ManufactureTxt.clear();
        MileageTxt.clear();
        CostTxt.clear();
       
        
        partBox();
        numBox();
        idcombBox();
        RegBox();
        Type();
        Time();
        
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
    
    public void numBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <String> regComb1=Database.getInstance().fillNumComboBook2();
        //regComb = new ComboBox();
        MechCombReg.setItems(regComb1);
        
        
        
    }
     public void RegBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <String> regComb1=Database.getInstance().fillNumCombo();
        //regComb = new ComboBox();
        regCombReg.setItems(regComb1);
        
    }
    public void Type()
    {
        comboType.getItems().removeAll(comboType.getItems());
        comboType.getItems().addAll("Diagnosis & Repair", "Scheduled Maintenance");
        
    }
    public void Time()
    {
        comboTime.getItems().removeAll(comboTime.getItems());
        comboTime.getItems().addAll("9:00", "9:15","9:30","9:45","10:00","10:15","10:30","10:45",
                "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45"
                ,"14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45","16:00","16:15","16:30",
                "16:45","17:00","17:15","17:30");
        
    }
    public void Manufature()
    {
        comboManufacture.getItems().removeAll(comboManufacture.getItems());
        comboManufacture.getItems().addAll("Acura", "Alfa Romeo","Aston Martin","Audi","Bentley","BMW","Bugatti","Buick",
                "Cadillac","Chevrolet","Chrysler","Citroen","Dodge","Ferrari","Fait","Ford","Geely","General Motors","GMC","Honda","Hyundai"
                ,"Infiniti","Jaguar","Jeep","Kia","Koenigsegg","Lamborghini","Land Rover","Lexus","Maserati","Mazda",
                "McLaren","Mercedes Benz","Mini","Mitsubishi","Nissan","Pagani","Porsche","Ram","Renault","Rolls Royce","Sabb","Subaru",
                "Suzuki","Tata","Tesla","Toyota","Volkswagen","Volvo");
        
    }
   
   public void RefreshPage(){
       try {
            ObservableList<Bookings> BookingsData = Database.getInstance().getBookings();

            BookTable.setEditable(true);

            BookingCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MechID"));
            PartCol.setCellValueFactory(new PropertyValueFactory<>("REG_NUM"));
            CustomerCol.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            RegNumCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_REGNUM"));
            ManufactureCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MANUFACTURE"));
            MileageCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MILEAGE"));
            DateCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_DATE"));
            TimeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TIME"));
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TYPE"));
            CostCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_TOTAL"));
          
            

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
       numBox();
       RegBox();
       Manufature();
       Type();
       Time();
      
        try {
            
            ObservableList<Bookings> BookingsData = Database.getInstance().getBookings();
           
            BookTable.setEditable(true); 

            BookingCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("BOOKING_MechID"));
            /*MechanicCol.setCellFactory(TextFieldTableCell.forTableColumn());
            MechanicCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bookings,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bookings, String> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_MechID(t.getNewValue());
                }
            } 
            );*/
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
            CostCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_RATE"));
            CostCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            CostCol.setOnEditCommit(
                    new EventHandler<CellEditEvent<Bookings,Double>>() {
                @Override
                public void handle(CellEditEvent<Bookings, Double> t) {
                    ((Bookings) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBOOKING_TOTAL(t.getNewValue());
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

