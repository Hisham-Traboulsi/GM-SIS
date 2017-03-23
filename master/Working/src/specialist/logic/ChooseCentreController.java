/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

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
 * FXML Controller class
 *
 * @author Shiraj Miah
 */





public class ChooseCentreController implements Initializable {

    @FXML
    private TextField SPC_NAME;
    @FXML
    private TextField SPC_ADDRESS;
    @FXML
    private TextField SPC_PHONE;
    @FXML
    private TextField SPC_EMAIL;
    
        
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

   @FXML
    private TableView<SPC> SPCtable = new TableView<SPC>();
   
   @FXML
    private TableColumn SPC_ID_view; 
   
   @FXML
    private TableColumn<SPC, String> SPC_NAME_view;
    @FXML
    private TableColumn SPC_ADDRESS_view;
    @FXML
    private TableColumn SPC_PHONE_view;
    @FXML
    private TableColumn SPC_EMAIL_view;
    
    private ObservableList<SPC> list=FXCollections.observableArrayList();
        private ObservableList<SPC> selected = null;

    
    @FXML
   public boolean add() throws SQLException
    {
      
      boolean added=false;
      
      
            String SPCNAME = (SPC_NAME.getText());
            String SPCADDRESS = (SPC_ADDRESS.getText());
            String SPCPHONE = (SPC_PHONE.getText());
            String SPCEMAIL = (SPC_EMAIL.getText());
 
            if (!validNumber(SPCPHONE))  {
                            //JOptionPane.showMessageDialog(null, "Please enter a correct phone number");
            }        
            else{
  
      added = Database.getInstance().addSPC( SPCNAME, SPCADDRESS,
              SPCPHONE,SPCEMAIL);
      reload();
            }
      return added;
    }
   public void remove() throws SQLException
    {
        selected = SPCtable.getSelectionModel().getSelectedItems();   
        
        int ID = selected.get(0).getIDnum();
        
        Database.getInstance().removeSPC(ID);
        reload();
        
    }
   
      public void clearFields()
    {
        SPC_NAME.clear();
        SPC_ADDRESS.clear();
        SPC_PHONE.clear();
        SPC_EMAIL.clear();
        
    }
   
       public boolean validNumber(String phone)
    {
       int length = phone.length();
       if(length == 0 || length < 11)
       {
           JOptionPane.showMessageDialog(null, "Enter valid 11 digit number!");
           return false;
       }
       
       for(int i = 0; i<length; i++)
       {
           if(phone.charAt(i) < '0' || phone.charAt(i) > '9')
           {
               JOptionPane.showMessageDialog(null, "Enter valid 11 digit number");
               return false;
           }
       }
       return true;
    }
       
           public void edit() throws SQLException
    {
        Database.getInstance().editSPC();
        reload();
    }
    
   public void reload(){
       try {
            ObservableList<SPC> spcData = Database.getInstance().getSPC();

            SPCtable.setEditable(true);

            SPC_ID_view.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            SPC_NAME_view.setCellValueFactory(new PropertyValueFactory<>("SPC_NAME"));
            SPC_ADDRESS_view.setCellValueFactory(new PropertyValueFactory<>("SPC_ADDRESS"));
            SPC_PHONE_view.setCellValueFactory(new PropertyValueFactory<>("SPC_PHONE"));
            SPC_EMAIL_view.setCellValueFactory(new PropertyValueFactory<>("SPC_EMAIL"));
          

            SPCtable.setItems(spcData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
   }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ObservableList<SPC> spcData = Database.getInstance().getSPC();

            SPCtable.setEditable(true);

            SPC_ID_view.setCellValueFactory(new PropertyValueFactory<>("IDnum"));
            SPC_NAME_view.setCellValueFactory(new PropertyValueFactory<>("SPC_NAME"));
            SPC_NAME_view.setCellFactory(TextFieldTableCell.forTableColumn());
            SPC_NAME_view.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<SPC,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SPC, String> t) {
                    ((SPC) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSPC_NAME(t.getNewValue());
                }
            }
            );
            SPC_ADDRESS_view.setCellValueFactory(new PropertyValueFactory<>("SPC_ADDRESS"));
            SPC_ADDRESS_view.setCellFactory(TextFieldTableCell.forTableColumn());
            SPC_ADDRESS_view.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<SPC,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SPC, String> t) {
                    ((SPC) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSPC_ADDRESS(t.getNewValue());
                }
            }
            );
            SPC_PHONE_view.setCellValueFactory(new PropertyValueFactory<>("SPC_PHONE"));
            SPC_PHONE_view.setCellFactory(TextFieldTableCell.forTableColumn());
            SPC_PHONE_view.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<SPC,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SPC, String> t) {
                    ((SPC) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSPC_PHONE(t.getNewValue());
                }
            }
            );
            SPC_EMAIL_view.setCellValueFactory(new PropertyValueFactory<>("SPC_EMAIL"));
            SPC_EMAIL_view.setCellFactory(TextFieldTableCell.forTableColumn());
            SPC_EMAIL_view.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<SPC,String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SPC, String> t) {
                    ((SPC) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSPC_EMAIL(t.getNewValue());
                }
            }
            );

            SPCtable.setItems(spcData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    

}
