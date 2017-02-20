/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.logic.SystemUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class AddCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<SystemUser> customerTable = new TableView<SystemUser>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<SystemUser, String> fullNameCol;

    @FXML
    private TableColumn<SystemUser, String> vehicleRegCol;

    @FXML
    private TableColumn<SystemUser, String> addressCol;

    @FXML
    private TableColumn<SystemUser, String> postCodeCol;
    
    @FXML
    private TableColumn<SystemUser, String> phoneCol;
    
    @FXML
    private TableColumn<SystemUser, String> emailCol;
     
    @FXML
    private TableColumn<SystemUser, String> typeCol;
      

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
