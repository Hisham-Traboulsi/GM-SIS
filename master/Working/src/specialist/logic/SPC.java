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
    private IntegerProperty idNum;
    private StringProperty spcName;
    private StringProperty spcAddress;
    private IntegerProperty spcPhone;
    private StringProperty spcEmail;

    
    
   public SPC(int idNum, String spcName, String spcAddress, int spcPhone, String spcEmail)
           
    {
        this.idNum =  new SimpleIntegerProperty(idNum);
        this.spcName =  new SimpleStringProperty(spcName);
        this.spcAddress =  new SimpleStringProperty(spcAddress);
        this.spcPhone =  new SimpleIntegerProperty(spcPhone);
        this.spcEmail =  new SimpleStringProperty(spcEmail);

    }
    
    /**
     *
     * @return
     */
   
       public int getIDnum()
    {
        return idNum.get();
    }
       
    public String getSPC_NAME()
    {
        return spcName.get();
    }
    
    public String getSPC_ADDRESS()
    {
        return spcAddress.get();
    }
    public int getSPC_PHONE()
    {
        return spcPhone.get();
    }
    public String getSPC_EMAIL()
    {
        return spcEmail.get();
    }
 
     public StringProperty SPC_NAMEProperty()
    {
        return spcName;
    }
    
    public StringProperty SPC_ADDRESSProperty()
    {
       return spcAddress;
    }
    public IntegerProperty SPC_PHONEProperty()
    {
       return spcPhone;
    }     
          
    public StringProperty SPC_EMAILProperty()
    {
       return spcEmail;
    }         
      
    /*
    
    
    
    */
        public void setIDnum(int idNum)
    {
        this.idNum.set(idNum);        
    }
        
    public void setSPC_NAME(String spcName)
    {
        this.spcName.set(spcName);        
    }
    public void setSPC_ADDRESS(String spcAddress)
    {
        this.spcAddress.set(spcAddress);        
    }
    
    public void setSPC_PHONE(int spcPhone)
    {
        this.spcPhone.set(spcPhone);        
    }
    
    public void setSPC_EMAIL(String spcEmail)
    {
        this.spcEmail.set(spcEmail);        
    }
  
 
    
}
   

