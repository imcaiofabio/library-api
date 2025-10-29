package com.caio.libraryapi.domain.service;

import com.caio.libraryapi.domain.model.Magazine;
import com.caio.libraryapi.domain.repository.MagazineRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MagazineService implements CrudService<Magazine, Long> {

    private final MagazineRepository magazineRepository;

    public MagazineService(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @Override
    public Magazine save(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    @Override
    public Magazine findById(Long id) {
        return magazineRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        magazineRepository.deleteById(id);
    }

    @Override
    public Collection<Magazine> findAll() {
        return magazineRepository.findAll();
    }
}
