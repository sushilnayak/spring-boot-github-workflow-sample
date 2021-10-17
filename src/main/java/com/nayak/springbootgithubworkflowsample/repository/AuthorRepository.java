package com.nayak.springbootgithubworkflowsample.repository;

import com.nayak.springbootgithubworkflowsample.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
