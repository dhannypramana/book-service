package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> { }
