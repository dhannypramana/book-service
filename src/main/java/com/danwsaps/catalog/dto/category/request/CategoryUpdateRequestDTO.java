package com.danwsaps.catalog.dto.category.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CategoryUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6530213320019867316L;

    private String name;

    private String description;

}
