package com.nayak.springbootgithubworkflowsample;

import com.nayak.springbootgithubworkflowsample.model.Book;
import com.nayak.springbootgithubworkflowsample.service.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@WebMvcTest
public class BookControllerBaseClass {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void before() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        given(bookService.getBookById(anyLong())).
                willReturn(new Book(1L, "Java Concurrency", "yyy", LocalDate.now(), null));
    }

}
