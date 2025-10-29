package com.caio.libraryapi.application.request;

import lombok.Data;

@Data
public class BookRequest {

    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;
    private Integer pageCount;
    private Long publisherId;

}
