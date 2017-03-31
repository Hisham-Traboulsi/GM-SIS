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
public class DisplayBookingsHelpMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea helpBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        helpBox.setEditable(false);
        
        helpBox.setText("1. Click once on a selected row to view list of parts \n"
                + "used in a single booking. Tese can be viewed in the combo \n"
                + "box after clicking once on a row. \n"
                + "To change the status of a bookings cost, double click on a \n"
                + "selected row.");
        
        helpBox.wrapTextProperty();
    }    
    
}
