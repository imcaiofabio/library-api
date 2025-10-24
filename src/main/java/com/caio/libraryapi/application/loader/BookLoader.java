package com.caio.libraryapi.application.loader;

import com.caio.libraryapi.domain.model.Book;
import com.caio.libraryapi.domain.service.BookService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class BookLoader implements ApplicationRunner {

    private final BookService bookService;

    public BookLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var inputStream = getClass().getClassLoader().getResourceAsStream("data/books.csv");
        if (inputStream == null) {
            System.out.println("Book data file not found.");
            return;
        }

        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> {
                var fields = line.split(",");
                if (fields.length == 3) {
                    var book = new Book(null, fields[0], fields[1], Integer.parseInt(fields[2]));
                    bookService.save(book);
                }
            });
        }

        System.out.println("Books loaded from file:");
        bookService.findAll().forEach(System.out::println);
    }
}
