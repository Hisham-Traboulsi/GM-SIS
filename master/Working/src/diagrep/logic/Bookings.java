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
public class Bookings {
    
    private IntegerProperty idNum;
    private StringProperty BookingMechanicID;
    private StringProperty PART_NAME;
    private IntegerProperty CUSTOMER_ID;
    private StringProperty BookingRegNum;
    private StringProperty BookingManufacture;
    private StringProperty BookingMileage;
    private StringProperty BookingDate;
    private StringProperty BookingTime;
    private StringProperty BookingType;
    private DoubleProperty BookingTotal;
    
    
     public Bookings(int idNum, String BookingMechanicID, String PART_NAME, 
             int CUSTOMER_ID, String BookingRegNum, String BookingManufacture, 
             String BookingMileage, String BookingDate, String BookingTime, String BookingType, double BookingTotal)
           
    {
        this.idNum =  new SimpleIntegerProperty(idNum);
        this.BookingMechanicID =  new SimpleStringProperty(BookingMechanicID);
        this.PART_NAME =  new SimpleStringProperty(PART_NAME);
        this.CUSTOMER_ID = new SimpleIntegerProperty(CUSTOMER_ID);
        this.BookingRegNum =  new SimpleStringProperty(BookingRegNum);
        this.BookingManufacture =  new SimpleStringProperty(BookingManufacture);
        this.BookingMileage =  new SimpleStringProperty(BookingMileage);
        this.BookingDate =  new SimpleStringProperty(BookingDate);
        this.BookingTime =  new SimpleStringProperty(BookingTime);
        this.BookingType =  new SimpleStringProperty(BookingType);
        this.BookingTotal =  new SimpleDoubleProperty(BookingTotal);


    }
     
     public int getIDnum()
    {
        return idNum.get();
    }
       
    public String getBOOKING_MechID()
    {
        return this.BookingMechanicID.get();
    }
    
    public String getPART_NAME()
    {
        return PART_NAME.get();
    }
    public int getCUSTOMER_ID()
    {
        return CUSTOMER_ID.get();
    }
    public String getBOOKING_REGNUM()
    {
        return BookingRegNum.get();
    }
    public String getBOOKING_MANUFACTURE()
    {
        return BookingManufacture.get();
    }
    public String getBOOKING_MILEAGE()
    {
        return BookingMileage.get();
    }
    public String getBOOKING_DATE()
    {
        return BookingDate.get();
    }
    public String getBOOKING_TIME()
    {
        return BookingTime.get();
    }
    public String getBOOKING_TYPE()
    {
        return BookingType.get();
    }
    public double getBOOKING_TOTAL()
    {
        return BookingTotal.get();
    }
    
    
    
    
    public StringProperty BOOKING_MechIDProperty()
    {
        return BookingMechanicID;
    }
    
    
    public IntegerProperty CUSTOMER_ID()
    {
       return CUSTOMER_ID;
    }
     
          
    public StringProperty BOOKING_REGNUMProperty()
    {
       return BookingRegNum;
    }        
    public StringProperty BOOKING_MANUFACTUREProperty()
    {
       return BookingManufacture;
    }        
    
    public StringProperty BOOKING_MILEAGEProperty()
    {
       return BookingMileage;
    }        
    
    public StringProperty BOOKING_DATEProperty()
    {
       return BookingDate;
    }        
    
    public StringProperty BOOKING_TIMEProperty()
    {
       return BookingTime;
    }    

    public StringProperty BOOKING_TYPEProperty()
    {
       return BookingType;
    }     
    public DoubleProperty BOOKING_TOTALProperty()
    {
       return BookingTotal;
    }
    
    
    
     public void setIDnum(int idNum)
    {
        this.idNum.set(idNum);        
    }
        
    public void setBOOKING_MechID(String BookingMechanicID)
    {
        this.BookingMechanicID.set(BookingMechanicID);        
    }
    public void setPART_NAME(String PART_NAME)
    {
        this.PART_NAME.set(PART_NAME);        
    }       
    
    
    public void setCUSTOMER_ID(int CUSTOMER_ID)
    {
        this.CUSTOMER_ID.set(CUSTOMER_ID);
    }
    
    public void setBOOKING_REGNUM(String BookingRegNum)
    {
        this.BookingRegNum.set(BookingRegNum);        
    }
    
    public void setBOOKING_MANUFACTURE(String BookingManufacture)
    {
        this.BookingManufacture.set(BookingManufacture);        
    }
    
    public void setBOOKING_MILEAGE(String BookingMileage)
    {
        this.BookingMileage.set(BookingMileage);        
    }
    
    public void setBOOKING_DATE(String BookingDate)
    {
        this.BookingDate.set(BookingDate);        
    }
    
    public void setBOOKING_TIME (String BookingTime)
    {
        this.BookingTime.set(BookingTime);        
    }
    public void setBOOKING_TYPE (String BookingType)
    {
        this.BookingType.set(BookingType);        
    }
    public void setBOOKING_TOTAL(double BookingTotal)
    {
        this.BookingTotal.set(BookingTotal);        
    }
    
    
    
}
