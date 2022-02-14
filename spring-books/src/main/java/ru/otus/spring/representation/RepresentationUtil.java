package ru.otus.spring.representation;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

@UtilityClass
public class RepresentationUtil {

    public String bookView(Book book) {
        return String.format("id: %d, title: %s, authorId: %d, authorName: %s, genreId: %d, genreName: %s",
                book.getId(), book.getTitle(), book.getAuthor().getId(), book.getAuthor().getName(),
                book.getGenre().getId(), book.getGenre().getName());
    }

    public String authorView(Author author) {
        return String.format("authorId: %d, authorName: %s", author.getId(), author.getName());
    }

    public String genreView(Genre genre) {
        return String.format("genreId: %d, genreName: %s", genre.getId(), genre.getName());
    }

    public String commentView(Comment comment) {
        return String.format("bookId: %d, commentId: %d, text: %s", comment.getBook().getId(), comment.getId(), comment.getText());
    }
}
