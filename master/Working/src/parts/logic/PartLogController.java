/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import common.database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author sergi
 */
public class PartLogController implements Initializable {

    @FXML
    private TableView<partLog> deliveredTable;
     @FXML
    private TableColumn<partLog, String> deliveredDate;
    @FXML
    private TableColumn<partLog, String> deliveredPart;
    ///////
    @FXML
    private TableView<partLog> withdrawnTable;
    @FXML
    private TableColumn<partLog, String> withdrawnDate;
    @FXML
    private TableColumn<partLog, String> withdrawnPart;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ///
       ObservableList  <partLog> deliveredData = Database.getInstance().getWithdrawalLog();
        deliveredDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        deliveredPart.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
        deliveredTable.setItems(deliveredData);
        ///
      ObservableList  <partLog> withdrawnData = Database.getInstance().getDeliveryLog();
        withdrawnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        withdrawnPart.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
         withdrawnTable.setItems(withdrawnData);
    }    
    
}
