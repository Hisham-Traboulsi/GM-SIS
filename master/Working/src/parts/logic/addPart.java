    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*Author Sergio*/
package parts.logic;

import common.database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author sergi
 */
public class addPart implements Initializable {
    
    
    
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
    private Button add;
         
    @FXML
   public boolean add()
    {
      
      boolean added=false;
      
      int id = Integer.parseInt(partID.getText());
      String name = (partName.getText());
      String desc = (partDesc.getText());
      int amount = Integer.parseInt(partAmount.getText());
      double cost = Double.parseDouble(partCost.getText());
  
      
      added = Database.getInstance().addPart(id,name,desc,amount,cost);
        
      return added;
    }
   
   /*@FXML
   public boolean delete()
    {
      
      boolean deleted=false;
      
      int id = Integer.parseInt(partID.getText());
      String name = (partName.getText());
      String desc = (partDesc.getText());
      int amount = Integer.parseInt(partAmount.getText());
      double cost = Double.parseDouble(partCost.getText());
  
      
      deleted= Database.getInstance().deletePart(id,name,desc,amount,cost);
        
      return deleted;
    }*/
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
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    } 
    
        
}

    

