package com.caio.libraryapi.application.request;

import lombok.Data;

@Data
public class MagazineRequest {

    private String title;
    private String author;
    private Integer publicationYear;
    private String issn;
    private Integer volume;
    private Integer number;

}
