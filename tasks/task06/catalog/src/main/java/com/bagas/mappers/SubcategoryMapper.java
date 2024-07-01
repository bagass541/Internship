package com.bagas.mappers;

import com.bagas.dto.ProductDTO;
import com.bagas.dto.SubcategoryDTO;
import com.bagas.entities.Category;
import com.bagas.entities.Product;
import com.bagas.entities.Subcategory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {

    SubcategoryMapper INSTANCE = Mappers.getMapper(SubcategoryMapper.class);

    @Mapping(source = "products", target = "productsDTO", qualifiedByName = "productsToProductsDTO")
    @Mapping(source = "category", target = "parent", qualifiedByName = "categoryToParent")
    SubcategoryDTO toDTO(Subcategory subcategory);

    @Mapping(source = "parent", target = "category", qualifiedByName = "parentToCategory")
    Subcategory toEntity(SubcategoryDTO subcategoryDTO);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubcategoryFromDTO(SubcategoryDTO subcategoryDTO, @MappingTarget Subcategory subcategory);

    @Named("categoryToParent")
    default Long categoryToParent(Category category) {
        return category.getId();
    }

    @Named("productsToProductsDTO")
    default List<ProductDTO> productsToProductsDTO(List<Product> products) {
        if (Objects.isNull(products)) {
            return null;
        }

        return products.stream()
                .map(ProductMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Named("parentToCategory")
    default Category parentToCategory(Long parent) {
        Category category = new Category();
        category.setId(parent);

        return category;
    }
}
