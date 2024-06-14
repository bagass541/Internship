package com.bagas.services;

import com.bagas.dto.SubcategoryDTO;
import com.bagas.entities.Subcategory;
import com.bagas.exceptions.SubcategoryNotFoundException;
import com.bagas.mappers.SubcategoryMapper;
import com.bagas.repositories.SubcategoryRepository;
import com.bagas.updaters.SubcategoryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bagas.constants.ExceptionMessageConstants.SUBCATEGORY_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepo;

    public SubcategoryDTO create(SubcategoryDTO subcategoryDTO) {
        Subcategory subcategory = subcategoryRepo.save(SubcategoryMapper.INSTANCE.toEntity(subcategoryDTO));
        return SubcategoryMapper.INSTANCE.toDTO(subcategoryRepo.save(subcategory));
    }

    public SubcategoryDTO update(Long id, SubcategoryDTO subcategoryDTO) {
        Subcategory subcategory = subcategoryRepo.findById(id)
                .orElseThrow(() -> new SubcategoryNotFoundException(SUBCATEGORY_NOT_FOUND_MESSAGE));

        SubcategoryUpdater.updateSubcategory(subcategory, subcategoryDTO);
        subcategoryRepo.save(subcategory);

        return SubcategoryMapper.INSTANCE.toDTO(subcategory);
    }

    public void deleteById(Long id) {
        subcategoryRepo.deleteById(id);
    }
}
