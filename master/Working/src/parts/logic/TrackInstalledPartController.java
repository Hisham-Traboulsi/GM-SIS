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
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
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
    private TableColumn <installedPart, Integer>INST_ID;
    @FXML
    private TableColumn <installedPart, Integer>PART_ID;
    @FXML
    private TableColumn <installedPart, Integer>VEHICLE_ID;
    @FXML
    private TableColumn <installedPart, Double>PART_COST;

    @FXML
    private TableColumn<installedPart, String> REG_NUM;
    @FXML
    private TableColumn<installedPart, String> INST_DATE;
    @FXML
    private TableColumn<installedPart, String> EXP_DATE;
    @FXML
    private TableColumn<installedPart, String> CUST_NAME;

    @FXML
    private TextField searchBox;
    @FXML
    private ObservableList<installedPart> selected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    
        public void updatePart() throws SQLException
    {
        Database.getInstance().editInstalledPart();
    }
    
       public void searchPart() throws SQLException
    {
       String searchVal=searchBox.getText();
       ObservableList  <installedPart> searchPartsData = Database.getInstance().searchInstalledPart(searchVal);
       
       installedPartsTable.setEditable(true);
       
        
           
            INST_ID.setCellValueFactory(new PropertyValueFactory<>("INST_ID"));
            INST_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            INST_ID.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setINST_ID(t.getNewValue());
                }
            }
            );
            
           
            PART_ID.setCellValueFactory(new PropertyValueFactory<>("PART_ID"));
            PART_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            PART_ID.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPART_ID(t.getNewValue());
                }
            }
            );
            
            VEHICLE_ID.setCellValueFactory(new PropertyValueFactory<>("VEHICLE_ID"));
            VEHICLE_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            VEHICLE_ID.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setVEHICLE_ID(t.getNewValue());
                }
            }
            );
            
            PART_COST.setCellValueFactory(new PropertyValueFactory<>("PART_COST"));
            PART_COST.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            PART_COST.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Double>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Double> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPART_COST(t.getNewValue());
                }
            }
            );
            
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
            
            INST_DATE.setCellValueFactory(new PropertyValueFactory<>("INST_DATE"));
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
            CUST_NAME.setCellValueFactory(new PropertyValueFactory<>("CUST_NAME"));
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

            
            installedPartsTable.setItems(searchPartsData);
            
            //selected = (ObservableList) usersTable.getSelectionModel();
        } 
       public void clearButton(){
          
           searchBox.clear();
           installedPartsTable.getItems().clear();
       }
   }
        
    
    
