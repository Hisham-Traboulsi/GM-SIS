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
import vehicles.logic.Vehicle;

/**
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
    private TableColumn<SPC, String> SPC_NAME_view;
    @FXML
    private TableColumn SPC_ADDRESS_view;
    @FXML
    private TableColumn SPC_PHONE_view;
    @FXML
    private TableColumn SPC_EMAIL_view;
    
    private ObservableList<SPC> list=FXCollections.observableArrayList();
    
    @FXML
   public boolean add() throws SQLException
    {
      
      boolean added=false;
      
            //int INSTID = Integer.parseInt(INST_ID.getText());
            String SPCNAME = (SPC_NAME.getText());
            String SPCADDRESS = (SPC_ADDRESS.getText());
            int SPCPHONE = Integer.parseInt(SPC_PHONE.getText());
            String SPCEMAIL = (SPC_EMAIL.getText());
            
  
      added = Database.getInstance().addSPC( SPCNAME, SPCADDRESS,
              SPCPHONE,SPCEMAIL);
      return added;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ObservableList<SPC> spcData = Database.getInstance().getSPC();

            SPCtable.setEditable(true);

            
            SPC_NAME_view.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            SPC_ADDRESS_view.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
            SPC_PHONE_view.setCellValueFactory(new PropertyValueFactory<>("PHONE"));
            SPC_EMAIL_view.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
          

            SPCtable.setItems(spcData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    

}
