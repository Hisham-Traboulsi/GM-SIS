/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hisha
 */
public class Customers 
{
    private IntegerProperty id;
    private StringProperty fullName;
    private StringProperty vehicleReg;
    private StringProperty address;
    private StringProperty postCode;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty type;
    
    public Customers(int id, String fullName, String vehicleReg, String address, String postCode, String phone, String email, String type)
    {
        this.id = new SimpleIntegerProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.vehicleReg = new SimpleStringProperty(vehicleReg);
        this.address = new SimpleStringProperty(address);
        this.postCode = new SimpleStringProperty(postCode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
    }
    
    public int getID()
    {
        return id.get();
    }
    
    public String getFullName()
    {
        return fullName.get();
    }
    
    public String getVehicleReg()
    {
        return vehicleReg.get();
    }
    
    public String getAddress()
    {
        return this.address.get();
    }
    
    public String getPostCode()
    {
       return this.postCode.get();
    }
    
    public String getPhone()
    {
        return this.phone.get();
    }
    
    public String getEmail()
    {
        return this.email.get();
    }
    
    public String getType()
    {
        return this.type.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg.set(vehicleReg);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public IntegerProperty getId() {
        return id;
    }

    public StringProperty VehicleRegProperty() {
        return vehicleReg;
    }

    public StringProperty AddressProperty() {
        return address;
    }

    public StringProperty PostCodeProperty() {
        return postCode;
    }

    public StringProperty PhoneProperty() {
        return phone;
    }

    public StringProperty EmailProperty() {
        return email;
    }

    public StringProperty TypeProperty() {
        return type;
    }
    
    
    
    
    
}