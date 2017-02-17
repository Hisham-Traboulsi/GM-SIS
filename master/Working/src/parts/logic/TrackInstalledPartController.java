/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author sergi
 */
public class TrackInstalledPartController implements Initializable {

     /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<installedPart> installedPartsTable = new TableView<installedPart>();

    @FXML
    private TableColumn INST_ID;
    @FXML
    private TableColumn PART_ID;
    @FXML
    private TableColumn VEHICLE_ID;
    @FXML
    private TableColumn PART_COST;

    @FXML
    private TableColumn<installedPart, String> REG_NUM;
    @FXML
    private TableColumn<installedPart, String> INST_DATE;
    @FXML
    private TableColumn<installedPart, String> EXP_DATE;
    @FXML
    private TableColumn<installedPart, String> CUST_NAME;

   
    @FXML
    private ObservableList<installedPart> selected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try {
            ObservableList<installedPart> installedPartsData = Database.getInstance().getinstalledPart();

            INST_ID.setCellValueFactory(new PropertyValueFactory<>("INST_ID"));
            PART_ID.setCellValueFactory(new PropertyValueFactory<>("PART_ID"));
            VEHICLE_ID.setCellValueFactory(new PropertyValueFactory<>("VEHICLE_ID"));
            PART_COST.setCellValueFactory(new PropertyValueFactory<>("PART_COST"));
            REG_NUM.setCellValueFactory(new PropertyValueFactory<>("REG_NUM"));
            INST_DATE.setCellValueFactory(new PropertyValueFactory<>("INST_DATE"));
            EXP_DATE.setCellValueFactory(new PropertyValueFactory<>("EXP_DATE"));
            CUST_NAME.setCellValueFactory(new PropertyValueFactory<>("CUST_NAME"));

            installedPartsTable.setItems(installedPartsData);
            
            //selected = (ObservableList) usersTable.getSelectionModel();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    } 
    
    public void trackInstalledParts()
    {
        //selected = (ObservableList) usersTable.getRowFactory();
        //System.out.println(selected);


    }
      
    
}
