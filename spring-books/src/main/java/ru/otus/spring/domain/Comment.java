package ru.otus.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "book")
@ToString(exclude = "book")
@Document(collection = "comment")
public class Comment {

    @Id
    private long id;

    @Field(name = "text")
    private String text;

    @Field(name = "book")
    private Book book;
}
