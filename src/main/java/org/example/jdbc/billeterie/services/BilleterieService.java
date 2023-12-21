package org.example.services;

import org.example.jdbc.bank.dao.ClientDAO;

import java.sql.Connection;

public class BilleterieService {


private ClientDAO clientDAO;

private Connection connection;

   public BilleterieService(){
       connection = org.example.utils.connection.DatabaseManager.getConnection();
       clientDAO=new ClientDAO(connection);


   }
}
