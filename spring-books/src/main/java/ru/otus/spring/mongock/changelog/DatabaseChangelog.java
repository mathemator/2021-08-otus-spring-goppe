package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.repository.GenreRepository;

//@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "stvort", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "mathemator")
    public void insert(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       CommentRepository commentRepository,
                       GenreRepository genreRepository) {
        genreRepository.save(new Genre(1, "NOVEL"));
        genreRepository.save(new Genre(2, "COMEDY"));

        authorRepository.save(new Author(1, "FRANZ KARKA"));
        authorRepository.save(new Author(2, "NIKOLAY GOGOL"));

        bookRepository.save(new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL")));
        bookRepository.save(new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY")));

        commentRepository.save(new Comment(1, "GOOD STUFF", new Book()));
        commentRepository.save(new Comment(2, "GOOD TOO", new Book()));
    }
}
