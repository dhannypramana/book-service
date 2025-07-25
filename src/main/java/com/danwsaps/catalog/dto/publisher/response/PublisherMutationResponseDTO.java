package com.danwsaps.catalog.dto.publisher.response;

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
public class PublisherMutationResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1931709034302687270L;

    @JsonProperty("id")
    private String secureId;

    private String name;

}
