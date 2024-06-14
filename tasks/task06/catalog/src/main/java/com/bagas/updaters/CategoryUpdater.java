package com.bagas.updaters;

import com.bagas.dto.CategoryDTO;
import com.bagas.entities.Category;
import com.bagas.entities.Group;

public class CategoryUpdater {

    public static void updateCategory(Category source, CategoryDTO target) {
        source.setName(target.getName());

        Group group = new Group();
        group.setId(target.getParent());

        source.setGroup(group);
    }
}
