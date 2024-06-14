package com.bagas.controllers;

import com.bagas.dto.ProductDTO;
import com.bagas.exceptions.ProductNotFoundException;
import com.bagas.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bagas.constants.CommonConstants.CREATE_PRODUCT_ENDPOINT;
import static com.bagas.constants.CommonConstants.DELETE_PRODUCT_ENDPOINT;
import static com.bagas.constants.CommonConstants.GET_PRODUCT_BY_ID;
import static com.bagas.constants.CommonConstants.ID_ENDPOINT;
import static com.bagas.constants.CommonConstants.ID_PATH_VARIABLE;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.PRODUCTS_BY_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.UPDATE_PRODUCT_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAIN_DOMAIN)
public class ProductController {

    private final ProductService productService;

    @GetMapping(GET_PRODUCT_BY_ID)
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping(PRODUCTS_BY_SUBCATEGORY_ENDPOINT + ID_ENDPOINT)
    public List<ProductDTO> getProductsBySubcategory(@PathVariable("id") Long id) {
        return productService.getBySubcategoryId(id);
    }

    @PostMapping(CREATE_PRODUCT_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @PutMapping(UPDATE_PRODUCT_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ProductDTO updateProduct(@PathVariable(ID_PATH_VARIABLE) Long id,
                                                    @RequestBody ProductDTO productDTO) {

        try {
            return productService.update(id, productDTO);
        } catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(DELETE_PRODUCT_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "delete endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public HttpStatus deleteProduct(@PathVariable(ID_PATH_VARIABLE) Long id) {
        productService.deleteById(id);
        return HttpStatus.OK;
    }
}
