/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void clearButton()
    {
        ID_Box.clear();
        FirstName_Box.clear();
        Surname_Box.clear();
        Password_Box.clear();
    }
    
}
