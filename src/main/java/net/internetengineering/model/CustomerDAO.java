/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.internetengineering.model;

import java.sql.*;

/**
 *
 * @author Hamed Ara
 */
public class CustomerDAO {
    private final static String dropIfExistQuery = "drop table customer if exists";
    private final static String createCustomerTableQuery = "create table customer (" +
                    "    id varchar(80) not null," +
                    "    name varchar(80) not null," +
                    "    family varchar(80) not null," +
                    "    balance bigint not null," +
                    "    primary key (id)" +
                    ")";
    private final static String insertAdminQuery = "insert into customer values ('1', 'admin','password',1000000)";
    
    
    public static void dropTableIfExist(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(dropIfExistQuery);
    }
    
    public static void createCustomerTable(Connection dbConnection) throws SQLException{
        Statement statement= dbConnection.createStatement();
        statement.execute(createCustomerTableQuery);
        insertAdmin(statement);
    }
    
    public static void insertAdmin(Statement statement) throws SQLException{
        statement.execute(insertAdminQuery);
    }
}
