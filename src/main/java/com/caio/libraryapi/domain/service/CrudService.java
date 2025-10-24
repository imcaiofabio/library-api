package com.caio.libraryapi.domain.service;

import java.util.Collection;

public interface CrudService<T, ID> {

    T save(T entity);

    T findById(ID id);

    void deleteById(ID id);

    Collection<T> findAll();

}
