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

    
    
   public SPC(String spcName, String spcAddress, int spcPhone, String spcEmail)
           
    {
        this.SPC_NAME =  new SimpleStringProperty(spcName);
        this.SPC_ADDRESS =  new SimpleStringProperty(spcAddress);
        this.SPC_PHONE =  new SimpleIntegerProperty(spcPhone);
        this.SPC_EMAIL =  new SimpleStringProperty(spcEmail);

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
 
     public StringProperty SPC_NAMEProperty()
    {
        return SPC_NAME;
    }
    
    public StringProperty SPC_ADDRESSProperty()
    {
       return SPC_ADDRESS;
    }     
          
    public StringProperty SPC_EMAILProperty()
    {
       return SPC_EMAIL;
    }         
      
    /*
    
    
    
    */
    public void setSPC_NAME(String spcName)
    {
        this.SPC_NAME.set(spcName);        
    }
    public void setSPC_ADDRESS(String spcAddress)
    {
        this.SPC_ADDRESS.set(spcAddress);        
    }
    
    public void setSPC_PHONE(int spcPhone)
    {
        this.SPC_PHONE.set(spcPhone);        
    }
    
    public void setSPC_EMAIL(String spcEmail)
    {
        this.SPC_EMAIL.set(spcEmail);        
    }
  
 
    
}
   

