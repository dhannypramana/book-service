package com.danwsaps.catalog.dto.category.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonPropertyOrder({
        "id",
        "name",
        "description"
})
@Builder
public class CategoryListResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8258064247622442956L;

    @JsonProperty("id")
    private String secureId;

    private String name;

    private String description;

}
