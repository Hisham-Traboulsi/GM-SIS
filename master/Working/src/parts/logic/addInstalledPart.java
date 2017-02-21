/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*Author Sergio*/
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
public class addInstalledPart implements Initializable {
    
    
    /*
    VARIABLE FOR ADD PARTS
    */
    @FXML
    private TextField INST_ID;
    @FXML
    private TextField REG_NUM;
    @FXML
    private TextField INST_DATE;
    @FXML
    private TextField EXP_DATE;
    @FXML
    private TextField PART_ID;
    @FXML
    private TextField CUST_NAME;
    @FXML
    private TextField VEHICLE_ID;
    @FXML
    private TextField PART_COST;
;
    
    @FXML
    private Button add;
    @FXML
    private Button clearButton;
    
    /*
    VARIABLE TO TRACK INSTALLEDPARTS
    */
     /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<installedPart> installedPartsTable = new TableView<installedPart>();

    @FXML
    private TableColumn <installedPart, Integer>INST_ID_view;
    @FXML
    private TableColumn <installedPart, Integer>PART_ID_view;
    @FXML
    private TableColumn <installedPart, Integer>VEHICLE_ID_view;
    @FXML
    private TableColumn <installedPart, Double>PART_COST_view;

    @FXML
    private TableColumn<installedPart, String> REG_NUM_view;
    @FXML
    private TableColumn<installedPart, String> INST_DATE_view;
    @FXML
    private TableColumn<installedPart, String> EXP_DATE_view;
    @FXML
    private TableColumn<installedPart, String> CUST_NAME_view;

    @FXML
    private TextField searchBox;
    @FXML
    private ObservableList<installedPart> selected = null;

         
    @FXML
   public boolean add()
    {
      
      boolean added=false;
      
            int INSTID = Integer.parseInt(INST_ID.getText());
            int PARTID = Integer.parseInt(PART_ID.getText());
            int VEHICLEID = Integer.parseInt(VEHICLE_ID.getText());
            double PARTCOST = Double.parseDouble(PART_COST.getText());
            String REGNUM = (REG_NUM.getText());
            String INSTDATE = (INST_DATE.getText());
            String EXPDATE = (EXP_DATE.getText());
            String CUSTNAME = (CUST_NAME.getText());
            
  
      
      added = Database.getInstance().addInstalledPart(INSTID, REGNUM, INSTDATE,
              EXPDATE,PARTID, CUSTNAME,VEHICLEID,PARTCOST);
        
      return added;
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
       
        
           
            INST_ID_view.setCellValueFactory(new PropertyValueFactory<>("INST_ID"));
            INST_ID_view.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            INST_ID_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setINST_ID(t.getNewValue());
                }
            }
            );
            
           
            PART_ID_view.setCellValueFactory(new PropertyValueFactory<>("PART_ID"));
            PART_ID_view.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            PART_ID_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPART_ID(t.getNewValue());
                }
            }
            );
            
            VEHICLE_ID_view.setCellValueFactory(new PropertyValueFactory<>("VEHICLE_ID"));
            VEHICLE_ID_view.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            VEHICLE_ID_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setVEHICLE_ID(t.getNewValue());
                }
            }
            );
            
            PART_COST_view.setCellValueFactory(new PropertyValueFactory<>("PART_COST"));
            PART_COST_view.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            PART_COST_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Double>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Double> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPART_COST(t.getNewValue());
                }
            }
            );
            
            REG_NUM_view.setCellValueFactory(new PropertyValueFactory<>("REG_NUM"));
            REG_NUM_view.setCellFactory(TextFieldTableCell.forTableColumn());
            REG_NUM_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setREG_NUM(t.getNewValue());
                }
            }
            );
            
            INST_DATE_view.setCellValueFactory(new PropertyValueFactory<>("INST_DATE"));
            INST_DATE_view.setCellFactory(TextFieldTableCell.forTableColumn());
            INST_DATE_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setINST_DATE(t.getNewValue());
                }
            }
            );
           
            EXP_DATE_view.setCellValueFactory(new PropertyValueFactory<>("EXP_DATE"));
            EXP_DATE_view.setCellFactory(TextFieldTableCell.forTableColumn());
            EXP_DATE_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEXP_DATE(t.getNewValue());
                }
            }
            );
            CUST_NAME_view.setCellValueFactory(new PropertyValueFactory<>("CUST_NAME"));
            CUST_NAME_view.setCellFactory(TextFieldTableCell.forTableColumn());
            CUST_NAME_view.setOnEditCommit(
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
       
     
    public void clearButton()
    {
    searchBox.clear();
    installedPartsTable.getItems().clear();
    INST_ID.clear();
    PART_ID.clear();
    VEHICLE_ID.clear();
    PART_COST.clear();
    REG_NUM.clear();
    INST_DATE.clear();
    EXP_DATE.clear();
    CUST_NAME.clear();
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    } 
    
        
}

    

