package org.tp.service;

import java.util.List;
import org.tp.data.Book;

public interface BookService {
    void createBook(Book book);
    Book findBookById(Long id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(Book book);
    List<Book> findBooksByCategoryName(String categoryName);
    List<Book> findBooksByAuthorId(Long authorId);
}

