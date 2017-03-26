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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class PartBookingController implements Initializable {

    @FXML
    private TableView<partBooking> bookings;
     @FXML
    private TableColumn<partBooking, String> date;
    @FXML
    private TableColumn<partBooking, String> name;
    @FXML
    private TableColumn<partBooking, String> surname;
    @FXML
    private TableColumn<partBooking, String> type;
    
    @FXML
    private ComboBox <String> reg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        numBox();
        
        
       
    }    
    public void showBooking()
            
    {   String regC=(String) reg.getValue();
        ObservableList <partBooking> bookingData = Database.getInstance().getpartBooking(regC);
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("SurName"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        bookings.setItems(bookingData);
    }
    public void numBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <String> regComb1=Database.getInstance().fillNumCombo();
        //regComb = new ComboBox();
        reg.setItems(regComb1);
        
    }
}
