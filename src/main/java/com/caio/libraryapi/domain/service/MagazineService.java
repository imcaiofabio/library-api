package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Magazine;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MagazineService implements CrudService<Magazine, Long> {

    private final Map<Long, Magazine> magazines = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Magazine save(Magazine magazine) {
        if (magazine.getId() == null) {
            magazine.setId(idGenerator.incrementAndGet());
        }
        magazines.put(magazine.getId(), magazine);
        return magazine;
    }

    @Override
    public Magazine findById(Long id) {
        return magazines.get(id);
    }

    @Override
    public void deleteById(Long id) {
        magazines.remove(id);
    }

    @Override
    public Collection<Magazine> findAll() {
        return magazines.values();
    }
}
