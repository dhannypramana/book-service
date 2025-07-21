package com.danwsaps.catalog.dto.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({
        "status",
        "result",
        "pages",
        "elements"
})
public class GenericPaginationResponseDTO<T> extends AbstractBaseResponseDTO {

    @Serial
    private static final long serialVersionUID = 942261021621165687L;

    private List<T> result;

    private Long elements;

    private Integer pages;

}
