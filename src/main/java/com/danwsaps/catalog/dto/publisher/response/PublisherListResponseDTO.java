package com.danwsaps.catalog.dto.publisher.response;

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
        "companyName",
        "address"
})
@Builder
public class PublisherListResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 149135615412022190L;

    @JsonProperty("id")
    private String secureId;

    private String name;

    private String companyName;

    private String address;

}
