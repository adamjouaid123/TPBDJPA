package Testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashSet;
import java.util.List;
import org.tp.data.*;
import org.tp.dao.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BookDAOTest {

    private EntityManagerFactory emf;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        // Configuration de l'EntityManagerFactory pour H2 en mémoire
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        bookDAO = new BookDAO(emf);
        authorDAO = new AuthorDAO(emf);
        categoryDAO = new CategoryDAO(emf);
    }

    @After
    public void tearDown() {
        // Fermeture de l'EntityManagerFactory
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    public void testCreateBook() {
        // Création d'un auteur
        Author author = new Author();
        author.setName("Test Author");
        authorDAO.create(author);

        // Création d'une catégorie
        Category category = new Category();
        category.setName("Test Category");
        categoryDAO.create(category);

        // Création d'un livre
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthors(new HashSet<>());
        book.getAuthors().add(author);
        book.setCategory(category);

        // Sauvegarde du livre
        bookDAO.create(book);

        // Vérification que l'ID du livre est généré
        assertNotNull(book.getId());
    }

    @Test
    public void testUpdateBook() {
        // Création d'un livre
        Book book = new Book();
        book.setTitle("Test Book");
        bookDAO.create(book);

        // Modification du titre
        book.setTitle("Updated Book Title");
        bookDAO.update(book);

        // Recherche du livre mis à jour
        Book updatedBook = bookDAO.findById(book.getId());

        // Vérification que le titre a été mis à jour
        assertEquals("Updated Book Title", updatedBook.getTitle());
    }

    @Test
    public void testDeleteBook() {
        // Création d'un livre
        Book book = new Book();
        book.setTitle("Test Book");
        bookDAO.create(book);

        // Suppression du livre
        bookDAO.delete(book);

        // Tentative de recherche du livre supprimé
        Book deletedBook = bookDAO.findById(book.getId());

        // Vérification que le livre a été supprimé
        assertNull(deletedBook);
    }

    @Test
    public void testFindBooksByAuthorId() {
        // Création d'un auteur
        Author author = new Author();
        author.setName("Test Author");
        authorDAO.create(author);

        // Création de deux livres associés à l'auteur
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthors(new HashSet<>());
        book1.getAuthors().add(author);
        bookDAO.create(book1);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthors(new HashSet<>());
        book2.getAuthors().add(author);
        bookDAO.create(book2);

        // Recherche des livres par ID d'auteur
        List<Book> books = bookDAO.findBooksByAuthorId(author.getId());

        // Vérification que les deux livres sont trouvés
        assertEquals(2, books.size());
    }

    @Test
    public void testFindBooksByCategoryName() {
        // Création d'une catégorie
        Category category = new Category();
        category.setName("Test Category");
        categoryDAO.create(category);

        // Création de deux livres associés à la catégorie
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setCategory(category);
        bookDAO.create(book1);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setCategory(category);
        bookDAO.create(book2);

        // Recherche des livres par nom de catégorie
        List<Book> books = bookDAO.findBooksByCategoryName(category.getName());

        // Vérification que les deux livres sont trouvés
        assertEquals(2, books.size());
    }
}