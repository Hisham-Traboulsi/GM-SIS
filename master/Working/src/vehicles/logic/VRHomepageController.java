/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.IntegerStringConverter;
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
    private TableColumn warrantyCol;
    @FXML
    private TableColumn warrantycompanyCol;
    @FXML
    private TableColumn warrantyaddressCol;
    @FXML
    private TableColumn warrantyexpiryCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            
            ObservableList<Vehicle> vehicleData = Database.getInstance().getVehicle();

            vehicleTable.setEditable(true);

            regnumCol.setCellValueFactory(new PropertyValueFactory<>("regnum"));
            modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
            makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
            engineCol.setCellValueFactory(new PropertyValueFactory<>("engine"));
            fueltypeCol.setCellValueFactory(new PropertyValueFactory<>("fueltype"));
            colourCol.setCellValueFactory(new PropertyValueFactory<>("colour"));
            motdateCol.setCellValueFactory(new PropertyValueFactory<>("motdate"));
            lastserviceCol.setCellValueFactory(new PropertyValueFactory<>("lastservice"));
            mileageCol.setCellValueFactory(new PropertyValueFactory<>("mileage"));
            warrantyCol.setCellValueFactory(new PropertyValueFactory<>("warranty"));
            warrantycompanyCol.setCellValueFactory(new PropertyValueFactory<>("warrantycompany"));
            warrantyaddressCol.setCellValueFactory(new PropertyValueFactory<>("warrantyaddress"));
            warrantyexpiryCol.setCellValueFactory(new PropertyValueFactory<>("warrantyexpiry"));

            vehicleTable.setItems(vehicleData);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
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
}
