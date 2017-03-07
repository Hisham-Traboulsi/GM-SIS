/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author ernes
 */
public class Bookings {
    private IntegerProperty BookingID;
    private StringProperty FullName;
    private StringProperty MechanicID;
    private StringProperty RegNum;
    private IntegerProperty VechicleMileage;
    private IntegerProperty DateID;
    private IntegerProperty TimeID;
    
    public Bookings(int BookingID, String FullName, String MechanicID, String RegNum, int VechicleMileage, int DateID, int TimeID)
    {
        this.BookingID = new SimpleIntegerProperty(BookingID);
        this.FullName = new SimpleStringProperty(FullName);
        this.MechanicID = new SimpleStringProperty(MechanicID);
        this.RegNum = new SimpleStringProperty(RegNum);
        this.VechicleMileage = new SimpleIntegerProperty(VechicleMileage);
        this.DateID = new SimpleIntegerProperty(DateID);
        this.TimeID = new SimpleIntegerProperty(TimeID);
    }
    
    public Bookings(String FullName, String MechanicID, String RegNum, int VechicleMileage, int DateID, int TimeID)
    {
       
        this.FullName = new SimpleStringProperty(FullName);
        this.MechanicID = new SimpleStringProperty(MechanicID);
        this.RegNum = new SimpleStringProperty(RegNum);
        this.VechicleMileage = new SimpleIntegerProperty(VechicleMileage);
        this.DateID = new SimpleIntegerProperty(DateID);
        this.TimeID = new SimpleIntegerProperty(TimeID);
        
    }
    
}
