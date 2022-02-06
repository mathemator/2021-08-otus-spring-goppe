package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Jenre;

import java.util.List;

public interface LibraryService {

    Book getBookById(long id);

    void addBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByJenre(String jenreName);

    List<Book> getBooksByAuthor(String authorName);

    void removeBookById(long id);

    void addJenre(Jenre jenre);

    Jenre getJenreById(long id);

    List<Jenre> getAllJenres();

    void removeJenreById(long id);

    void addAuthor(Author jenre);

    Author getAuthorById(long id);

    List<Author> getAllAuthors();

    void removeAuthorById(long id);


}
