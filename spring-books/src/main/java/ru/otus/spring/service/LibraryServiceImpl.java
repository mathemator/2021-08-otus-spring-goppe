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
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveBook(String bookId, String title, String authorId, String genreId) {
        Author author = getAuthorById(authorId).orElseThrow(() -> new RuntimeException("author does not exist"));
        Genre genre = getGenreById(genreId).orElseThrow(() -> new RuntimeException("genre does not exist"));
        Book book = new Book(bookId, title.toUpperCase(), author, genre);

        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genreName) {
        return bookRepository.findByGenreName(genreName.toUpperCase());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorName(authorName.toUpperCase());
    }

    @Override
    @Transactional
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
        commentRepository.deleteByBookId(id);
    }

    @Override
    @Transactional
    public void saveGenre(String genreid, String genreName) {
        genreRepository.save(new Genre(genreid, genreName));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getGenreById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteGenreById(String id) {
        genreRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveAuthor(String authorId, String authorName) {
        authorRepository.save(new Author(authorId, authorName));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAuthorById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveComment(String commentId, String text, String bookId) {
        Book book = getBookById(bookId).orElseThrow(() -> new RuntimeException("book does not exist"));
        commentRepository.save(new Comment(commentId, text, book));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(String id) {
        return commentRepository.findByBookId(id);
    }
}
