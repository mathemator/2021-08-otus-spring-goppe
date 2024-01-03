package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    Optional<Book> getBookById(long id);

    void saveBook(long bookId, String title, long authorId, long genreId);

    List<Book> getAllBooks();

    List<Book> getBooksByGenre(String genreName);

    List<Book> getBooksByAuthor(String authorName);

    void deleteBookById(long id);

    void saveGenre(long genreId, String genreName);

    Optional<Genre> getGenreById(long id);

    List<Genre> getAllGenres();

    void deleteGenreById(long id);

    void saveAuthor(long authorId, String authorName);

    Optional<Author> getAuthorById(long id);

    List<Author> getAllAuthors();

    void deleteAuthorById(long id);

    void saveComment(long commentId,
                     String text,
                     long bookId);

    Optional<Comment> getCommentById(long id);

    void deleteCommentById(long id);

    List<Comment> getCommentsByBookId(long id);


}
