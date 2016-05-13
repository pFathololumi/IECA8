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
public class InstrumentOfferingDAO {
    private final static String dropIfExistQuery = "drop table instr_offer if exists";
    private final static String createInstrOfferTableQuery = "create table instr_offer(" +
                "    instr_cust_id varchar(80) not null," +
                "    instr_symbol varchar(100) not null," +
                "    offer_id bigint not null," +
                "    primary key (instr_cust_id, instr_symbol, offer_id )," +
                "    constraint instr_fk foreign key(instr_cust_id, instr_symbol) references instrument(customer_id,symbol) on delete cascade," +
                "    constraint offer_id_fk foreign key(offer_id) references offering(db_id) on delete cascade" +
                ")";
    
    public static void dropTableIfExist(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(dropIfExistQuery);
    }
    
    public static void createInstrOfferTable(Connection dbConnection) throws SQLException{
        dbConnection.createStatement().execute(createInstrOfferTableQuery);
    }
}
