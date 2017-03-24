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
    }    
    
}
