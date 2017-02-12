package common;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * 
 * @author hisha
 */

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application
{
    
    private static BorderPane root = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/parts/gui/parts.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);*/
        
        URL loginUrl = getClass().getResource("/common/gui/Login.fxml");
        AnchorPane loginPane = FXMLLoader.load(loginUrl);
        
        root.setCenter(loginPane);
        
        Scene scene = new Scene(root);
        
        scene
              .getStylesheets()
              .add(getClass()
              .getResource("/common/gui/common_style.css")
              .toExternalForm());
        
        stage.setScene(scene);
        stage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static BorderPane getRoot()
    {
        return root;
    }
    
}