package com.danwsaps.catalog.util;

import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.enums.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<GenericResponseDTO<T>> ok (T response) {
        return build(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<GenericResponseDTO<T>> created (T response) {
        return build(response, HttpStatus.CREATED);
    }

    private static <T> ResponseEntity<GenericResponseDTO<T>> build (T response, HttpStatus status) {
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
