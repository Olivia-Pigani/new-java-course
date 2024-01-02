package service;

import dao.TodoDao;
import models.Statut;
import models.Todo;

import java.sql.SQLException;
import java.util.List;

public class TodoService {

    private TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }


    public void makeAtodo(Todo todo) throws SQLException {
        todoDao.save(todo);
    }

    public void deleteATodo(Long id) throws SQLException {
        todoDao.delete(id);
    }

    public void getAllTodos() throws SQLException {
       List<Todo> allTodos = todoDao.getAll();
        for (Todo t:allTodos
        ) {
            System.out.println(t);
        }
    }

    public void changeTodoStatus(Long id, Statut statut) throws SQLException {

        todoDao.changeTodoStatus(id,statut);


    }



}
