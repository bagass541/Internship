package com.bagas.mappers;

import com.bagas.dto.DescriptionDTO;
import com.bagas.entities.Description;
import com.bagas.entities.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    DescriptionMapper INSTANCE = Mappers.getMapper(DescriptionMapper.class);

    @Mapping(source = "product", target = "parent", qualifiedByName = "productToParent")
    DescriptionDTO toDTO(Description description);

    Description toEntity(DescriptionDTO descriptionDTO);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDescriptionFromDTO(DescriptionDTO descriptionDTO, @MappingTarget Description description);

    @Named("productToParent")
    default Long productToParent(Product product) {
        return product.getId();
    }
}
