/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.Main;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author hisha
 */
public class MenuBarController 
{
    @FXML
    private MenuItem addUser;
    
    @FXML
    private MenuItem editUser;
    
    @FXML
    private MenuItem removeUser;
    
    public void addMenuBar()
    {
        try
        {
            URL menuBarUrl = getClass().getResource("/common/gui/MenuBar.fxml");
            MenuBar menuBarPane = FXMLLoader.load(menuBarUrl);
            
            BorderPane pane = Main.getRoot();
            
            pane.setTop(menuBarPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void addUser(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
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
