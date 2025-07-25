package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Book;
import com.danwsaps.catalog.dto.book.projection.BookListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    Page<BookListProjection> findByTitleLikeIgnoreCaseAndDeletedFalse(String title, Pageable pageable);

    @Query("""
        SELECT b
        FROM Book b
        LEFT JOIN FETCH b.bookDetail
        LEFT JOIN FETCH b.publisher
        LEFT JOIN FETCH b.categories
        LEFT JOIN FETCH b.authors
        WHERE b.secureId = :secureId
    """)
    Optional<Book> findBySecureIdAndDeletedFalse(@Param("secureId") String secureId);

}
