/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField username_box;
    
    @FXML
    private TextField password_box;
    
    @FXML
    private Button handleButtonAction;
    
    
    @FXML
    private void LogInButton(ActionEvent event) throws IOException {
            
        if (isValidCredentials())
        {
            invalid_label.setText("");
            System.out.println("Logged in succesfully");
            
            /*Parent AdminParent = FXMLLoader.load(getClass().getResource("Admon.fxml"));
            Scene AdminScene = new Scene(AdminParent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(AdminScene);
            app_stage.show();*/
            
        }
        else
        {
            username_box.clear();
            password_box.clear();
            invalid_label.setText("Sorry, invalid credentials");
            
           
        }
        
    }
    public void clearButton()
    {
            username_box.clear();
            password_box.clear();
            invalid_label.setText("");
            
    }
    
    public boolean isValidCredentials()
    {
      
      boolean allow_access=false;
      
      System.out.println("SELECT * FROM AUTHENTICATION WHERE ID = " + "'" + username_box.getText() + "'"
       + " AND password= " + "'" + password_box.getText() + "'");
      
      Connection c = null;
      java.sql.Statement stmt = null;
      
      try{
          c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\hisha\\Desktop\\SEProject\\scratch\\Hisham\\GMSIS.db");
          c.setAutoCommit(false);
          
          System.out.println("Database opened successfully");
          stmt = c.createStatement();
          
          ResultSet rs= stmt.executeQuery ("SELECT * FROM AUTHENTICATION WHERE ID = " + "'" + username_box.getText() + "'"
       + " AND password= " + "'" + password_box.getText() + "'");
          
          while (rs.next() )
          {
              if (rs.getString("id") !=null && rs.getString("password") != null)
              {
                  String id= rs.getString("id");
                  System.out.println("id = " + id);
                  
                  String password = rs.getString("password");
                  allow_access = true;
              }
              else {
                  System.out.println("Error invalid credentials");
                  return allow_access;     
              }
          }
          rs.close();
          stmt.close();
          c.close();
      } catch (SQLException e)
      {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      
        
        return allow_access;
      
     
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
