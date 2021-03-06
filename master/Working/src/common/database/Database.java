/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.database;

import common.logic.SystemUser;
import customers.logic.Accounts;
import customers.logic.Customers;
import vehicles.logic.Vehicle;
import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import parts.logic.*;
import parts.logic.installedPart;
import specialist.logic.SPC;
import javafx.scene.control.ComboBox;
import specialist.logic.Outstanding;
import specialist.logic.OutstandingVehicle;
import specialist.logic.Returned;
import parts.logic.partLog;
import specialist.logic.ReturnedVehicle;

import diagrep.logic.Bookings;
import diagrep.logic.Mec;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
//import parts.logic.PartsController;

/**
 *
 * @author hisha
 */
public final class Database 
{
    private static final String DB_File_Name = "GMSIS.db";
    
    private static final Database db = new Database(DB_File_Name);
    
    private Connection conn;
    
    private PreparedStatement stmt;
    
    private ObservableList<SystemUser> usersData;
    private ObservableList<Customers> customerData;
    private ObservableList<String> customerVehicles;
    private ObservableList<Accounts> customerAccounts;
    private ObservableList<String> customerStatus;
    private ObservableList<String> listOfParts;
    private ObservableList<Part> partsData;
    private ObservableList<Part> partUsedData;
    private ObservableList<Vehicle> vehicleData;
    private ObservableList<Vehicle> searchVehicleData;
    
    private ObservableList<installedPart> installedPartsData;
    private ObservableList<installedPart> searchPartsData;
    private ObservableList<SPC> spcData;
    private ObservableList<Outstanding> outPartsData;
    private ObservableList<Returned> retPartsData;
    private ObservableList<OutstandingVehicle> outVehicleData;
    private ObservableList<partLog> deliveredData;
    private ObservableList<partLog> withdrawnData;
    private ObservableList<partBooking> bookingdata;
    private ObservableList<OutstandingVehicle> outVehicleSearchData;
    private ObservableList<Outstanding> outPartSearchData;
    private ObservableList<ReturnedVehicle> retVehicleData;    
    private ComboBox regComb;
    private ComboBox regCombReg;
    private ComboBox regCombCustName;
    private ComboBox IDComb;
    private ObservableList<Bookings> BookingsData;
    private ObservableList<Mec> MechanicData;
    private ObservableList<Bookings> searchBookingData;
    private Database(String DBFileName)
    {
        System.out.println("Trying to connect to the database");
        try
        {
            //Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);
            //conn.setAutoCommit(false);
            System.out.println("Connection successful");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Database connection failed");
            //throw new RuntimeException("Database connection failed!", ex);
        } 
    }
    
    public PreparedStatement preparedStatement(String sqlStmt)
    {
        //System.out.println("We are in the prepared statement method");
        PreparedStatement stmt = null;
        try 
        {
            stmt = conn.prepareStatement(sqlStmt);
            return stmt;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Unable to execute statement");
            return stmt;
        }
    }
    
    public boolean authentication(int ID, String password)
    {
        //System.out.println("We are in the authentication module");
        PreparedStatement auth = null;
        boolean check_access = false;
        try
        {
            auth = preparedStatement("SELECT * FROM AUTHENTICATION WHERE ID = ? AND PASSWORD = ?");
            //System.out.println("We are out side the prepared statement");
            auth.setInt(1, ID);
            auth.setString(2, password);
            
            ResultSet rs = auth.executeQuery();
            
            int count = 0;
            
            while(rs.next())
            {
                count++;
            }
            
            if(count == 1)
            {
                check_access = true;
            }
            System.out.println(count);
            
            auth.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return check_access;
    }
    
    public boolean isAdmin(int id) throws SQLException
    {
        PreparedStatement admin = preparedStatement("SELECT ADMIN FROM AUTHENTICATION WHERE ID=?");
        
        admin.setInt(1, id);
        
        ResultSet rs = admin.executeQuery();
        
        String checkAdmin = "";
        
        while(rs.next())
        {
           checkAdmin = rs.getString(1);
        }
        
        if(checkAdmin.equals("Y"))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public boolean addSysUser(String firstName, String surname, String password, String admin)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO AUTHENTICATION VALUES (?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, firstName);
           add.setString(3, surname);
           add.setString(4, password);
           add.setString(5, admin);
  
           add.execute();
           add.close();
           added = true;
           
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
    
    public ObservableList<SystemUser> getAllUsers() throws SQLException
    {   
        PreparedStatement allUsersStmt = null;
        
         usersData = FXCollections.observableArrayList();
        
       
        allUsersStmt = preparedStatement("SELECT * FROM AUTHENTICATION");
        ResultSet rs = allUsersStmt.executeQuery();
        
        while(rs.next())
        {
            int id = rs.getInt("ID");
            String firstName = rs.getString("FIRST_NAME");
            String surname = rs.getString("SURNAME");
            String password = rs.getString("PASSWORD");
            String admin = rs.getString("ADMIN");
            
            SystemUser sysUser = new SystemUser(id, firstName, surname, password, admin);
            
            usersData.add(sysUser);
        }
        return usersData;
    }
    
    public void editUser() throws SQLException
    {
        PreparedStatement editUserStmt = preparedStatement("UPDATE AUTHENTICATION SET FIRST_NAME=?, SURNAME=?, PASSWORD=?, ADMIN=? WHERE ID=?");
        int counter = 0;
        while(counter < usersData.size())
        {
            editUserStmt.setString(1, usersData.get(counter).getfirstName());
            editUserStmt.setString(2, usersData.get(counter).getSurname());
            editUserStmt.setString(3, usersData.get(counter).getPassword());
            editUserStmt.setString(4, usersData.get(counter).getAdmin());
            editUserStmt.setInt(5, usersData.get(counter).getID());
            
            editUserStmt.executeUpdate();
            
            counter++;
        }
        
        getAllUsers();
    }
    
    public void removeUser(int id) throws SQLException
    {
        PreparedStatement removeUserStmt = preparedStatement("DELETE FROM AUTHENTICATION WHERE ID=?");
        removeUserStmt.setInt(1, id);
        removeUserStmt.executeUpdate();
    }
    
    public ObservableList<Customers> getAllCustomers() throws SQLException
    {
        PreparedStatement allCustomersStmt = preparedStatement("SELECT * FROM CUSTOMERS"); 
                
        customerData = FXCollections.observableArrayList();
        ResultSet rs = allCustomersStmt.executeQuery();
        
        while(rs.next())
        {
            int id = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("FIRST_NAME");
            String surname = rs.getString("SURNAME");
            String address = rs.getString("ADDRESS");
            String postCode = rs.getString("POSTCODE");
            String phone = rs.getString("PHONE");
            String type = rs.getString("TYPE");
            String email = rs.getString("EMAIL");
            
            Customers customer = new Customers(id, firstName, surname, address, postCode, phone, email, type);
            
            customerData.add(customer);
        }
        return customerData;
    }
    
     public void removeCustomer(int id) 
    {
        try{
            PreparedStatement removeCustomerStmt = preparedStatement("DELETE FROM CUSTOMERS WHERE CUSTOMER_ID=?");
            removeCustomerStmt.setInt(1, id);
            removeCustomerStmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean addCustomer(String firstName, String surname,String address, String postCode, String phone, String email, String type)
    {
        PreparedStatement add = null;
        boolean added = false;
        
        try
        {
            add = preparedStatement("INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?,?,?)");
            add.setString(1, null);
            add.setString(2, firstName);
            add.setString(3, surname);
            add.setString(4, address);
            add.setString(5, postCode);
            add.setString(6, phone);
            add.setString(7, email);
            add.setString(8, type);
            
            add.execute();
            add.close();
            added = true;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return added;
    }
    
    public void editCustomer() throws SQLException
    {
        PreparedStatement editCustomerStmt = preparedStatement("UPDATE CUSTOMERS SET FIRST_NAME=?, SURNAME=?, ADDRESS=?, POSTCODE=?, PHONE=?, TYPE=?, EMAIL=? WHERE CUSTOMER_ID=?");
        int counter = 0;
        while(counter < customerData.size())
        {
            editCustomerStmt.setString(1, customerData.get(counter).getFirstName());
            editCustomerStmt.setString(2, customerData.get(counter).getSurname());
            editCustomerStmt.setString(3, customerData.get(counter).getAddress());
            editCustomerStmt.setString(4, customerData.get(counter).getPostCode());
            editCustomerStmt.setString(5, customerData.get(counter).getPhone());
            editCustomerStmt.setString(6, customerData.get(counter).getType());
            editCustomerStmt.setString(7, customerData.get(counter).getEmail());
            editCustomerStmt.setInt(8, customerData.get(counter).getID());
            
            editCustomerStmt.executeUpdate();
            
            counter++;
        }
        
        getAllCustomers();
    }
    
    public ObservableList<String> getCustomerVehicles(int customerID)
    {
        try 
        {
            PreparedStatement getCustomerVehilceStmt = preparedStatement("SELECT * FROM VEHICLE_RECORD WHERE CUSTOMER_ID = ?");
            
            getCustomerVehilceStmt.setInt(1, customerID);
            
            customerVehicles = FXCollections.observableArrayList();
            ResultSet rs = getCustomerVehilceStmt.executeQuery();
            while(rs.next())
            {
                String vehicleReg = rs.getString("REG_NUM");
                customerVehicles.add(vehicleReg);
            }
            return customerVehicles;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return customerVehicles;
        }
    }
    
   public ObservableList<Accounts> getCustomerAccountsData(int customerID)
   {
       try
       {
           PreparedStatement getCustomerAccountsDataStmt = preparedStatement("SELECT IDnum, REG_NUM, TIME, COST FROM DIAGNOSIS_REPAIR_BOOKINGS WHERE CUSTOMERID ="+customerID);
           
           customerAccounts = FXCollections.observableArrayList();
           ResultSet rs = getCustomerAccountsDataStmt.executeQuery();
           
           while(rs.next())
           {    System.out.println("A");
               int bookingID = rs.getInt("IDnum");
               String vehicleReg = rs.getString("REG_NUM");
               String bookingDate = rs.getString("TIME");
               double cost = rs.getDouble("COST");
               
               Accounts acc = new Accounts(customerID, bookingID, vehicleReg, bookingDate, cost);
               
               customerAccounts.add(acc);
               
           }
            return customerAccounts;
       }
       catch(SQLException ex)
       {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
           return customerAccounts;

       }
   }
   
   public void addStatus(int customerID, int bookingID) 
   {
        try 
        {
            System.out.println("Add status");
            String newBookingStatus = "UNPAID";
            PreparedStatement addStatusStmt = preparedStatement("INSERT INTO ACCOUNTS VALUES (?,?,?)");
            
            addStatusStmt.setInt(1, customerID);
            addStatusStmt.setInt(2, bookingID);
            addStatusStmt.setString(3, newBookingStatus);
            
            addStatusStmt.execute();
            addStatusStmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public ObservableList<String> getStatus(int customerID)
   {
       try
       {
           PreparedStatement getStatusStmt = preparedStatement("SELECT STATUS FROM ACCOUNTS WHERE CUSTOMER_ID=?");
           getStatusStmt.setInt(1, customerID);
           
           customerStatus = FXCollections.observableArrayList();
           ResultSet rs = getStatusStmt.executeQuery();
           
           while(rs.next())
           {
               String status = rs.getString("STATUS");
               
               customerStatus.add(status);
           }
           
       }
       catch(SQLException ex)
       {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return customerStatus;
   }
   
   private int getMaxBookingID() throws SQLException
   {    System.out.println("In max booking id");
       int maxID = 0;
        try 
        {   
            PreparedStatement id = preparedStatement("SELECT IDnum FROM DIAGNOSIS_REPAIR_BOOKINGS WHERE IDnum=(SELECT MAX(IDnum) FROM DIAGNOSIS_REPAIR_BOOKINGS)");
            ResultSet rs = id.executeQuery();
                if(rs.next())
                {
                    maxID = rs.getInt("IDnum");
                }
                
            
             
            System.out.println(maxID);
            return maxID;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return maxID;
        }
   }
   
   public void editBookingStatus(int customerID, int bookingID, String status)
   {
        try 
        {
            PreparedStatement editBookingStatusStmt = preparedStatement("UPDATE ACCOUNTS SET STATUS=? WHERE BOOKING_ID=?");
            editBookingStatusStmt.setString(1, status);
            editBookingStatusStmt.setInt(2, bookingID);
            
            editBookingStatusStmt.executeUpdate();
            
            getStatus(customerID);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public int getCustomerVehicles(String vehicleReg)
    {
        int customerID = 0;
        try 
        {
            PreparedStatement getCustomerVehilceStmt = preparedStatement("SELECT CUSTOMER_ID FROM VEHICLE_RECORD WHERE REG_NUM = ?");
            
            getCustomerVehilceStmt.setString(1, vehicleReg);
            
            ResultSet rs = getCustomerVehilceStmt.executeQuery();
            while(rs.next())
            {
                customerID = rs.getInt("CUSTOMER_ID");
            }
            return customerID;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return customerID;
        }
    }
   
   public void deleteStatus(int booking_ID) throws SQLException
   {
         PreparedStatement deleteStatusStmt = preparedStatement("DELETE FROM ACCOUNTS WHERE BOOKING_ID="+ booking_ID);
         deleteStatusStmt.executeUpdate();
   }
   
   public ObservableList<String> getListOfParts(int booking_ID) throws SQLException
   {
       listOfParts = FXCollections.observableArrayList();
       PreparedStatement getListOfPartsStmt = preparedStatement("SELECT PART_NAME FROM PARTS_INSTALLATION WHERE BOOKING_ID="+ booking_ID);
       ResultSet rs = getListOfPartsStmt.executeQuery();
      
       while(rs.next())
       {
           String partName = rs.getString("PART_NAME");
           
           listOfParts.add(partName);
       }
       
       return listOfParts;
   }
    
    /*Author Sergio*/
   public void addPart(String name, String description, int amount, double cost) throws NumberFormatException
    {   if(checkPartName(name))
    {
                      PreparedStatement add = null;
                     // boolean added = false;
                      try
                      {
                         add = preparedStatement("INSERT INTO PARTS_TRACKING VALUES (?, ?, ?, ?, ?)"); 
                         add.setString(1, null);
                         add.setString(2, name.trim());
                         add.setString(3, description.trim());
                         add.setInt(4, amount);
                         add.setDouble(5, cost);

                         add.execute();
                         add.close();
                        // added = true;
                         JOptionPane.showMessageDialog(null,"Part successfully added");
                      }
                        catch(SQLException ex)
                     {
                         JOptionPane.showMessageDialog(null,"Error, try again");
                         ex.printStackTrace();
                         System.err.println("Unable to access table or table doesnt exist");
                     }
    }
            
    }
   public boolean checkPartName(String partname){
       boolean check=true;
       try{
            
           
            PreparedStatement checkP = null;
            checkP = preparedStatement("SELECT NAME FROM PARTS_TRACKING");      
            ResultSet rs = checkP.executeQuery();
            
             while(rs.next())
                 {
                    String pn=rs.getString("NAME"); 
                
                      if(partname.equalsIgnoreCase(pn))
                        {
                            check=false;
                            JOptionPane.showMessageDialog(null," A part with that name already exists, enter a different name");
                            break;
                        } 
                }
        
           }
       catch(SQLException ex)
       {
                
        }
       
       return check;
   }
    /*Author Sergio*/
    public void addInstalledPart( String REG_NUM, String INST_DATE, 
            String EXP_DATE,String PART_NAME, int BOOKING_ID)
    {
        String newReg="";
        int BOOKINGID=BOOKING_ID;
        String partname=PART_NAME;
        try{
        PreparedStatement reg = null;
        reg = preparedStatement(" SELECT REG_NUM FROM 'DIAGNOSIS_REPAIR_BOOKINGS' WHERE IDnum =" + BOOKING_ID +" ");

        ResultSet rs = reg.executeQuery();

         newReg=rs.getString("REG_NUM");
        // System.out.println(newReg);
        
        }
        catch(SQLException ex){}
        catch(NullPointerException ex)
                {
                    JOptionPane.showMessageDialog(null,"We could not find a registration number assigned to that booking");
                }
       if(maxParts(newReg,BOOKINGID,partname))
       {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO PARTS_INSTALLATION VALUES (?, ?, ?, ?, ?, ? )"); 
           add.setString(1, null);
           add.setString(2, newReg);
           add.setString(3, INST_DATE);
           add.setString(4, EXP_DATE);
           add.setString(5, PART_NAME);
           add.setInt(6, BOOKING_ID);
           
           
  
           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Part successfully installed");
           calculateBill(REG_NUM,BOOKING_ID);
           
        }
       
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        catch(NullPointerException ex){
                      JOptionPane.showMessageDialog(null,"We could not find a registration number assigned to that booking");
              }
    }
 }
   
    
    /*
    Author Sergio Arrieta
    */
    public void addDelivery(String name)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        java.util.Date dateobj = new java.util.Date();
        String date = (df.format(dateobj));
        
        PreparedStatement add = null;
     
        try
        {
           add = preparedStatement("INSERT INTO PARTS_DELIVERIES VALUES (?, ?)"); 
           add.setString(1, date);
           add.setString(2, name);
           
           add.execute();
           add.close();

        }
        catch(SQLException ex)
        {
        }

    }
    /*
    Author Sergio Arrieta
    */
    public void addWithdrawal(String name)
    {
        PreparedStatement add = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        java.util.Date dateobj = new java.util.Date();
        String date = (df.format(dateobj));
        
        try
        {
           add = preparedStatement("INSERT INTO PARTS_WITHDRAWALS VALUES (?, ?)"); 
           add.setString(1, date);
           add.setString(2, name);
           
           add.execute();
           add.close();
           
           
           
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }

    }
    /*
    Author Sergio Arrieta
    */
    public ObservableList<partLog> getDeliveryLog() 
    {
        PreparedStatement getPart = null;
        deliveredData = FXCollections.observableArrayList();
        
       try{
        getPart = preparedStatement("SELECT * FROM PARTS_DELIVERIES");
        ResultSet rs = getPart.executeQuery();
        
        while(rs.next())
        {
            
            String date = rs.getString("Date");
            String name = rs.getString("Name");
            
            partLog part = new partLog(date,name);
            
            deliveredData.add(part);
        }
        
       }
       catch(SQLException ex)
       {
       }
        return deliveredData;
    }
    /*
    Author Sergio Arrieta
    */
    public ObservableList<partLog> getWithdrawalLog()
    {
        try{
         PreparedStatement getPart = null;
         withdrawnData = FXCollections.observableArrayList();
        
       
        getPart = preparedStatement("SELECT * FROM PARTS_WITHDRAWALS");
        ResultSet rs = getPart.executeQuery();
        
        while(rs.next())
        {
            
            String date = rs.getString("Date");
            String name = rs.getString("Name");
            
            partLog part = new partLog(date,name);
            
            withdrawnData.add(part);
        }
        }
        catch(SQLException ex)
        {
        }
        
        return withdrawnData;
        
    }
     /*Author Sergio*/
    public ObservableList<Part> getPartDetails(String partname) throws NullPointerException
    {   
        try{
        PreparedStatement getPart = null;
        partUsedData = FXCollections.observableArrayList();
        
       
        getPart = preparedStatement("SELECT * FROM PARTS_TRACKING WHERE NAME='" + partname +"'");
        ResultSet rs = getPart.executeQuery();
        
        while(rs.next())
        {
            int id = rs.getInt("RELEVANT_ID_NUM");
            System.out.println(id);
            String partName = rs.getString("NAME");
            System.out.println(partName);
            String partDesc = rs.getString("DESCRIPTION");
            System.out.println(partDesc);
            int partAmount = rs.getInt("AMOUNT");
            System.out.println(partAmount);
            double partCost = rs.getDouble("COST");
            System.out.println(partCost);
            
            Part part = new Part(id, partName, partDesc, partAmount, partCost);
            
            partUsedData.add(part);
        }
        }
        catch(SQLException ex){
            
        }
        return partUsedData;
    }
    /*Author Sergio*/
    public ObservableList<Part> getPart() throws SQLException
    {   
        
        PreparedStatement getPart = null;
        partsData = FXCollections.observableArrayList();
        
       
        getPart = preparedStatement("SELECT * FROM PARTS_TRACKING");
        ResultSet rs = getPart.executeQuery();
        
        while(rs.next())
        {
            int id = rs.getInt("RELEVANT_ID_NUM");
            String partName = rs.getString("NAME");
            String partDesc = rs.getString("DESCRIPTION");
            int partAmount = rs.getInt("AMOUNT");
            double partCost = rs.getDouble("COST");
            
            Part part = new Part(id, partName, partDesc, partAmount, partCost);
            
            partsData.add(part);
        }
        return partsData;
    }
     /*Author Sergio*/
    public ObservableList<installedPart> getinstalledPart() throws SQLException
    {   
        
        PreparedStatement getPart = null;
        installedPartsData = FXCollections.observableArrayList();
        
       
        getPart = preparedStatement("SELECT * FROM PARTS_INSTALLATION");
        ResultSet rs = getPart.executeQuery();
        
        while(rs.next())
        {
            int INST_ID = rs.getInt("INSTALLATION_ID");
            String REG_NUM = rs.getString("REG_NUM");
            String INST_DATE = rs.getString("INSTALLATION_DATE");
            String EXP_DATE= rs.getString("EXP_DATE");
            String PART_NAME = rs.getString("PART_NAME");
            int BOOKING_ID = rs.getInt("BOOKING_ID");
            
            
            
            
            installedPart installedPart = new installedPart(INST_ID, REG_NUM, INST_DATE, 
            EXP_DATE,PART_NAME, BOOKING_ID);
            
            installedPartsData.add(installedPart);
        }
        return installedPartsData;
    }
      /*Author Sergio*/
    public ObservableList<installedPart> searchInstalledPart(String searchVal ) 
    {   
        try{
        PreparedStatement searchInstalledPart = null;
        searchPartsData = FXCollections.observableArrayList();
        
      
        searchInstalledPart = preparedStatement("select * from PARTS_INSTALLATION where (BOOKING_ID,REG_NUM) in (select CUSTOMER_ID,REG_NUM from CUSTOMERS,DIAGNOSIS_REPAIR_BOOKINGS where CUSTOMERS.FIRST_NAME like '%" + searchVal + "%' OR CUSTOMERS.SURNAME LIKE'%" + searchVal +"%' OR DIAGNOSIS_REPAIR_BOOKINGS.REG_NUM LIKE '%" + searchVal + "%')");
        
        //searchInstalledPart.setString(1,searchVal);
        
        
        ResultSet rs = searchInstalledPart.executeQuery();
        
        while(rs.next())
        {
            int INST_ID = rs.getInt("INSTALLATION_ID");
            String REG_NUM = rs.getString("REG_NUM");
            String INST_DATE = rs.getString("INSTALLATION_DATE");
            String EXP_DATE= rs.getString("EXP_DATE");
            String PART_NAME = rs.getString("PART_NAME");
            int BOOKING_ID = rs.getInt("BOOKING_ID");

            installedPart searchedPart = new installedPart(INST_ID, REG_NUM, INST_DATE, 
            EXP_DATE,PART_NAME, BOOKING_ID);
            
            searchPartsData.add(searchedPart);
        }
        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        return searchPartsData;
    }
     /*Author Sergio*/
    public ObservableList<String> fillRegCombo()
    {
        
        ObservableList<String> regComb1 = FXCollections.observableArrayList();
        try{
         PreparedStatement fill = preparedStatement("SELECT NAME FROM PARTS_TRACKING");
         ResultSet rs = fill.executeQuery();
         while(rs.next())
            {
              regComb1.add(rs.getString("NAME"));
            }
               
        }
        catch(SQLException ex)
        {
        }
           return regComb1;
    }
   /*     
    author sergio
    */ 
    public ObservableList<Integer> fillIDcombo()
    {
        
        ObservableList<Integer> regComb1 = FXCollections.observableArrayList();
        try{
         PreparedStatement fill = preparedStatement("SELECT CUSTOMER_ID FROM CUSTOMERS");
         ResultSet rs = fill.executeQuery();
         while(rs.next())
            {
              regComb1.add(rs.getInt("CUSTOMER_ID"));
            }
               
        }
        catch(SQLException ex)
        {
        }
           return regComb1;
    }
    public ObservableList<Integer> fillBookingIDcombo()
    {
        
        ObservableList<Integer> regComb1 = FXCollections.observableArrayList();
        try{
         PreparedStatement fill = preparedStatement("SELECT IDnum FROM DIAGNOSIS_REPAIR_BOOKINGS");
         ResultSet rs = fill.executeQuery();
         while(rs.next())
            {
              regComb1.add(rs.getInt("IDnum"));
            }
               
        }
        catch(SQLException ex)
        {
        }
           return regComb1;
    }
    /*
    Author Sergio Arrieta
    */
    public ObservableList<String> fillNumCombo()
    {
        
        ObservableList<String> regComb1 = FXCollections.observableArrayList();
        try{
         PreparedStatement fill = preparedStatement("SELECT REG_NUM FROM VEHICLE_RECORD");
         ResultSet rs = fill.executeQuery();
         while(rs.next())
            {
              regComb1.add(rs.getString("REG_NUM"));
            }
               
        }
        catch(SQLException ex)
        {
        }
           return regComb1;
    }
    /*
    Author Sergio Arrieta
    */
    public ObservableList<String> fillNumComboBook()
    {
        
        ObservableList<String> regComb1 = FXCollections.observableArrayList();
        try{
         PreparedStatement fill = preparedStatement("SELECT REG_NUM FROM DIAGNOSIS_REPAIR_BOOKINGS");
         ResultSet rs = fill.executeQuery();
         while(rs.next())
            {
              regComb1.add(rs.getString("REG_NUM"));
            }
               
        }
        catch(SQLException ex)
        {
        }
           return regComb1;
    }
    
    public ObservableList<String> fillNumComboBook2()
    {
        
        ObservableList<String> regComb1 = FXCollections.observableArrayList();
        try{
         PreparedStatement fill = preparedStatement("SELECT IDnum FROM MECHANIC");
         ResultSet rs = fill.executeQuery();
         while(rs.next())
            {
              regComb1.add(rs.getString("IDnum"));
            }
               
        }
        catch(SQLException ex)
        {
        }
           return regComb1;
    }
    
    /*Author Sergio*/
    public boolean deletePart(int ID, String partName, String partDesc, int amount, double cost)
    {
        PreparedStatement delete = null;
        boolean deleted = false;
        try
        {
           delete= preparedStatement("DELETE FROM PARTS_TRACKING WHERE RELEVANT_ID_NUM=" 
                   + ID +" AND NAME='" 
                   + partName +"' AND DESCRIPTION='" 
                   + partDesc + "' AND AMOUNT=" 
                   + amount + " AND COST=" 
                   + cost);
                   
           delete.setInt(1, ID);
           delete.setString(2, partName);
           delete.setString(3, partDesc);
           delete.setInt(4, amount);
           delete.setDouble(5, cost);
  
           delete.execute();
           delete.close();
           deleted = true;
           JOptionPane.showMessageDialog(null,"Part successfully deleted");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return deleted;
    }
    /*
    Author Sergio Arrieta
    */
    public ObservableList<partBooking> getpartBooking(String reg) throws NullPointerException
    {   
        try{
        PreparedStatement getMec = null;
        bookingdata = FXCollections.observableArrayList();
        
       
        getMec = preparedStatement("SELECT * FROM 'DIAGNOSIS_REPAIR_BOOKINGS' WHERE REG_NUM=" +"'"+ reg +"'");
        ResultSet rs = getMec.executeQuery();
        
        while(rs.next())
        {
            int ID = rs.getInt("IDnum");
            int custID=rs.getInt("CUSTOMERID");
            String date = rs.getString("BOOKING_DATE");
            String type = rs.getString("TYPE");
 
            partBooking booking = new partBooking(date,custID,type,ID);
            
            bookingdata.add(booking);
        }
         
        }
        catch(SQLException ex)    
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
        }
        return bookingdata;
    }
    
    /*
    Author Sergio Arrieta
    */
    public void editInstalledPart() throws SQLException
    {
        try{
        PreparedStatement editInstalledPart = preparedStatement("UPDATE PARTS_INSTALLATION SET REG_NUM=?, INSTALLATION_DATE=?, EXP_DATE=?, PART_NAME= ?,BOOKING_ID=? WHERE INSTALLATION_ID=?");
        int counter = 0;
        while(counter < searchPartsData.size())
        {
            editInstalledPart.setString(1, searchPartsData.get(counter).getREG_NUM());
            editInstalledPart.setString(2, searchPartsData.get(counter).getINST_DATE());
            editInstalledPart.setString(3, searchPartsData.get(counter).getEXP_DATE());
            editInstalledPart.setString(4, searchPartsData.get(counter).getPART_NAME());
            editInstalledPart.setInt(5, searchPartsData.get(counter).getBOOKING_ID());
            
            editInstalledPart.setInt(6, searchPartsData.get(counter).getINST_ID());
           
            
            editInstalledPart.executeUpdate();
            
            counter++;
        }
         
        getinstalledPart();
        }
        catch( NullPointerException e)
        {
            
        }
    }
    /*
    Author Sergio Arrieta
    */
    public void updateStock(String partname) 
    {
        try
        {
        PreparedStatement updateStock = preparedStatement("UPDATE PARTS_TRACKING SET AMOUNT=AMOUNT-1 WHERE name=" + "'" + partname + "'");

        //updateStock.setInt(1,ID);
        updateStock.executeUpdate();
        
        getinstalledPart();
        }
    
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
        }
    }
    public boolean maxParts(String regNum,int bookingid,String partname) 
    {  boolean check=true;
       int count=0;
        try
        {
        PreparedStatement getPART= preparedStatement("SELECT AMOUNT FROM 'PARTS_TRACKING' WHERE NAME='"+partname+"'" );
     
        ResultSet rs2 = getPART.executeQuery();
        
        int stock=rs2.getInt("AMOUNT");
        if(stock==0 || stock<0)
        {
            
            JOptionPane.showMessageDialog(null,"Sorry we run out of stock for that part,select another one");
            return check=false;
        }
        else{
           
        PreparedStatement getBill= preparedStatement("SELECT PART_NAME FROM 'PARTS_INSTALLATION' WHERE REG_NUM ='" + regNum+ "' AND BOOKING_ID='" + bookingid +"'" );

        ResultSet rs = getBill.executeQuery();
        while(rs.next())
         { 
            String pn=rs.getString("PART_NAME");
        
            if(pn.equals(partname))
            {
                JOptionPane.showMessageDialog(null," You have already installed that part select another one");
                  check=false;
            }
            else{
                   count=count+1;
                   if(count==10)
                   {
                      
                      JOptionPane.showMessageDialog(null,"<html> You have already installed 10 parts<br><br/>"
                      + "-Remove at least one to add anymore<html>");
                      check=false;
                   } 
                }
         }
        }
        }
        
        catch(SQLException ex){
            
        }
        return check;
    }
    /*
    Author Sergio Arrieta
    */
    public void calculateBill(String regNum,int bookingid) 
    { 
        double totalcost=0.0;
       // String custName=name;
        try
        {
            
        PreparedStatement getBill= preparedStatement("SELECT COST FROM PARTS_TRACKING,PARTS_INSTALLATION WHERE PARTS_TRACKING.NAME=PARTS_INSTALLATION.PART_NAME ");

        ResultSet rs = getBill.executeQuery();
       
         totalcost= totalcost + rs.getDouble("COST");
       

        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
        }
         try{
             
        PreparedStatement getBill= preparedStatement("UPDATE DIAGNOSIS_REPAIR_BOOKINGS SET COST=COST + " + totalcost +" where IDnum='" + bookingid + "'");

         //  getBill.setInt(1, bookingid);
           getBill.execute();
           getBill.close();
           JOptionPane.showMessageDialog(null,"Part added to the bill");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Part NOT added to the bill");
        }
      
    }

    /*
    Author Sergio Arrieta
    */
    public void editPart() throws SQLException
    {
       try{ PreparedStatement editPart = preparedStatement("UPDATE PARTS_TRACKING SET NAME=?,DESCRIPTION=?,AMOUNT=?,COST=? WHERE RELEVANT_ID_NUM=?");
        int counter = 0;
        while(counter <partsData.size())
        {
            //editPart.setInt(1, partsData.get(counter).getID());
            editPart.setString(1, partsData.get(counter).getpartName());
            editPart.setString(2, partsData.get(counter).getpartDesc());
            editPart.setInt(3, partsData.get(counter).getAmount());
            editPart.setDouble(4, partsData.get(counter).getCost());
            editPart.setInt(5, partsData.get(counter).getID());
          
            editPart.executeUpdate();
            
            counter++;
        }
        
        getPart();
       }
       catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            
        }
    }
    /*
    Author Sergio Arrieta
    */
      public void removePart(int id) 
    {
        try{
        PreparedStatement removeInstalledPartStmt = preparedStatement("DELETE FROM PARTS_TRACKING WHERE RELEVANT_ID_NUM="+ id);
      // removeInstalledPartStmt.setInt(1, id);
        removeInstalledPartStmt.executeUpdate();
        }catch(SQLException ex)
        {
            
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"Please select a part");
        }
        catch(Exception ex)
        {
            
        }
        
        
    }
   /* Author Sergio Arrieta
    */
      public void removeInstalledPart(int id) throws SQLException
    {
        
        PreparedStatement removeInstalledPartStmt = preparedStatement("DELETE FROM PARTS_INSTALLATION WHERE INSTALLATION_ID="+ id);
      // removeInstalledPartStmt.setInt(1, id);
        removeInstalledPartStmt.executeUpdate();
        
        
    }
      /*
    Author Sergio Arrieta
    */
      public void removeBookingPart(int id) 
    {
        try{
        PreparedStatement removeInstalledPartStmt = preparedStatement("DELETE FROM DIAGNOSIS_REPAIR_BOOKINGS WHERE IDnum='"+ id+"'");
      // removeInstalledPartStmt.setInt(1, id);
        removeInstalledPartStmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Please select a booking");
        }
    }
      
    /*Author Sam*/
    public void removeVehicle(String regnum) 
    {
        try{
        PreparedStatement removeVehicleStmt = preparedStatement("DELETE FROM VEHICLE_RECORD WHERE REG_NUM=?");
        removeVehicleStmt.setString(1, regnum);
        removeVehicleStmt.executeUpdate();
        
        }
        catch(SQLException ex)
        {
            
        } 
    }

      
      /*Author Sam*/
    public ObservableList<Vehicle> getVehicle() throws SQLException {

        PreparedStatement getVehicle = null;
        vehicleData = FXCollections.observableArrayList();

        getVehicle = preparedStatement("SELECT * FROM VEHICLE_RECORD");
        ResultSet rs = getVehicle.executeQuery();

        while (rs.next()) {
            String regnum = rs.getString("REG_NUM");
            String model = rs.getString("MODEL");
            String make = rs.getString("MAKE");
            String engine = rs.getString("ENGINE_SIZE");
            String fueltype = rs.getString("FUEL_TYPE");
            String colour = rs.getString("COLOUR");
            String motdate = rs.getString("MOT_RENEWAL_DATE");
            String lastservice = rs.getString("PREVIOUS_SERVICE_DATE");
            String mileage = rs.getString("CURRENT_MILEAGE");
            String warranty = rs.getString("WARRANTY");
            String warrantycompany = rs.getString("WARRANTY_COMPANY");
            String warrantyaddress = rs.getString("WARRANTY_ADDRESS");
            String warrantyexpiry = rs.getString("WARRANTY_EXPIRY");
            int id = rs.getInt("CUSTOMER_ID");
                    
                    

            Vehicle vehicle = new Vehicle(id, regnum,  model,  make, engine, fueltype, colour, motdate, lastservice, mileage, warranty, warrantycompany, warrantyaddress, warrantyexpiry);

            vehicleData.add(vehicle);
        }
        return vehicleData;
    }
    
    /*Author Sam*/
    public boolean addVehicle(int ID, String regnum, String model , String make, String engine, String fueltype, String colour, String motdate, String lastservice, String mileage, String warranty, String warrantycompany, String warrantyaddress, String warrantyexpiry) {
        PreparedStatement add = null;
        boolean added = false;
        try {
            add = preparedStatement("INSERT INTO VEHICLE_RECORD VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            add.setString(1, regnum);
            add.setString(2, model);
            add.setString(3, make);
            add.setString(4, engine);
            add.setString(5, fueltype);
            add.setString(6, colour);
            add.setString(7, motdate);
            add.setString(8, lastservice);
            add.setString(9, mileage);
            add.setString(10, warranty);
            add.setString(11, warrantycompany);
            add.setString(12, warrantyaddress);
            add.setString(13, warrantyexpiry);
            add.setInt(14, ID);

            add.execute();
            add.close();
            added = true;
            JOptionPane.showMessageDialog(null, "Vehicle successfully added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }

        return added;
    }
    
    /*Author Shiraj*/
    public ObservableList<SPC> getSPC() throws SQLException
    {   
        
        PreparedStatement getSPC = null;
        spcData = FXCollections.observableArrayList();
        
       
        getSPC = preparedStatement("SELECT * FROM SPECIALIST_CENTRES");
        ResultSet rs = getSPC.executeQuery();
        
        while(rs.next())
        {
            int idNum = rs.getInt("IDnum");
            String spcName = rs.getString("NAME");
            String spcAddress = rs.getString("ADDRESS");
            String spcPhone = rs.getString("PHONE");
            String spcEmail = rs.getString("EMAIL");
            
            SPC spc = new SPC(idNum, spcName, spcAddress, spcPhone, spcEmail);
            
            spcData.add(spc);
        }
        return spcData;
    }
    
   
    
    
    public ObservableList<Outstanding> getOutstandingParts() throws SQLException
    {   
        
        PreparedStatement getOutstandingParts = null;
        outPartsData = FXCollections.observableArrayList();
        
       
        getOutstandingParts = preparedStatement("SELECT * FROM OUTSTANDING_PARTS");
        ResultSet rs = getOutstandingParts.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            int partID = rs.getInt("partID");
            String partName = rs.getString("partName");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            Double partCost = rs.getDouble("cost");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");
            
            Outstanding outstandingpart = new Outstanding(bookingID, spcName, partID, partName, deliveryDate, returnDate, partCost, customerID, firstName, surname);
            
            outPartsData.add(outstandingpart);
        }
        return outPartsData;
    }
    
     public void removeOutstandingPart(int bookingID) throws SQLException
    {
        
        PreparedStatement removeOutstandingPartStmt = preparedStatement("DELETE FROM OUTSTANDING_PARTS WHERE bookingID="+ bookingID);
      // removeInstalledPartStmt.setInt(1, id);
        removeOutstandingPartStmt.executeUpdate();
    }
     public void removeOutstandingVehicle(int bookingID) throws SQLException
    {
        
        PreparedStatement removeOutstandingVehicleStmt = preparedStatement("DELETE FROM OUTSTANDING_VEHICLES WHERE bookingID="+ bookingID);
      // removeInstalledPartStmt.setInt(1, id);
        removeOutstandingVehicleStmt.executeUpdate();
    }
     public void removeReturnedPart(int returnedID) throws SQLException
    {
        
        PreparedStatement removeReturnedPartStmt = preparedStatement("DELETE FROM RETURNED_PARTS WHERE returnID="+ returnedID);
      // removeInstalledPartStmt.setInt(1, id);
        removeReturnedPartStmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"Successfully Removed");
    }
     public void removeReturnedVehicle(int returnedID) throws SQLException
    {
        
        PreparedStatement removeReturnedVehicleStmt = preparedStatement("DELETE FROM RETURNED_VEHICLES WHERE returnID="+ returnedID);
      // removeInstalledPartStmt.setInt(1, id);
        removeReturnedVehicleStmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"Successfully Removed");
    }
     
     public ObservableList<Returned> getReturnedParts() throws SQLException
    {   
        
        PreparedStatement getReturnedParts = null;
        retPartsData = FXCollections.observableArrayList();
        
       
        getReturnedParts = preparedStatement("SELECT * FROM RETURNED_PARTS");
        ResultSet rs = getReturnedParts.executeQuery();
        
        while(rs.next())
        {
            int returnID = rs.getInt("returnID");
            String spcName = rs.getString("spcName");
            int partID = rs.getInt("partID");
            String partName = rs.getString("partName");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            Double partTotal = rs.getDouble("total");
            
            Returned returnedpart = new Returned(returnID, spcName, partID, partName, deliveryDate, returnDate, partTotal);
            
            retPartsData.add(returnedpart);
        }
        return retPartsData;
    }
    
        public ObservableList<String> getSPCName() throws SQLException
    {   
        
        PreparedStatement getSPCname = null;
        ObservableList<String> spcname = FXCollections.observableArrayList();
        
       
        getSPCname = preparedStatement("SELECT NAME FROM SPECIALIST_CENTRES");
        ResultSet rs = getSPCname.executeQuery();
        
        while(rs.next())
        {
            String spcName = rs.getString("NAME");

            
            //centreName spc = new centreName(spcName);
            
            spcname.add(spcName);
        }
        return spcname;
    }
    
    
    /*Author Shiraj*/
    public boolean addSPC( String SPC_NAME, String SPC_ADDRESS, String SPC_PHONE, String SPC_EMAIL)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO SPECIALIST_CENTRES VALUES (?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, SPC_NAME);
           add.setString(3, SPC_ADDRESS);
           add.setString(4, SPC_PHONE);
           add.setString(5, SPC_EMAIL);

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"SPC successfully added");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
        
    public void removeSPC(int ID) throws SQLException
    {
        
        PreparedStatement removeSPCStmt = preparedStatement("DELETE FROM SPECIALIST_CENTRES WHERE IDnum="+ ID);
      // removeInstalledPartStmt.setInt(1, id);
        removeSPCStmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"Successfully Removed");
    }
    
     /*Author Shiraj*/
    public boolean bookSPCPart( String SPC, int PARTID, String PARTNAME, LocalDate DELIVDATE, LocalDate RETURNDATE, Double PARTCOST, int customerID, String customerName, String customerSurname)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO OUTSTANDING_PARTS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
           
           add.setString(1, null);
           add.setString(2, SPC);
           add.setInt(3, PARTID);
           add.setString(4, PARTNAME);
           add.setString(5, "" +DELIVDATE);
           add.setString(6, "" +RETURNDATE);
           add.setDouble(7, PARTCOST);
           add.setInt(8, customerID);
           add.setString(9, customerName);
           add.setString(10, customerSurname);
        

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Booked");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }

     public boolean returnedSPCPart(String SPC, int PARTID, String PARTNAME, String DELIVDATE, String RETURNDATE, Double TOTAL, int CUSTOMERID)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO RETURNED_PARTS VALUES (?, ?, ?, ?, ?, ?, ?)"); 
         
           add.setString(1, null);
           add.setString(2, SPC);
           add.setInt(3, PARTID);
           add.setString(4, PARTNAME);
           add.setString(5, DELIVDATE);
           add.setString(6, RETURNDATE);
           add.setDouble(7, TOTAL);        

           add.execute();
           add.close();

           added = true;
           JOptionPane.showMessageDialog(null,"Returned");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
          public boolean sendPartBill(int CUSTOMERID, double TOTAL)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO BILL VALUES (?, ?, ?)"); 
         
           add.setInt(1, CUSTOMERID);
           add.setDouble(2, TOTAL);
           add.setBoolean(3, false);
        

           add.execute();
           add.close();

           added = true;
           JOptionPane.showMessageDialog(null,"Bill sent");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
          
     
     
      public boolean bookSPCVehicle( String SPC, String REGNUM, String MAKE, String MODEL, String ENGINE,
              String FUEL, String COLOUR, LocalDate DELIVDATE, LocalDate RETURNDATE, int customerID, String customerName, String customerSurname)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO OUTSTANDING_VEHICLES VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
           
           add.setString(1, null);
           add.setString(2, SPC);
           add.setString(3, REGNUM);
           add.setString(4, MAKE);
           add.setString(5, MODEL);
           add.setString(6, ENGINE);
           add.setString(7, FUEL);
           add.setString(8, COLOUR);
           add.setString(9, "" +DELIVDATE);
           add.setString(10, "" +RETURNDATE);
           add.setInt(11, customerID);
           add.setString(12, customerName);
           add.setString(13, customerSurname);
        

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Booked");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
       public ObservableList<OutstandingVehicle> getOutstandingVehicles() throws SQLException
    {   
        
        PreparedStatement getOutstandingVehicles = null;
        outVehicleData = FXCollections.observableArrayList();
        
       
        getOutstandingVehicles = preparedStatement("SELECT * FROM OUTSTANDING_VEHICLES");
        ResultSet rs = getOutstandingVehicles.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            String regNum = rs.getString("regNum");
            String make = rs.getString("make");
            String model = rs.getString("model");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstname");
            String surname = rs.getString("surname");
            
            OutstandingVehicle outstandingvehicle = new OutstandingVehicle(bookingID, spcName,
                    regNum, make, model, deliveryDate, returnDate, customerID, firstName, surname);
            
            outVehicleData.add(outstandingvehicle);
        }
        return outVehicleData;
    }
         public ObservableList<OutstandingVehicle> getOutstandingVehiclesFromSPC(String SPCNAME) throws SQLException
    {   
        
        PreparedStatement getOutstandingVehiclesFromSPC = null;
        outVehicleSearchData = FXCollections.observableArrayList();
        
       
        getOutstandingVehiclesFromSPC = preparedStatement("SELECT * FROM OUTSTANDING_VEHICLES WHERE spcName ='" +SPCNAME+"';");
        ResultSet rs = getOutstandingVehiclesFromSPC.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            String regNum = rs.getString("regNum");
            String make = rs.getString("make");
            String model = rs.getString("model");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");

            OutstandingVehicle outstandingvehiclesearch = new OutstandingVehicle(bookingID, spcName,
                    regNum, make, model, deliveryDate, returnDate, customerID, firstName, surname);
            
            outVehicleSearchData.add(outstandingvehiclesearch);
        }
        return outVehicleSearchData;
    }
     
         public ObservableList<Outstanding> getOutstandingPartsFromSPC(String SPCNAME) throws SQLException
    {   
        
        PreparedStatement getOutstandingPartsFromSPC = null;
        outPartSearchData = FXCollections.observableArrayList();
        
       
        getOutstandingPartsFromSPC = preparedStatement("SELECT * FROM OUTSTANDING_PARTS WHERE spcName ='" +SPCNAME+"';");
        ResultSet rs = getOutstandingPartsFromSPC.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            int partID = rs.getInt("partID");
            String partName = rs.getString("partName");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            double partCost = rs.getDouble("cost");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");

            Outstanding outstandingpartsearch = new Outstanding(bookingID, spcName, partID, 
                   partName, deliveryDate, returnDate, partCost, customerID, firstName, surname);
            
            outPartSearchData.add(outstandingpartsearch);
        }
        return outPartSearchData;
    }
         
          public ObservableList<OutstandingVehicle> getOutstandingVehiclesFromReg(String REG) throws SQLException
    {   
        
        PreparedStatement getOutstandingVehiclesFromReg = null;
        outVehicleSearchData = FXCollections.observableArrayList();
        
       
        getOutstandingVehiclesFromReg = preparedStatement("SELECT * FROM OUTSTANDING_VEHICLES WHERE regNum LIKE '%" +REG+"%';");
        ResultSet rs = getOutstandingVehiclesFromReg.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            String regNum = rs.getString("regNum");
            String make = rs.getString("make");
            String model = rs.getString("model");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");

            
            OutstandingVehicle outstandingvehiclesearch = new OutstandingVehicle(bookingID, spcName,
                    regNum, make, model, deliveryDate, returnDate, customerID, firstName, surname);
            
            outVehicleSearchData.add(outstandingvehiclesearch);
        }
        return outVehicleSearchData;
    }
       public ObservableList<OutstandingVehicle> getOutstandingVehiclesFromNAME(String FIRSTNAME) throws SQLException
    {   
   
        PreparedStatement getOutstandingVehiclesFromNAME = null;
        outVehicleSearchData = FXCollections.observableArrayList();
        
        System.out.println(FIRSTNAME);
        getOutstandingVehiclesFromNAME = preparedStatement("SELECT * FROM OUTSTANDING_VEHICLES WHERE firstName LIKE '%" +FIRSTNAME+"%' OR surname LIKE '%" +FIRSTNAME+"%'");

    
        ResultSet rs = getOutstandingVehiclesFromNAME.executeQuery();
        
        while(rs.next())
        {
            System.out.println("1");
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            String regNum = rs.getString("regNum");
            String make = rs.getString("make");
            String model = rs.getString("model");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");

            
            OutstandingVehicle outstandingvehiclesearch = new OutstandingVehicle(bookingID, spcName,
                    regNum, make, model, deliveryDate, returnDate, customerID, firstName, surname);
            
            outVehicleSearchData.add(outstandingvehiclesearch);
        }
        return outVehicleSearchData;
    }
     
       public ObservableList<Outstanding> getOutstandingPartsFromNAME(String FIRSTNAME) throws SQLException
    {   
        
        PreparedStatement getOutstandingPartsFromNAME = null;
        outPartSearchData = FXCollections.observableArrayList();
        
       
        getOutstandingPartsFromNAME = preparedStatement("SELECT * FROM OUTSTANDING_PARTS WHERE firstName LIKE '%" +FIRSTNAME+"%' OR surname LIKE '%" +FIRSTNAME+"%'");
        ResultSet rs = getOutstandingPartsFromNAME.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("bookingID");
            String spcName = rs.getString("spcName");
            int partID = rs.getInt("partID");
            String partName = rs.getString("partName");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            double partCost = rs.getDouble("cost");
            int customerID = rs.getInt("CUSTOMER_ID");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");

            Outstanding outstandingpartsearch = new Outstanding(bookingID, spcName, partID, 
                   partName, deliveryDate, returnDate, partCost, customerID, firstName, surname);
            
            outPartSearchData.add(outstandingpartsearch);
        }
        return outPartSearchData;
    }
     public ObservableList<ReturnedVehicle> getReturnedVehicles() throws SQLException
    {   
        
        PreparedStatement getReturnedVehicles = null;
        retVehicleData = FXCollections.observableArrayList();
        
       
        getReturnedVehicles = preparedStatement("SELECT * FROM RETURNED_VEHICLES");
        ResultSet rs = getReturnedVehicles.executeQuery();
        
        while(rs.next())
        {
            int bookingID = rs.getInt("returnID");
            String spcName = rs.getString("spcName");
            String regNum = rs.getString("regNum");
            String make = rs.getString("make");
            String model = rs.getString("model");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");   
            Double vehicleTotal = rs.getDouble("total");
            
            ReturnedVehicle returnedvehicle = new ReturnedVehicle(bookingID, spcName,
                    regNum, make, model, deliveryDate, returnDate, vehicleTotal);
            
            retVehicleData.add(returnedvehicle);
        }
        return retVehicleData;
    }
       
       public boolean returnedSPCVehicle(String SPC, String REGNUM, String VEHICLEMAKE, String VEHICLEMODEL, String DELIVDATE, String RETURNDATE, Double TOTAL)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO RETURNED_VEHICLES VALUES (?, ?, ?, ?, ?, ?, ?, ?)"); 
         
           add.setString(1, null);
           add.setString(2, SPC);
           add.setString(3, REGNUM);
           add.setString(4, VEHICLEMAKE);
           add.setString(5, VEHICLEMODEL);
           add.setString(6, DELIVDATE);
           add.setString(7, RETURNDATE);
           add.setDouble(8, TOTAL);
        

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Returned");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
       public void editSPC() throws SQLException
    {
        PreparedStatement editSPCStmt = preparedStatement("UPDATE SPECIALIST_CENTRES SET NAME=?, ADDRESS=?, PHONE=?, EMAIL=? WHERE IDnum=?");
        int counter = 0;
        while(counter < spcData.size())
        {

            editSPCStmt.setString(1, spcData.get(counter).getSPC_NAME());
            editSPCStmt.setString(2, spcData.get(counter).getSPC_ADDRESS());
            editSPCStmt.setString(3, spcData.get(counter).getSPC_PHONE());
            editSPCStmt.setString(4, spcData.get(counter).getSPC_EMAIL());
            editSPCStmt.setInt(5, spcData.get(counter).getIDnum());
            
            editSPCStmt.executeUpdate();
            
            counter++;
        }
        
       
    }
       
       public void editBookings() throws SQLException
    {
        PreparedStatement editBookingStmt = preparedStatement("UPDATE DIAGNOSIS_REPAIR_BOOKINGS SET MECHANICID=?, PARTNAME=?, CUSTOMERID=?, REG_NUM=?, MANUFACTURE=?, MILEAGE=?, BOOKING_DATE=?, TIME=?,TYPE=?,COST=?  WHERE IDnum=?");
        int counter = 0;
        while(counter < BookingsData.size())
            
        {

            editBookingStmt.setString(1, BookingsData.get(counter).getBOOKING_MechID());
            editBookingStmt.setString(2, BookingsData.get(counter).getPART_NAME());
            editBookingStmt.setInt(3, BookingsData.get(counter).getCUSTOMER_ID());
            editBookingStmt.setString(4, BookingsData.get(counter).getBOOKING_REGNUM());
            editBookingStmt.setString(5, BookingsData.get(counter).getBOOKING_MANUFACTURE());
            editBookingStmt.setString(6, BookingsData.get(counter).getBOOKING_MILEAGE());
            editBookingStmt.setString(7, BookingsData.get(counter).getBOOKING_DATE());
            editBookingStmt.setString(8, BookingsData.get(counter).getBOOKING_TIME());
            editBookingStmt.setString(9, BookingsData.get(counter).getBOOKING_TYPE());
            editBookingStmt.setInt(10, BookingsData.get(counter).getIDnum());
            editBookingStmt.setDouble(11, BookingsData.get(counter).getBOOKING_TOTAL());
            
            
            
            counter++;
        }
        
        getBookings();
    }
       
       public void editMec() throws SQLException
    {
        PreparedStatement editMecStmt = preparedStatement("UPDATE MECHANIC SET MECHANIC_NAME=?, MECHANIC_DATE=?, MECHANIC_NUMBER=?, MECHANIC_RATE=?  WHERE IDnum=?");
        int counter = 0;
        while(counter < MechanicData.size())
            
        {

            editMecStmt.setString(1, MechanicData.get(counter).getMECHANIC_NAME());
            editMecStmt.setString(2, MechanicData.get(counter).getMECHANIC_DATE());
            editMecStmt.setString(3, MechanicData.get(counter).getMECHANIC_NUMBER());
            editMecStmt.setDouble(4, MechanicData.get(counter).getMECHANIC_RATE());
            
            
            editMecStmt.executeUpdate();
            
            counter++;
        }
        
        getMec();
    }
      
   
    public static Database getInstance()
    {
        return db;
    }

    public void editVehicle() throws SQLException {
        {
        PreparedStatement editVehicleStmt = preparedStatement("UPDATE VEHICLE_RECORD SET MAKE=?, MODEL=?, ENGINE_SIZE=?, FUEL_TYPE=?, COLOUR=?, MOT_RENEWAL_DATE=?, PREVIOUS_SERVICE_DATE=?, CURRENT_MILEAGE=?, WARRANTY=?, WARRANTY_COMPANY=?, WARRANTY_ADDRESS=?, WARRANTY_EXPIRY=? WHERE REG_NUM=?");
        int counter = 0;
        while(counter < vehicleData.size())
        {
            
            editVehicleStmt.setString(1, vehicleData.get(counter).getMake());
            editVehicleStmt.setString(2, vehicleData.get(counter).getModel());
            editVehicleStmt.setString(3, vehicleData.get(counter).getEngine());
            editVehicleStmt.setString(4, vehicleData.get(counter).getFuelType());
            editVehicleStmt.setString(5, vehicleData.get(counter).getColour());
            editVehicleStmt.setString(6, vehicleData.get(counter).getMotDate());
            editVehicleStmt.setString(7, vehicleData.get(counter).getLastService());
            editVehicleStmt.setString(8, vehicleData.get(counter).getMileage());
            editVehicleStmt.setString(9, vehicleData.get(counter).getWarranty());
            editVehicleStmt.setString(10, vehicleData.get(counter).getWarrantyCompany());
            editVehicleStmt.setString(11, vehicleData.get(counter).getWarrantyAddress());
            editVehicleStmt.setString(12, vehicleData.get(counter).getWarrantyExpiry());
            editVehicleStmt.setString(13, vehicleData.get(counter).getRegnum());
            
            
            editVehicleStmt.executeUpdate();
            
            counter++;
        }
        
        getVehicle();
    }
    }
    
    
    /*Author Sam*/
    public ObservableList<Vehicle> searchVehicle (String searchVal) 
    {   
        try{
        PreparedStatement searchVehicle = null;
        searchVehicleData = FXCollections.observableArrayList();
        
      
        searchVehicle = preparedStatement("select * from VEHICLE_RECORD where MODEL LIKE '%" + searchVal + "%' OR MAKE LIKE '%" + searchVal +"%' OR REG_NUM LIKE '%" + searchVal + "%'");
        
        //searchInstalledPart.setString(1,searchVal);
        
        
        ResultSet rs = searchVehicle.executeQuery();
        
        while(rs.next())
        {
            String regnum = rs.getString("REG_NUM");
            String model = rs.getString("MODEL");
            String make = rs.getString("MAKE");
            String engine = rs.getString("ENGINE_SIZE");
            String fueltype = rs.getString("FUEL_TYPE");
            String colour = rs.getString("COLOUR");
            String motdate = rs.getString("MOT_RENEWAL_DATE");
            String lastservice = rs.getString("PREVIOUS_SERVICE_DATE");
            String mileage = rs.getString("CURRENT_MILEAGE");
            String warranty = rs.getString("WARRANTY");
            String warrantycompany = rs.getString("WARRANTY_COMPANY");
            String warrantyaddress = rs.getString("WARRANTY_ADDRESS");
            String warrantyexpiry = rs.getString("WARRANTY_EXPIRY");
            int id = rs.getInt("CUSTOMER_ID");

            Vehicle searchedVehicle = new Vehicle(id, regnum,  model,  make, engine, fueltype, colour, motdate, lastservice, mileage, warranty, warrantycompany, warrantyaddress, warrantyexpiry);
            
            searchVehicleData.add(searchedVehicle);
        }
        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        return searchVehicleData;
    }
    public ObservableList<Bookings> searchBooking (String searchVal) 
    {   
        try{
        PreparedStatement searchBooking = null;
        searchBookingData = FXCollections.observableArrayList();
        
      
        searchBooking = preparedStatement("select * from DIAGNOSIS_REPAIR_BOOKINGS where IDnum LIKE '%" + searchVal + "%' OR MANUFACTURE LIKE '%" + searchVal +"%' OR REG_NUM LIKE '%" + searchVal + "%'");
        
       
        
        
        ResultSet rs = searchBooking.executeQuery();
        
        while(rs.next())
        {
            int idNum = rs.getInt("IDnum");
            String BookingMechanicID = rs.getString("MECHANICID");
            String PARTNAME = rs.getString("PARTNAME");
            int CUSTOMERID = rs.getInt("CUSTOMERID");
            String BookingRegNum = rs.getString("REG_NUM");
            String BookingManufacture = rs.getString("MANUFACTURE");
            String BookingMileage = rs.getString("MILEAGE");
            String BookingDate = rs.getString("BOOKING_DATE");
            String BookingTime = rs.getString("TIME");
            String BookingType = rs.getString("TYPE");
            double BookingTotal = rs.getDouble("COST");

            Bookings searchedBooking = new Bookings(idNum, BookingMechanicID,  PARTNAME, 
                    CUSTOMERID, BookingRegNum, BookingManufacture, BookingMileage, BookingDate,
                    BookingTime, BookingType, BookingTotal);
            
            BookingsData.add(searchedBooking);
        }
        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        return BookingsData;
    }
     public ObservableList<Bookings> getBookings() throws SQLException
    {   
        
        PreparedStatement getBookings = null;
        BookingsData = FXCollections.observableArrayList();
        
       
        getBookings = preparedStatement("SELECT * FROM DIAGNOSIS_REPAIR_BOOKINGS");
        ResultSet rs = getBookings.executeQuery();
        
        while(rs.next())
        {
            int idNum = rs.getInt("IDnum");
            String BookingMechanicID = rs.getString("MECHANICID");
            String PARTNAME = rs.getString("PARTNAME");
            int CUSTOMERID = rs.getInt("CUSTOMERID");
            String BookingRegNum = rs.getString("REG_NUM");
            String BookingManufacture = rs.getString("MANUFACTURE");
            String BookingMileage = rs.getString("MILEAGE");
            String BookingDate = rs.getString("BOOKING_DATE");
            String BookingTime = rs.getString("TIME");
            String BookingType = rs.getString("TYPE");
            double BookingTotal = rs.getDouble("COST");
            
            Bookings bookings = new Bookings(idNum, BookingMechanicID, PARTNAME, CUSTOMERID, 
                    BookingRegNum,BookingManufacture ,BookingMileage , BookingDate,BookingTime, BookingType, BookingTotal);
            
            BookingsData.add(bookings);
        }
        return BookingsData;
    }
     
     public ObservableList<Mec> getMec() throws SQLException
    {   
        
        PreparedStatement getMec = null;
        MechanicData = FXCollections.observableArrayList();
        
       
        getMec = preparedStatement("SELECT * FROM MECHANIC");
        ResultSet rs = getMec.executeQuery();
        
        while(rs.next())
        {
            int idNum = rs.getInt("IDnum");
            String MechanicName = rs.getString("MECHANIC_NAME");
            String MechanicDate = rs.getString("MECHANIC_DATE");
            String MechanicNumber = rs.getString("MECHANIC_NUMBER");
            Double MechanicRate = rs.getDouble("MECHANIC_RATE");
            
            
            Mec mec = new Mec(idNum, MechanicName, MechanicDate, MechanicNumber, 
                    MechanicRate);
            
            MechanicData.add(mec);
        }
        return MechanicData;
    }
     public boolean addBookings( String BookingMechanicID, String PARTNAME, int CUSTOMERID,
            String BookingRegNum, String BookingManufacture, String BookingMileage, 
            String BookingDate, String BookingTime, String BookingType, double BookingTotal)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO DIAGNOSIS_REPAIR_BOOKINGS VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?)"); 
           add.setString(1, null);
           add.setString(2, BookingMechanicID);
           add.setString(3, PARTNAME);
           add.setInt(4, CUSTOMERID);
           add.setString(5, BookingRegNum);
           add.setString(6, BookingManufacture);
           add.setString(7, BookingMileage);
           add.setString(8, BookingDate);
           add.setString(9, BookingTime);
           add.setString(10, BookingType);
           add.setDouble(11, BookingTotal);

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Booking successfully added");
           addStatus(CUSTOMERID, getMaxBookingID());
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
     
     public boolean addMec( String MechanicName, String MechanicDate,
            String MechanicNumber, double MechanicRate )
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO MECHANIC VALUES (?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, MechanicName);
           add.setString(3, MechanicDate);
           add.setString(4, MechanicNumber);
           add.setDouble(5, MechanicRate);
           

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Mechanic successfully added");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
     
     public void removeBookings(int ID) throws SQLException
    {
        
        PreparedStatement removeBookingsStmt = preparedStatement("DELETE FROM DIAGNOSIS_REPAIR_BOOKINGS WHERE IDnum="+ ID);
      // removeInstalledPartStmt.setInt(1, id);
        removeBookingsStmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"Successfully Removed");
        deleteStatus(ID);
    }


public void removeMec(int ID) throws SQLException
    {
        
        PreparedStatement removeMechanicStmt = preparedStatement("DELETE FROM MECHANIC WHERE IDnum="+ ID);
      // removeInstalledPartStmt.setInt(1, id);
        removeMechanicStmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"Successfully Removed");
    }

}
    
    
     
       





