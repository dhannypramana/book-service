package com.danwsaps.catalog.dto.author.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AuthorUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5593154297277457526L;

    private String name;

    private String description;

    private LocalDate birthDate;

}
