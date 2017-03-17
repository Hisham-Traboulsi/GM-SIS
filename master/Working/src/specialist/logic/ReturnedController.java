/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class ReturnedController implements Initializable {

@FXML
    private TableView<Returned> ReturnedPartTable = new TableView<Returned>();
   
   @FXML
    private TableColumn bookingIDCol; 
   
   @FXML
    private TableColumn spcNameCol; 
   
   @FXML
    private TableColumn partIDCol;
    @FXML
    private TableColumn partNameCol;
    @FXML
    private TableColumn deliveryDateCol;
    @FXML
    private TableColumn returnDateCol;
    
    private ObservableList<Returned> list=FXCollections.observableArrayList();
        private ObservableList<Returned> selected = null;
        
        //returned vehicle
        @FXML
    private TableView<ReturnedVehicle> ReturnedVehicleTable = new TableView<ReturnedVehicle>();

   
   @FXML
    private TableColumn bookingIDvehicleCol; 
 
     @FXML
    private TableColumn spcNameVehicleCol; 
     
   @FXML
    private TableColumn vehicleRegCol; 
   
   @FXML
    private TableColumn vehicleMakeCol;
    @FXML
    private TableColumn vehicleModelCol;
    @FXML
    private TableColumn vehicleDeliveryCol;
    @FXML
    private TableColumn vehicleReturnCol;
    
    private ObservableList<ReturnedVehicle> list2=FXCollections.observableArrayList();
    private ObservableList<ReturnedVehicle> selected2 = null;

            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ObservableList<Returned> retPartsData = Database.getInstance().getReturnedParts();

            ReturnedPartTable.setEditable(true);
            
            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
            spcNameCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAME"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("PARTNAME"));
            deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATE"));
            returnDateCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATE"));
          

            ReturnedPartTable.setItems(retPartsData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
            try {
              //populate vehicle table
            ObservableList<ReturnedVehicle> retVehicleData = Database.getInstance().getReturnedVehicles();

            ReturnedVehicleTable.setEditable(true);

            bookingIDvehicleCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGIDVEHICLE"));
            spcNameVehicleCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAMEVEHICLE"));
            vehicleRegCol.setCellValueFactory(new PropertyValueFactory<>("REGNUM"));
            vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMAKE"));
            vehicleModelCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMODEL"));
            vehicleDeliveryCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATEVEHICLE"));
            vehicleReturnCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATEVEHICLE"));
          

            ReturnedVehicleTable.setItems(retVehicleData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
       public void remove() throws SQLException
    {
        selected = ReturnedPartTable.getSelectionModel().getSelectedItems();   
        
        int RETURNEDID = selected.get(0).getBOOKINGID();
        
        Database.getInstance().removeReturnedPart(RETURNEDID);
        
    }
    }    
    
