package com.danwsaps.catalog.service;

import com.danwsaps.catalog.domain.Category;
import com.danwsaps.catalog.dto.category.request.CategoryCreateRequestDTO;
import com.danwsaps.catalog.dto.category.response.CategoryListResponseDTO;
import com.danwsaps.catalog.dto.category.response.CategoryMutationResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    CategoryMutationResponseDTO createNewCategory(CategoryCreateRequestDTO dto);

    CategoryMutationResponseDTO deleteCategory(String secureId);

    CategoryMutationResponseDTO updateCategory(String secureId, CategoryCreateRequestDTO dto);

    Page<CategoryListResponseDTO> findCategoryList(Integer page, Integer limit, String direction, String sortBy, String name);

    Category findCategoryBySecureId(String secureId);

    List<Category> findCategoryBySecureIdIn(List<String> secureIds);

}
