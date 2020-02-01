/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.User;

/**
 *
 * @author hp
 */
public class UserDao {
    public static String validateUser(User user)throws SQLException{
        String qry="select username from users where userid=? and password=? and usertype=?";
        PreparedStatement ps=DBConnection.getConnection().prepareStatement(qry);
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next()){
            username=rs.getString(1);
        }
        return username;
    }
    public static boolean addUser(User user,String userName,String empId) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1, user.getUserId());
        ps.setString(2, userName);
        ps.setString(3, empId);
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getUserType());
        return (ps.executeUpdate()==1);
    }
    
    public static String getIdByUserId(String userId) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select empid from users where userid=? and usertype=?");
        ps.setString(1, userId);
        ps.setString(2, "Cashier");
        ResultSet rs=ps.executeQuery();
        String empId=null;
        if(rs.next()){
            empId=rs.getString("empid");
        } 
        return empId;
    }
    
     public static boolean removeCashier(String empId)throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from users where empid=? ");
        ps.setString(1,empId);
        PreparedStatement ps1=DBConnection.getConnection().prepareStatement("delete from employees where empid=? ");
        ps1.setString(1,empId);
        return (ps.executeUpdate()==1 && ps1.executeUpdate()==1);
    }
}
