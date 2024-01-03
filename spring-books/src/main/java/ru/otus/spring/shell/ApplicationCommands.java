package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.representation.RepresentationUtil;
import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final LibraryService libraryService;

    @ShellMethod(value = "save book", key = {"sb", "save-book"})
    public String saveBook(@ShellOption("-bid") long bookId,
                             @ShellOption("-n") String bookName,
                             @ShellOption("-aid") long authorId,
                             @ShellOption("-gid") long genreId) {

        libraryService.saveBook(bookId, bookName, authorId, genreId);
        return "the book has been saved";
    }

    @ShellMethod(value = "save genre", key = {"sg", "save-genre"})
    public String saveGenre(@ShellOption("-id") long genreId,
                              @ShellOption("-n") String genreName) {

        libraryService.saveGenre(genreId, genreName);
        return "the genre has been saved";
    }

    @ShellMethod(value = "save author", key = {"sa", "save-author"})
    public String saveAuthor(@ShellOption("-id") long authorid,
                              @ShellOption("-n") String authorName) {

        libraryService.saveAuthor(authorid, authorName);
        return "the author has been saved";
    }

    @ShellMethod(value = "save comment", key = {"sc", "save-comment"})
    public String saveComment(@ShellOption("-id") long commentId,
                             @ShellOption("-t") String text,
                              @ShellOption("-bid") long bookId) {

        libraryService.saveComment(commentId, text, bookId);
        return "the genre has been saved";
    }

    @ShellMethod(value = "delete book", key = {"db", "delete-book"})
    public String deleteBook(@ShellOption("-id") long bookId) {
        libraryService.deleteBookById(bookId);

        return "the book has been deleted";
    }

    @ShellMethod(value = "delete genre", key = {"dg", "delete-genre"})
    public String deleteGenre(@ShellOption("-id") long genreId) {
        libraryService.deleteGenreById(genreId);

        return "the genre has been deleted";
    }

    @ShellMethod(value = "delete author", key = {"da", "delete-author"})
    public String deleteAuthor(@ShellOption("-id") long authorId) {
        libraryService.deleteBookById(authorId);

        return "the author has been deleted";
    }

    @ShellMethod(value = "delete comment", key = {"dc", "delete-comment"})
    public String deleteComment(@ShellOption("-id") long commentId) {
        libraryService.deleteCommentById(commentId);

        return "the comment has been deleted";
    }

    @ShellMethod(value = "get book", key = {"gb", "get-book"})
    public String getBook(@ShellOption(value = "-id") long bookId) {
        Book bookById = libraryService.getBookById(bookId).orElseThrow(() -> new RuntimeException("book not found"));

        return RepresentationUtil.bookView(bookById);
    }

    @ShellMethod(value = "get comment", key = {"gc", "get-comment"})
    public String getComment(@ShellOption(value = "-id") long commentId) {
        Comment commentById = libraryService.getCommentById(commentId).orElseThrow(() -> new RuntimeException("comment not found"));

        return RepresentationUtil.commentView(commentById);
    }

    @ShellMethod(value = "get books by author", key = {"gba", "get-books-by-author"})
    public String getBooksByAuthor(@ShellOption("-an") String author) {
        List<Book> booksByAuthor = libraryService.getBooksByAuthor(author);

        return booksByAuthor.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get books by genre", key = {"gbg", "get-books-by-genre"})
    public String getBooksByGenre(@ShellOption("-gn") String genre) {

        List<Book> booksByGenre = libraryService.getBooksByGenre(genre);

        return booksByGenre.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get all books", key = {"gab", "get-all-books"})
    public String getAllBooks() {

        List<Book> allBooks = libraryService.getAllBooks();

        return allBooks.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get all authors", key = {"gaa", "get-all-authors"})
    public String getAllAuthors() {

        List<Author> allAuthors = libraryService.getAllAuthors();

        return allAuthors.stream().map(RepresentationUtil::authorView).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get all genres", key = {"gag", "get-all-genres"})
    public String getAllGenres() {

        List<Genre> allGenres = libraryService.getAllGenres();

        return allGenres.stream().map(RepresentationUtil::genreView).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get comments", key = {"gbc", "get-book-comments"})
    public String getBookComments(@ShellOption("-bid") long bookId) {

        List<Comment> bookComments = libraryService.getCommentsByBookId(bookId);

        return bookComments.stream().map(RepresentationUtil::commentView).collect(Collectors.joining("\n"));
    }
}