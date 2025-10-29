package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Publisher;
import com.caio.libraryapi.domain.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PublisherService implements CrudService<Publisher, Long> {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Collection<Publisher> findAll() {
        return publisherRepository.findAll();
    }
}
