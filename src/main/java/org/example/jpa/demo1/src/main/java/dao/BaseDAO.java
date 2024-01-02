package dao;


import models.Todo;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {



    protected BaseDAO() {
    }

    public abstract void save(T element) throws SQLException;
    public abstract void delete(Long id) throws SQLException;
    public abstract List<T>  getAll() throws SQLException;





}
