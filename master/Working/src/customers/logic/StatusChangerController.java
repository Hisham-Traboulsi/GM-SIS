/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class StatusChangerController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private RadioButton paid;
    
    @FXML
    private RadioButton unpaid;
    
    final ToggleGroup group = new ToggleGroup();
    
    private Stage stage = DisplayAllBookingsController.stage;
    
    private Accounts rowData = DisplayAllBookingsController.rowData;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paid.setToggleGroup(group);
        unpaid.setToggleGroup(group);
    }    
    
    public void editStatus()
    {
        System.out.println(rowData.getStatus());
        if(paid.isSelected() && rowData.getStatus().equals("PAID"))
        {
            JOptionPane.showMessageDialog(null, "Payment status is already set to PAID!");
            return;
        }
        else if(unpaid.isSelected() && rowData.getStatus().equals("UNPAID"))
        {
            JOptionPane.showMessageDialog(null, "Payment status is already set to UNPAID!");
            return;
        }
        else if(paid.isSelected() && rowData.getStatus().equals("UNPAID"))
        {
            Database.getInstance().editBookingStatus(rowData.getCustomerID(), rowData.getBookingID(), "PAID");
            JOptionPane.showMessageDialog(null, "Status was updated");
            stage.close();
        }
        else if(unpaid.isSelected() && rowData.getStatus().equals("PAID"))
        {
            Database.getInstance().editBookingStatus(rowData.getCustomerID(), rowData.getBookingID(), "UNPAID");
             JOptionPane.showMessageDialog(null, "Status was updated");
             stage.close();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Status wasnt updated");
            stage.close();
        }
                
    }
    
}
