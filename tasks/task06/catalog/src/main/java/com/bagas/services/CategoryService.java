package com.bagas.services;

import com.bagas.dto.CategoryDTO;
import com.bagas.entities.Category;
import com.bagas.exceptions.CategoryNotFoundException;
import com.bagas.mappers.CategoryMapper;
import com.bagas.repositories.CategoryRepository;
import com.bagas.updaters.CategoryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bagas.constants.ExceptionMessageConstants.CATEGORY_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryRepo.save(CategoryMapper.INSTANCE.toEntity(categoryDTO));
        return CategoryMapper.INSTANCE.toDTO(category);
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category categoryToUpdate = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND_MESSAGE));

        CategoryUpdater.updateCategory(categoryToUpdate, categoryDTO);
        categoryRepo.save(categoryToUpdate);

        return CategoryMapper.INSTANCE.toDTO(categoryToUpdate);
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}
