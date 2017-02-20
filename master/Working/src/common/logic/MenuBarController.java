/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.Main;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.scene.control.MenuBar;

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
    @FXML
    private MenuItem addPart;
   /* @FXML
    private MenuItem trackPart;
    @FXML
    private MenuItem trackInstalledPart;*/
    
    public void addMenuBar()
    {
        try
        {
            URL menuBarUrl = getClass().getResource("/common/gui/MenuBarAdmin.fxml");
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
    
    public void editUser()
    {
        try
        {
            addMenuBar();
            
            URL editUserUrl = getClass().getResource("/common/gui/EditUser.fxml");
            AnchorPane editUserPane = FXMLLoader.load(editUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(editUserPane);
            
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void removeUser()
    {
        try
        {
            addMenuBar();
            
            URL removeUserUrl = getClass().getResource("/common/gui/RemoveUser.fxml");
            AnchorPane removeUserPane = FXMLLoader.load(removeUserUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(removeUserPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void addPart(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL addPartUrl = getClass().getResource("/parts/gui/addPart.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void trackPart(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL trackPartUrl = getClass().getResource("/parts/gui/trackPart.fxml");
            AnchorPane trackPartPane = FXMLLoader.load(trackPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(trackPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void trackInstalledPart(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL trackInstalledPartUrl = getClass().getResource("/parts/gui/trackInstalledPart.fxml");
            AnchorPane trackInstalledPartPane = FXMLLoader.load(trackInstalledPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(trackInstalledPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void addInstalledPart(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL addInstalledPartUrl = getClass().getResource("/parts/gui/addInstalledPart.fxml");
            AnchorPane addInstalledPartPane = FXMLLoader.load(addInstalledPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addInstalledPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void logOut(ActionEvent event)
    {
        try
        {
            
           JFrame frame = new JFrame();
           Object[] options = {"Yes","No"};
           int n = JOptionPane.showOptionDialog(frame,
            "Are you sure you want to logout",
            "LOG OUT",
             JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE,
             null,
             options,
             options[1]);
              if (n == JOptionPane.YES_OPTION) {
      
                 {
                   URL logOutUrl = getClass().getResource("/common/gui/Login.fxml");
                   AnchorPane logOutPane = FXMLLoader.load(logOutUrl);
            
                   BorderPane border = Main.getRoot();
            
                    border.setCenter(logOutPane);
                    border.setTop(null);
         /*Parent home_page_parent = FXMLLoader.load(getClass().getResource("/common/gui/Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();*/
            }
        }
        }
        catch(HeadlessException | IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
