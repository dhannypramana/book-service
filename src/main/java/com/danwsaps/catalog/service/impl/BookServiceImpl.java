package com.danwsaps.catalog.service.impl;

import com.danwsaps.catalog.domain.Author;
import com.danwsaps.catalog.domain.Book;
import com.danwsaps.catalog.domain.Category;
import com.danwsaps.catalog.domain.Publisher;
import com.danwsaps.catalog.dto.book.projection.BookListProjection;
import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookListResponseDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;
import com.danwsaps.catalog.repository.BookRepository;
import com.danwsaps.catalog.service.BookService;
import com.danwsaps.catalog.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public BookMutationResponseDTO createNewBook(BookCreateRequestDTO dto) {
        Publisher publisher = publisherService.findPublisherBySecureId(dto.getPublisherId());
        List<Category> categories = categoryService.findCategoryBySecureIdIn(dto.getCategoryIds());
        List<Author> authors = authorService.findAuthorBySecureIdIn(dto.getAuthorIds());

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

}
