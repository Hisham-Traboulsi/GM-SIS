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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class SearchPageController implements Initializable {

    @FXML
    ComboBox spcBox;
    @FXML
    private TableView<OutstandingVehicle> SearchTable = new TableView<OutstandingVehicle>();
    @FXML
    private TableColumn regCol;
    @FXML
    private TableColumn spcCol;
    @FXML
    private TableColumn makeCol;
    @FXML
    private TableColumn modelCol;
    @FXML
    private TableColumn deliveryCol;
    @FXML
    private TableColumn returnCol;
    @FXML
    private Button spcSearch;
    @FXML
    private Button regSearch;

    @FXML
    private TableColumn bookingCol;
    @FXML
    private TextField regField;
    @FXML
    private TableColumn firstName;
    @FXML
    private TableColumn surname;
    @FXML
    private TableView<Outstanding> PartSearchTable = new TableView<Outstanding>();
    @FXML
    private TableColumn bookingIDCol;
    @FXML
    private TableColumn firstNamePart;
    @FXML
    private TableColumn surnamePart;
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
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField surnameField;
    @FXML
    private Button nameSearch;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
        ObservableList<String> spcData = Database.getInstance().getSPCName();
         spcBox.setItems(spcData); 

            //load();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    

    @FXML
    private void load() {      
        
            String SPCNAME = (String) spcBox.getValue();
              SearchTable.getItems().clear();
              PartSearchTable.getItems().clear();
            //System.out.println(SPCNAME);
                try {
              //populate vehicle table

            ObservableList<OutstandingVehicle> outVehicleSearchData = Database.getInstance().getOutstandingVehiclesFromSPC(SPCNAME);

            SearchTable.setEditable(true);

            bookingCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGIDVEHICLE"));                       
            firstName.setCellValueFactory(new PropertyValueFactory<>("FIRSTNAME"));           
            surname.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
            regCol.setCellValueFactory(new PropertyValueFactory<>("REGNUM"));
            spcCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAMEVEHICLE"));
            makeCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMAKE"));
            modelCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMODEL"));
            deliveryCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATEVEHICLE"));
            returnCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATEVEHICLE"));
          

            SearchTable.setItems(outVehicleSearchData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
                
        try {
              //populate part table
            //  PartSearchTable.getItems().clear();
              //SearchTable.getItems().clear();
            ObservableList<Outstanding> outPartSearchData = Database.getInstance().getOutstandingPartsFromSPC(SPCNAME);

            PartSearchTable.setEditable(true);

            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
            firstNamePart.setCellValueFactory(new PropertyValueFactory<>("FIRSTNAME"));
            surnamePart.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
            spcNameCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAME"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("PARTNAME"));
            deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATE"));
            returnDateCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATE"));
            partCost.setCellValueFactory(new PropertyValueFactory<>("PARTCOST"));
          

            PartSearchTable.setItems(outPartSearchData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
                
                
        
    }
    
    @FXML
        private void loadregsearch() {      
        
            String REGIS  = regField.getText();
            String REG = REGIS.toUpperCase();
              SearchTable.getItems().clear();
              PartSearchTable.getItems().clear();
                try {
              //populate vehicle table

            ObservableList<OutstandingVehicle> outVehicleSearchData = Database.getInstance().getOutstandingVehiclesFromReg(REG);

            SearchTable.setEditable(true);

            bookingCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGIDVEHICLE"));            
            regCol.setCellValueFactory(new PropertyValueFactory<>("REGNUM"));
            spcCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAMEVEHICLE"));
            makeCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMAKE"));
            modelCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMODEL"));
            deliveryCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATEVEHICLE"));
            returnCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATEVEHICLE"));
          

            SearchTable.setItems(outVehicleSearchData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
    }

    @FXML
    public void nameSearchButton(){
        
             String FIRSTNAMESEARCH  = firstNameField.getText();
             String FIRSTNAME = FIRSTNAMESEARCH.toUpperCase();

              SearchTable.getItems().clear();
              PartSearchTable.getItems().clear();
            //System.out.println(SPCNAME);
                try {
              //populate vehicle table

            ObservableList<OutstandingVehicle> outVehicleSearchData = Database.getInstance().getOutstandingVehiclesFromNAME(FIRSTNAME);
    
            SearchTable.setEditable(true);

            bookingCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGIDVEHICLE"));                       
            firstName.setCellValueFactory(new PropertyValueFactory<>("FIRSTNAME"));           
            surname.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
            regCol.setCellValueFactory(new PropertyValueFactory<>("REGNUM"));
            spcCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAMEVEHICLE"));
            makeCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMAKE"));
            modelCol.setCellValueFactory(new PropertyValueFactory<>("VEHICLEMODEL"));
            deliveryCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATEVEHICLE"));
            returnCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATEVEHICLE"));
          

            SearchTable.setItems(outVehicleSearchData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
                
        try {
              //populate part table
            //  PartSearchTable.getItems().clear();
              //SearchTable.getItems().clear();
            ObservableList<Outstanding> outPartSearchData = Database.getInstance().getOutstandingPartsFromNAME(FIRSTNAME);

            PartSearchTable.setEditable(true);

            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
            firstNamePart.setCellValueFactory(new PropertyValueFactory<>("FIRSTNAME"));
            surnamePart.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
            spcNameCol.setCellValueFactory(new PropertyValueFactory<>("SPCNAME"));
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("PARTID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("PARTNAME"));
            deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("DELIVERYDATE"));
            returnDateCol.setCellValueFactory(new PropertyValueFactory<>("RETURNDATE"));
            partCost.setCellValueFactory(new PropertyValueFactory<>("PARTCOST"));
          

            PartSearchTable.setItems(outPartSearchData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
                
                
        
    }

}
