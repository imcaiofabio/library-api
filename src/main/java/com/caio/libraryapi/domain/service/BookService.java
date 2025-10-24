package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService implements CrudService<Book, Long> {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.incrementAndGet());
        }
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return books.get(id);
    }

    @Override
    public void deleteById(Long id) {
        books.remove(id);
    }

    @Override
    public Collection<Book> findAll() {
        return books.values();
    }
}
