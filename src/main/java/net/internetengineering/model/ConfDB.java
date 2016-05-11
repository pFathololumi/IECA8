/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.internetengineering.model;

/**
 *
 * @author Hamed Ara
 */
//STEP 1. Import required packages
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.internetengineering.myServiceHandlers.MyHttpServlet;
import net.internetengineering.utils.HSQLUtil;
@WebServlet("/confdb")
public class ConfDB extends MyHttpServlet{
    private static final String b="";
    
    
    @Override
    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
        Connection conn = null;
        Statement stmt = null;
        try{

             System.out.println("Connecting to database...");
             conn= HSQLUtil.getInstance().openConnectioin();
             System.out.println("Creating statement...");
             Statement st = conn.createStatement();
		st.executeUpdate("insert into poll values('19', 'How are you?')");


		ResultSet rs = st.executeQuery("select * from poll");
		while (rs.next()) {
			String subj = rs.getString("subject");
			System.out.println(rs.getString("code") + '\t' + subj);
		}

		st.executeUpdate("delete from poll where code = '19'");
		
		
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }// nothing we can do
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}