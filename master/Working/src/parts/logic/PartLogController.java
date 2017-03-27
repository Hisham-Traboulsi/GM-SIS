/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
       ObservableList  <partLog> deliveredData = Database.getInstance().getDeliveryLog();
        deliveredDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        deliveredPart.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
        deliveredTable.setItems(deliveredData);
        ///
      ObservableList  <partLog> withdrawnData = Database.getInstance().getWithdrawalLog();
        withdrawnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        withdrawnPart.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
         withdrawnTable.setItems(withdrawnData);
    }    
    public void back(ActionEvent event)
    {
        try
        {
           // addMenuBar();
            
            URL DiagnosisUrl = getClass().getResource("/parts/gui/addPart.fxml");
            AnchorPane DiagnosisPane = FXMLLoader.load(DiagnosisUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(DiagnosisPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
