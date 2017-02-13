/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sergio
 */
public class Part
{
    private int id;
    private StringProperty partName;
    private StringProperty partDesc;
    private int amount;
    private double cost;
    
   public Part(int id, String partName, String partDesc, int amount, double cost)
    {
        this.id = id;
        this.partName = new SimpleStringProperty(partName);
        this.partDesc= new SimpleStringProperty(partDesc);
        this.amount= amount;
        this.cost=cost;
    }
    
    public int getID()
    {
        return id;
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
        
        return cost;
    }
    
    public int getAmount()
    {
        return amount;
    }
    
    public StringProperty partNameProperty()
    {
        return partName;
    }
    
    public StringProperty partDescProperty()
    {
       return partDesc;
    }
    
   
}
