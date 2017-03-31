/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.Main;
import common.database.Database;
import static customers.logic.AddCustomerController.rowData;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class DisplayAllBookingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Accounts> accountsTable = new TableView<Accounts>();
    
    @FXML
    private TableColumn customerIDCol;
    
    @FXML
    private TableColumn bookingIDCol;
    
    @FXML
    private TableColumn<Accounts, String> vehicleRegCol;
    
    @FXML
    private TableColumn<Accounts, String> bookingDateCol;
    
    @FXML
    private TableColumn<Accounts, Double> costCol;
    
    @FXML
    private TableColumn<Accounts, String> statusCol;
    
    private Customers dataRow = CustomerInfoController.rowData;
    
    protected static Accounts rowData;
    
    protected static Stage stage; 
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   
        displayData();
    }    
    
    public void displayData()
    {
        ObservableList<Accounts> data = mergeData();
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        bookingIDCol.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        vehicleRegCol.setCellValueFactory(new PropertyValueFactory<>("vehicleReg"));
        bookingDateCol.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
            accountsTable.setRowFactory(tv -> {
            TableRow<Accounts> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        rowData = row.getItem();
                        
                        stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/StatusChanger.fxml"));
                        stage.setScene(new Scene(root));
                        stage.setTitle("Change status");
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/customers/gui/help-icon.png")));
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(DisplayAllBookingsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    refresh();
                }
            });
            return row;
        });
        accountsTable.setItems(data);
    }
    
    public void refresh()
    {
        displayData();
    }
    
    private ObservableList<Accounts> mergeData()
    {
        ObservableList<Accounts> mergeData = FXCollections.observableArrayList();
        ObservableList<Accounts> customerAccount = Database.getInstance().getCustomerAccountsData(dataRow.getID());
        ObservableList<String> bookingStatus = Database.getInstance().getStatus(dataRow.getID());
        System.out.println(bookingStatus.size());
        for(int i = 0; i<customerAccount.size(); i++)
        {
            int customerID = customerAccount.get(i).getCustomerID();
            int bookingID = customerAccount.get(i).getBookingID();
            String vehicleReg = customerAccount.get(i).getVehicleReg();
            String bookingDate = customerAccount.get(i).getBookingDate();
            double cost = customerAccount.get(i).getCost();
            String status = bookingStatus.get(i);
            
            Accounts acc = new Accounts(customerID, bookingID, vehicleReg, bookingDate, cost, status);
            mergeData.add(acc);
        }
        
        return mergeData;
    }   
}
