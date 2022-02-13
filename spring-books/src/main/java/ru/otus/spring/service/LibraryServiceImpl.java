package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorRepository authorDao;
    private final BookRepository bookDao;
    private final GenreRepository genreDao;

    @Override
    public Optional<Book> getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public void addBook(String bookName, long authorId, long genreId) {
        Author author;
        try {
            author = getAuthorById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("author does not exist");
        }
        Genre genre;
        try {
            genre = getGenreById(genreId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("genre does not exist");
        }
        Book book = new Book(0, bookName.toUpperCase(), author, genre, Collections.emptyList());

        bookDao.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        return bookDao.getByGenre(genreName.toUpperCase());
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return bookDao.getByAuthor(authorName.toUpperCase());
    }

    @Override
    public void removeBookById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.save(genre);
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }

    @Override
    public void removeGenreById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    public void addAuthor(Author author) {
        authorDao.save(author);
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }

    @Override
    public void removeAuthorById(long id) {
        authorDao.deleteById(id);
    }
}
