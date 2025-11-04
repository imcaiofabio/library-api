package com.caio.libraryapi.application.loader;

import com.caio.libraryapi.domain.model.Book;
import com.caio.libraryapi.domain.service.AuthorService;
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
@Order(3)
public class BookLoader implements ApplicationRunner {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookLoader(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
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
                    var publisherId = Long.parseLong(fields[4]);
                    var authorId = Long.parseLong(fields[5]);

                    var publisher = publisherService.findById(publisherId);
                    var author = authorService.findById(authorId);

                    if (publisher != null && author != null) {
                        var book = new Book();
                        book.setTitle(fields[0]);
                        book.setPublicationYear(Integer.parseInt(fields[1]));
                        book.setIsbn(fields[2]);
                        book.setPageCount(Integer.parseInt(fields[3]));
                        book.setPublisher(publisher);
                        book.setAuthor(author);
                        bookService.save(book);
                    } else {
                        System.out.println("Publisher or Author not found for book: " + fields[0]);
                    }
                }
            });
        }

        System.out.println("Books loaded from file:");
        bookService.findAll().forEach(System.out::println);
    }
}
