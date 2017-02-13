/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.Main;
import common.logic.MenuBarController;
import common.database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hisha
 */
 /* @author sergio */

public class LoginController implements Initializable {
    
  /*  @FXML
    private Label label;
    
    @FXML
    private Label invalid_label;*/
    
    @FXML
    private TextField id_box;
    
    @FXML
    private TextField password_box;
    
    @FXML
    private Button LogInButton;
    
    private boolean admin;
    
    @FXML
    private void LogInButton(ActionEvent event) throws SQLException, IOException {
         
        if (isValidCredentials())
        {
            //invalid_label.setText("");
            JOptionPane.showMessageDialog(null,"Logged in succesfully");   
         
            URL welcomeUrl = getClass().getResource("/common/gui/Welcome.fxml");
            AnchorPane welcomePane = FXMLLoader.load(welcomeUrl);
            MenuBar bar;
            if(admin)
            {
                URL menuBarAdminUrl = getClass().getResource("/common/gui/MenuBarAdmin.fxml");
                bar = FXMLLoader.load(menuBarAdminUrl);
            }
            else
            {
                URL menuBarNonAdminUrl = getClass().getResource("/common/gui/MenuBarNonAdmin.fxml");
                bar = FXMLLoader.load(menuBarNonAdminUrl);
            }
            BorderPane pane = Main.getRoot();
            pane.setTop(bar);
            pane.setCenter(welcomePane);
        }
        else
        {
            id_box.clear();
            password_box.clear();
          //  invalid_label.setText("Sorry, invalid credentials");  
        }
        
    }
    public void clearButton()
    {
            id_box.clear();
            password_box.clear();
            //invalid_label.setText("");
    }
    
    public boolean isValidCredentials() throws SQLException
    {
      //System.out.println("We are in the is valid credentials method");
      boolean allow_access=false;
      
      int id = Integer.parseInt(id_box.getText());
  
      //System.out.println("Accessing the authentication method");
      
      //this assigns either true or false depending on the result we get from the authentication method
      allow_access = Database.getInstance().authentication(id, password_box.getText());
      admin = Database.getInstance().isAdmin(id);
      return allow_access;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
