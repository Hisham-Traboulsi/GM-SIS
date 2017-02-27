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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class AddUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField FirstName_Box;
    
    @FXML
    private TextField ID_Box;
    
    @FXML
    private TextField Surname_Box;
    
    @FXML
    private PasswordField Password_Box;
    
    @FXML
    private RadioButton Admin_Radio;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            ObservableList<SystemUser> usersData = Database.getInstance().getAllUsers();

            id_Col.setCellValueFactory(new PropertyValueFactory<>("ID"));
            firstName_Col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            surname_Col.setCellValueFactory(new PropertyValueFactory<>("surname"));
            password_Col.setCellValueFactory(new PropertyValueFactory<>("password"));
            admin_Col.setCellValueFactory(new PropertyValueFactory<>("admin"));
            
            usersTable.setItems(usersData);
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }   
    
    public void addSyUser()
    {
        boolean added = false;
        String isAdmin = "N";
        
        int id = 0;
        
        if(Admin_Radio.isSelected())
        {
            isAdmin = "Y";
        }
        else
        {
            isAdmin = "N";
        }
        
        if(FirstName_Box.getText().isEmpty() || Surname_Box.getText().isEmpty() || Password_Box.getText().isEmpty() || isAdmin.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields are required: \n FirstName\n Surname\n Password\n Admin ");
        }
        else
        {
            id = Integer.parseInt(ID_Box.getText());
            added = Database.getInstance().addSysUser(id, FirstName_Box.getText(), Surname_Box.getText(), Password_Box.getText(), isAdmin);
        }
        
        if(added)
        {
            JOptionPane.showMessageDialog(null, "Successfuly Added");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "System user was not added");
        }
        
        try
        {            
            URL addUserUrl = getClass().getResource("/common/gui/AddUser.fxml");
            AnchorPane addUserPane = FXMLLoader.load(addUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addUserPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        
        refresh();
        clearButton();
    }
    
    public void clearButton()
    {
        ID_Box.clear();
        FirstName_Box.clear();
        Surname_Box.clear();
        Password_Box.clear();
    }
    
    public void refresh()
    {
        try
        {            
            URL addUserUrl = getClass().getResource("/common/gui/AddUser.fxml");
            AnchorPane addUserPane = FXMLLoader.load(addUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addUserPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
