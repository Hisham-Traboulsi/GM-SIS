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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author hisha
 */
public class EditUserController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<SystemUser> usersData = Database.getInstance().getAllUsers();

            usersTable.setEditable(true);

            id_Col.setCellValueFactory(new PropertyValueFactory<>("ID"));
            /*id_Col.setCellFactory(TextFieldTableCell.forTableColumn(
            new StringConverter<Integer>() {

                @Override
                public String toString(Integer object) {
                    return object.toString();
                }

                @Override
                public Integer fromString(String string) {
                    return Integer.parseInt(string);
                }

            }
            ));
            id_Col.setOnEditCommit(
                    new EventHandler<CellEditEvent<SystemUser, String>>() {
                @Override
                public void handle(CellEditEvent<SystemUser, String> t) throws ClassCastException {
                    ((SystemUser) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setID(Integer.parseInt(t.getNewValue()));
                }
            }
            );*/

            firstName_Col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            firstName_Col.setCellFactory(TextFieldTableCell.forTableColumn());
            firstName_Col.setOnEditCommit(
                    new EventHandler<CellEditEvent<SystemUser, String>>() {
                @Override
                public void handle(CellEditEvent<SystemUser, String> t) {
                    ((SystemUser) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFirstName(t.getNewValue());
                }
            }
            );

            surname_Col.setCellValueFactory(new PropertyValueFactory<>("surname"));
            surname_Col.setCellFactory(TextFieldTableCell.forTableColumn());
            surname_Col.setOnEditCommit(
                    new EventHandler<CellEditEvent<SystemUser, String>>() {
                @Override
                public void handle(CellEditEvent<SystemUser, String> t) {
                    ((SystemUser) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSurname(t.getNewValue());
                }
            }
            );
            
            password_Col.setCellValueFactory(new PropertyValueFactory<>("password"));
            password_Col.setCellFactory(TextFieldTableCell.forTableColumn());
            password_Col.setOnEditCommit(
                    new EventHandler<CellEditEvent<SystemUser, String>>() {
                @Override
                public void handle(CellEditEvent<SystemUser, String> t) {
                    ((SystemUser) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPassword(t.getNewValue());
                }
            }
            );
            
            admin_Col.setCellValueFactory(new PropertyValueFactory<>("admin"));
            admin_Col.setCellFactory(TextFieldTableCell.forTableColumn());
            admin_Col.setOnEditCommit(
                    new EventHandler<CellEditEvent<SystemUser, String>>() {
                @Override
                public void handle(CellEditEvent<SystemUser, String> t) {
                    ((SystemUser) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAdmin(t.getNewValue());
                }
            }
            );

            usersTable.setItems(usersData);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public void update() throws SQLException
    {
        Database.getInstance().editUser();
    }
    
    public void refresh()
    {
        try
        {            
            URL addUserUrl = getClass().getResource("/common/gui/EditUser.fxml");
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
