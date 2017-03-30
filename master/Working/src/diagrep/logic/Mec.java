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
    
    private IntegerProperty idNum;
    private StringProperty MechanicName;
    private StringProperty MechanicDate;
    private StringProperty MechanicNumber;
    private DoubleProperty MechanicRate;
    
    public Mec(int idNum, String MechanicName, String MechanicDate, 
             String MechanicNumber, double MechanicRate)
           
    {
        this.idNum =  new SimpleIntegerProperty(idNum);
        this.MechanicName =  new SimpleStringProperty(MechanicName);
        this.MechanicDate =  new SimpleStringProperty(MechanicDate);
        this.MechanicNumber =  new SimpleStringProperty(MechanicNumber);
        this.MechanicRate =  new SimpleDoubleProperty(MechanicRate);
        

    }
    
    public int getIDnum()
    {
        return idNum.get();
    }
       
    public String getMECHANIC_NAME()
    {
        return this.MechanicName.get();
    }
    
    public String getMECHANIC_DATE()
    {
        return MechanicDate.get();
    }
    public String getMECHANIC_NUMBER()
    {
        return MechanicNumber.get();
    }
    public double getMECHANIC_RATE()
    {
        return MechanicRate.get();
    }
    
    public StringProperty MECHANIC_NAMEProperty()
    {
        return MechanicName;
    }
    
    public StringProperty MECHANIC_DATEProperty()
    {
       return MechanicDate;
    }
    public StringProperty MECHANIC_NUMBERProperty()
    {
       return MechanicNumber;
    }     
          
          
    
    public void setIDnum(int idNum)
    {
        this.idNum.set(idNum);        
    }
        
    public void setMECHANIC_NAME(String MechanicName)
    {
        this.MechanicName.set(MechanicName);        
    }
    public void setMECHANIC_DATE(String MechanicDate)
    {
        this.MechanicDate.set(MechanicDate);        
    }
    
    public void setMECHANIC_NUMBER(String MechanicNumber)
    {
        this.MechanicNumber.set(MechanicNumber);        
    }
    
    public void setMECHANIC_RATE(double MechanicRate)
    {
        this.MechanicRate.set(MechanicRate);        
    }
    
    
    
    
}
