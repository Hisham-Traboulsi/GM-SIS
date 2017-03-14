/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import parts.logic.Part;

/**
 * FXML Controller class
 *
 * @author Shiraj Miah
 */
public class BookPartRepairController implements Initializable {

 @FXML
    private TableView<Part> partsTable = new TableView<Part>();

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, String> partDesc;

    @FXML
    private TableColumn amount;

    @FXML
    private Button bookButton;
   
    @FXML
    private ObservableList<Part> list=FXCollections.observableArrayList();
    
    @FXML
        ComboBox spcBox;
    @FXML
        DatePicker deliveryDate;
    @FXML
        DatePicker returnDate;
    
    private ObservableList<Part> selected = null;

    
    @FXML
   public boolean sumbit() throws SQLException
    {
      
      boolean added=false;
            selected = partsTable.getSelectionModel().getSelectedItems();   

            //int INSTID = Integer.parseInt(INST_ID.getText());
            String SPC = (spcBox.getPromptText());
            //int PARTID = (id.getCellData(id));
           // String PARTNAME = (partName.getSelected());
            String DELIVDATE = (deliveryDate.getPromptText());
            String RETURNDATE = (returnDate.getPromptText());
         
           added = Database.getInstance().bookSPCPart(SPC, selected.get(0).getID(),selected.get(0).getpartName(), DELIVDATE, RETURNDATE);
            
  
   //   added = Database.getInstance().bookSPCPart( SPC, PARTNAME,
     //         DELIVDATE,RETURNDATE);
      return added;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
        ObservableList<String> spcData = Database.getInstance().getSPCName();

        
            spcBox.setItems(spcData);            
            
            ObservableList<Part> partsData = Database.getInstance().getPart();

            partsTable.setEditable(true);
            
            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            partName.setCellValueFactory(new PropertyValueFactory<>("partName"));
            partDesc.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
           // amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            //amountCol.setOnEditCommit(
              //      new EventHandler<TableColumn.CellEditEvent<Part,Integer>>() {
               // @Override
                //public void handle(TableColumn.CellEditEvent<Part, Integer> t) {
                  //  ((Part) t.getTableView().getItems().get(
                    //        t.getTablePosition().getRow())).setAmount(t.getNewValue());
                    //}
            //}
            //);
            
            //costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

            partsTable.setItems(partsData);
            
          
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
    } 
    }    
    

