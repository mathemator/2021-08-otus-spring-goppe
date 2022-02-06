package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.JenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Jenre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final JenreDao jenreDao;

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
    public List<Book> getBooksByJenre(String jenreName) {
        return bookDao.getByJenre(jenreName);
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return bookDao.getByAuthor(authorName);
    }

    @Override
    public void removeBookById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void addJenre(Jenre jenre) {
        jenreDao.insert(jenre);
    }

    @Override
    public Jenre getJenreById(long id) {
        return jenreDao.getById(id);
    }

    @Override
    public List<Jenre> getAllJenres() {
        return jenreDao.getAll();
    }

    @Override
    public void removeJenreById(long id) {
        jenreDao.deleteById(id);
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
