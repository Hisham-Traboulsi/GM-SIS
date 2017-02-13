/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import common.database.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
            id_Col.setCellFactory(TextFieldTableCell.forTableColumn(
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
            );

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
            password_Col.setCellValueFactory(new PropertyValueFactory<>("password"));
            admin_Col.setCellValueFactory(new PropertyValueFactory<>("admin"));

            usersTable.setItems(usersData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
