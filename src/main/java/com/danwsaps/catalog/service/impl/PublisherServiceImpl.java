package com.danwsaps.catalog.service.impl;

import com.danwsaps.catalog.domain.Publisher;
import com.danwsaps.catalog.dto.publisher.request.PublisherCreateRequestDTO;
import com.danwsaps.catalog.dto.publisher.request.PublisherUpdateRequestDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherListResponseDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherMutationResponseDTO;
import com.danwsaps.catalog.repository.PublisherRepository;
import com.danwsaps.catalog.service.PublisherService;
import com.danwsaps.catalog.util.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Page<PublisherListResponseDTO> findPublisherList(
            Integer page,
            Integer limit,
            String direction,
            String sortBy,
            String name
    ) {
        Sort sort = Sort.by(PaginationUtil.getSortDirection(direction), sortBy);
        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<Publisher> pageResult = publisherRepository.findPublisherByNameLikeIgnoreCaseAndDeletedFalse(
                name == null || name.isBlank() ? "%" : "%" + name + "%",
                pageable
        );

        return pageResult
                .map(publisher -> PublisherListResponseDTO
                        .builder()
                        .secureId(publisher.getSecureId())
                        .name(publisher.getName())
                        .companyName(publisher.getCompanyName())
                        .address(publisher.getAddress())
                        .build()
                );
    }

    @Override
    public PublisherMutationResponseDTO createNewPublisher(PublisherCreateRequestDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());
        publisher.setCompanyName(dto.getCompanyName());
        publisher.setAddress(dto.getAddress());

        publisherRepository.save(publisher);

        PublisherMutationResponseDTO response = new PublisherMutationResponseDTO();
        response.setSecureId(publisher.getSecureId());
        response.setName(publisher.getName());

        return response;
    }

    @Override
    public PublisherMutationResponseDTO updatePublisher(String id, PublisherUpdateRequestDTO dto) {
        Publisher publisher = findPublisherBySecureId(id);

        publisher.setName(StringUtils.defaultIfBlank(dto.getName(), publisher.getName()));
        publisher.setCompanyName(StringUtils.defaultIfBlank(dto.getCompanyName(), publisher.getCompanyName()));
        publisher.setAddress(StringUtils.defaultIfBlank(dto.getAddress(), publisher.getAddress()));

        publisherRepository.save(publisher);

        PublisherMutationResponseDTO response = new PublisherMutationResponseDTO();
        response.setSecureId(publisher.getSecureId());
        response.setName(publisher.getName());

        return response;
    }

    @Override
    public PublisherMutationResponseDTO deletePublisher(String id) {
        Publisher publisher = findPublisherBySecureId(id);

        publisherRepository.delete(publisher);

        PublisherMutationResponseDTO response = new PublisherMutationResponseDTO();
        response.setSecureId(publisher.getSecureId());
        response.setName(publisher.getName());

        return response;
    }

    private Publisher findPublisherBySecureId(String secureId) {
        return publisherRepository
                .findBySecureIdAndDeletedFalse(secureId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Publisher with id %s not found", secureId))
                );
    }

}
