package com.danwsaps.catalog.service;


import com.danwsaps.catalog.domain.Author;
import com.danwsaps.catalog.dto.author.request.AuthorCreateRequestDTO;
import com.danwsaps.catalog.dto.author.request.AuthorUpdateRequestDTO;
import com.danwsaps.catalog.dto.author.response.AuthorListResponseDTO;
import com.danwsaps.catalog.dto.author.response.AuthorMutationResponseDTO;
import org.springframework.data.domain.Page;

public interface AuthorService {

    AuthorMutationResponseDTO createNewAuthor(AuthorCreateRequestDTO dto);

    AuthorMutationResponseDTO updateAuthor(String secureId, AuthorUpdateRequestDTO dto);

    AuthorMutationResponseDTO deleteAuthor(String secureId);

    Page<AuthorListResponseDTO> findAuthorList(Integer page, Integer limit, String direction, String sortBy, String name);

    Author findAuthorBySecureId(String secureId);

}
