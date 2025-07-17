package com.danwsaps.catalog.repository;

import com.danwsaps.catalog.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

    Optional<Publisher> findBySecureIdAndDeletedFalse(String secureId);

}
