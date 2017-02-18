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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
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
            installedPartsTable.setEditable(true);

            INST_ID.setCellValueFactory(new PropertyValueFactory<>("INST_ID"));
            PART_ID.setCellValueFactory(new PropertyValueFactory<>("PART_ID"));
            VEHICLE_ID.setCellValueFactory(new PropertyValueFactory<>("VEHICLE_ID"));
            PART_COST.setCellValueFactory(new PropertyValueFactory<>("PART_COST"));
            
            REG_NUM.setCellValueFactory(new PropertyValueFactory<>("REG_NUM"));
            REG_NUM.setCellFactory(TextFieldTableCell.forTableColumn());
            REG_NUM.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setREG_NUM(t.getNewValue());
                }
            }
            );
            
            INST_DATE.setCellValueFactory(new PropertyValueFactory<>("INSTALLATION_DATE"));
            INST_DATE.setCellFactory(TextFieldTableCell.forTableColumn());
            INST_DATE.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setINST_DATE(t.getNewValue());
                }
            }
            );
           
            EXP_DATE.setCellValueFactory(new PropertyValueFactory<>("EXP_DATE"));
            EXP_DATE.setCellFactory(TextFieldTableCell.forTableColumn());
            EXP_DATE.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEXP_DATE(t.getNewValue());
                }
            }
            );
            CUST_NAME.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_FULLNAME"));
            CUST_NAME.setCellFactory(TextFieldTableCell.forTableColumn());
            CUST_NAME.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCUST_NAME(t.getNewValue());
                }
            }
            );

            installedPartsTable.setItems(installedPartsData);
            
            //selected = (ObservableList) usersTable.getSelectionModel();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    } 
    
    
        public void updatePart() throws SQLException
    {
        Database.getInstance().editInstalledPart();
    }
    
      
    
}
