package ru.otus.spring.service;

import ru.otus.spring.domain.Person;

public interface PersonService {

    Person makeNew(String name, int age);
}
