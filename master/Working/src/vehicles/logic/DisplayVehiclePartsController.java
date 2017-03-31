/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.logic;

import common.database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import parts.logic.installedPart;

/**
 * FXML Controller class
 *
 * @author ugonw
 */
public class DisplayVehiclePartsController implements Initializable {

    @FXML
    private TableColumn<?, ?> INST_ID_view;
    @FXML
    private TableColumn<?, ?> REG_NUM_view;
    @FXML
    private TableColumn<?, ?> INST_DATE_view;
    @FXML
    private TableColumn<?, ?> EXP_DATE_view;
    @FXML
    private TableColumn<?, ?> PART_NAME_view;
    @FXML
    private TableColumn<?, ?> BOOKING_ID_view;
    @FXML
    private TableView<installedPart> installedPartsTable = new TableView<installedPart>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    public void loadTable(String regnum)
    {
       
       installedPartsTable.setEditable(true);
       
            INST_ID_view.setCellValueFactory(new PropertyValueFactory<>("INST_ID"));
            
           
           PART_NAME_view.setCellValueFactory(new PropertyValueFactory<>("PART_NAME"));
           
            

            REG_NUM_view.setCellValueFactory(new PropertyValueFactory<>("REG_NUM"));
            
            
            INST_DATE_view.setCellValueFactory(new PropertyValueFactory<>("INST_DATE"));
            
           
            EXP_DATE_view.setCellValueFactory(new PropertyValueFactory<>("EXP_DATE"));
           
            BOOKING_ID_view.setCellValueFactory(new PropertyValueFactory<>("BOOKING_ID"));
            
    }
}
