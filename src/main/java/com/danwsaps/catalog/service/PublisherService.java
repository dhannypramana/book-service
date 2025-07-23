package com.danwsaps.catalog.service;

import com.danwsaps.catalog.domain.Publisher;
import com.danwsaps.catalog.dto.publisher.request.PublisherCreateRequestDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherUpdateRequestDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherListResponseDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherMutationResponseDTO;
import org.springframework.data.domain.Page;

public interface PublisherService {

    PublisherMutationResponseDTO createNewPublisher(PublisherCreateRequestDTO dto);

    PublisherMutationResponseDTO deletePublisher(String id);

    PublisherMutationResponseDTO updatePublisher(String id, PublisherUpdateRequestDTO dto);

    Page<PublisherListResponseDTO> findPublisherList(
            Integer page,
            Integer limit,
            String direction,
            String sortBy,
            String name
    );

    Publisher findPublisherBySecureId(String secureId);

}
