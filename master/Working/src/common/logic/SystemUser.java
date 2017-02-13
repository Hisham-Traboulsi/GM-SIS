/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hisha
 */
public class SystemUser 
{
    private int id;
    private StringProperty firstName;
    private StringProperty surname;
    private StringProperty password;
    private StringProperty admin;
    
    public SystemUser(int id, String firstName, String surname, String password, String admin)
    {
        this.id = id;
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
        this.password = new SimpleStringProperty(password);
        this.admin = new SimpleStringProperty(admin);
    }
    
    public int getID()
    {
        return id;
    }
    
    public String getfirstName()
    {
        return firstName.get();
    }
    
    public String getSurname()
    {
        return surname.get();
    }
    
    public String getPassword()
    {
        return password.get();
    }
    
    public String getAdmin()
    {
        return admin.get();
    }
    
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    
    public StringProperty surnameProperty()
    {
        return surname;
    }
    
    public StringProperty passwordProperty()
    {
        return password;
    }
    
    public StringProperty adminProperty()
    {
        return admin;
    }
}
