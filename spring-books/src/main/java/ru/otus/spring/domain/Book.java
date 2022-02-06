package ru.otus.spring.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    @NonNull
    private final String title;
    private final long authorId;
    private final long jenreId;
}
