package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional
    public void saveBook(long bookId, String title, long authorId, long genreId) {
        Author author = getAuthorById(authorId).orElseThrow(() -> new RuntimeException("author does not exist"));

        Genre genre = getGenreById(genreId).orElseThrow(() -> new RuntimeException("genre does not exist"));

        Book book = new Book(bookId, title.toUpperCase(), author, genre, Collections.emptyList());

        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genreName) {
        return bookRepository.getByGenre(genreName.toUpperCase());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.getByAuthor(authorName.toUpperCase());
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveGenre(long genreid, String genreName) {
        genreRepository.save(new Genre(genreid, genreName));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getGenreById(long id) {
        return genreRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreRepository.getAll();
    }

    @Override
    @Transactional
    public void deleteGenreById(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveAuthor(long authorId, String authorName) {
        authorRepository.save(new Author(authorId, authorName));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(long id) {
        return authorRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    @Override
    @Transactional
    public void deleteAuthorById(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveComment(long commentId, String text, long bookId) {
        Book book = getBookById(bookId).orElseThrow(() -> new RuntimeException("book does not exist"));
        commentRepository.save(new Comment(commentId, text, book));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(long id) {
        return commentRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.getAll();
    }

    @Override
    @Transactional
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(long id) {
        return commentRepository.getByBookId(id);
    }
}
