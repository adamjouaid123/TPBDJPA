package Testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.tp.data.Category;
import org.tp.dao.CategoryDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryDAOTest {

    private EntityManagerFactory emf;
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        categoryDAO = new CategoryDAO(emf);
    }

    @After
    public void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Test Category");
        categoryDAO.create(category);

        assertNotNull(category.getId());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Test Category");
        categoryDAO.create(category);

        category.setName("Updated Category");
        categoryDAO.update(category);

        Category updatedCategory = categoryDAO.findById(category.getId());

        assertEquals("Updated Category", updatedCategory.getName());
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Test Category");
        categoryDAO.create(category);

        categoryDAO.delete(category);

        Category deletedCategory = categoryDAO.findById(category.getId());

        assertNull(deletedCategory);
    }

    @Test
    public void testFindById() {
        Category category = new Category();
        category.setName("Test Category");
        categoryDAO.create(category);

        Category foundCategory = categoryDAO.findById(category.getId());

        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getName());
    }

    @Test
    public void testFindAllCategories() {
        Category category1 = new Category();
        category1.setName("Category 1");
        categoryDAO.create(category1);

        Category category2 = new Category();
        category2.setName("Category 2");
        categoryDAO.create(category2);

        List<Category> categories = categoryDAO.findAll();

        assertEquals(2, categories.size());
        assertEquals(category1.getName(), categories.get(0).getName());
        assertEquals(category2.getName(), categories.get(1).getName());
    }
}
