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


public class AddHelpMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea helpBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        helpBox.setEditable(false);
        
        helpBox.setText("1. To add a new customer enter the data in the required \n    "
                + "fields and press the Submit button otherwise \n    "
                + "press the Clear button to clear data from the fields. \n"
                + " \n 2. To view the customers options *DOUBLE CLICK* on the \n"
                + " selected row (customer).");
        
        helpBox.wrapTextProperty();
    }    
    
}
