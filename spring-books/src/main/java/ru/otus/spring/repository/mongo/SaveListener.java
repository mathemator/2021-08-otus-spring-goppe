package ru.otus.spring.repository.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

@Component
public class SaveListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event){
        if (Author.class.equals(event.getSource().getClass())) {
            Author updatedAuthor = (Author) event.getSource();
            Query query = new Query(Criteria.where("author.id").is(updatedAuthor.getId()));
            Update update = new Update().set("author", updatedAuthor);
            mongoOperations.updateMulti(query, update, Book.class);
        } else if (Book.class.equals(event.getSource().getClass())) {
            Book updatedBook = (Book) event.getSource();
            Query query = new Query(Criteria.where("book.id").is(updatedBook.getId()));
            Update update = new Update().set("book", updatedBook);
            mongoOperations.updateMulti(query, update, Comment.class);
        } else if (Genre.class.equals(event.getSource().getClass())) {
            Genre updatedGenre = (Genre) event.getSource();
            Query query = new Query(Criteria.where("genre.id").is(updatedGenre.getId()));
            Update update = new Update().set("genre", updatedGenre);
            mongoOperations.updateMulti(query, update, Book.class);
        }
    }
}
