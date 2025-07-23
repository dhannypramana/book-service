package com.danwsaps.catalog.service;

import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookListResponseDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;
import org.springframework.data.domain.Page;

public interface BookService {

    BookMutationResponseDTO createNewBook(BookCreateRequestDTO dto);

    Page<BookListResponseDTO> findBookList(
            Integer page,
            Integer limit,
            String direction,
            String sortBy,
            String title
    );

}
