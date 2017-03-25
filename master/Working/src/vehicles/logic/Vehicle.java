/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ugonw
 */
public class Vehicle {
    
    private IntegerProperty id;
    private StringProperty regnum;
    private StringProperty model;
    private StringProperty make;
    private StringProperty engine;
    private StringProperty fueltype;
    private StringProperty colour;
    private StringProperty motdate;
    private StringProperty lastservice;
    private StringProperty mileage;
    private StringProperty warranty;
    private StringProperty warrantycompany;
    private StringProperty warrantyaddress;
    private StringProperty warrantyexpiry;
            
    
   
    public Vehicle(int id, String regnum, String model , String make, String engine, String fueltype, String colour, String motdate, String lastservice, String mileage, String warranty, String warrantycompany, String warrantyaddress, String warrantyexpiry)
    {
        this.id = new SimpleIntegerProperty(id);
        this.regnum= new SimpleStringProperty(regnum);
        this.model=  new SimpleStringProperty(model);
        this.make = new SimpleStringProperty(make);
        this.engine = new SimpleStringProperty(engine);
        this.fueltype = new SimpleStringProperty(fueltype);
        this.colour = new SimpleStringProperty(colour);
        this.motdate = new SimpleStringProperty(motdate);
        this.lastservice = new SimpleStringProperty(lastservice);
        this.mileage = new SimpleStringProperty(mileage);
        this.warranty = new SimpleStringProperty(warranty);
        this.warrantycompany = new SimpleStringProperty(warrantycompany);
        this.warrantyaddress = new SimpleStringProperty(warrantyaddress);
        this.warrantyexpiry = new SimpleStringProperty(warrantyexpiry);
    }
    
    public int getID()
    {
        return id.get();
    }
    
    public String getRegnum()
    {
        return regnum.get();
    }
    
     public String getModel()
    {
        return model.get();
    
    }
    
    public String getMake()
    {
        return make.get();
    }
    
    public String getEngine()
    {
        return engine.get();
    }
    
    public String getFuelType()
    {
        return fueltype.get();
    }
    
    public String getColour(){
        
        return colour.get();
    }
    
    public String getMotDate()
    {
        return motdate.get();
    }
    
    public String getLastService()
    {
        return lastservice.get();
    }
    
    public String getMileage()
    {
        return mileage.get();
    }
    
    public String getWarranty()
    {
        return warranty.get();
    }
    
    public String getWarrantyCompany()
    {
        return warrantycompany.get();
    }
    
    public String getWarrantyAddress()
    {
        return warrantyaddress.get();
    }
    
    public String getWarrantyExpiry()
    {
        return warrantyexpiry.get();
    }
    
    ////
    public IntegerProperty idProperty() {
        return id;
    }
    
    public StringProperty regnumProperty()
    {
       return regnum;
    }
    
    public StringProperty modelProperty()
    {
       return model;
    }
    
    public StringProperty makeProperty()
    {
       return make;
    }
    
    public StringProperty engineProperty()
    {
       return engine;
    }
    
    public StringProperty fueltypeProperty()
    {
       return fueltype;
    }
    
    public StringProperty colourProperty()
    {
       return colour;
    }
    
    public StringProperty motdateProperty()
    {
       return motdate;
    }
    
    public StringProperty lastserviceProperty()
    {
       return lastservice;
    }
    
    public StringProperty mileageProperty()
    {
       return mileage;
    }
    
    public StringProperty warrantyProperty()
    {
       return warranty;
    }
    
    public StringProperty warrantycompanyProperty()
    {
       return warrantycompany;
    }
    
    public StringProperty warrantyaddressProperty()
    {
       return warrantyaddress;
    }
    
    public StringProperty warrantyexpiryProperty()
    {
       return warrantyexpiry;
    }

    ////
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public void setRegNum(String regnum)
    {
        this.regnum.set(regnum);
    }
    
    public void setModel(String model)
    {
        this.model.set(model);
    }
    
    public void setMake(String make)
    {
        this.make.set(make);        
    }
    
    public void setEngine(String engine)
    {
        this.engine.set(engine);
    }
    
    public void setFuelType(String fueltype)
    {
        this.fueltype.set(fueltype);
    }
            
    public void setColour(String colour)
    {
        this.colour.set(colour);
    }
    
    public void setMotDate(String motdate)
    {
        this.motdate.set(motdate);
    }
    
    public void setLastService(String lastservice)
    {
        this.lastservice.set(lastservice);
    }
   
    public void setMileage(String mileage)
    {
        this.mileage.set(mileage);
    }

    public void setWarranty(String warranty)
    {
        this.warranty.set(warranty);
    }
    
    public void setWarrantyCompany(String warrantycompany)
    {
        this.warrantycompany.set(warrantycompany);
    }
    
    public void setWarrantyAddress(String warrantyaddress)
    {
        this.warrantyaddress.set(warrantyaddress);
    }
    
    public void setWarrantyExpiry(String warrantyexpiry)
    {
        this.warrantyexpiry.set(warrantyexpiry);
    }
    
}
