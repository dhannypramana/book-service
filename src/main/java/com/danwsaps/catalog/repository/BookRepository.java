package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Book;
import com.danwsaps.catalog.dto.book.projection.BookListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    Page<BookListProjection> findByTitleLikeIgnoreCaseAndDeletedFalse(String title, Pageable pageable);

}
