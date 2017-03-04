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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class addVehicle implements Initializable {

    @FXML
    private TextField model;
    @FXML
    private TextField make;
    @FXML
    private TextField regnum;
    @FXML
    private TextField engine;
    @FXML
    private TextField fueltype;
    @FXML
    private TextField colour;
    @FXML
    private TextField mileage;
    @FXML
    private Button add;
    @FXML
    private Button back;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;
    @FXML
    private Label companyname_text;
    @FXML
    private TextField companyname;
    @FXML
    private Label companyaddress_text;
    @FXML
    private TextArea companyaddress;
    @FXML
    private Label expirydate_text;
    @FXML
    private TextField expirydate;
    @FXML
    private ToggleGroup warranty;
    @FXML
    private TextField motdate;
    @FXML
    private TextField lastservice;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        companyname_text.setVisible(false);
        companyname.setVisible(false);
        companyaddress_text.setVisible(false);
        companyaddress.setVisible(false);
        expirydate_text.setVisible(false);
        expirydate.setVisible(false);
    }

    @FXML
    private void goBack(ActionEvent e) {
        try
        {
            
            URL addPartUrl = getClass().getResource("/vehicles/gui/VehicleHomepage.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void radioButton(ActionEvent e) {
        if (e.getSource() == yes) {
            companyname_text.setVisible(true);
            companyname.setVisible(true);
            companyaddress_text.setVisible(true);
            companyaddress.setVisible(true);
            expirydate_text.setVisible(true);
            expirydate.setVisible(true);
        } else {
            companyname_text.setVisible(false);
            companyname.setVisible(false);
            companyaddress_text.setVisible(false);
            companyaddress.setVisible(false);
            expirydate_text.setVisible(false);
            expirydate.setVisible(false);
        }
    }

    public void add() {

        String vregnum = (regnum.getText());
        String vmodel = (model.getText());
        String vmake = (make.getText());
        String vengine = (engine.getText());
        String vfueltype = (fueltype.getText());
        String vcolour = (colour.getText());
        String vmotdate = (motdate.getText());
        String vlastservice = (lastservice.getText());
        String vmileage = (mileage.getText());

        String vwarranty = "";
        if (yes.isSelected()) {
            vwarranty = yes.getId();
        } else if (no.isSelected()) {
            vwarranty = no.getId();
        }

        String vwarrantycompany = (companyname.getText());
        String vwarrantyaddress = (companyaddress.getText());
        String vwarrantyexpiry = (expirydate.getText());

        Database.getInstance().addVehicle(vregnum, vmodel, vmake, vengine, vfueltype, vcolour, vmotdate, vlastservice, vmileage, vwarranty, vwarrantycompany, vwarrantyaddress, vwarrantyexpiry);
        
        try
        {
            
            URL addPartUrl = getClass().getResource("/vehicles/gui/VehicleHomepage.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    public void clearButton() {
        regnum.clear();
        model.clear();
        make.clear();
        engine.clear();
        fueltype.clear();
        colour.clear();
        motdate.clear();
        lastservice.clear();
        mileage.clear();
        companyname.clear();
        companyaddress.clear();
        expirydate.clear();
    }

}
