package com.caio.libraryapi.application.loader;

import com.caio.libraryapi.domain.model.Author;
import com.caio.libraryapi.domain.service.AuthorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@Order(1)
public class AuthorLoader implements ApplicationRunner {

    private final AuthorService authorService;

    public AuthorLoader(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var inputStream = getClass().getClassLoader().getResourceAsStream("data/authors.csv");
        if (inputStream == null) {
            System.out.println("Author data file not found.");
            return;
        }

        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> {
                var fields = line.split(",");
                if (fields.length == 3) {
                    var author = new Author();
                    author.setId(Long.parseLong(fields[0]));
                    author.setName(fields[1]);
                    author.setNationality(fields[2]);
                    authorService.save(author);
                }
            });
        }

        System.out.println("Authors loaded from file:");
        authorService.findAll().forEach(System.out::println);
    }
}
