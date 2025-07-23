package com.danwsaps.catalog.dto.book.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Validated
public class BookCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4446672780129158803L;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    @JsonProperty("publisher")
    private String publisherId;

    @NotEmpty
    @JsonProperty("categories")
    private List<@NotBlank String> categoryIds;

    @NotEmpty
    @JsonProperty("authors")
    private List<@NotBlank String> authorIds;

}
