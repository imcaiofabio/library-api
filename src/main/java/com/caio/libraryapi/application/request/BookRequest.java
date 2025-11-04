package com.caio.libraryapi.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank
    private String title;

    @NotNull
    private Integer publicationYear;

    @NotBlank
    private String isbn;

    @NotNull
    @Positive
    private Integer pageCount;

    @NotNull
    private Long publisherId;

    @NotNull
    private Long authorId;

}
