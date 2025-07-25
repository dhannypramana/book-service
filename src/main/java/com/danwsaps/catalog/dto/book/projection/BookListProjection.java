package com.danwsaps.catalog.dto.book.projection;

import java.time.LocalDateTime;

public interface BookListProjection {

    String getSecureId();

    String getTitle();

    String getDescription();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
