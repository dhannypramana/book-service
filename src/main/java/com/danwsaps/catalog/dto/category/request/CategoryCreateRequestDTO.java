package com.danwsaps.catalog.dto.category.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Data
@Validated
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CategoryCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5705796232908807400L;

    @NotBlank
    private String name;

    private String description;

}
