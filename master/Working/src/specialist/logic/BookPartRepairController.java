/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import common.database.Database;
import customers.logic.Customers;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
import parts.logic.Part;


/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class BookPartRepairController implements Initializable {
    
    LocalDate delivDate = null;
    LocalDate returnDate = null;

 @FXML
    private TableView<Part> partsTable = new TableView<Part>();

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, String> partDesc;

    @FXML
    private TableColumn price;

    @FXML
    private Button bookButton;
   
    
    @FXML
        ComboBox spcBox;

    private ObservableList<Customers> selected2 = null;
    private ObservableList<Part> selected = null;
    @FXML
    private Label label;
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private DatePicker returnDatePicker;
     @FXML
    private TableView<Customers> customerTable = new TableView<Customers>();
    @FXML
    private TableColumn customerID;
    @FXML
    private TableColumn customerSurname;
    @FXML
    private TableColumn customerName;

    
    @FXML
   public boolean submit() throws SQLException
    {
      
    boolean added=false;
    try{
            selected = partsTable.getSelectionModel().getSelectedItems();   
            selected2 = customerTable.getSelectionModel().getSelectedItems(); 
            //int INSTID = Integer.parseInt(INST_ID.getText());
            String SPC = (String) spcBox.getValue();
            int PARTID = selected.get(0).getID();
            String PARTNAME = selected.get(0).getpartName();
            Double PARTCOST = selected.get(0).getCost();
            LocalDate DELIVDATE = delivDate;
            LocalDate RETURNDATE = returnDate;
            int customerID = selected2.get(0).getID();
            String customerName = selected2.get(0).getFirstName();
            String customerSurname = selected2.get(0).getSurname();
         
           added = Database.getInstance().bookSPCPart(SPC, PARTID, PARTNAME, DELIVDATE, RETURNDATE, PARTCOST, customerID, customerName, customerSurname);
    }
    catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"Complete all fields!");
        }
        
      
        return added;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
             ObservableList<Customers> customerData = Database.getInstance().getAllCustomers();
            
            customerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            customerName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            customerSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            
            customerTable.setItems(customerData);
            
            
        ObservableList<String> spcData = Database.getInstance().getSPCName();

        
            spcBox.setItems(spcData);            
            
            ObservableList<Part> partsData = Database.getInstance().getPart();

            partsTable.setEditable(true);
            
            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            partName.setCellValueFactory(new PropertyValueFactory<>("partName"));
            partDesc.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
            price.setCellValueFactory(new PropertyValueFactory<>("cost"));

            partsTable.setItems(partsData);
            
          
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
    } 
    

    @FXML
    private void getDeliveryDate(ActionEvent event) {
        delivDate = deliveryDatePicker.getValue();
        //System.out.println(delivDate);
    }

    @FXML
    private void getReturnDate(ActionEvent event) {
        returnDate = returnDatePicker.getValue();
    }
    }    
    

