package com.caio.libraryapi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Resource {

    private String isbn;
    private Integer pageCount;

    @ManyToOne
    private Publisher publisher;

}
