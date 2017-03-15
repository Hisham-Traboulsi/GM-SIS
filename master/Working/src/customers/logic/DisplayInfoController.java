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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class DisplayInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label customerID;
    
    @FXML
    private Label fullName;
    
    @FXML
    private Label address;
    
    @FXML
    private Label postCode;
    
    @FXML
    private Label phone;
    
    @FXML
    private Label type;
    
    @FXML
    private ComboBox vehicleReg;
    
    private Customers rowData = AddCustomerController.rowData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayInfo();
    } 
        
    public void displayInfo()
    {
        customerID.setText(Integer.toString(rowData.getID()));
        fullName.setText(rowData.getFullName());
        address.setText(rowData.getAddress());
        postCode.setText(rowData.getPostCode());
        phone.setText(rowData.getPhone());
        type.setText(rowData.getType());
     
    }
}
