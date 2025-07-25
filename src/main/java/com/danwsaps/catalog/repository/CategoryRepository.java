package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findBySecureIdAndDeletedFalse(String secureId);

    Page<Category> findByNameLikeIgnoreCaseAndDeletedFalse(String name, Pageable pageable);

}
