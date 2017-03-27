/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;/**
/**
 * FXML Controller class
 *
 * @author ernes
 */
public class AddMech implements Initializable {

    @FXML
    private TextField MecTxt;
    @FXML
    private TextField NameTxt;
    @FXML
    private TextField DateTxt;
    @FXML
    private TextField NumberTxt;
    @FXML
    private TextField RateTxt;
    
     @FXML
    private TableView<Mec> MecTable = new TableView<Mec>();
    
     @FXML
    private Button addButton;
     
    @FXML
    private TableColumn MechIDCol; 
    @FXML
    private TableColumn NameCol;
    @FXML
    private TableColumn StartDateCol;
    @FXML
    private TableColumn NumberCol;
    @FXML
    private TableColumn RateCol;
    
    private ObservableList<Mec> list=FXCollections.observableArrayList();
        private ObservableList<Mec> selected = null;
       
        @FXML
        public boolean add() throws SQLException
    {
      
      boolean added=false;
      
      
            
            String MechanicName = (NameTxt.getText());
            String MechanicDate = (DateTxt.getText());
            String MechanicNumber = (NumberTxt.getText());
            String MechanicRate = (RateTxt.getText());
            
 
            
  
      added = Database.getInstance().addMec( MechanicName, MechanicDate,MechanicNumber,MechanicRate);
      reload();
            
      return added;
    }
        
        public void remove() throws SQLException
    {
        selected = MecTable.getSelectionModel().getSelectedItems();   
        
        int ID = selected.get(0).getIDnum();
        
        Database.getInstance().removeMec(ID);
        reload();
        
    }
        
        public void clearFields()
    {
        NameTxt.clear();
        DateTxt.clear();
        NumberTxt.clear();
        RateTxt.clear();
        
        
        
    }
        public void edit() throws SQLException
    {
        Database.getInstance().editMec();
        reload();
    }
    
        public void reload(){
       try {
            ObservableList<Mec> MechanicData = Database.getInstance().getMec();

            MecTable.setEditable(true);

            MechIDCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_NAME"));
            StartDateCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_DATE"));
            NumberCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_NUMBER"));
            RateCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_RATE"));
            
          
            

            MecTable.setItems(MechanicData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
   }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            ObservableList<Mec> MechanicData = Database.getInstance().getMec();

            MecTable.setEditable(true);

            MechIDCol.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_NAME"));
            NameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            NameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Mec,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Mec, String> t) {
                    ((Mec) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMECHANIC_NAME(t.getNewValue());
                }
            } 
            );
            StartDateCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_DATE"));
            StartDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            StartDateCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Mec,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Mec, String> t) {
                    ((Mec) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMECHANIC_DATE(t.getNewValue());
                }
            }
            );
            NumberCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_NUMBER"));
            NumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
            NumberCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Mec,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Mec, String> t) {
                    ((Mec) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMECHANIC_NUMBER(t.getNewValue());
                }
            }
            );
            RateCol.setCellValueFactory(new PropertyValueFactory<>("MECHANIC_RATE"));
            RateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            RateCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Mec,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Mec, String> t) {
                    ((Mec) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMECHANIC_RATE(t.getNewValue());
                }
            }
            );
            
            
            
            

            MecTable.setItems(MechanicData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
    }    

}
