/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.database;

import common.logic.SystemUser;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import parts.logic.Part;
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
    private ObservableList<Part> partsData;
    
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
        PreparedStatement allUsers = null;
        
         usersData = FXCollections.observableArrayList();
        
       
        allUsers = preparedStatement("SELECT * FROM AUTHENTICATION");
        ResultSet rs = allUsers.executeQuery();
        
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
        PreparedStatement editUser = preparedStatement("UPDATE AUTHENTICATION SET FIRST_NAME=?, SURNAME=?, PASSWORD=?, ADMIN=? WHERE ID=?");
        int counter = 0;
        while(counter < usersData.size())
        {
            editUser.setString(1, usersData.get(counter).getfirstName());
            editUser.setString(2, usersData.get(counter).getSurname());
            editUser.setString(3, usersData.get(counter).getPassword());
            editUser.setString(4, usersData.get(counter).getAdmin());
            editUser.setInt(5, usersData.get(counter).getID());
            
            editUser.executeUpdate();
            
            counter++;
        }
        
        getAllUsers();
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
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
    /*Author Sergio*/
    public boolean addInstalledPart(int INST_ID, String REG_NUM, String INST_DATE, 
            String EXP_DATE,int PART_ID, String CUST_NAME,int VEHICLE_ID, int PART_COST)
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
           add.setInt(8, PART_COST);
  
           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Part successfully added");
        }
        catch(SQLException ex)
        {
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
    
    public void editPart() throws SQLException
    {
        PreparedStatement editPart = preparedStatement("UPDATE PARTS_TRACKING SET  NAME=?, DESCRIPTION=?, AMOUNT=?, COST=? WHERE RELEVANT_ID_NUM=?");
        int counter = 0;
        while(counter < partsData.size())
        {
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
    
    public static Database getInstance()
    {
        return db;
    }
}
