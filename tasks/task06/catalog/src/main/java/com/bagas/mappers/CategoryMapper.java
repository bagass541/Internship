package com.bagas.mappers;

import com.bagas.dto.CategoryDTO;
import com.bagas.dto.SubcategoryDTO;
import com.bagas.entities.Category;
import com.bagas.entities.Group;
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
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "subcategories", target = "subcategoriesDTO", qualifiedByName = "subcategoriesToSubcategoriesDTO")
    @Mapping(source = "group", target = "parent", qualifiedByName = "groupToParent")
    CategoryDTO toDTO(Category category);

    @Mapping(source = "parent", target = "group", qualifiedByName = "parentToGroup")
    Category toEntity(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDTO(CategoryDTO categoryDTO, @MappingTarget Category category);

    @Named("groupToParent")
    default Long groupToParent(Group group) {
        return group.getId();
    }

    @Named("subcategoriesToSubcategoriesDTO")
    default List<SubcategoryDTO> subcategoriesToSubcategoriesDTO(List<Subcategory> subcategories) {
        if (Objects.isNull(subcategories)) {
            return null;
        }

        return subcategories.stream()
                .map(SubcategoryMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Named("parentToGroup")
    default Group parentToGroup(Long parent) {
        Group group = new Group();
        group.setId(parent);

        return group;
    }
}
