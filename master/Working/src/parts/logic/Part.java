/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

//import javafx.beans.property.SimpleStringProperty;
//mport javafx.beans.property.StringProperty;

/**
 *
 * @author sergio
 */
public class Part
{
    private int id;
    private String partName;
    private String partDesc;
    private int amount;
    private double cost;
    
    public Part(int id, String partName, String partDesc, int amount, double cost)
    {
        this.id = id;
        this.partName = partName;
        this.partDesc = partDesc;
        this.amount = amount;
        this.cost= cost;
    }
    
    public int getID()
    {
        return id;
    }
    
    public String getpartName()
    {
        return partName;
    }
    
    public String getpartDesc()
    {
        return partDesc;
    }
    
    public int getPartAmount()
    {
        return amount;
    }
    
    public double getpartCost()
    {
        return cost;
    }
    
   
}

