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
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.internetengineering.domain.Transaction;
import net.internetengineering.exception.DBException;
import net.internetengineering.myServiceHandlers.MyHttpServlet;
import net.internetengineering.utils.HSQLUtil;
@WebServlet("/confdb")
public class ConfDB extends MyHttpServlet{
    @Override
    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        PrintWriter out = response.getWriter();
        try{
            Transaction t = new Transaction("12", "1", "RANA", "GTC", "22", "200","201");
            TransactionDAO.createTransaction(t);
        } catch (DBException ex) {
            out.print(ex.getMessage());
            Logger.getLogger(ConfDB.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
           out.print("???? ????? ?? ?????? ?? ???? ???.");
           Logger.getLogger(ConfDB.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("Goodbye!");
    }
}