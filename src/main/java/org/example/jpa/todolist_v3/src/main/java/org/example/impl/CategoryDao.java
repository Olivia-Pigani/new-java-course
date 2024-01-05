package org.example.impl;

import org.example.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CategoryDao implements org.example.dao.CategoryDao {

    private EntityManagerFactory entityManagerFactory;
    @Override
    public void addCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteCategory(Long categoryId) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Category category = entityManager.find(Category.class,categoryId);
        if (category!=null){
            entityManager.remove(category);
        }
        transaction.commit();
        entityManager.close();


    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        categories = entityManager.createQuery("SELECT * FROM Category c", Category.class).getResultList();
        entityManager.close();
        return categories;
    }

    @Override
    public Category getOneCategoryById(Long categoryId) {
        Category categoryToFind = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        categoryToFind = entityManager.createQuery("SELECT c FROM Category c where c.id = :categoryId", Category.class)
                .setParameter("categoryId", categoryId)
                .getSingleResult();

       entityManager.close();
       return categoryToFind;

    }
}
