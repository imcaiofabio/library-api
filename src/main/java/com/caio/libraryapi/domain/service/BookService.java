package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Book;
import com.caio.libraryapi.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService implements CrudService<Book, Long> {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }
}
