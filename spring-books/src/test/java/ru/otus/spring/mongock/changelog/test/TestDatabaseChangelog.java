package ru.otus.spring.mongock.changelog.test;

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

@ChangeLog
public class TestDatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "stvort", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "mathemator")
    public void insert(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       CommentRepository commentRepository,
                       GenreRepository genreRepository) {
        genreRepository.save(new Genre("1", "NOVEL"));
        genreRepository.save(new Genre("2", "COMEDY"));

        authorRepository.save(new Author("1", "FRANZ KAFKA"));
        authorRepository.save(new Author("2", "NIKOLAY GOGOL"));

        Book castle = new Book("1", "THE CASTLE", new Author("1", "FRANZ KAFKA"), new Genre("1", "NOVEL"));
        bookRepository.save(castle);
        Book govins = new Book("2", "THE GOVERNMENT INSPECTOR", new Author("2", "NIKOLAY GOGOL"), new Genre("2", "COMEDY"));
        bookRepository.save(govins);
        Book expected3 = new Book("3", "DEAD SOULS", new Author("2", "NIKOLAY GOGOL"), new Genre("1", "NOVEL"));
        bookRepository.save(expected3);
        commentRepository.save(new Comment("1", "GOOD STUFF", castle));
        commentRepository.save(new Comment("2", "GOOD TOO", govins));
    }

    @ChangeSet(order = "003", id = "insertAdditionalData", author = "mathemator")
    public void insert(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       GenreRepository genreRepository) {
        genreRepository.save(new Genre("3", "TEST GENRE"));

        authorRepository.save(new Author("3", "TEST AUTHOR"));

        bookRepository.save(new Book("1", "THE CASTLE", new Author("1", "FRANZ KAFKA"), new Genre("1", "NOVEL")));
        bookRepository.save(new Book("3", "DEAD SOULS", new Author("2", "NIKOLAY GOGOL"), new Genre("1", "NOVEL")));
    }
}
