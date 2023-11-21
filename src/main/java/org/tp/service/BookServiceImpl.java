package org.tp.service;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.tp.dao.BookDAO;
import org.tp.data.Book;

public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;

    public BookServiceImpl(EntityManagerFactory emf) {
        this.bookDAO = new BookDAO(emf);
    }

    @Override
    public void createBook(Book book) {
        bookDAO.create(book);
    }

    @Override
    public Book findBookById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDAO.delete(book);
    }

    @Override
    public List<Book> findBooksByCategoryName(String categoryName) {
        return bookDAO.findBooksByCategoryName(categoryName);
    }

    @Override
    public List<Book> findBooksByAuthorId(Long authorId) {
        return bookDAO.findBooksByAuthorId(authorId);
    }

}

