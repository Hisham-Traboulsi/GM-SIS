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
public class Outstanding {
    private IntegerProperty bookingID;
    private StringProperty spcName;
    private IntegerProperty partID;
    private StringProperty partName;
    private StringProperty deliveryDate;
    private StringProperty returnDate;
    private DoubleProperty partCost;
    private IntegerProperty customerID;
    private StringProperty firstName;
    private StringProperty surname;


public Outstanding(int bookingID, String spcName, int partID, String partName, String deliveryDate, String returnDate, Double partCost, int customerID, String firstName, String surname)
           
    {
        this.bookingID =  new SimpleIntegerProperty(bookingID);
        this.spcName =  new SimpleStringProperty(spcName);
        this.partID =  new SimpleIntegerProperty(partID);        
        this.partName =  new SimpleStringProperty(partName);
        this.deliveryDate =  new SimpleStringProperty(deliveryDate);
        this.returnDate =  new SimpleStringProperty(returnDate);
        this.partCost =  new SimpleDoubleProperty(partCost);
        this.customerID =  new SimpleIntegerProperty(customerID);    
        this.firstName =  new SimpleStringProperty(firstName);
        this.surname =  new SimpleStringProperty(surname);

    }

       public int getBOOKINGID()
    {
        return bookingID.get();
    }
       
    public String getSPCNAME()
    {
        return this.spcName.get();
    }
       public int getPARTID()
    {
        return partID.get();
    }
    
    public String getPARTNAME()
    {
        return partName.get();
    }
    public String getDELIVERYDATE()
    {
        return deliveryDate.get();
    }
    public String getRETURNDATE()
    {
        return returnDate.get();
    }
    
        public Double getPARTCOST()
    {
        return partCost.get();
    }
        
    public int getCUSTOMERID()
    {
        return customerID.get();
    }
    
        public String getFIRSTNAME()
    {
        return firstName.get();
    }
    public String getSURNAME()
    {
        return surname.get();
    }
    
      public StringProperty SPCNAMEProperty()
    {
        return spcName;
    }
      
      public IntegerProperty PARTIDProperty()
      {
          return partID;
      }
    
    public StringProperty PARTNAMEProperty()
    {
       return partName;
    }
    public StringProperty DELIVERYDATEProperty()
    {
       return deliveryDate;
    }     
          
    public StringProperty RETURNDATEProperty()
    {
       return returnDate;
    }
    
        public DoubleProperty PARTCOSTProperty()
    {
       return partCost;
    }
        
    public IntegerProperty CUSTOMERIDProperty()
      {
          return customerID;
      }
        public StringProperty FIRSTNAMEProperty()
    {
       return firstName;
    }     
          
    public StringProperty SURNAMEProperty()
    {
       return surname;
    }
    
    
        public void setIDnum(int bookingID)
    {
        this.bookingID.set(bookingID);        
    }
        
    public void setSPCNAME(String spcName)
    {
        this.spcName.set(spcName);        
    }
    
        public void setPARTID(int partID)
    {
        this.partID.set(partID);        
    }
        
    public void setPARTNAME(String partName)
    {
        this.partName.set(partName);        
    }
    
    public void setDELIVERYDATE(String deliveryDate)
    {
        this.deliveryDate.set(deliveryDate);        
    }
    
    public void setRETURNDATE(String returnDate)
    {
        this.returnDate.set(returnDate);        
    }
        
    public void setPARTCOST(Double partCost)
    {
        this.partCost.set(partCost);        
    }
    public void setCUSTOMERID(int customerID)
    {
        this.customerID.set(customerID);        
    }
        public void setFIRSTNAME(String firstName)
    {
        this.firstName.set(firstName);        
    }
    
    public void setSURNAME(String surname)
    {
        this.surname.set(surname);        
    }
}