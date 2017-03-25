/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author ernes
 */
public class Mec {
    
    private IntegerProperty Mechanic;
    private StringProperty Name;
    private IntegerProperty HourlyRate;
    private IntegerProperty Number;
    private StringProperty Date ;
    
     public Mec(int Mechanic, String Name, int HourlyRate, int Number, String Date)
    {
        this.Mechanic =  new SimpleIntegerProperty(Mechanic);
        this.Name = new SimpleStringProperty(Name);
        this.HourlyRate= new SimpleIntegerProperty(HourlyRate);
        this.Number=  new SimpleIntegerProperty(Number);
        this.Date =new SimpleStringProperty(Date);
    }
    
     public int getMechanic()
    {
        return Mechanic.get();
    }
    
    public String getName()
    {
        return Name.get();
    }
    
    public int getHourlyRate()
    {
        return HourlyRate.get();
    }
    
    public int getNumber()
    {    
        return Number.get();
    }
    
    public String getDate()
    {
        return Date.get();
    }
    
    public IntegerProperty MechanicProperty()
    {
        return Mechanic;
    }
    
    public StringProperty NameProperty()
    {
       return Name;
    }
    
    public IntegerProperty HourlyRateProperty()
    {
        return HourlyRate;
    }
    
    public IntegerProperty NumberProperty()
    {
        return Number;
    }
    
    public StringProperty DateProperty()
    {
        return Date;
    }
    
    
    public void setMechanic(int Mechanic)
    {
        this.Mechanic.set(Mechanic);        
    }
    
    public void setName(String Name)
    {
        this.Name.set(Name);
    }
    
    public void setHourlyRate(int HourlyRate)
    {
        this.HourlyRate.set(HourlyRate);
    }
    
    public void setNumber(int Number)
    {
        this.Number.set(Number);
    }
    
    public void setDate(String Date)
    {
        this.Date.set(Date);
    }
    
}
