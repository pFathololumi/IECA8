/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.internetengineering.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.internetengineering.domain.Transaction;
import net.internetengineering.exception.DBException;
import net.internetengineering.utils.HSQLUtil;


/**
 *
 * @author Hamed Ara
 */
public class TransactionDAO {
    private static final String insertQuery = "insert into transaction "
            + "(buyer, seller, instrument, typeOfTrade, quantity, price) "
            + "values (?, ?, ?, ?, ?, ?)";

    private static final String selectQuery = "SELECT * FROM transaction order by time";
                    
    public static void createTransaction(Transaction t) throws DBException{
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = HSQLUtil.getInstance().openConnectioin();
            preparedStatement = dbConnection.prepareStatement(insertQuery);
            preparedStatement.setString(1, t.buyer);
            preparedStatement.setString(2, t.seller);
            preparedStatement.setString(3, t.instrument);
            preparedStatement.setString(4, t.typeOfTrade);
            preparedStatement.setString(5, t.quantity);
            preparedStatement.setString(6, t.price);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException("Unable to execute insert in Transaction table.",ex);
        }finally{
            try {
                if(preparedStatement!=null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(dbConnection!=null && !dbConnection.isClosed())
                    dbConnection.close();
            } catch (SQLException ex) {
                throw new DBException("Unable to close connection in Transaction table.",ex);
            }
        }
    }

    public static ResultSet getAllTransactions() throws DBException{
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection= HSQLUtil.getInstance().openConnectioin();
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            
            return preparedStatement.executeQuery();

        } catch (SQLException ex) {
            throw new DBException("Unable to execute select in Transaction table.",ex);
        }finally{
            try {
                if(preparedStatement!=null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(dbConnection!=null && !dbConnection.isClosed())
                    dbConnection.close();
            } catch (SQLException ex) {
                throw new DBException("Unable to close connection in Transaction table.",ex);
            }
        }
    }

}
