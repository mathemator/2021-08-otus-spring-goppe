package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    Optional<Book> getBookById(String id);

    void saveBook(String bookId, String title, String authorId, String genreId);

    List<Book> getAllBooks();

    List<Book> getBooksByGenre(String genreName);

    List<Book> getBooksByAuthor(String authorName);

    void deleteBookById(String id);

    void saveGenre(String genreId, String genreName);

    Optional<Genre> getGenreById(String id);

    List<Genre> getAllGenres();

    void deleteGenreById(String id);

    void saveAuthor(String authorId, String authorName);

    Optional<Author> getAuthorById(String id);

    List<Author> getAllAuthors();

    void deleteAuthorById(String id);

    void saveComment(String commentId,
                     String text,
                     String bookId);

    Optional<Comment> getCommentById(String id);

    List<Comment> getAllComments();

    void deleteCommentById(String id);

    List<Comment> getCommentsByBookId(String id);


}
