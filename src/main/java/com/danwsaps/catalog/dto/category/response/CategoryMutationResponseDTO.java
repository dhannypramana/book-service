package com.danwsaps.catalog.dto.category.response;

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
public class CategoryMutationResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7527115205247165416L;

    @JsonProperty("id")
    private String secureId;

    private String name;

}
