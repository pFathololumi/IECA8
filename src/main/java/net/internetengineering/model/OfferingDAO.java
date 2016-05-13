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
public class OfferingDAO {
    private final static String dropIfExistQuery = "drop table offering if exists";
    private final static String createOfferingTableQuery = "create table offering(" +
                    "    db_id bigint IDENTITY PRIMARY KEY," +
                    "    customer_id varchar(80) not null," +
                    "    price bigint not null," +
                    "    quantity bigint not null," +
                    "    type varchar(30) not null," +
                    "    kind integer not null," +
                    "    constraint custome_id_fk foreign key(customer_id) references customer(id) on delete cascade" +
                    ")";
    public static void dropTableIfExist(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(dropIfExistQuery);
    }
    
    public static void createOfferingTable(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(createOfferingTableQuery);
    }
}
