/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Shiraj
 */
public class centreName
{
    private StringProperty spc_name;

    
   public centreName(String spc_name)
           
    {
        this.spc_name =  new SimpleStringProperty(spc_name);
    }
    

    public String getSPC_NAME()
    {
        return spc_name.get();
    }
    

    public void setSPC_NAME(String spc_name)
    {
        this.spc_name.set(spc_name);        
    }

    
}