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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class BookVehicleRepairController implements Initializable {

    @FXML
        ComboBox spcBox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            
        ObservableList<String> spcData = Database.getInstance().getSPCName();
            spcBox.setItems(spcData);            
            
           
            
          
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
      
    }    
    
}
