package com.caio.libraryapi.domain.repository;

import com.caio.libraryapi.domain.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {
}
