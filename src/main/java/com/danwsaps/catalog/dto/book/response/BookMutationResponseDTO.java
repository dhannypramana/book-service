package com.danwsaps.catalog.dto.book.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonPropertyOrder({
        "id",
        "title"
})
public class BookMutationResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6324395963557037642L;

    @JsonProperty("id")
    private String secureId;

    private String title;

}
