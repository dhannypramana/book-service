package com.danwsaps.catalog.web;

import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherCreateRequestDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherUpdateRequestDTO;
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
