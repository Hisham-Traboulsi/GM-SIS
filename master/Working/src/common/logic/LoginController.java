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
    
    @FXML
    private Label label;
    
    @FXML
    private Label invalid_label;
    
    @FXML
    private TextField id_box;
    
    @FXML
    private TextField password_box;
    
    @FXML
    private Button LogInButton;
    
    
    @FXML
    private void LogInButton(ActionEvent event) throws IOException {
         
        if (isValidCredentials())
        {
            invalid_label.setText("");
            JOptionPane.showMessageDialog(null,"Logged in succesfully");   
         
            /*AdminParent = FXMLLoader.load(getClass().getResource("/common/gui/Admin.fxml"));
            Scene scene = new Scene(AdminParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);*/
            try
            {
               
                URL welcomeUrl = getClass().getResource("/common/gui/Welcome.fxml");
                AnchorPane welcomePane = FXMLLoader.load(welcomeUrl);

                URL menuBarUrl = getClass().getResource("/common/gui/MenuBar.fxml");
                MenuBar bar = FXMLLoader.load(menuBarUrl);

                BorderPane pane = Main.getRoot();

                pane.setTop(bar);
                pane.setCenter(welcomePane);
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
            
            
        }
        else
        {
            id_box.clear();
            password_box.clear();
            invalid_label.setText("Sorry, invalid credentials");  
        }
        
    }
    public void clearButton()
    {
            id_box.clear();
            password_box.clear();
            invalid_label.setText("");
    }
    
    public boolean isValidCredentials()
    {
      //System.out.println("We are in the is valid credentials method");
      boolean allow_access=false;
      
      int id = Integer.parseInt(id_box.getText());
  
      //System.out.println("Accessing the authentication method");
      
      //this assigns either true or false depending on the result we get from the authentication method
      allow_access = Database.getInstance().authentication(id, password_box.getText());
        
      return allow_access;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
