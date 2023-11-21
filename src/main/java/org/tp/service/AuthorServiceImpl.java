package org.tp.service;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.tp.dao.AuthorDAO;
import org.tp.data.Author;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDAO authorDAO;

    public AuthorServiceImpl(EntityManagerFactory emf) {
        this.authorDAO = new AuthorDAO(emf);
    }

    @Override
    public void createAuthor(Author author) {
        authorDAO.create(author);
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorDAO.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDAO.findAll();
    }

    @Override
    public void updateAuthor(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorDAO.delete(author);
    }
}

