package com.bagas.mappers;

import com.bagas.dto.DescriptionDTO;
import com.bagas.dto.ProductDTO;
import com.bagas.entities.Description;
import com.bagas.entities.Product;
import com.bagas.entities.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "subcategory", target = "parent", qualifiedByName = "subcategoryToParent")
    @Mapping(source = "description", target = "descriptionDTO", qualifiedByName = "descriptionToDescriptionDTO")
    ProductDTO toDTO(Product product);

    @Mapping(source = "parent", target = "subcategory", qualifiedByName = "parentToSubcategoryId")
    @Mapping(source = "descriptionDTO", target = "description", qualifiedByName = "descriptionDTOtoEntity")
    Product toEntity(ProductDTO productDTO);

    @Named("subcategoryToParent")
    default Long subcategoryToParent(Subcategory subcategory) {
        return subcategory.getId();
    }

    @Named("descriptionToDescriptionDTO")
    default DescriptionDTO descriptionToDescriptionDTO(Description description) {
        return DescriptionMapper.INSTANCE.toDTO(description);
    }

    @Named("parentToSubcategoryId")
    default Subcategory parentToSubCategory(Long id) {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(id);

        return subcategory;
    }

    @Named("descriptionDTOtoEntity")
    default Description descriptionDTOtoEntity(DescriptionDTO descriptionDTO) {
        return DescriptionMapper.INSTANCE.toEntity(descriptionDTO);
    }
}
