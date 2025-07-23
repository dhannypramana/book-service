package com.danwsaps.catalog.service;

import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;

public interface BookService {

    BookMutationResponseDTO createNewBook(BookCreateRequestDTO dto);

}
