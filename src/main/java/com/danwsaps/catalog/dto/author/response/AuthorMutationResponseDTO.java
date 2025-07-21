package com.danwsaps.catalog.dto.author.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonPropertyOrder({
        "id",
        "name"
})
public class AuthorMutationResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2588527716659685772L;

    @JsonProperty("id")
    private String secureId;

    private String name;

}
