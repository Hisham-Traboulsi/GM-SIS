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
public class RemoveHelpMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea helpBox;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        helpBox.setEditable(false);
        
        helpBox.setText("1. To remove a customer first search for the customer \n"
                + "either throught by scrolling or using the search bars. \n"
                + "\n 2. Select the customer row in order to hightlight the \n"
                + "customer  you want to remove. \n"
                + "\n 3. Click the remove button to remove the customer.");
    }    
    
}
