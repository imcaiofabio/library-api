package com.caio.libraryapi.domain.repository;

import com.caio.libraryapi.domain.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
