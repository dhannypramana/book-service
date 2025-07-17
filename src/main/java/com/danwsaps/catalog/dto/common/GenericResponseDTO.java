package com.danwsaps.catalog.dto.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GenericResponseDTO<T> extends AbstractBaseResponseDTO {

    @Serial
    private static final long serialVersionUID = 4047285699299338979L;

    private T result;

}
