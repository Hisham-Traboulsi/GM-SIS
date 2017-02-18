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
public class installedPart
{
    private IntegerProperty INST_ID;
    private StringProperty REG_NUM;
    private StringProperty INST_DATE;
    private StringProperty EXP_DATE;
    private IntegerProperty PART_ID;
    private StringProperty CUST_NAME;
    private IntegerProperty VEHICLE_ID;
    private DoubleProperty PART_COST;
    
    
   public installedPart(int INST_ID, String REG_NUM, String INST_DATE, String EXP_DATE,int PART_ID, 
           String CUST_NAME,int VEHICLE_ID, double PART_COST)
           
    {
        this.INST_ID =  new SimpleIntegerProperty(INST_ID);
        this.PART_ID =  new SimpleIntegerProperty(PART_ID);
        this.VEHICLE_ID =  new SimpleIntegerProperty(VEHICLE_ID);
        this.PART_COST =  new SimpleDoubleProperty(PART_COST);
        this.REG_NUM = new SimpleStringProperty(REG_NUM);
        this.INST_DATE = new SimpleStringProperty(INST_DATE);
        this.EXP_DATE= new SimpleStringProperty(EXP_DATE);
        this.CUST_NAME = new SimpleStringProperty(CUST_NAME);
        
    }
    
    /**
     *
     * @return
     */
    public int getINST_ID()
    {
        return INST_ID.get();
    }
    
    public int getPART_ID()
    {
        return PART_ID.get();
    }
    public int getVEHICLE_ID()
    {
        return VEHICLE_ID.get();
    }
    public double getPART_COST()
    {
        return PART_COST.get();
    }
    public String getREG_NUM()
    {
        return REG_NUM.get();
    }
    
    public String getINST_DATE()
    {
        return INST_DATE.get();
    }
     public String getEXP_DATE()
    {
        return EXP_DATE.get();
    }
      public String getCUST_NAME()
    {
        return CUST_NAME.get();
    }
       
    /*
      
      
      
      
      
      
      */
    public StringProperty REG_NUM()
    {
        return REG_NUM;
    }
    
    public StringProperty INST_DATE()
    {
       return INST_DATE;
    }
    public StringProperty EXP_DATE()
    {
       return EXP_DATE;
    }
    public StringProperty CUST_NAME()
    {
       return CUST_NAME;
    }
    /*
    
    
    
    */
    public void setINST_ID(int INST_ID)
    {
        this.INST_ID.set(INST_ID);        
    }
    public void setPART_ID(int PART_ID)
    {
        this.PART_ID.set(PART_ID);        
    }
    
    public void setVEHICLE_ID(int VEHICLE_ID)
    {
        this.VEHICLE_ID.set(VEHICLE_ID);        
    }
    
    public void setPART_COST(double PART_COST)
    {
        this.PART_COST.set(PART_COST);        
    }
  
    public void setREG_NUM(String REG_NUM)
    {
        this.REG_NUM.set(REG_NUM);
    }
     public void setINST_DATE(String INST_DATE)
    {
        this.INST_DATE.set(INST_DATE);
    }
    
      public void setCUST_NAME(String CUST_NAME)
    {
        this.CUST_NAME.set(CUST_NAME);
    }
    
       public void setEXP_DATE(String EXP_DATE)
    {
        this.EXP_DATE.set(EXP_DATE);
    }
    
    
}
   

