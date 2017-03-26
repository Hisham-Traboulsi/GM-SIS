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
    private StringProperty Name;
    private StringProperty SurName;
    private StringProperty Type;
    
    
   public partBooking(String Date,String Name,String SurName,String Type)
    {
        this.Date =  new SimpleStringProperty(Date);
        this.Name =  new SimpleStringProperty(Name);
        this.SurName =  new SimpleStringProperty(SurName);
        this.Type =  new SimpleStringProperty(Type);
        
    }
  
    
    public String getDate()
    {
        return Date.get();
    }
    
    public String getName()
    {
        return Name.get();
    
    }
    public String getSurName()
    {
        return SurName.get();
    
    }
    public String getType()
    {
        return Type.get();
    
    }
   
    /////
    public StringProperty DateNameProperty()
    {
        return Date;
    }
    
    public StringProperty NameDescProperty()
    {
       return Name;
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
    
    public void setName(String Name)
    {
        this.Name.set(Name);
    }
    
    public void setType(String Type)
    {
        this.Type.set(Type);
    }
     
}
   

