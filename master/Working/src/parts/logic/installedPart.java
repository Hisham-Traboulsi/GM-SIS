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
    private StringProperty PART_NAME;
    private IntegerProperty BOOKING_ID;
   
    
    
    
   public installedPart(int INST_ID, String REG_NUM, String INST_DATE, String EXP_DATE,String PART_NAME, 
           int BOOKING_ID)
           
    {
        this.INST_ID =  new SimpleIntegerProperty(INST_ID);
        this.PART_NAME =  new SimpleStringProperty(PART_NAME);
   
        
        this.REG_NUM = new SimpleStringProperty(REG_NUM);
        this.INST_DATE = new SimpleStringProperty(INST_DATE);
        this.EXP_DATE= new SimpleStringProperty(EXP_DATE);
        this.BOOKING_ID = new SimpleIntegerProperty(BOOKING_ID);
        
    }
    
    /**
     *
     * @return
     */
    public int getINST_ID()
    {
        return INST_ID.get();
    }
    
    public String getPART_NAME()
    {
        return PART_NAME.get();
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
      public int getBOOKING_ID()
    {
        return BOOKING_ID.get();
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
    public IntegerProperty BOOKING_ID()
    {
       return BOOKING_ID;
    }
     
    
    
    
    
    public void setINST_ID(int INST_ID)
    {
        this.INST_ID.set(INST_ID);        
    }
    public void setPART_NAME(String PART_NAME)
    {
        this.PART_NAME.set(PART_NAME);        
    }
    
   
    
  
  
    public void setREG_NUM(String REG_NUM)
    {
        this.REG_NUM.set(REG_NUM);
    }
     public void setINST_DATE(String INST_DATE)
    {
        this.INST_DATE.set(INST_DATE);
    }
    
      public void setBOOKING_ID(int BOOKING_ID)
    {
        this.BOOKING_ID.set(BOOKING_ID);
    }
       
    
       public void setEXP_DATE(String EXP_DATE)
    {
        this.EXP_DATE.set(EXP_DATE);
    }
    
    
}
   

