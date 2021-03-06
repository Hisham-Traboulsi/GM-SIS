/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Shiraj Miah
 */
public class ReturnedVehicle {
    private IntegerProperty bookingID;
    private StringProperty spcName;
    private StringProperty regNum;
    private StringProperty vehicleMake;
    private StringProperty vehicleModel;
    private StringProperty deliveryDate;
    private StringProperty returnDate;
    private DoubleProperty vehicleTotal;

    

public ReturnedVehicle(int bookingID, String spcName, String regNum, String vehicleMake, String vehicleModel, 
        String deliveryDate, String returnDate, Double vehicleTotal)
           
    {
        this.bookingID =  new SimpleIntegerProperty(bookingID);
        this.spcName =  new SimpleStringProperty(spcName);
        this.regNum =  new SimpleStringProperty(regNum);
        this.vehicleMake =  new SimpleStringProperty(vehicleMake);        
        this.vehicleModel =  new SimpleStringProperty(vehicleMake);
        this.deliveryDate =  new SimpleStringProperty(deliveryDate);
        this.returnDate =  new SimpleStringProperty(returnDate);
        this.vehicleTotal =  new SimpleDoubleProperty(vehicleTotal);

    }

       public int getBOOKINGIDVEHICLE()
    {
        return bookingID.get();
    }
       
    public String getSPCNAMEVEHICLE()
    {
        return this.spcName.get();
    }
    
    public String getREGNUM()
    {
        return this.regNum.get();
    }
       public String getVEHICLEMAKE()
    {
        return vehicleMake.get();
    }
    
    public String getVEHICLEMODEL()
    {
        return vehicleModel.get();
    }
    public String getDELIVERYDATEVEHICLE()
    {
        return deliveryDate.get();
    }
    public String getRETURNDATEVEHICLE()
    {
        return returnDate.get();
    }
    
    public Double getVEHICLETOTAL()
    {
        return vehicleTotal.get();
    }
    
      public StringProperty SPCNAMEProperty()
    {
        return spcName;
    }
    public StringProperty REGNUMProperty()
    {
        return regNum;
    }
      public StringProperty VEHICLEMAKEProperty()
      {
          return vehicleMake;
      }
    
    public StringProperty VEHICLEMODELProperty()
    {
       return vehicleModel;
    }
    public StringProperty DELIVERYDATEProperty()
    {
       return deliveryDate;
    }     
          
    public StringProperty RETURNDATEProperty()
    {
       return returnDate;
    }
        public DoubleProperty VEHICLETOTALProperty()
    {
       return vehicleTotal;
    }
    
    
        public void setIDnum(int bookingID)
    {
        this.bookingID.set(bookingID);        
    }
        
    public void setSPCNAME(String spcName)
    {
        this.spcName.set(spcName);        
    }
    
    public void setREGNUM(String regNum)
    {
        this.regNum.set(regNum);        
    }
    
    public void setVEHICLEMAKE(String vehicleMake)
    {
        this.vehicleMake.set(vehicleMake);        
    }
        
    public void setVEHICLEMODEL(String vehicleModel)
    {
        this.vehicleModel.set(vehicleModel);        
    }
    
    public void setDELIVERYDATE(String deliveryDate)
    {
        this.deliveryDate.set(deliveryDate);        
    }
    
    public void setRETURNDATE(String returnDate)
    {
        this.returnDate.set(returnDate);        
    }
    
    public void setVEHICLETOTAL(Double vehicleTotal)
    {
        this.vehicleTotal.set(vehicleTotal);        
    }
}
