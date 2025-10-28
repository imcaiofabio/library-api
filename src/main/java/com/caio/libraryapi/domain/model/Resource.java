package com.caio.libraryapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Resource {

    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;

}
