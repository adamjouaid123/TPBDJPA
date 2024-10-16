package org.tp;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.tp.service.*;
import org.tp.data.*;

public class Main {
    public static void main(String[] args) {
        // Initialisation de l'EntityManagerFactory (utilisez H2 pour les tests et Oracle pour le déploiement)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

        // Création d'une catégorie
        CategoryService categoryService = new CategoryServiceImpl(emf);
        Category fictionCategory = new Category();
        fictionCategory.setName("Fiction");
        categoryService.createCategory(fictionCategory);

        // Création de deux auteurs de nom Author1 et Author2
        AuthorService authorService = new AuthorServiceImpl(emf);
        Author author1 = new Author();
        author1.setName("Author 1");
        authorService.createAuthor(author1);

        Author author2 = new Author();
        author2.setName("Author 2");
        authorService.createAuthor(author2);

        BookService bookService = new BookServiceImpl(emf);
        
        // Création d'un PaperBook de titre PaperBook1 et de 200 pages
        PaperBook paperBook1 = new PaperBook();
        paperBook1.setTitle("PaperBook 1");
        paperBook1.setPageCount(200);
        paperBook1.setCategory(fictionCategory); // Associe la catégorie

        // Ajouter les auteurs Author1 et Author2 comme auteurs de ce livre
        Set<Author> authorsForPaperBook1 = new HashSet<>();
        authorsForPaperBook1.add(author1);
        authorsForPaperBook1.add(author2);
        paperBook1.setAuthors(authorsForPaperBook1);

        // Faire persister ce livre à l'aide du service bookService
        bookService.createBook(paperBook1);

        // Création d'un Ebook de titre EBook1 et de format PDF
        Ebook ebook1 = new Ebook();
        ebook1.setTitle("EBook 1");
        ebook1.setFormat("PDF");
        ebook1.setCategory(fictionCategory); // Associe la catégorie

        // Ajouter les auteurs Author1 et Author2 comme auteurs de cet ebook
        Set<Author> authorsForEbook1 = new HashSet<>();
        authorsForEbook1.add(author1);
        authorsForEbook1.add(author2);
        ebook1.setAuthors(authorsForEbook1);

        // Faire persister cet ebook à l'aide du service bookService
        bookService.createBook(ebook1);

        // Recherche d'un livre par son Id (utilisation de l'ID du paperbook précédent)
        Book foundBook = bookService.findBookById(paperBook1.getId());
        Category foundCategory = foundBook.getCategory(); // Extraction de la catégorie
        System.out.println("Category of PaperBook 1: " + foundCategory.getName());

        // Recherche d'auteurs d'un livre
        Set<Author> foundAuthors = foundBook.getAuthors();
        System.out.println("Authors of PaperBook 1:");
        for (Author author : foundAuthors) {
            System.out.println("Author Name: " + author.getName());
        }

        // Recherche de livres par catégorie (Fiction par exemple), affichage des titres
        List<Book> booksByCategory = bookService.findBooksByCategoryName("Fiction");
        System.out.println("Books in category Fiction:");
        for (Book book : booksByCategory) {
            System.out.println("Title: " + book.getTitle());
        }

        // Recherche de livres par auteur (par exemple avec l'Author 1 créé avant), affichage des titres
        List<Book> booksByAuthor = bookService.findBooksByAuthorId(author1.getId());
        System.out.println("Books by Author 1:");
        for (Book book : booksByAuthor) {
            System.out.println("Title: " + book.getTitle());
        }

        // Fermeture de l'EntityManagerFactory
        emf.close();
    }
}
