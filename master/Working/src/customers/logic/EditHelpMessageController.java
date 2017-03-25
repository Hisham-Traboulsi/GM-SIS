/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class EditHelpMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea helpBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        helpBox.setEditable(false);
        
        helpBox.setText("1. To edit a customer you first find your selected \n"
                + "customer either by scrolling throught the table or searching \n"
                + "for customer either by name or vehicle. Once the customer \n"
                + "row has been found double click on a cell that you want to \n"
                + "edit. \n"
                + "\n 2. Once you make the edit press the return key on the \n"
                + "on the key board ('Enter key') and click on the update button.");
    }    
    
}
