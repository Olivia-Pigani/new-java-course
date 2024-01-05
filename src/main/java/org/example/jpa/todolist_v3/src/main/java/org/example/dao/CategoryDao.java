package org.example.dao;

import org.example.model.Category;

import java.util.List;

public interface CategoryDao {

    public void addCategory(Category category);
    public void deleteCategory(Long categoryId);
    public List<Category> getAllCategories();
    public Category getOneCategoryById(Long categoryId);

}
