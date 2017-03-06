/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.Main;
import common.logic.LoginController;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    
    private boolean admin = LoginController.admin;
     
    public void addMenuBar()
    {
        try
        {   
            MenuBar menuBarPane = null;
            
            if(admin)
            {
                URL menuBarUrl = getClass().getResource("/common/gui/MenuBarAdmin.fxml");
                menuBarPane = FXMLLoader.load(menuBarUrl);
            }
            else
            {
                URL menuBarUrl = getClass().getResource("/common/gui/MenuBarNonAdmin.fxml");
                menuBarPane = FXMLLoader.load(menuBarUrl);
            }
            
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
    
    public void addCustomer(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL addCustomerUrl = getClass().getResource("/customers/gui/AddCustomer.fxml");
            AnchorPane addCustomerPane = FXMLLoader.load(addCustomerUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addCustomerPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void ScheduledMain(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL ScheduledMainUrl = getClass().getResource("/diagrep/gui/ScheduledMaintenance.fxml");
            AnchorPane ScheduledMainPane = FXMLLoader.load(ScheduledMainUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(ScheduledMainPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    
    
    public void removeCustomer(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL removeCustomerUrl = getClass().getResource("/customers/gui/RemoveCustomer.fxml");
            AnchorPane addCustomerPane = FXMLLoader.load(removeCustomerUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addCustomerPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void editCustomer(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL editCustomerUrl = getClass().getResource("/customers/gui/EditCustomer.fxml");
            AnchorPane addCustomerPane = FXMLLoader.load(editCustomerUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addCustomerPane);
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
    
    public void vehicleHomepage(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL addPartUrl = getClass().getResource("/vehicles/gui/VehicleHomepage.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void addVehicle(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL addPartUrl = getClass().getResource("/vehicles/gui/AddVehicle.fxml");
            AnchorPane addPartPane = FXMLLoader.load(addPartUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(addPartPane);
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
    
    public void chooseCenter(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL chooseCenterUrl = getClass().getResource("/specialist/gui/chooseCentre.fxml");
            AnchorPane chooseCenterPane = FXMLLoader.load(chooseCenterUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(chooseCenterPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void bookPartRepair(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL bookPartRepairUrl = getClass().getResource("/specialist/gui/bookPartRepair.fxml");
            AnchorPane bookPartRepairPane = FXMLLoader.load(bookPartRepairUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(bookPartRepairPane);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
        public void bookVehicleRepair(ActionEvent event)
    {
        try
        {
            addMenuBar();
            
            URL bookVehicleRepairUrl = getClass().getResource("/specialist/gui/bookVehicleRepair.fxml");
            AnchorPane bookVehicleRepairPane = FXMLLoader.load(bookVehicleRepairUrl);
            
            BorderPane border = Main.getRoot();
            
            border.setCenter(bookVehicleRepairPane);
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
