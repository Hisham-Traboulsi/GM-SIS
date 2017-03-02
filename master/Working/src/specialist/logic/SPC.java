/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Shiraj
 */
public class SPC
{
    private StringProperty SPC_NAME;
    private StringProperty SPC_ADDRESS;
    private IntegerProperty SPC_PHONE;
    private StringProperty SPC_EMAIL;

    
    
   public SPC(String SPC_NAME, String SPC_ADDRESS, int SPC_PHONE, String SPC_EMAIL)
           
    {
        this.SPC_NAME =  new SimpleStringProperty(SPC_NAME);
        this.SPC_ADDRESS =  new SimpleStringProperty(SPC_ADDRESS);
        this.SPC_PHONE =  new SimpleIntegerProperty(SPC_PHONE);
        this.SPC_EMAIL =  new SimpleStringProperty(SPC_EMAIL);

    }
    
    /**
     *
     * @return
     */
    public String getSPC_NAME()
    {
        return SPC_NAME.get();
    }
    
    public String getSPC_ADDRESS()
    {
        return SPC_ADDRESS.get();
    }
    public int getSPC_PHONE()
    {
        return SPC_PHONE.get();
    }
    public String getSPC_EMAIL()
    {
        return SPC_EMAIL.get();
    }
 
      
      
      
      
    /*
    
    
    
    */
    public void setSPC_NAME(String SPC_NAME)
    {
        this.SPC_NAME.set(SPC_NAME);        
    }
    public void setSPC_ADDRESS(String SPC_ADDRESS)
    {
        this.SPC_ADDRESS.set(SPC_ADDRESS);        
    }
    
    public void setSPC_PHONE(int SPC_PHONE)
    {
        this.SPC_PHONE.set(SPC_PHONE);        
    }
    
    public void setSPC_EMAIL(String SPC_EMAIL)
    {
        this.SPC_EMAIL.set(SPC_EMAIL);        
    }
  
 
    
}
   

