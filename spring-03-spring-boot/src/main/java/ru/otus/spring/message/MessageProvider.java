package ru.otus.spring.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageProvider {

    private final String locale;
    private final MessageSource messageSource;

    public MessageProvider(@Value("${locale}") String locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    public String getMessage(String placeholder) {
        return messageSource.getMessage(
                placeholder,
                new String[0],
                Locale.forLanguageTag(locale));
    }
}
