package com.bagas.mappers;

import com.bagas.dto.CategoryDTO;
import com.bagas.dto.GroupDTO;
import com.bagas.entities.Category;
import com.bagas.entities.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "categories", target = "categoriesDTO", qualifiedByName = "categoriesToCategoriesDTO")
    GroupDTO toDTO(Group group);

    Group toEntity(GroupDTO groupDTO);

    @Named("categoriesToCategoriesDTO")
    default List<CategoryDTO> categoriesToCategoriesDTO(List<Category> categories) {
        if (Objects.isNull(categories)) {
            return null;
        }

        return categories.stream()
                .map(CategoryMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
