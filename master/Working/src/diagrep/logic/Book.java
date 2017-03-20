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
    private IntegerProperty BookingMechanic;
    private StringProperty BookingDate;
    private StringProperty BookingRegNum;
    private IntegerProperty BookingMileage;
    private StringProperty BookingTime;
    private IntegerProperty BookingVehicle;
    private StringProperty BookingName;
    
    public Book(int id, int BookingMechanic, String BookingDate, String BookingRegNum, int BookingMileage, String BookingTime, int BookingVehicle, String BookingName)
    {
        this.id =  new SimpleIntegerProperty(id);
        this.BookingMechanic = new SimpleIntegerProperty(BookingMechanic);
        this.BookingDate= new SimpleStringProperty(BookingDate);
        this.BookingRegNum= new SimpleStringProperty(BookingRegNum);
        this.BookingMileage=  new SimpleIntegerProperty(BookingMileage);
        this.BookingTime= new SimpleStringProperty(BookingTime);
        this.BookingVehicle=  new SimpleIntegerProperty(BookingVehicle);
        this.BookingName= new SimpleStringProperty(BookingName);
        
    }
    
    public int getID()
    {
        return id.get();
    }
    
    public int getBookingMechanic()
    {
        return BookingMechanic.get();
    }
    
    public String getBookingDate()
    {
        return BookingDate.get();
    }
    
    public String getBookingRegNum()
    {
        return BookingRegNum.get();
    }
    
    public int getBookingMileage()
    {
        return BookingMileage.get();
    }
    
    public String getBookingTime()
    {
        return BookingTime.get();
    }
    
    public int getBookingVehicle()
    {
        return BookingVehicle.get();
    }
    
    public String getBookingName()
    {
        return BookingName.get();
    }
    
    
    public IntegerProperty BookingMechanicProperty()
    {
        return BookingMechanic;
    }
    
    public StringProperty BookingDateProperty()
    {
       return BookingDate;
    }
    
    public StringProperty BookingRegNumProperty()
    {
       return BookingRegNum;
    }
    
    public IntegerProperty BookingMileageProperty()
    {
       return BookingMileage;
    }
    
    
    public StringProperty BookingTimeProperty()
    {
       return BookingTime;
    }
    
    public IntegerProperty BookingVehicleProperty()
    {
       return BookingVehicle;
    }
    
     public StringProperty BookingNameProperty()
    {
       return BookingName;
    }
     
     
     
    public void setID(int id)
    {
        this.id.set(id);        
    }
    
    public void setBookingMechanic(int BookingMechanic)
    {
        this.BookingMechanic.set(BookingMechanic);
    }
    
    public void setBookingDate(String BookingDate)
    {
        this.BookingDate.set(BookingDate);
    }
    
    public void setBookingRegNum(String BookingRegNum)
    {
        this.BookingRegNum.set(BookingRegNum);        
    }
    
    public void setBookingMileage(int BookingMileage)
    {
        this.BookingMileage.set(BookingMileage);
    }
    
    public void setBookingTime(String BookingTime)
    {
        this.BookingTime.set(BookingTime);        
    }
    
    public void setBookingVehicle(int BookingVehicle)
    {
        this.BookingVehicle.set(BookingVehicle);
    }
    
    public void setBookingName(String BookingName)
    {
        this.BookingName.set(BookingName);
    }
    
    
    
}
