package com.danwsaps.catalog.dto.author.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({
        "id",
        "name",
        "address",
        "description"
})
@Builder
public class AuthorListResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1826483735590965519L;

    @JsonProperty("id")
    private String secureId;

    private String name;

    private String description;

    private LocalDate birthDate;

}
