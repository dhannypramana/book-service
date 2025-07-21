package com.danwsaps.catalog.dto.publisher.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Data
@Validated
public class PublisherCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7555951481076589950L;

    @NotBlank
    private String name;

    @NotBlank
    private String companyName;

    private String address;

}
