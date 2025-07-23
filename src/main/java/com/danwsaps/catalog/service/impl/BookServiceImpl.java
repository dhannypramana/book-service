package com.danwsaps.catalog.service.impl;

import com.danwsaps.catalog.domain.Author;
import com.danwsaps.catalog.domain.Book;
import com.danwsaps.catalog.domain.Category;
import com.danwsaps.catalog.domain.Publisher;
import com.danwsaps.catalog.dto.book.request.BookCreateRequestDTO;
import com.danwsaps.catalog.dto.book.response.BookMutationResponseDTO;
import com.danwsaps.catalog.repository.BookRepository;
import com.danwsaps.catalog.service.BookService;
import lombok.RequiredArgsConstructor;
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
