package com.nayak.springbootgithubworkflowsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String name;
    private String isbn;
    private LocalDate publishedDate;

    private List<AuthorDto> authors = new ArrayList<>();
}
