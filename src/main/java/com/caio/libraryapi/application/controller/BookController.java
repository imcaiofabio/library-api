package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.domain.model.Book;
import com.caio.libraryapi.domain.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Collection<Book> findAll() {
        return bookService.findAll();
    }
}
