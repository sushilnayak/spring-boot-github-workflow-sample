package com.nayak.springbootgithubworkflowsample.controller;

import com.nayak.springbootgithubworkflowsample.model.Book;
import com.nayak.springbootgithubworkflowsample.model.BookDto;
import com.nayak.springbootgithubworkflowsample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> allBooks(PageRequest pageRequest) {
        return ResponseEntity.ok(bookService.getAllBooks(pageRequest));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> bookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid BookDto bookDto) {
        Book book = bookService.saveBook(bookDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bookId}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).body(book);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody @Valid BookDto bookDto) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookDto));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}
