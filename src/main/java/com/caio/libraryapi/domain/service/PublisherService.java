package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PublisherService implements CrudService<Publisher, Long> {

    private final Map<Long, Publisher> publishers = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Publisher save(Publisher publisher) {
        if (publisher.getId() == null) {
            publisher.setId(idGenerator.incrementAndGet());
        }
        publishers.put(publisher.getId(), publisher);
        return publisher;
    }

    @Override
    public Publisher findById(Long id) {
        return publishers.get(id);
    }

    @Override
    public void deleteById(Long id) {
        publishers.remove(id);
    }

    @Override
    public Collection<Publisher> findAll() {
        return publishers.values();
    }
}
