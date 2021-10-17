package com.nayak.springbootgithubworkflowsample.repository;

import com.nayak.springbootgithubworkflowsample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
