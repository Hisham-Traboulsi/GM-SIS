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
import javax.swing.JOptionPane;

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
        @FXML
    private TableColumn partTotal;
    
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
    @FXML
    private TableColumn vehicleTotal;
    
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
            partTotal.setCellValueFactory(new PropertyValueFactory<>("PARTTOTAL"));          

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
            vehicleTotal.setCellValueFactory(new PropertyValueFactory<>("VEHICLETOTAL"));
          

            ReturnedVehicleTable.setItems(retVehicleData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
      public void remove() throws SQLException
    {
        try{
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
                             selected = ReturnedPartTable.getSelectionModel().getSelectedItems();   
                             int RETURNEDID = selected.get(0).getBOOKINGID();
                             Database.getInstance().removeReturnedPart(RETURNEDID);
                                reload();
                        }
                        else if(selection == 1)
                        {
                         
                        }
        }
                catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"Select a booking to delete!");
        }

    }
       
    public void removeVehicle() throws SQLException
    {
        try{
                Object [] options = {"Yes", "No"};
        int selection2 = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to remove this booking?",
                        "CONFIRM",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        null); 
                        
                        if(selection2 == 0)
                        {
                        
                              selected2 = ReturnedVehicleTable.getSelectionModel().getSelectedItems();   
        
                              int RETURNEDID = selected2.get(0).getBOOKINGIDVEHICLE();
        
                                Database.getInstance().removeReturnedVehicle(RETURNEDID);
                            reload();
                        }
                        else if(selection2 == 1)
                        {
                        }
        }
                catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"Select a booking to delete!");
        }

    }
    public void reload(){
  
        try {
            ObservableList<Returned> retPartsData = Database.getInstance().getReturnedParts();

            ReturnedPartTable.setEditable(true);
            
            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
            spcNameCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAME"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("PARTNAME"));
            deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATE"));
            returnDateCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATE"));
            partTotal.setCellValueFactory(new PropertyValueFactory<>("PARTTOTAL"));          


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
            vehicleTotal.setCellValueFactory(new PropertyValueFactory<>("VEHICLETOTAL"));


            ReturnedVehicleTable.setItems(retVehicleData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }   
}
    }    
    
