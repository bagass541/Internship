package com.bagas.mappers;

import com.bagas.dto.DescriptionDTO;
import com.bagas.entities.Description;
import com.bagas.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    DescriptionMapper INSTANCE = Mappers.getMapper(DescriptionMapper.class);

    @Mapping(source = "product", target = "parent", qualifiedByName = "productToParent")
    DescriptionDTO toDTO(Description description);

    Description toEntity(DescriptionDTO descriptionDTO);

    @Named("productToParent")
    default Long productToParent(Product product) {
        return product.getId();
    }
}
