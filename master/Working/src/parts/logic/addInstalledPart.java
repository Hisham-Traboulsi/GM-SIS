/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*Author Sergio*/
package parts.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
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
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JFrame;


/**
 * FXML Controller class
 *
 * @author sergi
 */
public class addInstalledPart implements Initializable {
    
    
    /*
    VARIABLE FOR ADD PARTS
    */
    @FXML
    private TextField INST_ID;
    @FXML
    private TextField REG_NUM;
    
    @FXML
    private DatePicker EXP_DATE;
    @FXML
    private TextField PART_NAME;
    @FXML
    private TextField VEHICLE_ID_BILL;
    @FXML
    private TextField CUSTOMER_ID;
    @FXML
    private TextField VEHICLE_ID;
    @FXML
    private ComboBox regComb;
    @FXML
    private ComboBox regCombReg;
    @FXML
    private ComboBox regCombCustName;
    @FXML
    private ComboBox IDcomb;
   

    
    @FXML
    private Button add;
    @FXML
    private Button clearButton;
    
    /*
    VARIABLE TO TRACK INSTALLEDPARTS
    */
     /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<installedPart> installedPartsTable = new TableView<installedPart>();
    
    

    @FXML
    private TableColumn <installedPart, Integer>INST_ID_view;
    @FXML
    private TableColumn <installedPart, String>PART_NAME_view;
    @FXML
    private TableColumn <installedPart, Integer>VEHICLE_ID_view;
   
    @FXML
    private TableColumn<installedPart, String> REG_NUM_view;
    @FXML
    private TableColumn<installedPart, String> INST_DATE_view;
    @FXML
    private TableColumn<installedPart, String> EXP_DATE_view;
    @FXML
    private TableColumn<installedPart, Integer> CUSTOMER_ID_view;

    @FXML
    private TextField searchBox;
    @FXML
    private ObservableList<installedPart> selected = null;

         
    @FXML
   public boolean add() 
    { 
       DateFormat df = new SimpleDateFormat("dd/MM/yy");
       Date dateobj = new Date();
       
       Calendar cal = Calendar.getInstance();
       Date today = cal.getTime();
       cal.add(Calendar.DATE, 364); 
       Date nextYear = cal.getTime();
       boolean added=false;
      if(empty())
      {
      
      

            String PARTNAME = (String) regComb.getValue();
            String REGNUM = (String) regCombReg.getValue();
            String INSTDATE = (df.format(dateobj));
            String EXPDATE = (df.format(nextYear));
            int CUSTOMERID = (Integer)(IDcomb.getValue());
            
  
      
      added = Database.getInstance().addInstalledPart( REGNUM, INSTDATE,
              EXPDATE,PARTNAME, CUSTOMERID);
      updateAmount();
      Database.getInstance().calculateBill(REGNUM,CUSTOMERID);
      addWithdrawalDate();
      clearRest();
      searchPart();
      }
      return added;
    }
    public void enterPressed(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                  add();
                }
            }
    public void partBooking(ActionEvent event)
    {
        try
        {
            //addMenuBar();
            
            URL addPartUrl = getClass().getResource("/parts/gui/partBooking.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void enterPressedSearch(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                  searchPart();
                }
            }
   public void addWithdrawalDate()
   {    String name= (String) regComb.getValue();
        Database.getInstance().addWithdrawal(name);
   }
   public void updateAmount() 
    {
        String partname=(String) regComb.getValue();
        Database.getInstance().updateStock(partname);
        Database.getInstance().partBelowZero();
        //return ID;
    }
   public void getBill() 
    {
        selected = installedPartsTable.getSelectionModel().getSelectedItems();   
        Database.getInstance().calculateBill(selected.get(0).getREG_NUM(),selected.get(0).getCUSTOMER_ID());
        searchPart();
    }
    public void remove() throws SQLException
    {
        try{
             selected = installedPartsTable.getSelectionModel().getSelectedItems(); 
             int id =selected.get(0).getINST_ID();
             
             JFrame frame = new JFrame();
             Object[] options = {"Yes","No"};
             int n = JOptionPane.showOptionDialog(frame,
             "Are you sure you want to remove that part",
             "Remove part",
             JOptionPane.YES_NO_CANCEL_OPTION,
             JOptionPane.WARNING_MESSAGE,
             null,
             options,
             options[1]);
              if (n == JOptionPane.YES_OPTION) 
                {     
                   {
                      Database.getInstance().removeInstalledPart(id);
                        searchPart();
                   }        
                 }
           }
           catch(NullPointerException e)
           { 
              JOptionPane.showMessageDialog(null,"Select a part to remove");
           }
     }
    
    
      public void updatePart() throws SQLException
    {  try{
        Database.getInstance().editInstalledPart();
        searchPart();
    }
    catch(NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"Select a part");
        }
    }
    
       public void searchPart() 
    {
        
       String searchVal=searchBox.getText();
       ObservableList  <installedPart> searchPartsData = Database.getInstance().searchInstalledPart(searchVal);
       
       installedPartsTable.setEditable(true);
       
            INST_ID_view.setCellValueFactory(new PropertyValueFactory<>("INST_ID"));
            
           
           PART_NAME_view.setCellValueFactory(new PropertyValueFactory<>("PART_NAME"));
           PART_NAME_view.setCellFactory(TextFieldTableCell.forTableColumn());
            PART_NAME_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPART_NAME(t.getNewValue());
                }
            }
            );
            

            REG_NUM_view.setCellValueFactory(new PropertyValueFactory<>("REG_NUM"));
            REG_NUM_view.setCellFactory(TextFieldTableCell.forTableColumn());
            REG_NUM_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setREG_NUM(t.getNewValue());
                }
            }
            );
            
            INST_DATE_view.setCellValueFactory(new PropertyValueFactory<>("INST_DATE"));
            INST_DATE_view.setCellFactory(TextFieldTableCell.forTableColumn());
            INST_DATE_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setINST_DATE(t.getNewValue());
                }
            }
            );
           
            EXP_DATE_view.setCellValueFactory(new PropertyValueFactory<>("EXP_DATE"));
            EXP_DATE_view.setCellFactory(TextFieldTableCell.forTableColumn());
            EXP_DATE_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,String>>() {
                @Override
                public void handle(CellEditEvent<installedPart, String> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEXP_DATE(t.getNewValue());
                }
            }
            );
            CUSTOMER_ID_view.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
            CUSTOMER_ID_view.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            CUSTOMER_ID_view.setOnEditCommit(
                    new EventHandler<CellEditEvent<installedPart,Integer>>() {
                @Override
                public void handle(CellEditEvent<installedPart, Integer> t) {
                    ((installedPart) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCUSTOMER_ID(t.getNewValue());
                }
            }
            );
            

            
            installedPartsTable.setItems(searchPartsData);
            
            //selected = (ObservableList) usersTable.getSelectionModel();
        } 
       
     
    public void clearButton()
    {
    searchBox.clear();
    installedPartsTable.getItems().clear();
  
    regComb.getItems().clear();
    regCombReg.getItems().clear();
    IDcomb.getItems().clear();
    partBox();
    numBox();
    idcombBox();

    }
    public boolean empty(){
           boolean check=true;
        
            boolean part = regComb.getSelectionModel().isEmpty();
            boolean reg = regCombReg.getSelectionModel().isEmpty();
            boolean id = IDcomb.getSelectionModel().isEmpty();
        
            if (part)
        {
            JOptionPane.showMessageDialog(null,"Please select a part name");
            check=false;
        }
        else if(reg)
        {
            JOptionPane.showMessageDialog(null,"Please select a vehicle registration");
            check=false;
        }
        else if(id)
        {
            JOptionPane.showMessageDialog(null,"Please select a customer ID");
            check=false;
        }
        return check;
    }
    public void clearRest(){
        
    searchBox.clear();
    regComb.getItems().clear();
    regCombReg.getItems().clear();
    IDcomb.getItems().clear();
    partBox();
    numBox();
    idcombBox();
    }
    public void partBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        //regComb = new ComboBox();
        regComb.setItems(regComb1);
        
    }
    public void idcombBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <Integer> regComb1=Database.getInstance().fillIDcombo();
        //regComb = new ComboBox();
        IDcomb.setItems(regComb1);
        
    }
    public void numBox()
    {
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        regComb = new ComboBox();
        regComb.getItems().addAll(regComb1);*/
        ObservableList <String> regComb1=Database.getInstance().fillNumComboBook();
        //regComb = new ComboBox();
        regCombReg.setItems(regComb1);
        
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /// partBox();
       partBox();
       numBox();
       idcombBox();
       searchPart();
       
       
        /*ObservableList <String> regComb1=Database.getInstance().fillRegCombo();
        //regComb = new ComboBox();
        regComb.setItems(regComb1);*/
        
       //regComb.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> choosePart(newValue));
    } 
    
    public void help()
    {
        JOptionPane.showMessageDialog(null,
                "<html>To add a part to a vehicle :<br />Select values from drop down boxes and click on submit button<br/><br/> "
                    + "To view bookings :<br /> Press booking button<br/><br/>"
                    + "To remove a part :<br /> Select a part from the table and press remove button<br/><br/>"
                    + "To search:<br/>Enter search parameter and press search button<br/><br/><html>"
                    + "To clear the boxes:<br/>Press clear button<br/><br/><html>");
        ///To update  a part:<br/>Double click on a cell, input new value, press enter and press update button
    }
     
    
    
   

    
}
