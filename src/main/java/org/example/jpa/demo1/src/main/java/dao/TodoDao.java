package dao;

import models.Statut;
import models.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class TodoDao extends BaseDAO<Todo> {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_jpa");
    private static EntityManager em = emf.createEntityManager();

    public TodoDao() {
    }

    @Override
    public void save(Todo element) throws SQLException {
        em.getTransaction().begin();
        em.persist(element);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    @Override
    public void delete(Long id) throws SQLException {
        em.getTransaction().begin();
        Todo todoToDelete = em.find(Todo.class,id);
        em.remove(todoToDelete);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public List<Todo> getAll() throws SQLException {
        List<Todo> todoList;
        todoList = em.createQuery("select t from Todo t", Todo.class).getResultList();


        return todoList;
    }

    public void changeTodoStatus(Long id,Statut statut) throws SQLException{
        em.getTransaction().begin();
        // UN L ??????
        Todo todoToUpdate = em.find(Todo.class,id);

        System.out.println(" before :");
        System.out.println(todoToUpdate);

        System.out.println(" after :");
        todoToUpdate.setStatut(statut);

        System.out.println(todoToUpdate);


        em.close();
        emf.close();
    }
}
