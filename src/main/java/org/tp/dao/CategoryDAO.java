package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.tp.data.Category;

public class CategoryDAO implements CRUDRepository<Category> {
    private final EntityManagerFactory emf;

    public CategoryDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Category findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        List<Category> categories = query.getResultList();
        em.close();
        return categories;
    }

    @Override
    public void update(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(category) ? category : em.merge(category));
        em.getTransaction().commit();
        em.close();
    }
}

