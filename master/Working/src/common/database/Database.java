/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.database;

import common.logic.SystemUser;
import customers.logic.Customers;
import vehicles.logic.Vehicle;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import parts.logic.Part;
import parts.logic.installedPart;
import specialist.logic.SPC;
import specialist.logic.centreName;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import specialist.logic.Outstanding;
import specialist.logic.Returned;
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
    private ObservableList<Part> partsData;
    private ObservableList<Vehicle> vehicleData;
    private ObservableList<installedPart> installedPartsData;
    private ObservableList<installedPart> searchPartsData;
    private ObservableList<SPC> spcData;
    private ObservableList<Outstanding> outPartsData;
    private ObservableList<Returned> retPartsData;
    private ObservableList<centreName> spcname;
    
    private ComboBox regComb;
        
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
        PreparedStatement allCustomersStmt = preparedStatement("SELECT * FROM CUSTOMER_ACCOUNTS"); 
                
        customerData = FXCollections.observableArrayList();
        ResultSet rs = allCustomersStmt.executeQuery();
        
        while(rs.next())
        {
            int id = rs.getInt("CUSTOMER_ID");
            String fullName = rs.getString("FULL_NAME");
            String address = rs.getString("ADDRESS");
            String postCode = rs.getString("POSTCODE");
            String phone = rs.getString("PHONE");
            String type = rs.getString("TYPE");
            String email = rs.getString("EMAIL");
            
            Customers customer = new Customers(id, fullName, address, postCode, phone, email, type);
            
            customerData.add(customer);
        }
        return customerData;
    }
    
     public void removeCustomer(int id) 
    {
        try{
            PreparedStatement removeCustomerStmt = preparedStatement("DELETE FROM CUSTOMER_ACCOUNTS WHERE CUSTOMER_ID=?");
            removeCustomerStmt.setInt(1, id);
            removeCustomerStmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean addCustomer(String fullName, String address, String postCode, String phone, String email, String type)
    {
        PreparedStatement add = null;
        boolean added = false;
        
        try
        {
            add = preparedStatement("INSERT INTO CUSTOMER_ACCOUNTS VALUES(?,?,?,?,?,?,?)");
            add.setString(1, null);
            add.setString(2, fullName);
            add.setString(3, address);
            add.setString(4, postCode);
            add.setString(5, phone);
            add.setString(6, email);
            add.setString(7, type);
            
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
        PreparedStatement editCustomerStmt = preparedStatement("UPDATE CUSTOMER_ACCOUNTS SET FULL_NAME=?, ADDRESS=?, POSTCODE=?, PHONE=?, TYPE=?, EMAIL=? WHERE CUSTOMER_ID=?");
        int counter = 0;
        while(counter < customerData.size())
        {
            editCustomerStmt.setString(1, customerData.get(counter).getFullName());
            editCustomerStmt.setString(2, customerData.get(counter).getAddress());
            editCustomerStmt.setString(3, customerData.get(counter).getPostCode());
            editCustomerStmt.setString(4, customerData.get(counter).getPhone());
            editCustomerStmt.setString(5, customerData.get(counter).getType());
            editCustomerStmt.setString(6, customerData.get(counter).getEmail());
            editCustomerStmt.setInt(7, customerData.get(counter).getID());
            
            editCustomerStmt.executeUpdate();
            
            counter++;
        }
        
        getAllCustomers();
    }
    
    /*Author Sergio*/
    public boolean addPart(String name, String description, int amount, double cost)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO PARTS_TRACKING VALUES (?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, name);
           add.setString(3, description);
           add.setInt(4, amount);
           add.setDouble(5, cost);
  
           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Part successfully added");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
    /*Author Sergio*/
    public boolean addInstalledPart( String REG_NUM, String INST_DATE, 
            String EXP_DATE,String PART_NAME, String CUST_NAME,int VEHICLE_ID)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO PARTS_INSTALLATION VALUES (?, ?, ?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, REG_NUM);
           add.setString(3, INST_DATE);
           add.setString(4, EXP_DATE);
           add.setString(5, PART_NAME);
           add.setString(6, CUST_NAME);
           add.setInt(7, VEHICLE_ID);
           
  
           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Part successfully added");
           
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
    public void partBelowZero() throws SQLException 
    {
 
        PreparedStatement partBelowZero = preparedStatement("DELETE FROM 'PARTS_TRACKING' WHERE AMOUNT=0 OR AMOUNT<0");
        partBelowZero.executeUpdate();
        
        
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
            String CUST_NAME = rs.getString("CUSTOMER_FULLNAME");
            int VEHICLE_ID = rs.getInt("VEHICLE_ID");
            
            
            
            installedPart installedPart = new installedPart(INST_ID, REG_NUM, INST_DATE, 
            EXP_DATE,PART_NAME, CUST_NAME,VEHICLE_ID);
            
            installedPartsData.add(installedPart);
        }
        return installedPartsData;
    }
      /*Author Sergio*/
    public ObservableList<installedPart> searchInstalledPart(String searchVal ) throws SQLException
    {   
        try{
        PreparedStatement searchInstalledPart = null;
        searchPartsData = FXCollections.observableArrayList();
        
      
        searchInstalledPart = preparedStatement("SELECT * FROM 'PARTS_INSTALLATION' WHERE REG_NUM LIKE '%" + searchVal +  "%' OR" + " CUSTOMER_FULLNAME LIKE '%" + searchVal +"%'" );
        
        //searchInstalledPart.setString(1,searchVal);
        
        
        ResultSet rs = searchInstalledPart.executeQuery();
        
        while(rs.next())
        {
            int INST_ID = rs.getInt("INSTALLATION_ID");
            String REG_NUM = rs.getString("REG_NUM");
            String INST_DATE = rs.getString("INSTALLATION_DATE");
            String EXP_DATE= rs.getString("EXP_DATE");
            String PART_NAME = rs.getString("PART_NAME");
            String CUST_NAME = rs.getString("CUSTOMER_FULLNAME");
            int VEHICLE_ID = rs.getInt("VEHICLE_ID");
            
            
            
            installedPart searchedPart = new installedPart(INST_ID, REG_NUM, INST_DATE, 
            EXP_DATE,PART_NAME, CUST_NAME,VEHICLE_ID);
            
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
              regComb1.add(rs.getString("name"));
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
    public void editInstalledPart() throws SQLException
    {
        PreparedStatement editInstalledPart = preparedStatement("UPDATE PARTS_INSTALLATION SET REG_NUM=?, INSTALLATION_DATE=?, EXP_DATE=?, PART_NAME= ?,CUSTOMER_FULLNAME=?,VEHICLE_ID=? WHERE INSTALLATION_ID=?");
        int counter = 0;
        while(counter < searchPartsData.size())
        {
            editInstalledPart.setString(1, searchPartsData.get(counter).getREG_NUM());
            editInstalledPart.setString(2, searchPartsData.get(counter).getINST_DATE());
            editInstalledPart.setString(3, searchPartsData.get(counter).getEXP_DATE());
            editInstalledPart.setString(4, searchPartsData.get(counter).getPART_NAME());
            editInstalledPart.setString(5, searchPartsData.get(counter).getCUST_NAME());
            editInstalledPart.setInt(6, searchPartsData.get(counter).getVEHICLE_ID());
            editInstalledPart.setInt(7, searchPartsData.get(counter).getINST_ID());
           
            
            editInstalledPart.executeUpdate();
            
            counter++;
        }
        
        getinstalledPart();
    }
    public void updateStock(String partname) throws SQLException
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
    public void calculateBill(String regNum,String name) throws SQLException 
    { 
        double cost=0.0;
        String custName=name;
        try
        {
            
        PreparedStatement getBill= preparedStatement("SELECT COST FROM PARTS_TRACKING,PARTS_INSTALLATION WHERE PARTS_TRACKING.RELEVANT_ID_NUM=PARTS_INSTALLATION.PART_ID AND REG_NUM=" + "'" + regNum + "'");

        ResultSet rs = getBill.executeQuery();
        while(rs.next())
        {
            cost= cost + rs.getDouble("COST");
          //custName=rs.getString("CUSTOMER_FULLNAME)");
        }
        
        JOptionPane.showMessageDialog(null,"The bill for " + name + " adds up to £ " + cost);
        }
        
    
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
        }

    }
    
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
       catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            
        }
    }
      public void removeInstalledPart(int id) throws SQLException
    {
        
        PreparedStatement removeInstalledPartStmt = preparedStatement("DELETE FROM PARTS_INSTALLATION WHERE INSTALLATION_ID="+ id);
      // removeInstalledPartStmt.setInt(1, id);
        removeInstalledPartStmt.executeUpdate();
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
                    
                    
                    

            Vehicle vehicle = new Vehicle(regnum,  model,  make, engine, fueltype, colour, motdate, lastservice, mileage, warranty, warrantycompany, warrantyaddress, warrantyexpiry);

            vehicleData.add(vehicle);
        }
        return vehicleData;
    }
    
    /*Author Sam*/
    public boolean addVehicle(String regnum, String model , String make, String engine, String fueltype, String colour, String motdate, String lastservice, String mileage, String warranty, String warrantycompany, String warrantyaddress, String warrantyexpiry) {
        PreparedStatement add = null;
        boolean added = false;
        try {
            add = preparedStatement("INSERT INTO VEHICLE_RECORD VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            int spcPhone = rs.getInt("PHONE");
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
            
            Outstanding outstandingpart = new Outstanding(bookingID, spcName, partID, partName, deliveryDate, returnDate);
            
            outPartsData.add(outstandingpart);
        }
        return outPartsData;
    }
    
     public void removeOutstandingPart(int id) throws SQLException
    {
        
        PreparedStatement removeOutstandingPartStmt = preparedStatement("DELETE FROM OUTSTANDING WHERE BOOK_ID="+ id);
      // removeInstalledPartStmt.setInt(1, id);
        removeOutstandingPartStmt.executeUpdate();
    }
    
     public ObservableList<Returned> getReturnedParts() throws SQLException
    {   
        
        PreparedStatement getReturnedParts = null;
        retPartsData = FXCollections.observableArrayList();
        
       
        getReturnedParts = preparedStatement("SELECT * FROM RETURNED_PARTS");
        ResultSet rs = getReturnedParts.executeQuery();
        
        while(rs.next())
        {
            String spcName = rs.getString("spcName");
            int partID = rs.getInt("partID");
            String partName = rs.getString("partName");
            String deliveryDate = rs.getString("deliveryDate");
            String returnDate = rs.getString("returnDate");
            
            Returned returnedpart = new Returned(spcName, partID, partName, deliveryDate, returnDate);
            
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
    public boolean addSPC( String SPC_NAME, String SPC_ADDRESS, int SPC_PHONE, String SPC_EMAIL)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO SPECIALIST_CENTRES VALUES (?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, SPC_NAME);
           add.setString(3, SPC_ADDRESS);
           add.setInt(4, SPC_PHONE);
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
    
     /*Author Shiraj*/
    public boolean bookSPCPart( String SPC, int PARTID, String PARTNAME, String DELIVDATE, String RETURNDATE)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO OUTSTANDING_PARTS VALUES (?, ?, ?, ?, ?, ?)"); 
           
           add.setString(1, null);
           add.setString(2, SPC);
           add.setInt(3, PARTID);
           add.setString(4, PARTNAME);
           add.setString(5, DELIVDATE);
           add.setString(6, RETURNDATE);
        

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

     public boolean returnedSPCPart( String SPC, int PARTID, String PARTNAME, String DELIVDATE, String RETURNDATE)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO RETURNED_PARTS VALUES (?, ?, ?, ?, ?)"); 
           add.setString(1, SPC);
           add.setInt(2, PARTID);
           add.setString(3, PARTNAME);
           add.setString(4, DELIVDATE);
           add.setString(5, RETURNDATE);
        

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

    
}
