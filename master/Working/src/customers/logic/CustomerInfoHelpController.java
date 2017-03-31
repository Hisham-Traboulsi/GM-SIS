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
public class CustomerInfoHelpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea helpBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        helpBox.setEditable(false);
        
        helpBox.setText("1. To use a customer functions search for a customer \n"
                + "either by name or by vehicle. \n"
                + "\n 2. Double click on a selected row to display options \n"
                + "then select your option.\n"
                + "\n 3. To search by vehicle you must enter the full vehicle \n"
                + "registration number to view its related data");
    }    
    
}
