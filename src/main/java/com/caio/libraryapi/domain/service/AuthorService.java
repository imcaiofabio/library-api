package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Author;
import com.caio.libraryapi.domain.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthorService implements CrudService<Author, Long> {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Collection<Author> findAll() {
        return authorRepository.findAll();
    }
}
