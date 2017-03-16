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
public class OutstandingController implements Initializable {

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
    
    private ObservableList<Outstanding> list=FXCollections.observableArrayList();
    private ObservableList<Outstanding> selected = null;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ObservableList<Outstanding> outPartsData = Database.getInstance().getOutstandingParts();

            OutstandingPartTable.setEditable(true);

            bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("BOOKINGID"));
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
    }    
      @FXML
   public boolean returned() throws SQLException
    {
      
    boolean added=false;
            selected = OutstandingPartTable.getSelectionModel().getSelectedItems();   

            String SPC = selected.get(0).getSPCNAME();
            int PARTID = selected.get(0).getPARTID();
            String PARTNAME = selected.get(0).getPARTNAME();
            String DELIVDATE = selected.get(0).getDELIVERYDATE();
            String RETURNDATE = selected.get(0).getRETURNDATE();
         
           added = Database.getInstance().returnedSPCPart(SPC, PARTID, PARTNAME, DELIVDATE, RETURNDATE);

      
        return added;
    }
   public void remove() throws SQLException
    {
        selected = OutstandingPartTable.getSelectionModel().getSelectedItems();   

        String SPC = selected.get(0).getSPCNAME();
        int PARTID = selected.get(0).getPARTID();
        String PARTNAME = selected.get(0).getPARTNAME();
        String DELIVDATE = selected.get(0).getDELIVERYDATE();
        String RETURNDATE = selected.get(0).getRETURNDATE();  
        
        //Database.getInstance().removeOutstandingPart(SPC, PARTID, PARTNAME, DELIVDATE, RETURNDATE);
        
    }
}
