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

/**
 *
 * @author hp
 */
public class EmpDao {
    public static boolean addEmployee(Emp e) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1, e.getEmpid());
        ps.setString(2, e.getEmpname());
        ps.setString(3, e.getJob());
        ps.setDouble(4, e.getSal());
        int a=ps.executeUpdate();
        return (a==1);
    }
    public static ArrayList<String> getEmployeesId() throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        System.out.println("h");
        ResultSet rs=st.executeQuery("select empid from employees");
        ArrayList<String> empid = new ArrayList<>();
        while(rs.next()){
            empid.add(rs.getString("empid"));
        }
        return empid;
    }
    
    public static ArrayList<Emp> getAllData() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from employees");
        ArrayList<Emp> empList=new ArrayList<Emp>();
        while(rs.next()){
            Emp e=new Emp();
            e.setEmpid(rs.getString("empid"));
            e.setEmpname(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));
            empList.add(e);
        }
        return empList;
    }
    
    public static Emp findEmployeeById(String empId)throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from employees where empid=(?)");
        ps.setString(1,empId);
        ResultSet rs=ps.executeQuery();
        Emp emp=new Emp();
        while(rs.next()){
            if(rs.getString("empid").equals(empId)){
                emp.setEmpid(rs.getString("empid"));
                emp.setEmpname(rs.getString("ename"));
                emp.setJob(rs.getString("job"));
                emp.setSal(rs.getDouble("sal"));
                return emp;
            }
        }
        return null;
    }
    public static boolean updateEmployee(Emp e) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update employees set ename=?,job=?,sal=? where empid=? ");
        ps.setString(1,e.getEmpname());
        ps.setString(2,e.getJob());
        ps.setDouble(3,e.getSal());
        ps.setString(4,e.getEmpid());
        int result=ps.executeUpdate();
        return (result==1);
    }
    
    public static boolean removeEmployee(String empId)throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from employees where empid=? ");
        ps.setString(1,empId);
        return (ps.executeUpdate()==1);
    }
}
