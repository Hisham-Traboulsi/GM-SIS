/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

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
 * @author hisha
 */
public class EditUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private  TableView usersTable;
    
    @FXML
    private TableColumn id_Col;
    
    @FXML
    private TableColumn<SystemUser, String> firstName_Col;
    
    @FXML
    private TableColumn<SystemUser, String> surname_Col;
    
    @FXML
    private TableColumn<SystemUser, String> password_Col;
    
    @FXML
    private TableColumn<SystemUser, String> admin_Col;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            ObservableList<SystemUser> usersData = Database.getInstance().getAllUsers();
            
            id_Col.setCellValueFactory(new PropertyValueFactory<>("ID"));
            firstName_Col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            surname_Col.setCellValueFactory(new PropertyValueFactory<>("surname"));
            password_Col.setCellValueFactory(new PropertyValueFactory<>("password"));
            admin_Col.setCellValueFactory(new PropertyValueFactory<>("admin"));
            
            usersTable.setItems(usersData);
        } 
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
