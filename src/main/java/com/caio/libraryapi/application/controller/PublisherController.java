package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.application.request.PublisherRequest;
import com.caio.libraryapi.domain.model.Publisher;
import com.caio.libraryapi.domain.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@Valid @RequestBody PublisherRequest request) {
        var publisher = new Publisher(null, request.getName(), request.getCity());
        var savedPublisher = publisherService.save(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> findById(@PathVariable Long id) {
        var publisher = publisherService.findById(id);
        return publisher != null ? ResponseEntity.ok(publisher) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Publisher>> findAll() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@PathVariable Long id, @Valid @RequestBody PublisherRequest request) {
        var publisher = new Publisher(id, request.getName(), request.getCity());
        var updatedPublisher = publisherService.save(publisher);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
