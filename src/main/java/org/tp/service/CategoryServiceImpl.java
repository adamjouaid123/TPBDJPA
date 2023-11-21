package org.tp.service;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.tp.data.Category;
import org.tp.dao.CategoryDAO;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(EntityManagerFactory emf) {
        this.categoryDAO = new CategoryDAO(emf);
    }

    @Override
    public void createCategory(Category category) {
        categoryDAO.create(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }
}

