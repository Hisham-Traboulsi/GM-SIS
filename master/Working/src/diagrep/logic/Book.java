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
public class Book {
    
    private IntegerProperty id;
    private IntegerProperty Mechanic;
    private StringProperty Date;
    private StringProperty RegNum;
    private IntegerProperty Mileage;
    private StringProperty Time;
    private IntegerProperty Vehicle;
    private StringProperty Name;
    
    public Book(int id, int Mechanic, String Date, String RegNum, int Mileage, String Time, int Vehicle, String Name)
    {
        this.id =  new SimpleIntegerProperty(id);
        this.Mechanic = new SimpleIntegerProperty(Mechanic);
        this.Date= new SimpleStringProperty(Date);
        this.RegNum= new SimpleStringProperty(RegNum);
        this.Mileage=  new SimpleIntegerProperty(Mileage);
        this.Time= new SimpleStringProperty(Time);
        this.Vehicle=  new SimpleIntegerProperty(Vehicle);
        this.Name= new SimpleStringProperty(Name);
        
    }
    
    public int getID()
    {
        return id.get();
    }
    
    public int getMechanic()
    {
        return Mechanic.get();
    }
    
    public String getDate()
    {
        return Date.get();
    }
    
    public String getRegNum()
    {
        return RegNum.get();
    }
    
    public int getMileage()
    {
        return Mileage.get();
    }
    
    public String getTime()
    {
        return Time.get();
    }
    
    public int getVehicle()
    {
        return Vehicle.get();
    }
    
    public String getName()
    {
        return Name.get();
    }
    
    
    public IntegerProperty MechanicProperty()
    {
        return Mechanic;
    }
    
    public StringProperty DateProperty()
    {
       return Date;
    }
    
    public StringProperty RegNumProperty()
    {
       return RegNum;
    }
    
    public IntegerProperty MileageProperty()
    {
       return Mileage;
    }
    
    
    public StringProperty TimeProperty()
    {
       return Time;
    }
    
    public IntegerProperty VehicleProperty()
    {
       return Vehicle;
    }
    
     public StringProperty NameProperty()
    {
       return Name;
    }
     
     
     
    public void setID(int id)
    {
        this.id.set(id);        
    }
    
    public void setMechanic(int Mechanic)
    {
        this.Mechanic.set(Mechanic);
    }
    
    public void setDate(String Date)
    {
        this.Date.set(Date);
    }
    
    public void setRegNum(String RegNum)
    {
        this.RegNum.set(RegNum);        
    }
    
    public void setMileage(int Mileage)
    {
        this.Mileage.set(Mileage);
    }
    
    public void setTime(String Time)
    {
        this.Time.set(Time);        
    }
    
    public void setVehicle(int Vehicle)
    {
        this.Vehicle.set(Vehicle);
    }
    
    public void setName(String Name)
    {
        this.Name.set(Name);
    }
    
    
    
}
