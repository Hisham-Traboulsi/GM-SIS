/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.database.Database;
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
import javax.swing.JOptionPane;
import java.sql.*;

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
    private Button handleButtonAction;
    
    
    @FXML
    private void LogInButton(ActionEvent event) throws IOException {
         
        if (isValidCredentials())
        {
            invalid_label.setText("");
            JOptionPane.showMessageDialog(null,"Logged in succesfully");   
         
            Parent AdminParent = FXMLLoader.load(getClass().getResource("/common/gui/Admin.fxml"));
            Scene scene = new Scene(AdminParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            
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
      
      /*System.out.println("SELECT * FROM AUTHENTICATION WHERE ID = " + "'" + username_box.getText() + "'"
       + " AND password= " + "'" + password_box.getText() + "'");
      
      Connection c = null;
      java.sql.Statement stmt = null;
      
      try{
          c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\sergi\\Desktop\\SE\\scratch\\Hisham\\GMSIS.db");
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
      */
      int id = Integer.parseInt(id_box.getText());
      
      //System.out.println("Accessing the authentication method");
      allow_access = Database.getInstance().authentication(id, password_box.getText());
        
      return allow_access;
      
     
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
