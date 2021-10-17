package com.nayak.springbootgithubworkflowsample.controller;

import com.nayak.springbootgithubworkflowsample.model.Author;
import com.nayak.springbootgithubworkflowsample.model.Book;
import com.nayak.springbootgithubworkflowsample.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BookRepository bookRepository;

    @Container
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("testdb")
            .withUsername("abc")
            .withPassword("abc");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    public void setup() {
        Book book = new Book();
        book.setIsbn("YY12234");
        book.setName("Random Book");
        book.setPublishedDate(LocalDate.now());
        book.setAuthors(Collections.singletonList(new Author("Sushil")));

        this.bookRepository.save(book);
    }


    @Test
    @DisplayName("Controller should fetch Books with Id=1 data successfully")
    void controllerShouldFetchAllDataSuccessfully() {
        webTestClient.get()
                .uri("/api/book/{bookId}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.name").isEqualTo("Random Book");
    }
}