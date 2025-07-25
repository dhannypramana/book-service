package com.danwsaps.catalog.web;

import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookDetailResponseDTO;
import com.danwsaps.catalog.dto.book.response.BookListResponseDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericPaginationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.service.BookService;
import com.danwsaps.catalog.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookResource {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<GenericPaginationResponseDTO<BookListResponseDTO>> findBookList (
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "sortBy", required = false, defaultValue = "updatedAt") String sortBy,
            @RequestParam(value = "title", required = false) String title
    ) {
        return ResponseUtil.ok(bookService.findBookList(page, limit, direction, sortBy, title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<BookDetailResponseDTO>> findBookById(
            @PathVariable("id") String id
    ) {
        return ResponseUtil.ok(bookService.findBookDetail(id));
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO<BookMutationResponseDTO>> createNewBook(
            @RequestBody @Valid BookCreateRequestDTO dto
    ) {
        return ResponseUtil.created(bookService.createNewBook(dto));
    }

}
