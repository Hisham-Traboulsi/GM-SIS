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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class addPart implements Initializable {
    /*
    
    ADD METHOD VARIABLES
    */
    
    
    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextArea partDesc;
    @FXML
    private TextField partAmount;
    @FXML
    private TextField partCost;
    
    /*
    
    TABLE VIEW VARIABLES
    */
         
    @FXML
    private TableView<Part> partsTable = new TableView<Part>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, String> partDescCol;

    @FXML
    private TableColumn amountCol;

    @FXML
    private TableColumn costCol;
   
    @FXML
   public boolean add()
    {
      partsTable.getItems().clear();
      boolean added=false;
      
      int id = Integer.parseInt(partID.getText());
      String name = (partName.getText());
      String desc = (partDesc.getText());
      int amount = Integer.parseInt(partAmount.getText());
      double cost = Double.parseDouble(partCost.getText());
  
      
      added = Database.getInstance().addPart(id,name,desc,amount,cost);
        
      return added;
      
    }
   
    public void clearButton()
    {
    partID.clear();
    partName.clear();
    partDesc.clear();
    partAmount.clear();
    partCost.clear();
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      try {
            ObservableList<Part> partsData = Database.getInstance().getPart();

            partsTable.setEditable(true);
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
            partDescCol.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            amountCol.setOnEditCommit(
                    new EventHandler<CellEditEvent<Part,Integer>>() {
                @Override
                public void handle(CellEditEvent<Part, Integer> t) {
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
   public void updatePart() throws SQLException
    {   //int id=Integer.parseInt(partID.getText());
        //int amount=Integer.parseInt(partAmount.getText());
    
        Database.getInstance().editPart();
    }
    
   
    
        
}

    

