/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;
/**/
import javafx.beans.property.IntegerProperty;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sergio
 */
public class partBooking
{
    private StringProperty Date;
    private IntegerProperty customerID;
    private StringProperty Type;
    private IntegerProperty ID;
    
    
   public partBooking(String Date,int customerID,String Type,int ID)
    {
        this.Date =  new SimpleStringProperty(Date);
        this.customerID=  new SimpleIntegerProperty(customerID);
        
        this.Type =  new SimpleStringProperty(Type);
        this.ID=  new SimpleIntegerProperty(ID);
        
    }
  
    
    public String getDate()
    {
        return Date.get();
    }
    
    public int getcustomerID()
    {
        return customerID.get();
    
    }
    
    public String getType()
    {
        return Type.get();
    
    }
    public int getBookID()
    {
        return ID.get();
    
    }
   
    /////
    public StringProperty DateNameProperty()
    {
        return Date;
    }
    
    public IntegerProperty customerIDProperty()
    {
       return customerID;
    }
    public StringProperty TypeProperty()
    {
       return Type;
    }
    //  
    public void setDate(String Date)
    {
        this.Date.set(Date);
    }
    
    public void setcustomerID(int customerID)
    {
        this.customerID.set(customerID);
    }
    
    public void setType(String Type)
    {
        this.Type.set(Type);
    }
     
}
   

