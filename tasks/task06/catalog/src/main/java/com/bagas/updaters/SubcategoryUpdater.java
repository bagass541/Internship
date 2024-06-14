package com.bagas.updaters;

import com.bagas.dto.SubcategoryDTO;
import com.bagas.entities.Category;
import com.bagas.entities.Subcategory;

public class SubcategoryUpdater {

    public static void updateSubcategory(Subcategory source, SubcategoryDTO target) {
        source.setName(target.getName());

        Category category = new Category();
        category.setId(target.getParent());

        source.setCategory(category);
    }
}
