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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class OutstandingController implements Initializable {
    @FXML
    private TextField partBill; 
    @FXML
    private TextField vehicleBill;
//parts table
    @FXML
    private TableView<Outstanding> OutstandingPartTable = new TableView<Outstanding>();

   
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
    private TableColumn partCost;
    
    private ObservableList<Outstanding> list=FXCollections.observableArrayList();
    private ObservableList<Outstanding> selected = null;

    // vehicle table
    @FXML
    private TableView<OutstandingVehicle> OutstandingVehicleTable = new TableView<OutstandingVehicle>();

   
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
    

    
    
    private ObservableList<OutstandingVehicle> list2=FXCollections.observableArrayList();
    private ObservableList<OutstandingVehicle> selected2 = null;
    @FXML
    private Button returnButton;
    @FXML
    private Button vehicleReturnButton;
    @FXML
    private Button partsCancel;
    @FXML
    private Button vehicleCancel;
    @FXML
    private TableColumn customerID;
    @FXML
    private TableColumn customerIDvehicle;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            //populate part table
            ObservableList<Outstanding> outPartsData = Database.getInstance().getOutstandingParts();

            OutstandingPartTable.setEditable(true);

            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("CUSTOMERID"));
            spcNameCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAME"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("PARTNAME"));
            deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATE"));
            returnDateCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATE"));
            partCost.setCellValueFactory(new PropertyValueFactory<>("PARTCOST"));
          

            OutstandingPartTable.setItems(outPartsData);
            
                    } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
           
                try {
              //populate vehicle table
            ObservableList<OutstandingVehicle> outVehicleData = Database.getInstance().getOutstandingVehicles();

            OutstandingVehicleTable.setEditable(true);

            bookingIDvehicleCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGIDVEHICLE"));
            customerIDvehicle.setCellValueFactory(new PropertyValueFactory<>("CUSTOMERID"));
            spcNameVehicleCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAMEVEHICLE"));
            vehicleRegCol.setCellValueFactory(new PropertyValueFactory<>("REGNUM"));
            vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMAKE"));
            vehicleModelCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMODEL"));
            vehicleDeliveryCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATEVEHICLE"));
            vehicleReturnCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATEVEHICLE"));
          

            OutstandingVehicleTable.setItems(outVehicleData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    
      @FXML
   public boolean returned() throws SQLException
    {
      
    boolean added=false;
    boolean added2=false;
            selected = OutstandingPartTable.getSelectionModel().getSelectedItems();   
            
           // int BOOKINGID = selected.get(0).getBOOKINGID();
            String SPC = selected.get(0).getSPCNAME();
            int PARTID = selected.get(0).getPARTID();
            int CUSTOMERID = selected.get(0).getCUSTOMERID();
            String PARTNAME = selected.get(0).getPARTNAME();
            String DELIVDATE = selected.get(0).getDELIVERYDATE();
            String RETURNDATE = selected.get(0).getRETURNDATE();
            Double partCost = selected.get(0).getPARTCOST();
            Double bill = Double.parseDouble(partBill.getText());
            Double TOTAL = partCost + bill;
            
           added = Database.getInstance().returnedSPCPart(SPC, PARTID, PARTNAME, DELIVDATE, RETURNDATE, TOTAL, CUSTOMERID);
           added2 = Database.getInstance().sendPartBill(CUSTOMERID, TOTAL);
           remove();
           reload();
        return added;
        
    }
    @FXML
   public boolean returnedVehicle() throws SQLException
    {
      
    boolean added=false;
    boolean added2=false;
            selected2 = OutstandingVehicleTable.getSelectionModel().getSelectedItems();   
            
            //int BOOKINGID = selected2.get(0).getBOOKINGIDVEHICLE();
            String SPC = selected2.get(0).getSPCNAMEVEHICLE();
            int CUSTOMERID = selected2.get(0).getCUSTOMERID();
            String REGNUM = selected2.get(0).getREGNUM();
            String VEHICLEMAKE = selected2.get(0).getVEHICLEMAKE();
            String VEHICLEMODEL = selected2.get(0).getVEHICLEMODEL();
            String DELIVDATE = selected2.get(0).getDELIVERYDATEVEHICLE();
            String RETURNDATE = selected2.get(0).getRETURNDATEVEHICLE();
            Double TOTAL = Double.parseDouble(vehicleBill.getText());
            System.out.println(TOTAL);
         
           added = Database.getInstance().returnedSPCVehicle(SPC, REGNUM, VEHICLEMAKE, VEHICLEMODEL, DELIVDATE, RETURNDATE, TOTAL);
           added2 = Database.getInstance().sendPartBill(CUSTOMERID, TOTAL);

           remove2();
           reload();
      
        return added;
        
    }
   
   public void remove() throws SQLException
    {
        selected = OutstandingPartTable.getSelectionModel().getSelectedItems();   
        
        int BOOKINGID = selected.get(0).getBOOKINGID();
        
        Database.getInstance().removeOutstandingPart(BOOKINGID);
        
    }

   public void remove2() throws SQLException
    {
        selected2 = OutstandingVehicleTable.getSelectionModel().getSelectedItems();   
        
        int BOOKINGID = selected2.get(0).getBOOKINGIDVEHICLE();
        
        Database.getInstance().removeOutstandingVehicle(BOOKINGID);
        
    }
   
   public void reload(){
         
        try {
            //populate part table
            ObservableList<Outstanding> outPartsData = Database.getInstance().getOutstandingParts();

            OutstandingPartTable.setEditable(true);

            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            spcNameCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAME"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("PARTNAME"));
            deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATE"));
            returnDateCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATE"));
          

            OutstandingPartTable.setItems(outPartsData);
            
                    } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
           
                try {
              //populate vehicle table
            ObservableList<OutstandingVehicle> outVehicleData = Database.getInstance().getOutstandingVehicles();

            OutstandingVehicleTable.setEditable(true);

            bookingIDvehicleCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGIDVEHICLE"));
            customerIDvehicle.setCellValueFactory(new PropertyValueFactory<>("CUSTOMERID"));
            spcNameVehicleCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAMEVEHICLE"));
            vehicleRegCol.setCellValueFactory(new PropertyValueFactory<>("REGNUM"));
            vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMAKE"));
            vehicleModelCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMODEL"));
            vehicleDeliveryCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATEVEHICLE"));
            vehicleReturnCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATEVEHICLE"));
          

            OutstandingVehicleTable.setItems(outVehicleData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
   }
    @FXML
     public void cancelPart() throws SQLException
    {
        Object [] options = {"Yes", "No"};
        int selection = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to cancel this booking?",
                        "CONFIRM",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        null); 
                        
                        System.out.println(selection);
                        if(selection == 0)
                        {
                             selected = OutstandingPartTable.getSelectionModel().getSelectedItems();   
        
                             int BOOKINGID = selected.get(0).getBOOKINGID();
        
                             Database.getInstance().removeOutstandingPart(BOOKINGID);
                                reload();
                        }
                        else if(selection == 1)
                        {
                         
                        }

    }
       
    @FXML
    public void cancelVehicle() throws SQLException
    {
                Object [] options = {"Yes", "No"};
        int selection2 = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to cancel this booking?",
                        "CONFIRM",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        null); 
                        
                        if(selection2 == 0)
                        {
                            selected2 = OutstandingVehicleTable.getSelectionModel().getSelectedItems();   
                             int BOOKINGID = selected2.get(0).getBOOKINGIDVEHICLE();
                             Database.getInstance().removeOutstandingVehicle(BOOKINGID);
                            reload();
                        }
                        else if(selection2 == 1)
                        {
                        }

    }
}