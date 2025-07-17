package com.danwsaps.catalog.dto.publisher.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PublisherUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5856757991506379822L;

    private String name;

    private String companyName;

    private String address;

}
