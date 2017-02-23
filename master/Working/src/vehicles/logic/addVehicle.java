/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.logic;

import common.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    private DatePicker motrenewal;
    @FXML
    private DatePicker servicedate;
    @FXML
    private Button add;
    @FXML
    private Button back;
    @FXML
    private Label ourLabel;
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
    private DatePicker expirydate;

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
    private void changeLabel(ActionEvent e) {
        System.out.println("Hello World!");
        ourLabel.setText("Hello World!");
    }

    @FXML
    private void goBack(ActionEvent e) {
        try {

            URL addInstalledPartUrl = getClass().getResource("/common/gui/Welcome.fxml");
            AnchorPane addInstalledPartPane = FXMLLoader.load(addInstalledPartUrl);

            BorderPane border = Main.getRoot();

            border.setCenter(addInstalledPartPane);
        } catch (IOException ex) {
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

}
