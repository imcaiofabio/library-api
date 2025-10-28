package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.application.request.PublisherRequest;
import com.caio.libraryapi.domain.model.Publisher;
import com.caio.libraryapi.domain.service.PublisherService;
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
    public Publisher create(@RequestBody PublisherRequest request) {
        var publisher = new Publisher(null, request.getName(), request.getCity());
        return publisherService.save(publisher);
    }

    @GetMapping("/{id}")
    public Publisher findById(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @GetMapping
    public Collection<Publisher> findAll() {
        return publisherService.findAll();
    }

    @PutMapping("/{id}")
    public Publisher update(@PathVariable Long id, @RequestBody PublisherRequest request) {
        var publisher = new Publisher(id, request.getName(), request.getCity());
        return publisherService.save(publisher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publisherService.deleteById(id);
    }

}
