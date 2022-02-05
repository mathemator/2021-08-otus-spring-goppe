package ru.otus.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringDemoApplicationTests {

    @Autowired
    private Shell shell;

    @Test
    public void simpleIntegrationTest() {

        String input = "testUser\n" +
                "30\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String res = (String) shell.evaluate(() -> "l");
        assertThat(res).isEqualTo("Текущий пользователь: testUser возраст: 30");

        String input2 = "2\n" +
                "3\n" +
                "4\n" +
                "3\n" +
                "2\n"+
                "1\n";
        in = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in);

        String res2 = (String) shell.evaluate(() -> "quiz");
        assertThat(res2).isEqualTo("Всего доброго!");
    }

    @Test
    public void testAvailability() {
        Object res = shell.evaluate(() -> "quiz");
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

}
