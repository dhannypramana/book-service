package com.danwsaps.catalog.web;

import com.danwsaps.catalog.dto.author.request.AuthorCreateRequestDTO;
import com.danwsaps.catalog.dto.author.request.AuthorUpdateRequestDTO;
import com.danwsaps.catalog.dto.author.response.AuthorListResponseDTO;
import com.danwsaps.catalog.dto.author.response.AuthorMutationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericPaginationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.service.AuthorService;
import com.danwsaps.catalog.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/author")
@RequiredArgsConstructor
public class AuthorResource {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<GenericPaginationResponseDTO<AuthorListResponseDTO>> findAuthorList(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "sortBy", required = false, defaultValue = "updatedAt") String sortBy,
            @RequestParam(value = "name", required = false) String name
    ) {
        return ResponseUtil.ok(authorService.findAuthorList(page, limit, direction, sortBy, name));
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO<AuthorMutationResponseDTO>> createNewAuthor(
            @RequestBody @Valid AuthorCreateRequestDTO dto
    ) {
        return ResponseUtil.created(authorService.createNewAuthor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<AuthorMutationResponseDTO>> updateAuthor(
            @PathVariable("id") String id,
            @RequestBody AuthorUpdateRequestDTO dto
    ) {
        return ResponseUtil.ok(authorService.updateAuthor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<AuthorMutationResponseDTO>> deleteAuthor(
            @PathVariable("id") String id
    ) {
        return ResponseUtil.ok(authorService.deleteAuthor(id));
    }

}
