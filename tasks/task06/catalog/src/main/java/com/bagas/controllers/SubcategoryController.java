package com.bagas.controllers;

import com.bagas.dto.SubcategoryDTO;
import com.bagas.services.SubcategoryService;
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

import static com.bagas.constants.CommonConstants.CREATE_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.DELETE_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.ID_PATH_VARIABLE;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.UPDATE_SUBCATEGORY_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAIN_DOMAIN)
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @PostMapping(CREATE_SUBCATEGORY_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public SubcategoryDTO createSubcategory(@RequestBody SubcategoryDTO subcategory) {
        return subcategoryService.create(subcategory);
    }

    @PutMapping(UPDATE_SUBCATEGORY_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public SubcategoryDTO updateSubcategory(@PathVariable(ID_PATH_VARIABLE) Long id,
                                                            @RequestBody SubcategoryDTO subcategory) {

        return subcategoryService.update(id, subcategory);
    }

    @DeleteMapping(DELETE_SUBCATEGORY_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public HttpStatus deleteSubcategory(@PathVariable(ID_PATH_VARIABLE) Long id) {
        subcategoryService.deleteById(id);
        return HttpStatus.OK;
    }
}
