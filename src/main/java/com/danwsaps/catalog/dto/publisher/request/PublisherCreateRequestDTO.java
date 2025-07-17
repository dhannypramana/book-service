package com.danwsaps.catalog.dto.publisher.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class PublisherCreateRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String companyName;

    private String address;

}
