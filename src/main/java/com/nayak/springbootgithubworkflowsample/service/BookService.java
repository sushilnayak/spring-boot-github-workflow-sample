package com.nayak.springbootgithubworkflowsample.service;

import com.nayak.springbootgithubworkflowsample.exception.BookNotFoundException;
import com.nayak.springbootgithubworkflowsample.mapper.MapStructMapper;
import com.nayak.springbootgithubworkflowsample.model.Book;
import com.nayak.springbootgithubworkflowsample.model.BookDto;
import com.nayak.springbootgithubworkflowsample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MapStructMapper mapStructMapper;

    public Page<Book> getAllBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with bookId=" + bookId + " is not available"));
    }

    public Book saveBook(BookDto bookDto) {
        return bookRepository.save(mapStructMapper.bookToBookDto(bookDto));
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id=" + bookId + " not found"));
        bookRepository.delete(book);
    }

    public Book updateBook(Long bookId, BookDto bookDto) {

        return null;
    }
}
