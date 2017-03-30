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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    private TableColumn<partBooking, Integer> id;
    
    @FXML
    private ComboBox <String> reg;
    @FXML
    private ObservableList<partBooking> selected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        numBox();
        
        
       
    }    
    public void showBooking()
            
           
    {  if(empty()) {
        try{
        String regC=(String) reg.getValue();
        ObservableList <partBooking> bookingData = Database.getInstance().getpartBooking(regC);
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("SurName"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookings.setItems(bookingData);
         }
      catch(NullPointerException e)
      {
         
      }
    }
    }
    public void numBox()
    {
       
        ObservableList <String> regComb1=Database.getInstance().fillNumComboBook();
        //regComb = new ComboBox();
        reg.setItems(regComb1);
        
    }
    public boolean empty(){
        
        selected = bookings.getSelectionModel().getSelectedItems();
           boolean check=true;
        
            boolean part = reg.getSelectionModel().isEmpty();
           if (part)
        {
            JOptionPane.showMessageDialog(null,"Please select a vehicle registration ");
            check=false;
        }
           
      
        return check;
    }
    public void remove() throws SQLException
    {  selected = bookings.getSelectionModel().getSelectedItems();
        if(empty())
        
        {     try{//selected = bookings.getSelectionModel().getSelectedItems();
              String name =(selected.get(0).getName());
              String surname =(selected.get(0).getSurName());
    
              JFrame frame = new JFrame();
              Object[] options = {"Yes","No"};
              int n = JOptionPane.showOptionDialog(frame,
              "Are you sure you want to delete the booking for " + name + " " + surname,
              "Delete booking",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.WARNING_MESSAGE,
              null,
              options,
              options[1]);
              if (n == JOptionPane.YES_OPTION) 
              {
                  
                      //  selected = bookings.getSelectionModel().getSelectedItems();   
                        Database.getInstance().removeBookingPart(selected.get(0).getBookID());
                         showBooking();
                    
                  
              }
              }
        catch(NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"Select a booking to remove");
        }
        }
        
        
    }
    public void back(ActionEvent event)
    {
        try
        {
           // addMenuBar();
            
            URL DiagnosisUrl = getClass().getResource("/parts/gui/addInstalledPart.fxml");
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
