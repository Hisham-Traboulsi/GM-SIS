/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ernes
 */
public class MechanicID implements Initializable {

     @FXML
    private TextField MechanicTxt;
    
    @FXML
    private TextField NameTxt;
    
    @FXML
    private TextField RateTxt;
    
    @FXML
    private TextField NumberTxt;
    
    @FXML
    private TextField StartDatetxt;

    @FXML
    private TableView<Mec> MechanicTable = new TableView<Mec>();
    
    @FXML
    private TableColumn<Mec, Integer> MechanicCol;
    
    @FXML
    private TableColumn<Mec, String> NameCol;

    @FXML
    private TableColumn<Mec, String> StartDateCol;

    @FXML
    private TableColumn<Mec, Integer> HourlyRateCol;
    
    @FXML
    private TableColumn<Mec, Integer> NumberCol;
    
    @FXML
    private ObservableList<Mec> list=FXCollections.observableArrayList();
    
   public boolean addMechanic()
    {
      MechanicTable.getItems().clear();
      boolean added=false;
      
      int Mechanic = Integer.parseInt(MechanicTxt.getText());
      String Name = (NameTxt.getText());
      int HourlyRate = Integer.parseInt(RateTxt.getText());
      int Number = Integer.parseInt(NumberTxt.getText());
      String Date = (StartDatetxt.getText());
      
      added = Database.getInstance().addMec(Mechanic, Name, HourlyRate, Number, Date);
      refresh();
 
      return added;
    }
   
   public void refresh()
    {
        try
        {            
            URL MecUrl = getClass().getResource("/diagrep/gui/MechanicID.fxml");
            AnchorPane MecPane = FXMLLoader.load(MecUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(MecPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
            ObservableList<Mec> MecData = Database.getInstance().getMec();
            
            MechanicTable.setEditable(true);
            
            
            MechanicCol.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            StartDateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
            HourlyRateCol.setCellValueFactory(new PropertyValueFactory<>("HourlyRate"));
            NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
           
            
            MechanicTable.setItems(MecData);
                    
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
   
    }
    
}
    
    
