package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public void addBook(String bookName, long authorId, long genreId) {
        Author author = getAuthorById(authorId).orElseThrow(() -> new RuntimeException("author does not exist"));

        Genre genre = getGenreById(genreId).orElseThrow(() -> new RuntimeException("genre does not exist"));

        Book book = new Book(0, bookName.toUpperCase(), author, genre, Collections.emptyList());

        bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        return bookRepository.getByGenre(genreName.toUpperCase());
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.getByAuthor(authorName.toUpperCase());
    }

    @Override
    public void removeBookById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return genreRepository.getById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAll();
    }

    @Override
    public void removeGenreById(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Optional<Author> getAuthorById(long id) {
        return authorRepository.getById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    @Override
    public void removeAuthorById(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(long id) {
        return commentRepository.getById(id);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.getAll();
    }

    @Override
    public void removeCommentById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getCommentsByBookId(long id) {
        return commentRepository.getByBookId(id);
    }
}
