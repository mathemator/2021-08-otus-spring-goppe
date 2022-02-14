package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "book")
@ToString(exclude = "book")
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false, unique = true)
    private String text;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
    private Book book;
}
