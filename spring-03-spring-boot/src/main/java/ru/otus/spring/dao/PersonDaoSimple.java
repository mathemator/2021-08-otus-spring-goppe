package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Person;

@Component
public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }

    public Person makeNew(String name, int age) {
        return new Person(name, age);
    }
}
