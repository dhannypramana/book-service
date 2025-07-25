package com.danwsaps.catalog.dto.book.response;

import com.danwsaps.catalog.dto.author.response.AuthorMutationResponseDTO;
import com.danwsaps.catalog.dto.category.response.CategoryMutationResponseDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherMutationResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "publisher",
        "categories",
        "authors",
        "createdAt",
        "updatedAt"
})
public class BookDetailResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6658930382839425396L;

    @JsonProperty("id")
    private String secureId;

    private String title;

    private String description;

    private PublisherMutationResponseDTO publisher;

    private Set<CategoryMutationResponseDTO> categories;

    private Set<AuthorMutationResponseDTO> authors;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
