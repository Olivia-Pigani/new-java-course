package org.example.jdbc.billeterie.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.utils.connection.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO <T>{

    protected Connection _connection;
    protected PreparedStatement statement;
    protected String request;
    protected ResultSet resultSet;
    protected BaseDAO(Connection connection){
        _connection=connection;
    }


public abstract List<T> get() throws SQLException, ExecutionControl.NotImplementedException;
public abstract T getById(int id) throws SQLException, ExecutionControl.NotImplementedException;
public abstract void save(T element) throws SQLException, ExecutionControl.NotImplementedException;
public abstract void update(T element) throws SQLException, ExecutionControl.NotImplementedException;
public abstract void delete(T element) throws SQLException, ExecutionControl.NotImplementedException;








}
