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
public class InstrumentDAO {
    private final static String dropIfExistQuery = "drop table instrument if exists";
    private final static String createInstrumentTableQuery = "create table instrument (" +
                    "    customer_id varchar(80) not null," +
                    "    symbol varchar(100) not null," +
                    "    quantity bigint not null," +
                    "    primary key (customer_id,symbol)," +
                    "    constraint customer_id_fk foreign key(customer_id) references customer(id) on delete cascade" +
                    ")";
    public static void dropTableIfExist(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(dropIfExistQuery);
    }
    
    public static void createInstrumentTable(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(createInstrumentTableQuery);
    }
}
