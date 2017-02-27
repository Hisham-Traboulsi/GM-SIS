/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hisha
 */
public class SystemUser 
{
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty surname;
    private StringProperty password;
    private StringProperty admin;
    
    public SystemUser(int id, String firstName, String surname, String password, String admin)
    {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
        this.password = new SimpleStringProperty(password);
        this.admin = new SimpleStringProperty(admin);
    }
    
    public SystemUser(String firstName, String surname, String password, String admin)
    {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
        this.password = new SimpleStringProperty(password);
        this.admin = new SimpleStringProperty(admin);
    }
    
    public int getID()
    {
        return id.get();
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
    
    public void setID(int id)
    {
        this.id.set(id);        
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName.set(firstName);
    }
    
    public void setSurname(String surname)
    {
        this.surname.set(surname);
    }
    
    public void setPassword(String password)
    {
        this.password.set(password);
    }
    
    public void setAdmin(String admin)
    {
        this.admin.set(admin);
    }
}
