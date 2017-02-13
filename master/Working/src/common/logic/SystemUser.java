/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

/**
 *
 * @author hisha
 */
public class SystemUser 
{
    private int id;
    private String firstName;
    private String surname;
    private String password;
    private String admin;
    
    public SystemUser(int id, String firstName, String surname, String password, String admin)
    {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.admin = admin;
    }
    
    public int getID()
    {
        return id;
    }
    
    public String getfirstName()
    {
        return firstName;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getAdmin()
    {
        return admin;
    }
}
