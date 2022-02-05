package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import ru.otus.spring.io.IOService;
import ru.otus.spring.io.OpenedConsoleIOService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest({"spring.main.allow-bean-definition-overriding=true"})
public class IntegrationTest {

    @Autowired
    private Shell shell;

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public IOService ioService() {
            String input = "testUser\n" +
                    "30\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "3\n" +
                    "2\n" +
                    "1\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            return new OpenedConsoleIOService(in, System.out);
        }
    }

    @Test
    public void simpleIntegrationTest() {

        String res = (String) shell.evaluate(() -> "l");
        assertThat(res).isEqualTo("Текущий пользователь: testUser возраст: 30");

        String res2 = (String) shell.evaluate(() -> "quiz");
        assertThat(res2).isEqualTo("Всего доброго!");
    }

    @Test
    public void testAvailability() {
        Object res = shell.evaluate(() -> "quiz");
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

}
