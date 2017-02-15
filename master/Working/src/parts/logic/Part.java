/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

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
public class Part
{
    private IntegerProperty id;
    private StringProperty partName;
    private StringProperty partDesc;
    private IntegerProperty amount;
    private DoubleProperty cost;
    
   public Part(int id, String partName, String partDesc, int amount, double cost)
    {
        this.id =  new SimpleIntegerProperty(id);
        this.partName = new SimpleStringProperty(partName);
        this.partDesc= new SimpleStringProperty(partDesc);
        this.amount=  new SimpleIntegerProperty(amount);
        this.cost=new SimpleDoubleProperty(cost);
    }
    
    public int getID()
    {
        return id.get();
    }
    
    public String getpartName()
    {
        return partName.get();
    }
    
    public String getpartDesc()
    {
        return partDesc.get();
    
    }
    
    public double getCost(){
        
        return cost.get();
    }
    
    public int getAmount()
    {
        return amount.get();
    }
    /////
    public StringProperty partNameProperty()
    {
        return partName;
    }
    
    public StringProperty partDescProperty()
    {
       return partDesc;
    }
    //
    public void setID(int id)
    {
        this.id.set(id);        
    }
    
    public void setpartName(String partName)
    {
        this.partName.set(partName);
    }
    
    public void setpartDesc(String partDesc)
    {
        this.partDesc.set(partDesc);
    }
    
    public void setAmount(int amount)
    {
        this.amount.set(amount);
    }
    
    public void setCost(double cost)
    {
        this.cost.set(cost);
    }
}
   

