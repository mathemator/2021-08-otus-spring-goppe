package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.io.IOService;
import ru.otus.spring.io.OpenedConsoleIOService;

@Configuration
public class AppConfig {

    @Bean
    public IOService ioService() {
        return new OpenedConsoleIOService(System.in, System.out);
    }

}
