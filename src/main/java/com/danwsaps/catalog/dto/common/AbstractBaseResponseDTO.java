package com.danwsaps.catalog.dto.common;

import com.danwsaps.catalog.enums.ResponseStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@NoArgsConstructor
public abstract class AbstractBaseResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3071077906399355825L;

    private ResponseStatus status;

}
