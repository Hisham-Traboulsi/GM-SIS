    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*Author Sergio*/
package parts.logic;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import common.Main;
import common.database.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class addPart implements Initializable {
    /*
    
    ADD METHOD VARIABLES
    */
    
    
    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextArea partDesc;
    @FXML
    private TextField partAmount;
    @FXML
    private TextField partCost;
    
    /*
    
    TABLE VIEW VARIABLES
    */
         
    @FXML
    private TableView<Part> partsTable = new TableView<Part>();

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, String> partDescCol;

    @FXML
    private TableColumn amountCol;

    @FXML
    private TableColumn costCol;
    @FXML
    private ObservableList<Part> list=FXCollections.observableArrayList();
    
   
    
   public void add() throws SQLException
    {  // boolean added=false;
        if(empty() || !partName.getText().trim().isEmpty() || !partDesc.getText().trim().isEmpty())
      {
      partsTable.getItems().clear();
      
      
      //int id = Integer.parseInt(partID.getText());
      String name = (partName.getText());
      String desc = (partDesc.getText());
      int amount = Integer.parseInt(partAmount.getText());
      double cost = Double.parseDouble(partCost.getText());
      
      if (!name.matches(".*\\w.*"))
      {
          //System.out.println("You're not allowed my g");
          JOptionPane.showMessageDialog(null,"Please enter a valid part name");
          refresh();
          return;
      }
      if (!desc.matches(".*\\w.*"))
      {
          //System.out.println("You're not allowed my g");
          JOptionPane.showMessageDialog(null,"Please enter a valid part description");
          refresh();
          return;
      }
      
      Database.getInstance().addPart(name,desc,amount,cost);
      refresh();
      addDeliveryDate();
      clearButton();
      }
      
      //return added;
    }
   public void addDeliveryDate()
   {    String name= (partName.getText());
        Database.getInstance().addDelivery(name);
   }
   
    public void enterPressed(KeyEvent event) throws SQLException {
                if (event.getCode() == KeyCode.ENTER) {
                  add();
                }
            }
     public void partLog(ActionEvent event)
    {
        try
        {
           // addMenuBar();
            
            URL DiagnosisUrl = getClass().getResource("/parts/gui/partLog.fxml");
            AnchorPane DiagnosisPane = FXMLLoader.load(DiagnosisUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(DiagnosisPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void clearButton()
    {
    
    partName.clear();
    partDesc.clear();
    partAmount.clear();
    partCost.clear();
    refresh();
    }
    public boolean empty()
    { boolean check=true;
    
        if ( partName.getText().isEmpty()||partName.getText().equals("")||partName.getText().equals(" ")) {
          JOptionPane.showMessageDialog(null,"Please enter a part name");
          check=false;
     } else if ( partCost.getText().isEmpty()||partCost.getText().equals("")||partCost.getText().equals(" ")) {
          JOptionPane.showMessageDialog(null,"Please enter a part cost");
          check=false;
     } else if ( partAmount.getText().isEmpty()||partAmount.getText().equals("")||partAmount.getText().equals(" ")) {
         JOptionPane.showMessageDialog(null,"Please enter a quantity");
         check=false;
     }
        else if (partDesc.getText().isEmpty()||partDesc.getText().equals("")||partDesc.getText().equals(" ")) {
         JOptionPane.showMessageDialog(null,"Please enter a part description");
         check=false;
         
     }
        
        return check;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      try {
            ObservableList<Part> partsData = Database.getInstance().getPart();

            partsTable.setEditable(true);
            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
            partNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            partNameCol.setOnEditCommit(
                    new EventHandler<CellEditEvent<Part,String>>() {
                @Override
                public void handle(CellEditEvent<Part, String> t) {
                    ((Part) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setpartName(t.getNewValue());
                }
            }
            );
            partDescCol.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
            partDescCol.setCellFactory(TextFieldTableCell.forTableColumn());
            partDescCol.setOnEditCommit(
                    new EventHandler<CellEditEvent<Part,String>>() {
                @Override
                public void handle(CellEditEvent<Part, String> t) {
                    ((Part) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setpartDesc(t.getNewValue());
                }
            }
            );
            
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            amountCol.setOnEditCommit(
                    new EventHandler<CellEditEvent<Part,Integer>>() {
                @Override
                public void handle(CellEditEvent<Part, Integer> t) {
                    ((Part) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAmount(t.getNewValue());
                    }
            }
            );
            
            costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
            costCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            costCol.setOnEditCommit(
                    new EventHandler<CellEditEvent<Part,Double>>() {
                @Override
                public void handle(CellEditEvent<Part, Double> t) {
                    ((Part) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCost(t.getNewValue());
                }
            }
            );

            partsTable.setItems(partsData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    } 
   public void updatePart() throws SQLException
    { 
    try{
        Database.getInstance().editPart();
        refresh();
    }
    catch( NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"Please to edit:<br/><br/>" + "- Double click a cell<br/><br/>" +
                    "- Enter new value<br/><br/>" +
                    "- Press enter<br/><br/>" +
                    "- Press update button<br/><br/>");
        }
    }
    public void help()
    {
        JOptionPane.showMessageDialog(null,
                "<html>To add a part:<br />Fill in all the textfields and click on submit button or press enter<br/><br/> "
                    + "To update a part:<br/>Double click on the a cell, input new value, press enter and press update button<br/><br/>"
                    + "To clear the boxes:<br/>Press clear button<br/><br/>"
                    +" To view parts delivered and withdrawn:<br />Press part log button<html>");
    }
    public void refresh()
    {try {
            ObservableList<Part> partsData = Database.getInstance().getPart();

    partsTable.setEditable(true);
            
    idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
    partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
    partDescCol.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
    amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    amountCol.setOnEditCommit(
    new EventHandler<CellEditEvent<Part,Integer>>() {
            @Override
    public void handle(CellEditEvent<Part, Integer> t) {
                    ((Part) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAmount(t.getNewValue());
                    }
            }
            );
            
            costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

            partsTable.setItems(partsData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }  
    
        
}

    

