package ru.otus.spring;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.quiz.Quiz;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        Quiz quiz = context.getBean(Quiz.class);
        quiz.display();

        context.close();
    }
}
