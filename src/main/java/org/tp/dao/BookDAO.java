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

    // Création d'un livre dans la BD (ajout)
    @Override
    public void create(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book); // Sauvegarde l'objet book dans la base
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Si une erreur survient, on annule la transaction
        } finally {
            em.close();
        }
    }

    // Recherche d'un Livre par son identifiant id
    @Override
    public Book findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id); // Trouve le livre en utilisant son ID
        } finally {
            em.close();
        }
    }

    // Recherche de tous les livres de la base
    @Override
    public List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
            return query.getResultList(); // Renvoie la liste de tous les livres
        } finally {
            em.close();
        }
    }

    // Mise à jour d'un livre existant dans la base
    @Override
    public void update(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(book); // Met à jour un livre existant dans la base
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Supprimer un livre de la base
    @Override
    public void delete(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(book) ? book : em.merge(book)); // Supprime le livre
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Rechercher tous les livres d'un auteur (identifié ici par son Id)
    public List<Book> findBooksByAuthorId(Long authorId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId", Book.class);
            query.setParameter("authorId", authorId);
            return query.getResultList(); // Renvoie la liste des livres de l'auteur
        } finally {
            em.close();
        }
    }

    // Rechercher tous les livres qui correspondent à une catégorie
    public List<Book> findBooksByCategoryName(String categoryName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.category.name = :categoryName", Book.class);
            query.setParameter("categoryName", categoryName);
            return query.getResultList(); // Renvoie la liste des livres de cette catégorie
        } finally {
            em.close();
        }
    }
}
