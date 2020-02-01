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
import java.util.HashMap;
import planetfood.dbutil.DBConnection;

/**
 *
 * @author hp
 */
public class CategoryDao {
    public static HashMap<String,String> getAllCategoryId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select cat_name,cat_id from categories");
        HashMap <String,String> categories=new HashMap<>();
        while(rs.next()){
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName, catId);
        }
        return categories;
    }
    
    public static String getNewCategoryId()throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select count(*) from categories");
        int catId=101;
        if(rs.next()){
            catId=catId+rs.getInt(1);
        }
        return ("C"+catId);
    }
    
    public static boolean addCategory(String catId,String catName)throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into categories values(?,?)");
        ps.setString(1, catId);
        ps.setString(2, catName);
        int status=ps.executeUpdate();
        return status>0;
    }
    
    public static boolean updateCategory(String catName,String catId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update categories set cat_name=(?) where cat_id=(?)");
        ps.setString(1, catName);
        ps.setString(2, catId);
        int result=ps.executeUpdate();
        return (result>0);
    }
}
