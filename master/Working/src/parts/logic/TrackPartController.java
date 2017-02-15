/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class TrackPartController implements Initializable {

     /**
     * Initializes the controller class.
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
    private ObservableList<Part> selected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try {
            ObservableList<Part> partsData = Database.getInstance().getPart();

            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
            partDescCol.setCellValueFactory(new PropertyValueFactory<>("partDesc"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

            partsTable.setItems(partsData);
            
            //selected = (ObservableList) usersTable.getSelectionModel();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    } 
    
    public void track()
    {
        //selected = (ObservableList) usersTable.getRowFactory();
        //System.out.println(selected);


    }
    
    
}
