package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findByTitleLikeIgnoreCaseAndDeletedFalse(String title, Pageable pageable);

}
