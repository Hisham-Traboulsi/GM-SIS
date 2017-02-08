/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class AdminController implements Initializable {

    @FXML
    private TextField FirstName_Box;
    
    @FXML
    private TextField ID_Box;
    
    @FXML
    private TextField Surname_Box;
    
    @FXML
    private PasswordField Password_Box;
    
    @FXML
    private RadioButton Admin_Radio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void addingSysUser()
    {
        boolean added = false;
        String isAdmin = "N";
        
        int id = Integer.parseInt(ID_Box.getText());
        
        if(Admin_Radio.isSelected())
        {
            isAdmin = "Y";
        }
        else
        {
            isAdmin = "N";
        }
        
        added = Database.getInstance().addSysUser(id, FirstName_Box.getText(), Surname_Box.getText(), Password_Box.getText(), isAdmin);
        
        if(added)
        {
            JOptionPane.showMessageDialog(null, "Successfuly Added");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "System user was not added");
        }
        
        clearButton();
    }
    
    public void clearButton()
    {
        ID_Box.clear();
        FirstName_Box.clear();
        Surname_Box.clear();
        Password_Box.clear();
    }
    
}
