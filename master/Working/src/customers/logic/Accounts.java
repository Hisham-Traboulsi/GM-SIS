/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hisha
 */
public class Accounts 
{
    private IntegerProperty customerID;
    private IntegerProperty bookingID;
    private StringProperty vehicleReg;
    private StringProperty bookingDate;
    private DoubleProperty cost;
    private StringProperty status;

    
    
    public Accounts(int customerID, int bookingID, String vehicleReg, String bookingDate, double cost)
    {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.bookingID = new SimpleIntegerProperty(bookingID);
        this.vehicleReg = new SimpleStringProperty(vehicleReg);
        this.bookingDate = new SimpleStringProperty(bookingDate);
        this.cost = new SimpleDoubleProperty(cost);
    }
    
    public Accounts(int customerID, int bookingID, String status)
    {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.bookingID = new SimpleIntegerProperty(bookingID);
        this.status = new SimpleStringProperty(status);
    }
    
    public Accounts(int customerID, int bookingID, String vehicleReg, String bookingDate, double cost, String status)
    {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.bookingID = new SimpleIntegerProperty(bookingID);
        this.vehicleReg = new SimpleStringProperty(vehicleReg);
        this.bookingDate = new SimpleStringProperty(bookingDate);
        this.cost = new SimpleDoubleProperty(cost);
        this.status = new SimpleStringProperty(status);
    }

    public IntegerProperty CustomerIDProperty() {
        return customerID;
    }

    public void setCustomerID(IntegerProperty customerID) {
        this.customerID = customerID;
    }

    public IntegerProperty BookingIDProperty() {
        return bookingID;
    }

    public void setBookingID(IntegerProperty bookingID) {
        this.bookingID = bookingID;
    }
    
    public StringProperty VehicleRegProperty() {
        return vehicleReg;
    }

    public void setVehicleReg(StringProperty vehicleReg) {
        this.vehicleReg = vehicleReg;
    }
    public StringProperty BookingDateProperty() {
        return bookingDate;
    }

    public void setBookingDate(StringProperty bookingDate) {
        this.bookingDate = bookingDate;
    }

    public DoubleProperty CostProperty() {
        return cost;
    }

    public void setCost(DoubleProperty cost) {
        this.cost = cost;
    }
    
    public StringProperty StatusProperty() {
        return status;
    }

    public void setStatus(StringProperty status) {
        this.status = status;
    }
    
    //set methods
    public void setCustomerID(int customerID)
    {
        this.customerID.set(customerID);
    }
    
    public void setBookingID(int bookingID)
    {
        this.bookingID.set(bookingID);
    }
    
    public void setVehicleReg(String vehicleReg)
    {
        this.vehicleReg.set(vehicleReg);
    }
    
    public void setBookingDate(String bookingDate)
    {
        this.bookingDate.set(bookingDate);
    }
    
    public void setCost(double cost)
    {
        this.cost.set(cost);
    }
    
    public void setStatus(String status)
    {
        this.status.set(status);
    }
    //getter methods
    
    public int getCustomerID()
    {
        return this.customerID.get();
    }
    
    public int getBookingID()
    {
        return this.bookingID.get();
    }
    
    public String getVehicleReg()
    {
        return this.vehicleReg.get();
    }
    
    public String getBookingDate()
    {
        return this.bookingDate.get();
    }
    
    public double getCost()
    {
        return this.cost.get();
    } 
    
    public String getStatus()
    {
        return this.status.get();
    }
}
