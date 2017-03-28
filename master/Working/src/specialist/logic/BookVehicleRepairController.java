/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import common.database.Database;
import customers.logic.Customers;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import vehicles.logic.Vehicle;

/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class BookVehicleRepairController implements Initializable {

    LocalDate delivDate = null;
    LocalDate returnDate = null;
    @FXML
        ComboBox spcBox;

    @FXML
        Button bookButton;
    
    @FXML
    private TableView<Vehicle> vehicleTable = new TableView<Vehicle>();

    @FXML
    private TableColumn vehicleRegCol;

    @FXML
    private TableColumn vehicleMakeCol;

    @FXML
    private TableColumn vehicleModelCol;

    @FXML
    private TableColumn vehicleEngineCol;
    
    @FXML
    private TableColumn vehicleFuelCol;
        
    @FXML
    private TableColumn vehicleColourCol;
    
    private ObservableList<Vehicle> list=FXCollections.observableArrayList();
    private ObservableList<Vehicle> selected = null;
    private ObservableList<Customers> selected2 = null;
    @FXML
    private Label label;
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private DatePicker returnDatePicker;
     @FXML
    private TableView<Customers> customerTable = new TableView<Customers>();
    @FXML
    private TableColumn customerID;
    @FXML
    private TableColumn customerName;
    @FXML
    private TableColumn customerSurname;

    
    @FXML
   public boolean submit() throws SQLException
    {
      
    boolean added=false;
            selected = vehicleTable.getSelectionModel().getSelectedItems();   

            String SPC = (String) spcBox.getValue();
            String REGNUM  = selected.get(0).getRegnum();
            String MAKE = selected.get(0).getMake();
            String MODEL  = selected.get(0).getModel();
            String ENGINE = selected.get(0).getEngine();
            String FUEL  = selected.get(0).getFuelType();
            String COLOUR = selected.get(0).getColour();
            LocalDate DELIVDATE = delivDate;
            LocalDate RETURNDATE = returnDate;
            int customerID = selected2.get(0).getID();
            String customerName = selected2.get(0).getFirstName();
            String customerSurname = selected2.get(0).getSurname();

         
           added = Database.getInstance().bookSPCVehicle(SPC, REGNUM, MAKE, MODEL, ENGINE, FUEL, COLOUR, DELIVDATE, RETURNDATE,  customerID, customerName, customerSurname);

      
        return added;
    }
    

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            ObservableList<Customers> customerData = Database.getInstance().getAllCustomers();
            
            customerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            customerName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            customerSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            
            customerTable.setItems(customerData);
             
        ObservableList<String> spcData = Database.getInstance().getSPCName();
            spcBox.setItems(spcData);            
            
           
            ObservableList<Vehicle> vehicleData = Database.getInstance().getVehicle();

            vehicleTable.setEditable(true);
            
            vehicleRegCol.setCellValueFactory(new PropertyValueFactory<>("Regnum"));
            vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<>("Make"));
            vehicleModelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));
            vehicleEngineCol.setCellValueFactory(new PropertyValueFactory<>("Engine"));
            vehicleFuelCol.setCellValueFactory(new PropertyValueFactory<>("FuelType"));
            vehicleColourCol.setCellValueFactory(new PropertyValueFactory<>("Colour"));


            vehicleTable.setItems(vehicleData);
          
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
      
    }    

    @FXML
    private void getDeliveryDate(ActionEvent event) {
        delivDate = deliveryDatePicker.getValue();
        //System.out.println(delivDate);
    }

    @FXML
    private void getReturnDate(ActionEvent event) {
        returnDate = returnDatePicker.getValue();
    }
    
}
