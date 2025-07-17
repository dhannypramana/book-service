package com.danwsaps.catalog.service;

import com.danwsaps.catalog.dto.publisher.request.PublisherCreateRequestDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherUpdateRequestDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherMutationResponseDTO;

public interface PublisherService {

    PublisherMutationResponseDTO createNewPublisher(PublisherCreateRequestDTO dto);

    PublisherMutationResponseDTO deletePublisher(String id);

    PublisherMutationResponseDTO updatePublisher(String id, PublisherUpdateRequestDTO dto);

}
