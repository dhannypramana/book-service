package com.danwsaps.catalog.service.impl;

import com.danwsaps.catalog.domain.Category;
import com.danwsaps.catalog.dto.category.request.CategoryCreateRequestDTO;
import com.danwsaps.catalog.dto.category.response.CategoryListResponseDTO;
import com.danwsaps.catalog.dto.category.response.CategoryMutationResponseDTO;
import com.danwsaps.catalog.repository.CategoryRepository;
import com.danwsaps.catalog.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryListResponseDTO> findCategoryList(
            Integer page,
            Integer limit,
            String direction,
            String sortBy,
            String name
    ) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortDirection(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<Category> pageResult = categoryRepository
                .findByNameLikeIgnoreCaseAndDeletedFalse(
                        name == null || name.isBlank() ? "%" : "%" + name + "%",
                        pageable
                );

        return pageResult
                .map(category -> CategoryListResponseDTO
                            .builder()
                            .secureId(category.getSecureId())
                            .name(category.getName())
                            .description(category.getDescription())
                            .build()
                );
    }

    @Override
    public CategoryMutationResponseDTO createNewCategory(CategoryCreateRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        categoryRepository.save(category);

        CategoryMutationResponseDTO response = new CategoryMutationResponseDTO();
        response.setSecureId(category.getSecureId());
        response.setName(category.getName());

        return response;
    }

    @Override
    public CategoryMutationResponseDTO updateCategory(String secureId, CategoryCreateRequestDTO dto) {
        Category category = findCategoryBySecureId(secureId);

        category.setName(StringUtils.defaultIfBlank(dto.getName(), category.getName()));
        category.setDescription(StringUtils.defaultIfBlank(dto.getDescription(), category.getDescription()));

        categoryRepository.save(category);

        CategoryMutationResponseDTO response = new CategoryMutationResponseDTO();
        response.setSecureId(category.getSecureId());
        response.setName(category.getName());

        return response;
    }

    @Override
    public CategoryMutationResponseDTO deleteCategory(String secureId) {
        Category category = findCategoryBySecureId(secureId);

        categoryRepository.delete(category);

        CategoryMutationResponseDTO response = new CategoryMutationResponseDTO();
        response.setSecureId(category.getSecureId());
        response.setName(category.getName());

        return response;
    }

    @Override
    public Category findCategoryBySecureId(String secureId) {
        return categoryRepository
                .findBySecureIdAndDeletedFalse(secureId)
                .orElseThrow(() -> new EntityNotFoundException (
                        String.format("Category with id %s not found", secureId))
                );
    }

}
