package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, String> {

    Optional<Author> findBySecureIdAndDeletedFalse(String secureId);

    Page<Author> findByNameLikeIgnoreCaseAndDeletedFalse(String name, Pageable pageable);

    Set<Author> findBySecureIdInAndDeletedFalse(List<String> secureIds);

}
