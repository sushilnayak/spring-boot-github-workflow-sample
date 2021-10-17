package com.nayak.springbootgithubworkflowsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class AuthorDto {
    List<Book> books = new ArrayList<>();
    private String authorName;

    public AuthorDto(String authorName) {
        this.authorName = authorName;
    }
}
