/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.Main;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class RemoveUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<SystemUser> usersTable = new TableView<SystemUser>();

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
    
    private ObservableList<SystemUser> selected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try {
            ObservableList<SystemUser> usersData = Database.getInstance().getAllUsers();

            id_Col.setCellValueFactory(new PropertyValueFactory<>("ID"));
            firstName_Col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            surname_Col.setCellValueFactory(new PropertyValueFactory<>("surname"));
            password_Col.setCellValueFactory(new PropertyValueFactory<>("password"));
            admin_Col.setCellValueFactory(new PropertyValueFactory<>("admin"));

            usersTable.setItems(usersData);
            
            //selected = (ObservableList) usersTable.getSelectionModel();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    } 
    
    public void remove() throws SQLException
    {
        selected = usersTable.getSelectionModel().getSelectedItems();   
        //System.out.println(selected.get(0).getID());
        Database.getInstance().removeUser(selected.get(0).getID());
        refresh();
    }
    
    public void refresh()
    {
        try
        {   
            URL editUserUrl = getClass().getResource("/common/gui/RemoveUser.fxml");
            AnchorPane editUserPane = FXMLLoader.load(editUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(editUserPane);
            
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
