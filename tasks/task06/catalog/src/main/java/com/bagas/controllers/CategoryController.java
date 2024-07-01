package com.bagas.controllers;

import com.bagas.dto.CategoryDTO;
import com.bagas.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bagas.constants.CommonConstants.CREATE_CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.DELETE_CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.ID_PATH_VARIABLE;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.UPDATE_CATEGORY_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAIN_DOMAIN)
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(CREATE_CATEGORY_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO);
    }

    @PutMapping(UPDATE_CATEGORY_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public CategoryDTO updateCategory(@PathVariable(ID_PATH_VARIABLE) Long id,
                                      @RequestBody CategoryDTO categoryDTO) {

        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping(DELETE_CATEGORY_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "delete endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public HttpStatus deleteCategory(@PathVariable(ID_PATH_VARIABLE) Long id) {
        categoryService.deleteById(id);
        return HttpStatus.OK;
    }
}
