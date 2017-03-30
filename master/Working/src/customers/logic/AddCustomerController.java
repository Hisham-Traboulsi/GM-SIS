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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    
    @FXML
    private TextField searchByName;
    
    @FXML
    private TextField searchByVehicle;
    
    final ToggleGroup group = new ToggleGroup();
    
    private static BorderPane root = new BorderPane();
    
    protected static Customers rowData;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        privateRadio.setToggleGroup(group);
        businessRadio.setToggleGroup(group);
        searchByName.setPromptText("Search By Name");
        searchByVehicle.setPromptText("Search By Vehicle");
        
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
                        Object [] options = {"Add Vehicle", "Book Appointment"};
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
                                URL bookingsUrl = getClass().getResource("/diagrep/gui/addBook.fxml");
                                AnchorPane bookingPane = FXMLLoader.load(bookingsUrl);

                                BorderPane border = Main.getRoot();

                                border.setCenter(bookingPane);
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }        
                        }
                    }
                });
                return row ;
            });
            
            FilteredList<Customers> filteredData=new FilteredList<>(customerData,e->true);
            searchByName.textProperty().addListener((observableValue,oldValue,newValue)->{
		filteredData.setPredicate((Predicate<? super Customers>)customer->{
			if(newValue==null||newValue.isEmpty()){
				return true;
                        }
			String lowerCaseFilter=newValue.toLowerCase();
			if(customer.getFirstName().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			else if(customer.getSurname().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			return false;
		});
            });
            SortedList<Customers> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(customerTable.comparatorProperty());
            customerTable.setItems(sortedData);
        }   
        catch(SQLException ex)
        {
            ex.printStackTrace();
        } 
    }  
    
    public void addCustomer()
    {
        boolean added = false;
        
        //checks on first name
        if(firstNameBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "First Name is required");
            return;
        }
        else if((firstNameBox.getText().chars().allMatch(Character::isLetter)) == false)
        {
            JOptionPane.showMessageDialog(null, "First Name must only consist of letters (no numbers and no spaces)");
            return;
        }
        
        //checks on surname
        if(surnameBox.getText().isEmpty())
        {
             JOptionPane.showMessageDialog(null, "Surname is required");
            return;
        }
        else if((surnameBox.getText().chars().allMatch(Character::isLetter)) == false)
        {
            JOptionPane.showMessageDialog(null, "First Name must only consist of letters (no numbers and no spaces)");
            return;
        }
        
        //checks on address
        if(addressBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Address is required");
            return;
        }
        
        //checks on postcode
        if(postCodeBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Post Code is required");
            return;
        }
        
        //checks on the phone number
        if(phoneBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Phone is required");
            return;
        }
        else if(!checkIsInteger(phoneBox.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please enter a correct phone number");
            return;
        }
        
        //checks on the email
        if(emailBox.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Email is required");
            return;
        }
        
        //checks on radio buttons
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
       
        added = Database.getInstance().addCustomer(firstNameBox.getText(), surnameBox.getText(),addressBox.getText(), postCodeBox.getText(), phoneBox.getText(), type, emailBox.getText());
         
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
    
    public void displayHelp() throws IOException
    {
        Stage stage = new Stage();                                
        Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/AddHelpMessage.fxml"));                                                                
        stage.setScene(new Scene(root));
        stage.setTitle("Help Message"); 
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/customers/gui/help-icon.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
   
}
