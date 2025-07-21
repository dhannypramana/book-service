package com.danwsaps.catalog.util;

import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;

public class PaginationUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1673860616749368823L;

    public static Sort.Direction getSortDirection(String sortDirection) {
        if (sortDirection.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        }

        return Sort.Direction.DESC;
    }

}
