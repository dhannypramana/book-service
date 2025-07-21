package com.danwsaps.catalog.util;

import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.enums.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serial;
import java.io.Serializable;

public class ResponseUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 7539283236452834447L;

    public static <T extends Serializable> ResponseEntity<GenericResponseDTO<T>> ok (T response) {
        return build(response, HttpStatus.OK);
    }

    public static <T extends Serializable> ResponseEntity<GenericResponseDTO<T>> created (T response) {
        return build(response, HttpStatus.CREATED);
    }

    private static <T extends Serializable> ResponseEntity<GenericResponseDTO<T>> build (T response, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(
                        GenericResponseDTO
                                .<T>builder()
                                .result(response)
                                .status(ResponseStatus.OK)
                                .build()
                );
    }

}
