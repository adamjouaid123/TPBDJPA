package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.tp.data.Book;

public class BookDAO implements CRUDRepository<Book> {
    private final EntityManagerFactory emf;

    public BookDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    // ToDo: Création d'un livre dans la BD (ajout)
    @Override
    public void create(Book book) {
        EntityManager em = emf.createEntityManager();
        // ToDo
    }

    // ToDo: Recherche d'un Livre par son identifiant id
    @Override
    public Book findById(Long id) {
        EntityManager em = emf.createEntityManager();
        // ToDo
    }

    // ToDo: Recherche de tous les livres de la base
    @Override
    public List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        // ToDo
        return books;
    }

    // ToDo: Mise à jour d'un livre existant dans la base
    @Override
    public void update(Book book) {
        EntityManager em = emf.createEntityManager();
        // ToDo
        em.close();
    }

    // ToDo: Supprimer un livre de la base
    @Override
    public void delete(Book book) {
        EntityManager em = emf.createEntityManager();
        // ToDo
        em.close();
    }

    // ToDo: Rechercher tous les livres d'un auteur (identifié ici par son Id)
    public List<Book> findBooksByAuthorId(Long authorId) {
        EntityManager em = emf.createEntityManager();
        // ToDo (Astuce: On doit réaliser une requête JPQL de jointure entre un livre et sa collection pour travailler avec les éléments de celle-ci)
        em.close();
        return books;
    }

    // ToDo: Rechercher tous les livres qui correspondent à une catégorie
    public List<Book> findBooksByCategoryName(String categoryName) {
        EntityManager em = emf.createEntityManager();
        // ToDo (Astuce: On doit réaliser une requête JPQL basique sur Book)
        em.close();
        return books;
    }
}
