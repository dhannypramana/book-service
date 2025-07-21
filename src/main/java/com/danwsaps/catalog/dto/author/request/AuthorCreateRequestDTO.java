package com.danwsaps.catalog.dto.author.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Validated
public class AuthorCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5593154297277457526L;

    @NotBlank
    private String name;

    private String description;

    private LocalDate birthDate;

}
