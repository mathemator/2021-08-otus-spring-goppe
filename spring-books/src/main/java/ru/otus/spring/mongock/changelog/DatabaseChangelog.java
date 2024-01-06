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

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "mathemator", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "mathemator")
    public void insert(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       CommentRepository commentRepository,
                       GenreRepository genreRepository) {
        Genre novel = new Genre(1, "NOVEL");
        genreRepository.save(novel);
        Genre comedy = new Genre(2, "COMEDY");
        genreRepository.save(comedy);

        Author franzKafka = new Author(1, "FRANZ KAFKA");
        authorRepository.save(franzKafka);
        Author nikolayGogol = new Author(2, "NIKOLAY GOGOL");
        authorRepository.save(nikolayGogol);

        Book castle = new Book(1, "THE CASTLE", franzKafka, novel);
        bookRepository.save(castle);
        Book govins = new Book(2, "THE GOVERNMENT INSPECTOR", nikolayGogol, comedy);
        bookRepository.save(govins);

        commentRepository.save(new Comment(1, "GOOD STUFF", castle));
        commentRepository.save(new Comment(2, "GOOD TOO", govins));
    }
}
