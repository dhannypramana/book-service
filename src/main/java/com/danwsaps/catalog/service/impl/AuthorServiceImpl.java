package com.danwsaps.catalog.service.impl;

import com.danwsaps.catalog.domain.Author;
import com.danwsaps.catalog.dto.author.request.AuthorCreateRequestDTO;
import com.danwsaps.catalog.dto.author.request.AuthorUpdateRequestDTO;
import com.danwsaps.catalog.dto.author.response.AuthorListResponseDTO;
import com.danwsaps.catalog.dto.author.response.AuthorMutationResponseDTO;
import com.danwsaps.catalog.repository.AuthorRepository;
import com.danwsaps.catalog.service.AuthorService;
import com.danwsaps.catalog.util.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Page<AuthorListResponseDTO> findAuthorList(
            Integer page,
            Integer limit,
            String direction,
            String sortBy,
            String name
    ) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortDirection(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<Author> pageResult = authorRepository
                .findByNameLikeIgnoreCaseAndDeletedFalse(
                        name == null || name.isBlank() ? "%" : "%" + name + "%",
                        pageable
                );

        return pageResult
                .map(author -> AuthorListResponseDTO
                        .builder()
                        .secureId(author.getSecureId())
                        .name(author.getName())
                        .description(author.getDescription())
                        .birthDate(author.getBirthDate())
                        .build()
                );
    }

    @Override
    public AuthorMutationResponseDTO createNewAuthor(AuthorCreateRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setDescription(dto.getDescription());
        author.setBirthDate(dto.getBirthDate());

        authorRepository.save(author);

        AuthorMutationResponseDTO response = new AuthorMutationResponseDTO();
        response.setSecureId(author.getSecureId());
        response.setName(author.getName());

        return response;
    }

    @Override
    public AuthorMutationResponseDTO updateAuthor(String secureId, AuthorUpdateRequestDTO dto) {
        Author author = findAuthorBySecureId(secureId);

        author.setName(StringUtils.defaultIfBlank(dto.getName(), author.getName()));
        author.setDescription(StringUtils.defaultIfBlank(dto.getDescription(), author.getDescription()));
        author.setBirthDate(dto.getBirthDate() == null ? author.getBirthDate() : dto.getBirthDate());

        authorRepository.save(author);

        AuthorMutationResponseDTO response = new AuthorMutationResponseDTO();
        response.setSecureId(author.getSecureId());
        response.setName(author.getName());

        return response;
    }

    @Override
    public AuthorMutationResponseDTO deleteAuthor(String secureId) {
        Author author = findAuthorBySecureId(secureId);

        authorRepository.delete(author);

        AuthorMutationResponseDTO response = new AuthorMutationResponseDTO();
        response.setSecureId(author.getSecureId());
        response.setName(author.getName());

        return response;
    }

    @Override
    public List<Author> findAuthorBySecureIdIn(List<String> secureIds) {
        List<Author> authors = authorRepository.findBySecureIdInAndDeletedFalse(secureIds);

        if (authors.isEmpty() || authors.size() != secureIds.size()) {
            throw new EntityNotFoundException("Authors not found for ids: " + secureIds.toString());
        }

        return authors;
    }

    @Override
    public Author findAuthorBySecureId(String secureId) {
        return authorRepository
                .findBySecureIdAndDeletedFalse(secureId)
                .orElseThrow(() -> new EntityNotFoundException (
                        String.format("Author with id %s not found", secureId))
                );
    }

}
