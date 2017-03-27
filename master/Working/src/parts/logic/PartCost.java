/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;
/**/
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
public class PartCost
{
    private IntegerProperty id;
    private DoubleProperty cost;
    
    
   public PartCost(int id,double cost)
    {
        this.id =  new SimpleIntegerProperty(id);
        this.cost =  new SimpleDoubleProperty(cost);
        
    }
  
  
   
     
}
   

