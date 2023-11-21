package org.tp.service;

import java.util.List;
import org.tp.data.Author;

public interface AuthorService {
    void createAuthor(Author author);
    Author findAuthorById(Long id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(Author author);
}

