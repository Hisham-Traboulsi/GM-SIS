/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ernes
 */
public class addBook implements Initializable {

    
    @FXML
    private TextField BookingMechanic;
    @FXML
    private TextField BookingDate;
    @FXML
    private TextField BookingRegNum;
    @FXML
    private TextField BookingMileage;
    @FXML
    private TextField BookingTime;
    @FXML
    private TextField BookingVehicle;
    @FXML
    private TextField BookingName;
    
    @FXML
    private TableView<Book> BookingTable = new TableView<Book>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<Book, Integer> MechanicCol;

    @FXML
    private TableColumn<Book, String> DateCol;
    
    @FXML
    private TableColumn<Book, String> RegNumCol;

    @FXML
    private TableColumn<Book, Integer> MileageCol;
    
    @FXML
    private TableColumn<Book, String> TimeCol;

    @FXML
    private TableColumn<Book, Integer> VehicleCol;
    
    @FXML
    private TableColumn<Book, String> NameCol;

     
    @FXML
    private ObservableList<Book> list=FXCollections.observableArrayList();
    
    public boolean add()
    {
      BookingTable.getItems().clear();
      boolean added=false;
      
      //int INSTID = Integer.parseInt(INST_ID.getText());
      int Mechanic = Integer.parseInt(BookingMechanic.getText());
      String Date = (BookingDate.getText());
      String RegNum = (BookingRegNum.getText());
      int Mileage = Integer.parseInt(BookingMileage.getText());
      String Time = (BookingTime.getText());
      int Vehicle = Integer.parseInt(BookingVehicle.getText());
      String Name = (BookingName.getText());
 
      
      added = Database.getInstance().addBook(Mechanic,Date,RegNum,Mileage,Time,Vehicle,Name);
     
      return added;
      
    }
    
    public void refresh()
    {
        try
        {            
            URL BookUrl = getClass().getResource("/diagrep/gui/addBook.fxml");
            AnchorPane BookPane = FXMLLoader.load(BookUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(BookPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            try{
            ObservableList<Book> BookData = Database.getInstance().getBook();
            
            BookingTable.setEditable(true);
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
            RegNumCol.setCellValueFactory(new PropertyValueFactory<>("RegNum"));
            MileageCol.setCellValueFactory(new PropertyValueFactory<>("Mileage"));
            TimeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
            VehicleCol.setCellValueFactory(new PropertyValueFactory<>("Vehicle"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            
            BookingTable.setItems(BookData);
                    
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
   
    }
    
}
        


