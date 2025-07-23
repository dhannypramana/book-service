package com.danwsaps.catalog.web;

import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.service.BookService;
import com.danwsaps.catalog.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookResource {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<GenericResponseDTO<BookMutationResponseDTO>> createNewBook(
            @RequestBody @Valid BookCreateRequestDTO dto
    ) {
        return ResponseUtil.created(bookService.createNewBook(dto));
    }

}
