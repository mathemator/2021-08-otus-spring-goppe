package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//import ru.otus.spring.domain.Author;
//import ru.otus.spring.domain.Book;
//import ru.otus.spring.domain.Genre;
//import ru.otus.spring.representation.RepresentationUtil;
//import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

//@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

//    private final LibraryService libraryService;
//
//    @ShellMethod(value = "create book", key = {"cb", "create-book"})
//    public String createBook(@ShellOption("-n") String bookName,
//                             @ShellOption("-aid") long authorId,
//                             @ShellOption("-gid") long genreId) {
//
//        libraryService.addBook(bookName, authorId, genreId);
//        return "the book has been added";
//    }
//
//    @ShellMethod(value = "delete book", key = {"db", "delete-book"})
//    public String deleteBook(@ShellOption("-id") long bookId) {
//        libraryService.removeBookById(bookId);
//
//        return "the book has been deleted";
//    }
//
//    @ShellMethod(value = "get book", key = {"gb", "get-book"})
//    public String getBook(@ShellOption(value = "-id") long bookId) {
////        Book bookById;
////        try {
////            bookById = libraryService.getBookById(bookId);
////        } catch (EmptyResultDataAccessException e) {
////            return "book not found";
////        }
////
////        return RepresentationUtil.bookView(bookById);
//        return null;
//    }
//
//    @ShellMethod(value = "update book", key = {"ub", "update-book"})
//    public String updateBook(@ShellOption("-id") long bookId,
//                             @ShellOption("-n") String bookName,
//                             @ShellOption("-aid") long authorId,
//                             @ShellOption("-gid") long genreId) {
//        Author author;
//        try {
//            author = libraryService.getAuthorById(authorId);
//        } catch (EmptyResultDataAccessException e) {
//            return "author does not exist";
//        }
//        Genre genre;
//        try {
//            genre = libraryService.getGenreById(genreId);
//        } catch (EmptyResultDataAccessException e) {
//            return "author does not exist";
//        }
//        Book book = new Book(bookId, bookName.toUpperCase(), author, genre);
//        libraryService.updateBookById(book);
//        return "the book has been updated";
//    }
//
//    @ShellMethod(value = "get books by author", key = {"gba", "get-books-by-author"})
//    public String getBooksByAuthor(@ShellOption("-an") String author) {
//
//        List<Book> booksByAuthor = libraryService.getBooksByAuthor(author);
//
//        return booksByAuthor.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n"));
//    }
//
//    @ShellMethod(value = "get books by genre", key = {"gbg", "get-books-by-genre"})
//    public String getBooksByGenre(@ShellOption("-gn") String genre) {
//
//        List<Book> booksByGenre = libraryService.getBooksByGenre(genre);
//
//        return booksByGenre.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n"));
//    }
//
//    @ShellMethod(value = "get books by genre", key = {"gab", "get-all-books"})
//    public String getAllBooks() {
//
//        List<Book> allBooks = libraryService.getAllBooks();
//
//        return allBooks.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n"));
//    }
//
//    @ShellMethod(value = "get books by genre", key = {"gaa", "get-all-authors"})
//    public String getAllAuthors() {
//
//        List<Author> allAuthors = libraryService.getAllAuthors();
//
//        return allAuthors.stream().map(RepresentationUtil::authorView).collect(Collectors.joining("\n"));
//    }
//
//    @ShellMethod(value = "get books by genre", key = {"gag", "get-all-genres"})
//    public String getAllGenres() {
//
//        List<Genre> allGenres = libraryService.getAllGenres();
//
//        return allGenres.stream().map(RepresentationUtil::genreView).collect(Collectors.joining("\n"));
//    }
}