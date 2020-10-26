/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praja
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class MysqlConnection {
//    private static Statement stmt = null;
//    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Scanner sc = null;
    //private static Connection connection = null;
    
    public static boolean connected(int n,String data[]) {
        // TODO code application logic here
        Connection connection = null;
        try
        {
            sc = new Scanner(System.in);
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
        if(n==1){
            storedata_login(connection,data);          
            storedata_registration(connection,data);
        }
        else if(n==2)
        {
            return check_login(connection,data);
        }
        else if(n==3)
        {
            return check_forgotpassword(connection,data);  
        }
        else if(n==4){
            change_password(connection,data);
        }
        else if(n==5)
        {
            storedata_org_login(connection,data);
            storedata_org_registration(connection,data);
        }
        else if(n==6)
        {
            return check_forgotpassword(connection,data);  
        }
        else if(n==7)
        {
            add_salon_booking_info(connection,data);
        }
        else if(n==8)
        {
            add_service_charge_info(connection,data);
        }
        else if(n==9)
        {
            add_service_booking_info(connection,data);
        }
        else if(n==10)
        {
            edit_user_information_in_table(connection,data);
        }
        else if(n==11)
        {
            edit_org_information_in_table(connection,data);
        }
        else if(n==12)
        {
            edit_home_service_information_in_table(connection,data);
        }
        return true;
    }
        
    public static int storedata_registration(Connection con, String data[]){
        try {
            PreparedStatement ps = con.prepareStatement("insert into user_info values(?,?,?,?,?,?)");
        ps.setString(1, data[0]);
        ps.setString(2, data[1]);
        ps.setString(3, data[2]); 
        ps.setString(4, data[3]);
//        if(!(data[4].equals("NULL")))
//        {
//            ps.setString(5, data[4]);
//        }
//        else{
//            ps.setNull(5,Types.NULL);
//        }
        ps.setString(5, data[4]);
        ps.setString(6, data[6]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        return 0;
    }
    
    public static int storedata_login(Connection con, String data[]){
        try {
            PreparedStatement ps = con.prepareStatement("insert into username_pwd values(?,?)");
        ps.setString(1, data[0]);
        ps.setString(2, data[8]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        return 0;
    }
    
    public static boolean check_login(Connection con, String data[]){
        if(data[0].equals("admin") && data[1].equals("admin"))
        {
            Counter_for_id f = new Counter_for_id();
            f.Counter_for_id();
            String a[] = {LoginJFrame.id_user};
            admin.main(a);
            return true;
        }
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select Password from username_pwd where Username='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("Password");
        if(pwds.equals(data[1]))
        {
            //main page
            Counter_for_id f = new Counter_for_id();
            f.Counter_for_id();
            String a[] ={LoginJFrame.id_user};
            user_mainpage.main(a);
            return true;
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select org_pwd,org_type from organization_id_pwd where org_id='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("org_pwd");
        String type = rs.getString("org_type");
        if(pwds.equals(data[1]))
        {
            //main page according to type
            if(type.equals("s"))
            {
                Counter_for_id f = new Counter_for_id();
                f.Counter_for_id();
                String[] a = {LoginJFrame.id_user};
                edit_org_info.main(a);
                return true;
            }
            else if(type.equals("e"))
            {
                Counter_for_id f = new Counter_for_id();
                f.Counter_for_id();
                String[] a = {LoginJFrame.id_user};
                edit_home_service_info.main(a);
                return true;
            }
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
     
        return false;
    }
    
    public static boolean check_forgotpassword(Connection con, String data[]){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select user_email_address from user_info where user_id='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("user_email_address");
        if(pwds.equals(data[1]))
        {
            return true;
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select R_email_address from restaurant_info where R_id='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("R_email_address");
        if(pwds.equals(data[1]))
        {
            return true;
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select H_email_address from hotel_info where H_id='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("H_email_address");
        if(pwds.equals(data[1]))
        {
            return true;
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select S_email_address from salon_info where S_id='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("S_email_address");
        if(pwds.equals(data[1]))
        {
            return true;
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_email_address from home_services_info where HS_id='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("HS_email_address");
        if(pwds.equals(data[1]))
        {
            return true;
        }}} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        JOptionPane.showMessageDialog(null,"Username or Email-Address is wrong");
        return false;
    }
    
    public static void change_password(Connection con, String data[]){
        if(verify_username(con,data[0]))
        {
            try {
            Statement stat = con.createStatement();
            String sqlp = "update username_pwd set password='"+data[1]+"' where Username='"+data[0]+"'";
                boolean rs = stat.execute(sqlp);
                if(rs==true){
                    System.out.println("Password changed successfull");
                }
            } catch (SQLException err) {
            System.out.println(err.getMessage());}
        }
        else{
            try {
            Statement stat = con.createStatement();
            String sqlp = "update organization_id_pwd set org_pwd='"+data[1]+"' where org_id='"+data[0]+"'";
                boolean rs = stat.execute(sqlp);
                if(rs==true){
                    System.out.println("Password changed successfull");
                }
            } catch (SQLException err) {
            System.out.println(err.getMessage());}
        }
    }
    
    public static int storedata_org_login(Connection con, String data[]){
        try {
            PreparedStatement ps = con.prepareStatement("insert into organization_id_pwd values(?,?,?)");
        ps.setString(1, data[11]);
        ps.setString(2, data[12]);
        if(data[0].equals("Hotel"))
        {
            data[0]="h";
        }
        else if(data[0].equals("Restaurant"))
        {
            data[0]="r";
        }
        else if(data[0].equals("Salon"))
        {
            data[0]="s";
        }
        else if(data[0].equals("Home Repair and Maintenance"))
        {
            data[0]="e";
        }
        ps.setString(3, data[0]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        return 0;
    }
    
    public static int storedata_org_registration(Connection con, String data[]){
//        for(String i:data){
//            System.out.println(i);}
            
        if(data[0].equals("h"))
        {
        try {
        PreparedStatement ps = con.prepareStatement("insert into hotel_info values(?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, data[11]);
        ps.setString(2, data[1]);
        ps.setString(3, data[2]); 
        ps.setString(4, data[3]);
        ps.setString(5, data[4]);
//        if(!(data[13].equals("NULL")))
//        {
//            ps.setString(6, data[13]);
//        }
//        else{
//            ps.setNull(6,Types.NULL);
//        }
        ps.setString(6, data[5]);
        ps.setString(7, data[6]);
        ps.setString(8, data[7].toLowerCase());
        ps.setString(9, data[8].toLowerCase());
        ps.setString(10, data[9].toLowerCase());
        ps.setString(11, data[10]);

        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        else if(data[0].equals("r"))
        {
                try {
            PreparedStatement ps = con.prepareStatement("insert into restaurant_info values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, data[11]);
            ps.setString(2, data[1]);
            ps.setString(3, data[2]); 
            ps.setString(4, data[3]);
            ps.setString(5, data[4]);
//            if(!(data[13].equals("NULL")))
//            {
//                ps.setString(6, data[13]);
//            }
//            else{
//                ps.setNull(6,Types.NULL);
//            }
            ps.setString(6, data[5]);
            ps.setString(7, data[6]);
            ps.setString(8, "NV");
            ps.setString(9, data[7].toLowerCase());
            ps.setString(10, data[8].toLowerCase());
            ps.setString(11, data[9].toLowerCase());
            ps.setString(12, "NA");
            ps.setString(13, data[10]);

            int i = ps.executeUpdate();
            System.out.println(i + " records affected");
            } catch (Exception err) {
            System.out.println(err.getMessage());}
        }
        else if(data[0].equals("s"))
        {
            try {
        PreparedStatement ps = con.prepareStatement("insert into salon_info values(?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, data[11]);
        ps.setString(2, data[1]);
        if(data[2].equals("Unisex")){
            data[2]="b";
        }
        else if(data[2].equals("Only for men"))
        {
            data[2]="m";
        }
        else if(data[2].equals("Only for Women"))
        {
            data[2]="w";
        }
        ps.setString(3, data[2]); 
        ps.setString(4, data[3]);
        ps.setString(5, data[4]);
//        if(!(data[13].equals("NULL")))
//        {
//            ps.setString(6, data[13]);
//        }
//        else{
//            ps.setNull(6,Types.NULL);
//        }
        ps.setString(6, data[5]);
        ps.setString(7, data[6]);
        ps.setString(8, data[7].toLowerCase());
        ps.setString(9, data[8].toLowerCase());
        ps.setString(10, data[9].toLowerCase());
        ps.setString(11, data[10]);

        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        else if(data[0].equals("e"))
        {
                try {
        PreparedStatement ps = con.prepareStatement("insert into home_services_info values(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, data[11]);
        ps.setString(2, data[1]);
//        if(data[2].equals("Plumber")){
//            data[2]="p";
//        }
//        else if(data[2].equals("Electrician"))
//        {
//            data[2]="e";
//        }
//        else if(data[2].equals("Carpenter"))
//        {
//            data[2]="c";
//        }
//        else if(data[2].equals("ALL")){
//            data[2]="a";
//        }
//        ps.setString(3, data[2]); 
        ps.setString(3, data[3]);
        ps.setString(4, data[4]);
//        if(!(data[13].equals("NULL")))
//        {
//            ps.setString(5, data[13]);
//        }
//        else{
//            ps.setNull(5,Types.NULL);
//        }
        ps.setString(5, data[5]);
        ps.setString(6, data[6]);
        ps.setString(7, data[7].toLowerCase());
        ps.setString(8, data[8].toLowerCase());
        ps.setString(9, data[9].toLowerCase());
        ps.setString(10, data[10]);

        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        return 0;
    }
    
    public static boolean verify_username(Connection con, String data){
        try {
        Statement stat = con.createStatement();
        String sqlp = "select exists(Select * from user_info where user_id='"+data+"') as t";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("t");
        if(pwds.equals("1"))
        {
            return true;
        }
        else if(pwds.equals("0")){
            return false;
        }
            }} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        return false;
    }
    
    public static boolean verify_salon_booking(Connection con, String data[]){
        try {
        Statement stat = con.createStatement();
        String sqlp = "select exists(Select * from salon_booking_info where username_customer='"+data[0]+"' and S_id='"+data[1]+"' and S_Arrival_Date='"+data[2]+"' and S_Arrival_Time='"+data[3]+"') as t";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("t");
        if(pwds.equals("1"))
        {
            return true;
        }
        else if(pwds.equals("0")){
            return false;
        }
            }} catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        return false;
    }
    
    public static String get_phone_number(Connection con, String pwds){
       
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select S_phone_no_1 from salon_info where S_id='"+pwds+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        String pwdss="9999999999";
        while(rs.next()){
        pwdss = rs.getString("S_phone_no_1");}
        return pwdss;
        } catch (SQLException err) {
        System.out.println(err.getMessage());}
        return "9999999999";
         }
    
    public static int add_salon_booking_info(Connection con, String data[]){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select S_id from salon_info where S_address='"+data[6]+"' and S_name='"+data[5]+"' and S_area='"+data[3]+"' and S_city='"+data[2]+"' and S_state='"+data[1]+"' and S_type='"+data[4]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        String pwds="a";
        while(rs.next()){
        pwds = rs.getString("S_id");}
        
        String h[] = {data[0],pwds,data[7],data[8]};
        if(!verify_salon_booking(con,h)){
        PreparedStatement ps = con.prepareStatement("insert into salon_booking_info values(?,?,?,?,?)");
        ps.setString(1, data[0]);
        ps.setString(2, pwds);
        ps.setString(3, data[7]); 
        ps.setString(4, data[8]);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ps.setString(5, timestamp.toString());
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");}
        else{
            JOptionPane.showMessageDialog(null,"Booking Exist");
            return 0;
        }
        
        String aa[] = {get_phone_number(con,pwds)};
        Salon_Confirm_message.main(aa);} catch (Exception err) {
        System.out.println(err.getMessage());}
        
        return 0;
    }
    
    public static int add_service_charge_info(Connection con, String data[]){
        if(data[0].equals("y")){
        try {
            PreparedStatement ps = con.prepareStatement("insert into HS_visting_charge_entry values(?,?,?)");
        ps.setString(1, data[6]);
        ps.setString(2, "c");
        ps.setString(3, data[1]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}}
        
        if(data[2].equals("y")){
        try {
            PreparedStatement ps = con.prepareStatement("insert into HS_visting_charge_entry values(?,?,?)");
        ps.setString(1, data[6]);
        ps.setString(2, "e");
        ps.setString(3, data[3]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}}
        
        if(data[4].equals("y")){
        try {
            PreparedStatement ps = con.prepareStatement("insert into HS_visting_charge_entry values(?,?,?)");
        ps.setString(1, data[6]);
        ps.setString(2, "p");
        ps.setString(3, data[5]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}}
        
        return 0;
    }
    
    /*public static boolean verify_service_booking(Connection con, String data[]){
        String f="a",s="a",t="a";
//        boolean g=true,h=true,k=true;
        if(data[3].equals("true")){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_id from Home_services_info where HS_address='"+data[5]+"' and HS_name='"+data[4]+"' and HS_area='"+data[2]+"' and HS_city='"+data[1]+"' and HS_state='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        f = rs.getString("HS_id");}
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        
        try{
        Statement stat = con.createStatement();
        String sqlp = "select exists(Select * from HS_customer_address as d natural join Home_service_type_booking_info as f"
                + " where d.username_customer='"+LoginJFrame.id_user+"' and d.HS_id='"+f+"' and"
                + " d.Address='"+data[5]+"' and d.Area='"+data[2]+"' and d.City='"+data[1]+"' and"
                + " d.State='"+data[0]+"' and d.pincode='"+data[15]+"' and d.Preferred_Date='"+data[17]+"' and"
                + " d.Preferred_Time='"+data[18]+"' and f.HS_type='p') as tp";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("t");
        if(pwds.equals("1"))
        {
            JOptionPane.showMessageDialog(null,"Booking Exist for Plumber");
        }
        else if(pwds.equals("0")){
            g=false;
        }
            }} catch (SQLException err) {
        System.out.println(err.getMessage());}}
        
        if(data[6].equals("true")){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_id from Home_services_info where HS_address='"+data[8]+"' and HS_name='"+data[7]+"' and HS_area='"+data[2]+"' and HS_city='"+data[1]+"' and HS_state='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        s = rs.getString("HS_id");}
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        
        try{
        Statement stat = con.createStatement();
        String sqlp = "select exists(Select * from HS_customer_address as d natural join Home_service_type_booking_info as f"
                + " where d.username_customer='"+LoginJFrame.id_user+"' and d.HS_id='"+s+"' and"
                + " d.Address='"+data[8]+"' and d.Area='"+data[2]+"' and d.City='"+data[1]+"' and"
                + " d.State='"+data[0]+"' and d.pincode='"+data[15]+"' and d.Preferred_Date='"+data[17]+"' and"
                + " d.Preferred_Time='"+data[18]+"' and f.HS_type='e') as tp";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("t");
        if(pwds.equals("1"))
        {
            h=true;
        }
        else if(pwds.equals("0")){
            h=false;
        }
            }} catch (SQLException err) {
        System.out.println(err.getMessage());}}
        
        if(data[9].equals("true")){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_id from Home_services_info where HS_address='"+data[11]+"' and HS_name='"+data[10]+"' and HS_area='"+data[2]+"' and HS_city='"+data[1]+"' and HS_state='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        t = rs.getString("HS_id");}
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        
        try{
        Statement stat = con.createStatement();
        String sqlp = "select exists(Select * from HS_customer_address as d natural join Home_service_type_booking_info as f"
                + " where d.username_customer='"+LoginJFrame.id_user+"' and d.HS_id='"+t+"' and"
                + " d.Address='"+data[11]+"' and d.Area='"+data[2]+"' and d.City='"+data[1]+"' and"
                + " d.State='"+data[0]+"' and d.pincode='"+data[15]+"' and d.Preferred_Date='"+data[17]+"' and"
                + " d.Preferred_Time='"+data[18]+"' and f.HS_type='c') as tp";
        ResultSet rs = stat.executeQuery(sqlp);
        while(rs.next()){
        String pwds = rs.getString("t");
        if(pwds.equals("1"))
        {
            k=true;
        }
        else if(pwds.equals("0")){
            k=false;
        }
            }} catch (SQLException err) {
        System.out.println(err.getMessage());}}
        
        return false;
    }*/
        
    public static int add_service_booking_info(Connection con, String data[]){
        
        String previous="-";
        String last="-";
        if(data[3].equals("true")){
            Counter_for_id.increase();
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_id from Home_services_info where HS_address='"+data[5]+"' and HS_name='"+data[4]+"' and HS_area='"+data[2]+"' and HS_city='"+data[1]+"' and HS_state='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        String pwds="a";
        while(rs.next()){
        pwds = rs.getString("HS_id");}
        previous=String.valueOf(pwds);
//        if(!verify_service_booking(con,data)){
        PreparedStatement ps = con.prepareStatement("insert into HS_customer_address values(?,?,?,?,?,?,?)");
        ps.setString(1, String.valueOf(Counter_for_id.Counter_for_id()));
        ps.setString(2, LoginJFrame.id_user);
        ps.setString(3, pwds); 
        ps.setString(4, data[14]);
//        ps.setString(5, data[2]);
//        ps.setString(6, data[1]);
//        ps.setString(7, data[0]);
        ps.setString(5, data[15]);
        ps.setString(6, data[17]);
        ps.setString(7,data[18]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");//}
//        else{
//            JOptionPane.showMessageDialog(null,"Booking Exist");
//            return 0;}
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        
        try {
            PreparedStatement ps = con.prepareStatement("insert into Home_service_type_booking_info values(?,?,?)");
        ps.setString(1, String.valueOf(Counter_for_id.Counter_for_id()));
        ps.setString(2, "p");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ps.setString(3, timestamp.toString());
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        
        if(data[6].equals("true")){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_id from Home_services_info where HS_address='"+data[8]+"' and HS_name='"+data[7]+"' and HS_area='"+data[2]+"' and HS_city='"+data[1]+"' and HS_state='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        String pwds="a";
        while(rs.next()){
        pwds = rs.getString("HS_id");}
        
        last=String.valueOf(pwds);
        if(!pwds.equals(previous))
        {
            Counter_for_id.increase();
       
//        if(!verify_service_booking(con,data)){
        PreparedStatement ps = con.prepareStatement("insert into HS_customer_address values(?,?,?,?,?,?,?)");
        ps.setString(1, String.valueOf(Counter_for_id.Counter_for_id()));
        ps.setString(2, LoginJFrame.id_user);
        ps.setString(3, pwds); 
        ps.setString(4, data[14]);
//        ps.setString(5, data[2]);
//        ps.setString(6, data[1]);
//        ps.setString(7, data[0]);
        ps.setString(5, data[15]);
        ps.setString(6, data[17]);
        ps.setString(7,data[18]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");//}
//        else{
//            JOptionPane.showMessageDialog(null,"Booking Exist");
//            return 0;}
        }} catch (Exception err) {
        System.out.println(err.getMessage());}
        
        try {
            PreparedStatement ps = con.prepareStatement("insert into Home_service_type_booking_info values(?,?,?)");
        ps.setString(1, String.valueOf(Counter_for_id.Counter_for_id()));
        ps.setString(2, "e");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ps.setString(3, timestamp.toString());
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        
        if(data[9].equals("true")){
        try {
        Statement stat = con.createStatement();
        String sqlp = "Select HS_id from Home_services_info where HS_address='"+data[11]+"' and HS_name='"+data[10]+"' and HS_area='"+data[2]+"' and HS_city='"+data[1]+"' and HS_state='"+data[0]+"'";
        ResultSet rs = stat.executeQuery(sqlp);
        String pwds="a";
        while(rs.next()){
        pwds = rs.getString("HS_id");}
        
        if(!pwds.equals(previous) && !pwds.equals(last))
        {
            Counter_for_id.increase();
        
//        if(!verify_service_booking(con,data)){
        PreparedStatement ps = con.prepareStatement("insert into HS_customer_address values(?,?,?,?,?,?,?)");
        ps.setString(1, String.valueOf(Counter_for_id.Counter_for_id()));
        ps.setString(2, LoginJFrame.id_user);
        ps.setString(3, pwds); 
        ps.setString(4, data[14]);
//        ps.setString(5, data[2]);
//        ps.setString(6, data[1]);
//        ps.setString(7, data[0]);
        ps.setString(5, data[15]);
        ps.setString(6, data[17]);
        ps.setString(7,data[18]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");//}
//        else{
//            JOptionPane.showMessageDialog(null,"Booking Exist");
//            return 0;}
        }} catch (Exception err) {
        System.out.println(err.getMessage());}
        
        try {
            PreparedStatement ps = con.prepareStatement("insert into Home_service_type_booking_info values(?,?,?)");
        ps.setString(1, String.valueOf(Counter_for_id.Counter_for_id()));
        ps.setString(2, "c");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ps.setString(3, timestamp.toString());
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        
        return 0;
    }
    
    public static void edit_user_information_in_table(Connection con,String data[])
    {
        try{
        String sql = "update User_info set user_First_name=?,user_Last_name=?,user_gender=?,user_phone_no_1=?,"
                + "user_email_address=? where user_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, data[1]);
        ps.setString(2, data[2]);
        ps.setString(3, data[3]);
        ps.setString(4, data[4]);
//        ps.setString(5, data[5]);
        ps.setString(5, data[6]);
        ps.setString(6, data[0]);        
        
      ps.executeUpdate();
      System.out.println("Database updated successfully ");
        } catch (SQLException err) {
        System.out.println(err.getMessage());
        }
    }
    
    public static void edit_org_information_in_table(Connection con,String data[])
    {
        try{
        String sql = "update Salon_info set S_name=?,S_type=?,S_contanct_person=?,S_phone_no_1=?,"
                + "S_email_address=?,S_address=?,S_area=?,S_city=?,S_state=?,S_pincode=? where S_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, data[1]);
        ps.setString(2, data[2]);
        ps.setString(3, data[3]);
        ps.setString(4, data[4]);
//        ps.setString(5, data[5]);
        ps.setString(5, data[6]);
        ps.setString(6, data[7]);  
        ps.setString(7, data[8]);
        ps.setString(8, data[9]);
        ps.setString(9, data[10]);
        ps.setString(10, data[11]);
        ps.setString(11, data[0]);
        
      ps.executeUpdate();
      System.out.println("Database updated successfully ");
        } catch (SQLException err) {
        System.out.println(err.getMessage());
        }
    }
    
    public static void edit_home_service_information_in_table(Connection con,String[] data){
        try{
        String sql = "update Home_services_info set HS_name=?,HS_contanct_person=?,HS_phone_no_1=?,"
                + "HS_email_address=?,HS_address=?,HS_area=?,HS_city=?,HS_state=?,HS_pincode=? where HS_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, data[1]);
        ps.setString(2, data[8]);
        ps.setString(3, data[9]);
        ps.setString(4, data[10]);
        ps.setString(5, data[11]);
        ps.setString(6, data[12]);  
        ps.setString(7, data[13]);
        ps.setString(8, data[14]);
        ps.setString(9, data[15]);
        ps.setString(10, data[0]);
        
        ps.executeUpdate();
//      System.out.println("Database updated successfully ");
        } catch (SQLException err) {
        System.out.println(err.getMessage());
        }
        
        try {
        Statement stat = con.createStatement();
        String sqlp = "delete from HS_visting_charge_entry where HS_id='"+data[0]+"'";
        stat.executeUpdate(sqlp);
        } catch (SQLException err) {
        System.out.println(err.getMessage());}
        
        if(!data[2].equals("a")){
        try {
            PreparedStatement ps = con.prepareStatement("insert into HS_visting_charge_entry values(?,?,?)");
        ps.setString(1, data[0]);
        ps.setString(2, data[2]);
        ps.setString(3, data[3]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
    
        if(!data[4].equals("a")){
        try {
            PreparedStatement ps = con.prepareStatement("insert into HS_visting_charge_entry values(?,?,?)");
        ps.setString(1, data[0]);
        ps.setString(2, data[4]);
        ps.setString(3, data[5]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        
        if(!data[6].equals("a")){
        try {
            PreparedStatement ps = con.prepareStatement("insert into HS_visting_charge_entry values(?,?,?)");
        ps.setString(1, data[0]);
        ps.setString(2, data[6]);
        ps.setString(3, data[7]);
        
        int i = ps.executeUpdate();
        System.out.println(i + " records affected");
        } catch (Exception err) {
        System.out.println(err.getMessage());}
        }
        System.out.println("Database updated successfully ");
    }

}
