package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.application.request.AuthorRequest;
import com.caio.libraryapi.domain.model.Author;
import com.caio.libraryapi.domain.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody AuthorRequest request) {
        var author = new Author();
        author.setName(request.getName());
        author.setNationality(request.getNationality());
        var savedAuthor = authorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        var author = authorService.findById(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Author>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
        var author = new Author();
        author.setId(id);
        author.setName(request.getName());
        author.setNationality(request.getNationality());
        var updatedAuthor = authorService.save(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
