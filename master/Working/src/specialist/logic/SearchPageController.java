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
            //System.out.println(SPCNAME);
                try {
              //populate vehicle table
            ObservableList<OutstandingVehicle> outVehicleSearchData = Database.getInstance().getOutstandingVehiclesFromSPC(SPCNAME);

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
    
}
