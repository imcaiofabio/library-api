package com.caio.libraryapi.application.loader;

import com.caio.libraryapi.domain.model.Book;
import com.caio.libraryapi.domain.service.BookService;
import com.caio.libraryapi.domain.service.PublisherService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@Order(2) // Ensures this runs after PublisherLoader
public class BookLoader implements ApplicationRunner {

    private final BookService bookService;
    private final PublisherService publisherService;

    public BookLoader(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
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
                if (fields.length == 6) {
                    var publisherId = Long.parseLong(fields[5]);
                    var publisher = publisherService.findById(publisherId);

                    if (publisher != null) {
                        var book = new Book();
                        book.setTitle(fields[0]);
                        book.setAuthor(fields[1]);
                        book.setPublicationYear(Integer.parseInt(fields[2]));
                        book.setIsbn(fields[3]);
                        book.setPageCount(Integer.parseInt(fields[4]));
                        book.setPublisher(publisher);
                        bookService.save(book);
                    } else {
                        System.out.println("Publisher not found for ID: " + publisherId);
                    }
                }
            });
        }

        System.out.println("Books loaded from file:");
        bookService.findAll().forEach(System.out::println);
    }
}
