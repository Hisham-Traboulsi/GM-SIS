/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AddCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Customers> customerTable = new TableView<Customers>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<Customers, String> firstNameCol;
    
    @FXML
    private TableColumn<Customers, String> surnameCol;

    @FXML
    private TableColumn<Customers, String> addressCol;

    @FXML
    private TableColumn<Customers, String> postCodeCol;
    
    @FXML
    private TableColumn<Customers, String> phoneCol;
    
    @FXML
    private TableColumn<Customers, String> emailCol;
     
    @FXML
    private TableColumn<Customers, String> typeCol;
    
    @FXML
    private TextField firstNameBox;
    
    @FXML
    private TextField surnameBox;
    
    @FXML
    private TextField addressBox;
    
    @FXML
    private TextField postCodeBox;
    
    @FXML
    private TextField phoneBox;
    
    @FXML
    private TextField emailBox;
    
    @FXML
    private RadioButton privateRadio;
    
    @FXML
    private RadioButton businessRadio;
    
    final ToggleGroup group = new ToggleGroup();
    
    private static BorderPane root = new BorderPane();
    
    protected static Customers rowData;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        privateRadio.setToggleGroup(group);
        businessRadio.setToggleGroup(group);
        
        try
        {
            ObservableList<Customers> customerData = Database.getInstance().getAllCustomers();
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postCodeCol.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            
            customerTable.setRowFactory( tv -> {
                TableRow<Customers> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        rowData = row.getItem();
                        Object [] options = {"Add Vehicle", "Book Appointment", "Display Info"};
                        int selection = JOptionPane.showOptionDialog(null,
                        "Would you like to",
                        "Customers Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        null); 
                        
                        System.out.println(selection);
                        if(selection == 0)
                        {
                            try {
                                URL addVehiclesUrl = getClass().getResource("/vehicles/gui/AddVehicle.fxml");
                                AnchorPane addVehiclesPane = FXMLLoader.load(addVehiclesUrl);

                                BorderPane border = Main.getRoot();

                                border.setCenter(addVehiclesPane);
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(selection == 1)
                        {
                            try {
                                URL bookingsUrl = getClass().getResource("/diagrep/gui/BookingRepairs.fxml");
                                AnchorPane bookingPane = FXMLLoader.load(bookingsUrl);

                                BorderPane border = Main.getRoot();

                                border.setCenter(bookingPane);
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }        
                        }
                        else if(selection == 2)
                        {                   
                            try {                                
                                Stage stage = new Stage();                                
                                Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/DisplayInfo.fxml"));                                                                
                                stage.setScene(new Scene(root));
                                stage.setTitle("Customer Info");                                
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }                           
                        }
                    }
                });
                return row ;
            });
            
            customerTable.setItems(customerData);
        }   
        catch(SQLException ex)
        {
            ex.printStackTrace();
        } 
    }  
    
    public void addCustomer()
    {
        boolean added = false;
        
        String type = "";
        
        if(privateRadio.isSelected())
        {
            type = "P";
        }
        else if(businessRadio.isSelected())
        {
            type = "B";
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select a type");
            return;
        }
       
        if(firstNameBox.getText().isEmpty() ||surnameBox.getText().isEmpty() || addressBox.getText().isEmpty() || postCodeBox.getText().isEmpty() || phoneBox.getText().isEmpty() || emailBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields are required: \n Full Name\n Address\n Post Code\n Phone\n Email");
            return;
        }
        else if((firstNameBox.getText().chars().allMatch(Character::isLetter)) == false || surnameBox.getText().chars().allMatch(Character::isLetter) == false)
        {
            JOptionPane.showMessageDialog(null, "First Name and Surname must only consist of letters");
            return;
        }
        else if(!checkIsInteger(phoneBox.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please enter a correct phone number");
            return;
        }
        else
        {
            added = Database.getInstance().addCustomer(firstNameBox.getText(), surnameBox.getText(),addressBox.getText(), postCodeBox.getText(), phoneBox.getText(), type, emailBox.getText());
        }
        
        if(added)
        {
            JOptionPane.showMessageDialog(null, "Customer was added");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Customer was not added");
        }
        refresh();
        clear();
    }
    
    public void refresh()
    {
        try
        {            
            URL addUserUrl = getClass().getResource("/customers/gui/AddCustomer.fxml");
            AnchorPane addUserPane = FXMLLoader.load(addUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addUserPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void clear()
    {
        firstNameBox.clear();
        surnameBox.clear();
        addressBox.clear();
        postCodeBox.clear();
        phoneBox.clear();
        emailBox.clear();
        
        privateRadio.setSelected(false);
        businessRadio.setSelected(false);
    }
    
    public boolean checkIsInteger(String number)
    {
       int length = number.length();
       if(length == 0 || length < 11)
       {
           JOptionPane.showMessageDialog(null, "The number's length you entered was less than 11 digits or you entered no digits at all");
           return false;
       }
       
       for(int i = 0; i<length; i++)
       {
           if(number.charAt(i) < '0' || number.charAt(i) > '9')
           {
               JOptionPane.showMessageDialog(null, "Please enter a number WITHOUT any letters");
               return false;
           }
       }
       return true;
    }
    
    public boolean isValiEmail(String email)
    {
        if(!email.contains("@"))
        {
            JOptionPane.showMessageDialog(null, "This is not a valid email");
            return false;
        }
        return true;
    }
   
}
