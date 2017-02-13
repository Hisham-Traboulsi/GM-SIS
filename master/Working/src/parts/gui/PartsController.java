/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*Author Sergio*/
package parts.gui;

import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import parts.logic.Part;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class PartsController implements Initializable {
    
    ObservableList partData = FXCollections.observableArrayList();
    
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
    @FXML
    private TextField searchBox;
    @FXML
    private Button search;
     @FXML
    private Button update;
     @FXML
    private Button delete;
     @FXML
    private Button clearButton;
     @FXML
    private Button add;
    @FXML
    private TableView partsTable;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn <Part,String> colName;
    @FXML
    private TableColumn <Part,String>colDesc;
    @FXML
    private TableColumn <Part,Integer>colAmount;
    @FXML
    private TableColumn <Part,Double> colCost;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            
        partData = Database.getInstance().getPart();
        
     colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
     colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
     colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
     colCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        
     partsTable.setItems(partData);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    } 
    
    @FXML
   public boolean add()
    {
      
      boolean added=false;
      
      int id = Integer.parseInt(partID.getText());
      String name = (partName.getText());
      String desc = (partDesc.getText());
      int amount = Integer.parseInt(partAmount.getText());
      int cost = Integer.parseInt(partCost.getText());
  
      
      added = Database.getInstance().addPart(id,name,desc,amount,cost);
        
      return added;
    }

    /**
     *
     */
    public void clearButton()
    {
    partID.clear();
    partName.clear();
    partDesc.clear();
    partAmount.clear();
    partCost.clear();
    searchBox.clear();
    }
        
}

    

