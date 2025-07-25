package com.danwsaps.catalog.service.impl;

import com.danwsaps.catalog.domain.Author;
import com.danwsaps.catalog.domain.Book;
import com.danwsaps.catalog.domain.Category;
import com.danwsaps.catalog.domain.Publisher;
import com.danwsaps.catalog.dto.author.response.AuthorMutationResponseDTO;
import com.danwsaps.catalog.dto.book.projection.BookListProjection;
import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookDetailResponseDTO;
import com.danwsaps.catalog.dto.book.response.BookListResponseDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;
import com.danwsaps.catalog.dto.category.response.CategoryMutationResponseDTO;
import com.danwsaps.catalog.dto.publisher.response.PublisherMutationResponseDTO;
import com.danwsaps.catalog.repository.BookRepository;
import com.danwsaps.catalog.service.BookService;
import com.danwsaps.catalog.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorServiceImpl authorService;

    private final PublisherServiceImpl publisherService;

    private final CategoryServiceImpl categoryService;

    @Override
    public Page<BookListResponseDTO> findBookList(Integer page, Integer limit, String direction, String sortBy, String title) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortDirection(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<BookListProjection> pageResult = bookRepository.findByTitleLikeIgnoreCaseAndDeletedFalse(
                title == null || title.isBlank() ? "%" : "%" + title + "%",
                pageable
        );

        return pageResult
                .map(book ->
                        BookListResponseDTO
                            .builder()
                            .secureId(book.getSecureId())
                            .title(book.getTitle())
                            .description(book.getDescription())
                            .createdAt(book.getCreatedAt())
                            .updatedAt(book.getUpdatedAt())
                            .build()
                );
    }

    @Override
    public BookDetailResponseDTO findBookDetail(String id) {
        Book book = findBookBySecureId(id);

        PublisherMutationResponseDTO publisherResponse = new PublisherMutationResponseDTO();
        publisherResponse.setSecureId(book.getPublisher().getSecureId());
        publisherResponse.setName(book.getPublisher().getName());

        Set<CategoryMutationResponseDTO> categories = book
                .getCategories()
                .stream()
                .map(category -> {
                    CategoryMutationResponseDTO categoryResponse = new CategoryMutationResponseDTO();
                    categoryResponse.setSecureId(category.getSecureId());
                    categoryResponse.setName(category.getName());
                    return categoryResponse;
                })
                .collect(Collectors.toSet());

        Set<AuthorMutationResponseDTO> authors = book
                .getAuthors()
                .stream()
                .map(author -> {
                    AuthorMutationResponseDTO authorResponse = new AuthorMutationResponseDTO();
                    authorResponse.setSecureId(author.getSecureId());
                    authorResponse.setName(author.getName());
                    return authorResponse;
                })
                .collect(Collectors.toSet());

        BookDetailResponseDTO response = new BookDetailResponseDTO();
        response.setSecureId(book.getSecureId());
        response.setTitle(book.getTitle());
        response.setDescription(book.getDescription());
        response.setPublisher(publisherResponse);
        response.setCategories(categories);
        response.setAuthors(authors);
        response.setCreatedAt(book.getCreatedAt());
        response.setUpdatedAt(book.getUpdatedAt());

        return response;
    }

    @Override
    public BookMutationResponseDTO createNewBook(BookCreateRequestDTO dto) {
        Publisher publisher = publisherService.findPublisherBySecureId(dto.getPublisherId());
        Set<Category> categories = categoryService.findCategoryBySecureIdIn(dto.getCategoryIds());
        Set<Author> authors = authorService.findAuthorBySecureIdIn(dto.getAuthorIds());

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPublisher(publisher);
        book.setCategories(categories);
        book.setAuthors(authors);

         bookRepository.save(book);

        BookMutationResponseDTO response = new BookMutationResponseDTO();
        response.setSecureId(book.getSecureId());
        response.setTitle(book.getTitle());

        return response;
    }

    private Book findBookBySecureId(String secureId) {
        return bookRepository
                .findBySecureIdAndDeletedFalse(secureId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Book with id %s not found", secureId))
                );
    }

}
