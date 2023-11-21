package org.tp.service;

import java.util.List;
import org.tp.data.Category;

public interface CategoryService {
    void createCategory(Category category);
    Category findCategoryById(Long id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Category category);
}
