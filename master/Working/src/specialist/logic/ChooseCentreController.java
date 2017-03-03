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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import parts.logic.Part;



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
    private TableColumn <SPC, String>SPC_NAME_view;
    @FXML
    private TableColumn <SPC, String>SPC_ADDRESS_view;
    @FXML
    private TableColumn <SPC, Integer>SPC_PHONE_view;
    @FXML
    private TableColumn <SPC, String>SPC_EMAIL_view;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<SPC> spcData = Database.getInstance().getSPC();

            SPCtable.setEditable(true);
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
            partDescCol.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            amountCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Part,Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Part, Integer> t) {
                    ((Part) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAmount(t.getNewValue());
                    }
            }
            );
            
            costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

            partsTable.setItems(partsData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    
    
}
