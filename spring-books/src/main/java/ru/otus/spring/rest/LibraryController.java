package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.LibraryService;

import java.util.List;

@Controller
public class LibraryController {

    private final LibraryService libraryService;
    public static final Book DEFAULT_BOOK = new Book(0, "title", new Author(1, ""), new Genre(1, ""));

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = libraryService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(value = "/delete_book")
    public String handleDeleteUser(@RequestParam("id") int id) {
        libraryService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/add_book")
    public String addBook(Model model) {
        model.addAttribute("book", DEFAULT_BOOK);
        return "edit_book";
    }

    @GetMapping("/edit_book")
    public String editBook(@RequestParam("id") int id, Model model) {
        Book book = libraryService.getBookById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit_book";
    }

    @PostMapping(value = "/edit_book", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String saveBook(@RequestBody Book book, Model model) {

        Book saved = libraryService.saveBook(book.getId(), book.getTitle(),
                book.getAuthor().getId(), book.getGenre().getId());
        model.addAttribute(saved);
        return "redirect:/books";
    }
}
