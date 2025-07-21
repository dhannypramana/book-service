package com.danwsaps.catalog.web;

import com.danwsaps.catalog.dto.category.request.CategoryCreateRequestDTO;
import com.danwsaps.catalog.dto.category.response.CategoryListResponseDTO;
import com.danwsaps.catalog.dto.category.response.CategoryMutationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericPaginationResponseDTO;
import com.danwsaps.catalog.dto.common.GenericResponseDTO;
import com.danwsaps.catalog.service.CategoryService;
import com.danwsaps.catalog.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<GenericPaginationResponseDTO<CategoryListResponseDTO>> findPublisherList(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "sortBy", required = false, defaultValue = "updatedAt") String sortBy,
            @RequestParam(value = "name", required = false) String name
    ) {
        return ResponseUtil.ok(categoryService.findCategoryList(page, limit, direction, sortBy, name));
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO<CategoryMutationResponseDTO>> createNewCategory(@RequestBody @Valid CategoryCreateRequestDTO dto) {
        return ResponseUtil.created(categoryService.createNewCategory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<CategoryMutationResponseDTO>> updateCategory(@RequestBody CategoryCreateRequestDTO dto, @PathVariable String id) {
        return ResponseUtil.ok(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<CategoryMutationResponseDTO>> deleteCategory(@PathVariable String id) {
        return ResponseUtil.ok(categoryService.deleteCategory(id));
    }

}
