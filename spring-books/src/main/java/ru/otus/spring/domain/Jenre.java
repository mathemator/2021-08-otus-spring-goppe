package ru.otus.spring.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Jenre {
    private final long id;
    @NonNull
    private final String name;
}
