/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.database;

import common.logic.SystemUser;
import customers.logic.Customers;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import parts.logic.Part;
import parts.logic.installedPart;
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
    private ObservableList<installedPart> installedPartsData;
    private ObservableList<installedPart> searchPartsData;
    
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
    
    public boolean addSysUser(int ID, String firstName, String surname, String password, String admin)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO AUTHENTICATION VALUES (?, ?, ?, ?, ?)"); 
           add.setInt(1, ID);
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
            String fullName = rs.getString("FULLNAME");
            String address = rs.getString("ADDRESS");
            String postCode = rs.getString("POSTCODE");
            String phone = rs.getString("PHONE");
            String type = rs.getString("TYPE");
            String email = rs.getString("EMAIL");
            String vehicleReg = rs.getString("VEHICLE_REG");
            
            Customers customer = new Customers(id, fullName, vehicleReg, address, postCode, phone, email, type);
            
            customerData.add(customer);
        }
        return customerData;
    }
    
    /*Author Sergio*/
    public boolean addPart(int ID, String name, String description, int amount, double cost)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO PARTS_TRACKING VALUES (?, ?, ?, ?, ?)"); 
           add.setInt(1, ID);
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
    public boolean addInstalledPart(int INST_ID, String REG_NUM, String INST_DATE, 
            String EXP_DATE,int PART_ID, String CUST_NAME,int VEHICLE_ID, double PART_COST)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO PARTS_INSTALLATION VALUES (?, ?, ?, ?, ?, ?, ?, ?)"); 
           add.setInt(1, INST_ID);
           add.setString(2, REG_NUM);
           add.setString(3, INST_DATE);
           add.setString(4, EXP_DATE);
           add.setInt(5, PART_ID);
           add.setString(6, CUST_NAME);
           add.setInt(7, VEHICLE_ID);
           add.setDouble(8, PART_COST);
  
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
            int PART_ID = rs.getInt("PART_ID");
            String CUST_NAME = rs.getString("CUSTOMER_FULLNAME");
            int VEHICLE_ID = rs.getInt("VEHICLE_ID");
            double PART_COST = rs.getInt("PART_COST");
            
            
            installedPart installedPart = new installedPart(INST_ID, REG_NUM, INST_DATE, 
            EXP_DATE,PART_ID, CUST_NAME,VEHICLE_ID,PART_COST);
            
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
            int PART_ID = rs.getInt("PART_ID");
            String CUST_NAME = rs.getString("CUSTOMER_FULLNAME");
            int VEHICLE_ID = rs.getInt("VEHICLE_ID");
            double PART_COST = rs.getInt("PART_COST");
            
            
            installedPart searchedPart = new installedPart(INST_ID, REG_NUM, INST_DATE, 
            EXP_DATE,PART_ID, CUST_NAME,VEHICLE_ID,PART_COST);
            
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
        PreparedStatement editInstalledPart = preparedStatement("UPDATE PARTS_INSTALLATION SET REG_NUM=?, INSTALLATION_DATE=?, EXP_DATE=?, PART_ID= ?,CUSTOMER_FULLNAME=?,VEHICLE_ID=?,PART_COST=? WHERE INSTALLATION_ID=?");
        int counter = 0;
        while(counter < searchPartsData.size())
        {
            editInstalledPart.setString(1, searchPartsData.get(counter).getREG_NUM());
            editInstalledPart.setString(2, searchPartsData.get(counter).getINST_DATE());
            editInstalledPart.setString(3, searchPartsData.get(counter).getEXP_DATE());
            editInstalledPart.setString(4, searchPartsData.get(counter).getCUST_NAME());
            editInstalledPart.setInt(5, searchPartsData.get(counter).getVEHICLE_ID());
            editInstalledPart.setDouble(6, searchPartsData.get(counter).getPART_COST());
            editInstalledPart.setInt(7, searchPartsData.get(counter).getPART_ID());
            editInstalledPart.setInt(8, searchPartsData.get(counter).getINST_ID());
           
            
            editInstalledPart.executeUpdate();
            
            counter++;
        }
        
        getinstalledPart();
    }
    public void deleteInstalledPart() throws SQLException
    {
        PreparedStatement deleteInstalledPart = preparedStatement("DELETE FROM PARTS_INSTALLATION WHERE REG_NUM=?," +
                 " INSTALLATION_DATE=?, EXP_DATE=?, PART_ID= ?,CUSTOMER_FULLNAME= ?,"
                + "VEHICLE_ID= ?,+ PART_COST WHERE INSTALLATION_ID=?");
        int counter = 0;
        while(counter < installedPartsData.size())
        {
            deleteInstalledPart.setString(1, installedPartsData.get(counter).getREG_NUM());
            deleteInstalledPart.setString(2, installedPartsData.get(counter).getINST_DATE());
            deleteInstalledPart.setString(2, installedPartsData.get(counter).getEXP_DATE());
            deleteInstalledPart.setString(2, installedPartsData.get(counter).getCUST_NAME());
            deleteInstalledPart.setInt(3, installedPartsData.get(counter).getVEHICLE_ID());
            deleteInstalledPart.setDouble(3, installedPartsData.get(counter).getPART_COST());
            deleteInstalledPart.setInt(3, installedPartsData.get(counter).getPART_ID());
            deleteInstalledPart.setInt(3, installedPartsData.get(counter).getINST_ID());
           
            
            deleteInstalledPart.executeUpdate();
            
            counter++;
        }
        
        getinstalledPart();
    }
    public static Database getInstance()
    {
        return db;
    }

    
}
