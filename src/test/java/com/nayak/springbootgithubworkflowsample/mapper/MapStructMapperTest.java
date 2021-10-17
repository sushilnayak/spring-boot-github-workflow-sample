package com.nayak.springbootgithubworkflowsample.mapper;

import com.nayak.springbootgithubworkflowsample.model.Author;
import com.nayak.springbootgithubworkflowsample.model.AuthorDto;
import com.nayak.springbootgithubworkflowsample.model.Book;
import com.nayak.springbootgithubworkflowsample.model.BookDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapStructMapperTest {
    private final MapStructMapper mapStructMapper = Mappers.getMapper(MapStructMapper.class);

    @Test
    void bookDtoTest() {
        BookDto bookDto = new BookDto("awesome book", "isbn12345", LocalDate.now(), Arrays.asList(new AuthorDto("Sushil")));

        Book book = mapStructMapper.bookToBookDto(bookDto);
        assertThat(book.getIsbn()).isEqualTo(bookDto.getIsbn());
        assertThat(book.getName()).isEqualTo(bookDto.getName());
        assertThat(book.getPublishedDate()).isEqualTo(bookDto.getPublishedDate());

        List<Author> authors = book.getAuthors();
        assertThat(authors).hasSize(1);
        assertThat(authors.get(0).getName()).isEqualTo(bookDto.getAuthors().get(0).getAuthorName());
    }

    @Test
    void authorDtoTest(){
        AuthorDto authorDto = new AuthorDto("sushil");
        Author author = mapStructMapper.authorToAuthorDto(authorDto);
        assertThat(author.getName()).isEqualTo(authorDto.getAuthorName());

        assertThat(author.getBooks()).isEmpty();
        assertThat(authorDto.getBooks()).isEmpty();
    }
}