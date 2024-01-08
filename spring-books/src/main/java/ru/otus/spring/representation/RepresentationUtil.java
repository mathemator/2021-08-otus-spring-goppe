package ru.otus.spring.representation;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

@UtilityClass
public class RepresentationUtil {

    public String bookView(Book book) {
        return String.format("id: %s, title: %s, authorId: %s, authorName: %s, genreId: %s, genreName: %s",
                book.getId(), book.getTitle(), book.getAuthor().getId(), book.getAuthor().getName(),
                book.getGenre().getId(), book.getGenre().getName());
    }

    public String authorView(Author author) {
        return String.format("authorId: %s, authorName: %s", author.getId(), author.getName());
    }

    public String genreView(Genre genre) {
        return String.format("genreId: %s, genreName: %s", genre.getId(), genre.getName());
    }

    public String commentView(Comment comment) {
        return String.format("bookId: %s, commentId: %s, text: %s", comment.getBook().getId(), comment.getId(), comment.getText());
    }
}
