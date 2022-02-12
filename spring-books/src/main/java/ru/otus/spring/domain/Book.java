package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class Book {
    private final long id;
    @NonNull
    private  String title;
    private Author author;
    private Genre genre;
}
