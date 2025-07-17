package com.danwsaps.catalog.dto.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ErrorResponseDTO extends AbstractBaseResponseDTO {

    @Serial
    private static final long serialVersionUID = 8585251567200598615L;

    private String message;

}
