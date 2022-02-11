package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        return bookDao.getByGenre(genreName);
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
    public void updateBookById(Book book) {
        bookDao.updateById(book);
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.insert(genre);
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
        authorDao.insert(author);
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
