package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.tp.data.Author;

public class AuthorDAO implements CRUDRepository<Author> {
    private final EntityManagerFactory emf;

    public AuthorDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Author findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        List<Author> authors = query.getResultList();
        em.close();
        return authors;
    }

    @Override
    public void update(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(author);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(author) ? author : em.merge(author));
        em.getTransaction().commit();
        em.close();
    }
}

