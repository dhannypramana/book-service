package com.danwsaps.catalog.dto.book.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "createdAt",
        "updatedAt"
})
@Builder
public class BookListResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4984217819501120688L;

    @JsonProperty("id")
    private String secureId;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
