package com.bagas.services;

import com.bagas.dto.ProductDTO;
import com.bagas.entities.Product;
import com.bagas.exceptions.ProductNotFoundException;
import com.bagas.mappers.ProductMapper;
import com.bagas.repositories.ProductRepository;
import com.bagas.updaters.ProductUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bagas.constants.ExceptionMessageConstants.PRODUCT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;

    public ProductDTO getById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE));

        return ProductMapper.INSTANCE.toDTO(product);
    }

    public List<ProductDTO> getBySubcategoryId(Long id) {
        return productRepo.findBySubcategory_id(id).stream()
                .map(ProductMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO create(ProductDTO productDTO) {
        Product product = productRepo.save(ProductMapper.INSTANCE.toEntity(productDTO));
        product.getDescription().setProduct(product);

        return ProductMapper.INSTANCE.toDTO(product);
    }

    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product productToUpdate = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE));

        ProductUpdater.updateProduct(productToUpdate, productDTO);
        productRepo.save(productToUpdate);

        return ProductMapper.INSTANCE.toDTO(productToUpdate);
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }
}
