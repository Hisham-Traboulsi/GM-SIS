/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.gui;

import common.database.Database;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class PartsController implements Initializable {
    
    ObservableList data = FXCollections.observableArrayList();
    
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
    private Button search;
     @FXML
    private Button update;
     @FXML
    private Button delete;
     @FXML
    private Button add;
    @FXML
    private TableView table;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDesc;
    @FXML
    private TableColumn colAmount;
    @FXML
    private TableColumn colCost;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
     colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
     colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
     colCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        
        
    } 
    
    @FXML
   public boolean add()
    {
      
      boolean allow_access=false;
      
      int id = Integer.parseInt(partID.getText());
      String name = (partName.getText());
      String desc = (partDesc.getText());
      int amount = Integer.parseInt(partAmount.getText());
      int cost = Integer.parseInt(partCost.getText());
  
      
      allow_access = Database.getInstance().addPart(id,name,desc,amount,cost);
        
      return allow_access;
    }
        
}

    

