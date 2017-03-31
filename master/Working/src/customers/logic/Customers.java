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
    private StringProperty firstName;
    private StringProperty surname;
    private StringProperty address;
    private StringProperty postCode;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty type;
    private StringProperty vehicleReg;
    
    public Customers(int id, String firstName, String surname,String address, String postCode, String phone, String email, String type)
    {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
        this.address = new SimpleStringProperty(address);
        this.postCode = new SimpleStringProperty(postCode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
    }
    
    public Customers(String firstName ,String surname ,String address, String postCode, String phone, String email, String type)
    {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
        this.address = new SimpleStringProperty(address);
        this.postCode = new SimpleStringProperty(postCode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
    }
    
    public Customers(int id, String firstName, String surname, String address, String postCode, String phone, String email, String type, String vehicleReg)
    {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
        this.address = new SimpleStringProperty(address);
        this.postCode = new SimpleStringProperty(postCode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
        this.vehicleReg = new SimpleStringProperty(vehicleReg);
    }
    
    public Customers(int id, String vehicleReg)
    {
          this.id = new SimpleIntegerProperty(id);
          this.vehicleReg = new SimpleStringProperty(vehicleReg);
    }
    
    public int getID()
    {
        return id.get();
    }
    
    public String getFirstName()
    {
        return firstName.get();
    }
    
    public String getSurname()
    {
        return surname.get();
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
    
    public String getVehicleReg()
    {
        return this.vehicleReg.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public void setSurname(String surename){
        this.surname.set(surename);
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
    
    public void setVehicleReg(String vehicleReg)
    {
        this.vehicleReg.set(vehicleReg);
    }

    public IntegerProperty getId() {
        return id;
    }
    
    public StringProperty FirstNameProperty(){
        return firstName;
    }
    
    public StringProperty SurnameProperty(){
        return surname;
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
    
    public StringProperty TypeVehicleReg()
    {
        return vehicleReg;
    }
}
