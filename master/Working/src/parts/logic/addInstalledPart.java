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
public class addInstalledPart implements Initializable {
    
    
    
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
   
     
    public void clearButton()
    {
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

    

