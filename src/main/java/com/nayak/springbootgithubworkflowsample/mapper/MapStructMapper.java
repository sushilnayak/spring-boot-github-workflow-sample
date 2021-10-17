package com.nayak.springbootgithubworkflowsample.mapper;

import com.nayak.springbootgithubworkflowsample.model.Author;
import com.nayak.springbootgithubworkflowsample.model.AuthorDto;
import com.nayak.springbootgithubworkflowsample.model.Book;
import com.nayak.springbootgithubworkflowsample.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

    Book bookToBookDto(BookDto bookDto);

    @Mappings({
            @Mapping(target = "name", source = "authorDto.authorName")
    })
    Author authorToAuthorDto(AuthorDto authorDto);
}
