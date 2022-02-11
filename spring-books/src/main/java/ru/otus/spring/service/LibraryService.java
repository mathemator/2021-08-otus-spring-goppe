package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface LibraryService {

    Book getBookById(long id);

    void addBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByGenre(String genreName);

    List<Book> getBooksByAuthor(String authorName);

    void removeBookById(long id);

    void updateBookById(Book book);

    void addGenre(Genre genre);

    Genre getGenreById(long id);

    List<Genre> getAllGenres();

    void removeGenreById(long id);

    void addAuthor(Author genre);

    Author getAuthorById(long id);

    List<Author> getAllAuthors();

    void removeAuthorById(long id);


}
