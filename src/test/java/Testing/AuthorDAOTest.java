package Testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.tp.data.Author;
import org.tp.dao.AuthorDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AuthorDAOTest {

    private EntityManagerFactory emf;
    private AuthorDAO authorDAO;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        authorDAO = new AuthorDAO(emf);
    }

    @After
    public void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setName("Test Author");
        authorDAO.create(author);

        assertNotNull(author.getId());
    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author();
        author.setName("Test Author");
        authorDAO.create(author);

        author.setName("Updated Author");
        authorDAO.update(author);

        Author updatedAuthor = authorDAO.findById(author.getId());

        assertEquals("Updated Author", updatedAuthor.getName());
    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author();
        author.setName("Test Author");
        authorDAO.create(author);

        authorDAO.delete(author);

        Author deletedAuthor = authorDAO.findById(author.getId());

        assertNull(deletedAuthor);
    }

    @Test
    public void testFindById() {
        Author author = new Author();
        author.setName("Test Author");
        authorDAO.create(author);

        Author foundAuthor = authorDAO.findById(author.getId());

        assertNotNull(foundAuthor);
        assertEquals(author.getName(), foundAuthor.getName());
    }

    @Test
    public void testFindAllAuthors() {
        Author author1 = new Author();
        author1.setName("Author 1");
        authorDAO.create(author1);

        Author author2 = new Author();
        author2.setName("Author 2");
        authorDAO.create(author2);

        List<Author> authors = authorDAO.findAll();

        assertEquals(2, authors.size());
        assertEquals(author1.getName(), authors.get(0).getName());
        assertEquals(author2.getName(), authors.get(1).getName());
    }
}