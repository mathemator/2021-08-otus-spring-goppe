package ru.otus.spring.person;

import ru.otus.spring.domain.Person;

public interface PersonService {

    Person getByName(String name);

    Person makeNew(String name, int age);
}
