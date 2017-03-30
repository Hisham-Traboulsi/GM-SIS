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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    private DatePicker expirydate;
    @FXML
    private ToggleGroup warranty;
    @FXML
    private DatePicker motdate;
    @FXML
    private DatePicker lastservice;
    @FXML
    private ComboBox<String> chooseVehicle;
    @FXML
    private ComboBox IDcomb;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        
        //customerID combo box
        idcombBox();
                
                
        //adding items to choice box
        chooseVehicle.getItems().addAll(
                "Ford Focus 1.2L Petrol",
                "Ford Fiesta 1.4L Petrol",
                "Volkswagen Golf 1.6L Petrol",
                "Volkswagen Scirocco 1.4L Petrol",
                "Honda Civic 1.6L Petrol",
                "Honda Jazz 1.2L Petrol",
                "Vauxhall Corsa 1.2L Petrol",
                "Vauxhall Corsa 1.4L Petrol",
                "Vauxhall Astra 1.4L Petrol",
                "Vauxhall Astra 1.6L Petrol",
                "Toyota Yaris 1.2L Petrol",
                "Peugeot 308 1.6L Diesel",
                "Nissan Micra 1.0L Petrol",
                "Daewoo Matiz 1.0L Petrol",
                "Audi TT 1.4L Petrol",
                "Audi A3 1.4L Petrol",
                "Audi A5 1.6L Petrol",
                "Audi A6 2.0L Petrol",
                "Range Rover Evoque 2.0L Diesel",
                "Merecedes C Class 1.6L Petrol"
        );

        //Listen for selection changes
        chooseVehicle.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> chooseVehicle(newValue));

        companyname_text.setVisible(false);
        companyname.setVisible(false);
        companyaddress_text.setVisible(false);
        companyaddress.setVisible(false);
        expirydate_text.setVisible(false);
        expirydate.setVisible(false);

    }

    public void chooseVehicle(String newValue) 
    {
            if(newValue.equals("Ford Focus 1.2L Petrol"))
            {
                model.setText("Ford");
                make.setText("Focus");
                engine.setText("1.2");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Ford Fiesta 1.4L Petrol"))
            {
                model.setText("Ford");
                make.setText("Fiesta");
                engine.setText("1.4");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Volkswagen Golf 1.6L Petrol"))
            {
                model.setText("Volkswagen");
                make.setText("Golf");
                engine.setText("1.6");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Volkswagen Scirocco 1.4L Petrol"))
            {
                model.setText("Volkswagen");
                make.setText("Scirocco");
                engine.setText("1.4");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Honda Civic 1.6L Petrol"))
            {
                model.setText("Honda");
                make.setText("Civic");
                engine.setText("1.6");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Honda Jazz 1.2L Petrol"))
            {
                model.setText("Honda");
                make.setText("Jazz");
                engine.setText("1.2");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Vauxhall Corsa 1.2L Petrol"))
            {
                model.setText("Vauxhall");
                make.setText("Corsa");
                engine.setText("1.2");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Vauxhall Corsa 1.4L Petrol"))
            {
                model.setText("Vauxhall");
                make.setText("Corsa");
                engine.setText("1.4");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Vauxhall Astra 1.4L Petrol"))
            {
                model.setText("Vauxhall");
                make.setText("Astra");
                engine.setText("1.4");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Vauxhall Astra 1.6L Petrol"))
            {
                model.setText("Vauxhall");
                make.setText("Astra");
                engine.setText("1.6");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Toyota Yaris 1.2L Petrol"))
            {
                model.setText("Toyota");
                make.setText("Yaris");
                engine.setText("1.2");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Peugeot 308 1.6L Diesel"))
            {
                model.setText("Peugeot");
                make.setText("308");
                engine.setText("1.6");
                fueltype.setText("Diesel");
            }else if(newValue.equals("Nissan Micra 1.0L Petrol"))
            {
                model.setText("Nissan");
                make.setText("Micra");
                engine.setText("1.0");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Daewoo Matiz 1.0L Petrol"))
            {
                model.setText("Daewoo");
                make.setText("Matiz");
                engine.setText("1.0");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Audi TT 1.4L Petrol"))
            {
                model.setText("Audi");
                make.setText("TT");
                engine.setText("1.4");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Audi A3 1.4L Petrol"))
            {
                model.setText("Audi");
                make.setText("A3");
                engine.setText("1.4");
                fueltype.setText("Petrol");
            }
            else if(newValue.equals("Audi A5 1.6L Petrol"))
            {
                model.setText("Audi");
                make.setText("A5");
                engine.setText("1.6");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Audi A6 2.0L Petrol"))
            {
                model.setText("Audi");
                make.setText("A6");
                engine.setText("2.0");
                fueltype.setText("Petrol");
            }else if(newValue.equals("Range Rover Evoque 2.0L Diesel"))
            {
                model.setText("Range Rover");
                make.setText("Evoque");
                engine.setText("2.0");
                fueltype.setText("Diesel");
            }else if(newValue.equals("Merecedes C Class 1.6L Petrol"))
            {
                model.setText("Mercedes");
                make.setText("C Class");
                engine.setText("1.6");
                fueltype.setText("Petrol");
            }
    }
    
    @FXML
    private void goBack(ActionEvent e) {
        try {

            URL addPartUrl = getClass().getResource("/vehicles/gui/VehicleHomepage.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);

            BorderPane border = Main.getRoot();

            border.setCenter(addPartPane);
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
    
    public void idcombBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <Integer> idComb1 =Database.getInstance().fillIDcombo();
        //regComb = new ComboBox();
        IDcomb.setItems(idComb1);
        
    }

    @FXML
    public void add() {
        
        int vID = (int) IDcomb.getValue();
        String vregnum = (regnum.getText());
        String vmodel = (model.getText());
        String vmake = (make.getText());
        String vengine = (engine.getText());
        String vfueltype = (fueltype.getText());
        String vcolour = (colour.getText());
        String vmotdate = motdate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String vlastservice = lastservice.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String vmileage = (mileage.getText());

        String vwarranty = "";
        String vwarrantycompany = "N/A";
        String vwarrantyaddress = "N/A";
        String vwarrantyexpiry = "N/A";
        
        if (yes.isSelected()) {
            vwarranty = yes.getId();
            vwarrantycompany = (companyname.getText());
            vwarrantyaddress = (companyaddress.getText());
            vwarrantyexpiry = expirydate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else if (no.isSelected()) {
            vwarranty = no.getId();
        }
        

        Database.getInstance().addVehicle(vID ,vregnum, vmodel, vmake, vengine, vfueltype, vcolour, vmotdate, vlastservice, vmileage, vwarranty, vwarrantycompany, vwarrantyaddress, vwarrantyexpiry);

        try {

            URL addPartUrl = getClass().getResource("/vehicles/gui/VehicleHomepage.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);

            BorderPane border = Main.getRoot();

            border.setCenter(addPartPane);
        } catch (IOException ex) {
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
        motdate.setValue(null);
        lastservice.setValue(null);
        mileage.clear();
        companyname.clear();
        companyaddress.clear();
        expirydate.setValue(null);
    }

}
