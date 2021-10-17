package com.nayak.springbootgithubworkflowsample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQUENCE")
    @SequenceGenerator(name = "AUTHOR_SEQUENCE", sequenceName = "AUTHOR_SEQUENCE", allocationSize = 1)
    private Long id;
    private String name;

    @Schema(hidden = true)
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }
}
