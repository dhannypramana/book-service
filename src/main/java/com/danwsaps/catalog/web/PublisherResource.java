package com.danwsaps.catalog.web;

import com.danwsaps.catalog.dto.common.GenericPaginationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherCreateRequestDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherUpdateRequestDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherListResponseDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherMutationResponseDTO;
import com.danwsaps.catalog.service.PublisherService;
import com.danwsaps.catalog.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publisher")
@RequiredArgsConstructor
public class PublisherResource {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<GenericPaginationResponseDTO<PublisherListResponseDTO>> findPublisherList(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "sortBy", required = false, defaultValue = "updatedAt") String sortBy,
            @RequestParam(value = "name", required = false) String name
    ) {
        return ResponseUtil.ok(publisherService.findPublisherList(page, limit, direction, sortBy, name));
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO<PublisherMutationResponseDTO>> createNewPublisher(
            @RequestBody @Valid PublisherCreateRequestDTO dto
    ) {
        return ResponseUtil.created(publisherService.createNewPublisher(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<PublisherMutationResponseDTO>> updatePublisher(
            @RequestBody @Valid PublisherUpdateRequestDTO dto,
            @PathVariable String id
    ) {
        return ResponseUtil.ok(publisherService.updatePublisher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<PublisherMutationResponseDTO>> deletePublisher(
            @PathVariable String id
    ) {
        return ResponseUtil.ok(publisherService.deletePublisher(id));
    }

}
