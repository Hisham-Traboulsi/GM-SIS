/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class ChooseCentreController implements Initializable {

    @FXML
    private TextField SPC_NAME;
    @FXML
    private TextField SPC_ADDRESS;
    @FXML
    private TextField SPC_PHONE;
    @FXML
    private TextField SPC_EMAIL;
    
        
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

   @FXML
    private TableView<SPC> SPCtable = new TableView<SPC>();
   
   @FXML
    private TableColumn <SPC, String>SPC_NAME_view;
    @FXML
    private TableColumn <SPC, String>SPC_ADDRESS_view;
    @FXML
    private TableColumn <SPC, Integer>SPC_PHONE_view;
    @FXML
    private TableColumn <SPC, String>SPC_EMAIL_view;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
