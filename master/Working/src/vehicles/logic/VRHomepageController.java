/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.logic;


import common.Main;
import common.database.Database;
import customers.logic.AddCustomerController;
import customers.logic.Customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
import parts.logic.Part;

/**
 * FXML Controller class
 *
 * @author ugonw
 */
public class VRHomepageController implements Initializable {

    /*
    
    TABLE VIEW VARIABLES
     */
    @FXML
    private TableView<Vehicle> vehicleTable = new TableView<Vehicle>();
    @FXML
    private TableColumn regnumCol;
    @FXML
    private TableColumn modelCol;
    @FXML
    private TableColumn makeCol;
    @FXML
    private TableColumn engineCol;
    @FXML
    private TableColumn fueltypeCol;
    @FXML
    private TableColumn colourCol;
    @FXML
    private TableColumn motdateCol;
    @FXML
    private TableColumn lastserviceCol;
    @FXML
    private TableColumn mileageCol;
    @FXML
    private TableColumn warrantycompanyCol;
    @FXML
    private TableColumn warrantyaddressCol;
    @FXML
    private TableColumn warrantyexpiryCol;
    @FXML
    private TextField searchField;

    final ObservableList<Vehicle> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn customeridCol;
    
    private ObservableList<Vehicle> selected = null;

    protected static Vehicle rowData;
    @FXML
    private Button viewInfo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadTable();
                
        searchField.setPromptText("Search...");
        
        
        
    }
    
    @FXML
    public void searchVehicle() 
    {
        
       String searchVal = searchField.getText();
       ObservableList  <Vehicle> searchVehicleData = Database.getInstance().searchVehicle(searchVal);
       
       vehicleTable.setEditable(true);

            regnumCol.setCellValueFactory(new PropertyValueFactory<>("regnum"));
            regnumCol.setCellFactory(TextFieldTableCell.forTableColumn());
            regnumCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setRegNum(t.getNewValue());
                }
            }
            );
            
            modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
            modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
            modelCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setModel(t.getNewValue());
                }
            }
            );
            
            makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
            makeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            makeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMake(t.getNewValue());
                }
            }
            );
            
            engineCol.setCellValueFactory(new PropertyValueFactory<>("engine"));
            engineCol.setCellFactory(TextFieldTableCell.forTableColumn());
            engineCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEngine(t.getNewValue());
                }
            }
            );
            
            fueltypeCol.setCellValueFactory(new PropertyValueFactory<>("fueltype"));
            fueltypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            fueltypeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFuelType(t.getNewValue());
                }
            }
            );
            
            colourCol.setCellValueFactory(new PropertyValueFactory<>("colour"));
            colourCol.setCellFactory(TextFieldTableCell.forTableColumn());
            colourCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setColour(t.getNewValue());
                }
            }
            );
            
            motdateCol.setCellValueFactory(new PropertyValueFactory<>("motdate"));
            motdateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            motdateCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMotDate(t.getNewValue());
                }
            }
            );
            
            lastserviceCol.setCellValueFactory(new PropertyValueFactory<>("lastservice"));
            lastserviceCol.setCellFactory(TextFieldTableCell.forTableColumn());
            lastserviceCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setLastService(t.getNewValue());
                }
            }
            );
            
            mileageCol.setCellValueFactory(new PropertyValueFactory<>("mileage"));
            mileageCol.setCellFactory(TextFieldTableCell.forTableColumn());
            mileageCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMileage(t.getNewValue());
                }
            }
            );
            
            warrantycompanyCol.setCellValueFactory(new PropertyValueFactory<>("warrantycompany"));
            warrantycompanyCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warrantycompanyCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWarrantyCompany(t.getNewValue());
                }
            }
            );
            
            warrantyaddressCol.setCellValueFactory(new PropertyValueFactory<>("warrantyaddress"));
            warrantyaddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warrantyaddressCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWarrantyAddress(t.getNewValue());
                }
            }
            );
            
            warrantyexpiryCol.setCellValueFactory(new PropertyValueFactory<>("warrantyexpiry"));
            warrantyexpiryCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warrantyexpiryCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWarrantyExpiry(t.getNewValue());
                }
            }
            ); 
            
            customeridCol.setCellValueFactory(new PropertyValueFactory<>("id"));
     
            vehicleTable.setItems(searchVehicleData);
            
            vehicleTable.setRowFactory( tv -> {
                TableRow<Vehicle> row = new TableRow<>();
                viewInfo.setOnMouseClicked(MouseEvent -> {
                        rowData = row.getItem();
                        Object [] options = {"Display Info", "Past & Future Bookings"};
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
                        else if(selection == 1)
                        {
                            try {                                
                                Stage stage = new Stage();                                
                                Parent root = FXMLLoader.load(getClass().getResource("/customers/gui/DisplayAllBookings.fxml"));                                                                
                                stage.setScene(new Scene(root));
                                stage.setTitle("Past & Future Bookings");                                
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                            }         
                        }
                });
                
                        return row ;
                     
            });
    }
    
    @FXML
    public void addVehicle()
    {
        try {

            URL addInstalledPartUrl = getClass().getResource("/vehicles/gui/AddVehicle.fxml");
            AnchorPane addInstalledPartPane = FXMLLoader.load(addInstalledPartUrl);

            BorderPane border = Main.getRoot();

            border.setCenter(addInstalledPartPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void goBack()
    {
        try {

            URL addInstalledPartUrl = getClass().getResource("/common/gui/Welcome.fxml");
            AnchorPane addInstalledPartPane = FXMLLoader.load(addInstalledPartUrl);

            BorderPane border = Main.getRoot();

            border.setCenter(addInstalledPartPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadTable()
    {
        // Table
        try {

            ObservableList<Vehicle> vehicleData = Database.getInstance().getVehicle();

            vehicleTable.setEditable(true);

            regnumCol.setCellValueFactory(new PropertyValueFactory<>("regnum"));
            regnumCol.setCellFactory(TextFieldTableCell.forTableColumn());
            regnumCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setRegNum(t.getNewValue());
                }
            }
            );
            
            modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
            modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
            modelCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setModel(t.getNewValue());
                }
            }
            );
            
            makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
            makeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            makeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMake(t.getNewValue());
                }
            }
            );
            
            engineCol.setCellValueFactory(new PropertyValueFactory<>("engine"));
            engineCol.setCellFactory(TextFieldTableCell.forTableColumn());
            engineCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEngine(t.getNewValue());
                }
            }
            );
            
            fueltypeCol.setCellValueFactory(new PropertyValueFactory<>("fueltype"));
            fueltypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            fueltypeCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFuelType(t.getNewValue());
                }
            }
            );
            
            colourCol.setCellValueFactory(new PropertyValueFactory<>("colour"));
            colourCol.setCellFactory(TextFieldTableCell.forTableColumn());
            colourCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setColour(t.getNewValue());
                }
            }
            );
            
            motdateCol.setCellValueFactory(new PropertyValueFactory<>("motdate"));
            motdateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            motdateCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMotDate(t.getNewValue());
                }
            }
            );
            
            lastserviceCol.setCellValueFactory(new PropertyValueFactory<>("lastservice"));
            lastserviceCol.setCellFactory(TextFieldTableCell.forTableColumn());
            lastserviceCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setLastService(t.getNewValue());
                }
            }
            );
            
            mileageCol.setCellValueFactory(new PropertyValueFactory<>("mileage"));
            mileageCol.setCellFactory(TextFieldTableCell.forTableColumn());
            mileageCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMileage(t.getNewValue());
                }
            }
            );
            
            warrantycompanyCol.setCellValueFactory(new PropertyValueFactory<>("warrantycompany"));
            warrantycompanyCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warrantycompanyCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWarrantyCompany(t.getNewValue());
                }
            }
            );
            
            warrantyaddressCol.setCellValueFactory(new PropertyValueFactory<>("warrantyaddress"));
            warrantyaddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warrantyaddressCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWarrantyAddress(t.getNewValue());
                }
            }
            );
            
            warrantyexpiryCol.setCellValueFactory(new PropertyValueFactory<>("warrantyexpiry"));
            warrantyexpiryCol.setCellFactory(TextFieldTableCell.forTableColumn());
            warrantyexpiryCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Vehicle, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Vehicle, String> t) {
                    ((Vehicle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWarrantyExpiry(t.getNewValue());
                }
            }
            ); 
            
            customeridCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            vehicleTable.setItems(vehicleData);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addVehicle(ActionEvent e) {
        try {

            URL addInstalledPartUrl = getClass().getResource("/common/gui/AddVehicle.fxml");
            AnchorPane addInstalledPartPane = FXMLLoader.load(addInstalledPartUrl);

            BorderPane border = Main.getRoot();

            border.setCenter(addInstalledPartPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void update() throws SQLException
    {
        Database.getInstance().editVehicle();
        loadTable();
    }
    
    @FXML
    public void refresh()
    {
        loadTable();
    }
    
    @FXML
    public void help()
    {
        JOptionPane.showMessageDialog(null,
                "<html>TO ADD A VEHICLE :<br />SELECT 'ADD' BUTTON AND FILL FORM<br/><br/> "
                    + "TO DELETE A VEHICLE :<br/> SELECT VEHICLE THEN REMOVE BUTTON <br/><br/>"
                    + "TO SEARCH :<br/> ENTER VALUE INTO SEARCH FIELD AND PRESS SEARCH BUTTON<br/><br/><html>"
                    + "TO EDIT VEHICLE :<br/> DOUBLE CLICK FIELD IN TABLE, EDIT AND PRESS UPDATE BUTTON <br/><br/><html>");
        
    }
    
    @FXML
    public void remove() 
    {
        selected = vehicleTable.getSelectionModel().getSelectedItems();   
        Database.getInstance().removeVehicle(selected.get(0).getID());
        loadTable();
    }
}
