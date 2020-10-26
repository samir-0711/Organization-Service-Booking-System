
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praja
 */
public class Counter_for_id {
    public static int id_counter=0;
    
    public Counter_for_id()    
    {
//        System.out.println(id_counter);

        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String pass = "mysql";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_dbms", username,
            pass);
//            stmt = connection.createStatement();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error!!!");
        }
//            System.out.println(id_counter);
      
        try {
        Statement stat = connection.createStatement();
        String sqlp = "Select d from counter";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
            id_counter+=Integer.parseInt(rs.getString("d"));}
            }catch (SQLException err) {
        System.out.println(err.getMessage());}
    }
    
    public static void increase()
    {
        id_counter++;
    }
    
    public static int Counter_for_id()
    {
        return id_counter;
    }
    
    public static void main(String args[])
    {
        Counter_for_id b = new Counter_for_id();
    }
}


