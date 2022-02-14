package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    Optional<Book> getBookById(long id);

    void addBook(String bookName,
                  long authorId,
                  long genreId);

    List<Book> getAllBooks();

    List<Book> getBooksByGenre(String genreName);

    List<Book> getBooksByAuthor(String authorName);

    void removeBookById(long id);

    void addGenre(Genre genre);

    Optional<Genre> getGenreById(long id);

    List<Genre> getAllGenres();

    void removeGenreById(long id);

    void addAuthor(Author genre);

    Optional<Author> getAuthorById(long id);

    List<Author> getAllAuthors();

    void removeAuthorById(long id);

    void addComment(Comment comment) ;

    Optional<Comment> getCommentById(long id);

    List<Comment> getAllComment() ;

    void removeCommentById(long id);

    List<Comment> getCommentsByBookId(long id);


}
