package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.application.request.BookRequest;
import com.caio.libraryapi.domain.model.Book;
import com.caio.libraryapi.domain.service.AuthorService;
import com.caio.libraryapi.domain.service.BookService;
import com.caio.libraryapi.domain.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookRequest request) {
        var publisher = publisherService.findById(request.getPublisherId());
        var author = authorService.findById(request.getAuthorId());

        if (publisher == null || author == null) {
            return ResponseEntity.badRequest().build(); // Or a more specific error
        }

        var book = new Book();
        book.setTitle(request.getTitle());
        book.setPublicationYear(request.getPublicationYear());
        book.setIsbn(request.getIsbn());
        book.setPageCount(request.getPageCount());
        book.setPublisher(publisher);
        book.setAuthor(author);

        var savedBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        var book = bookService.findById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        var publisher = publisherService.findById(request.getPublisherId());
        var author = authorService.findById(request.getAuthorId());

        if (publisher == null || author == null) {
            return ResponseEntity.badRequest().build();
        }

        var book = new Book();
        book.setId(id);
        book.setTitle(request.getTitle());
        book.setPublicationYear(request.getPublicationYear());
        book.setIsbn(request.getIsbn());
        book.setPageCount(request.getPageCount());
        book.setPublisher(publisher);
        book.setAuthor(author);

        var updatedBook = bookService.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
