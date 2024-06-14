package com.bagas.updaters;

import com.bagas.dto.ProductDTO;
import com.bagas.entities.Product;
import com.bagas.entities.Subcategory;

import java.util.Objects;

public class ProductUpdater {

    public static void updateProduct(Product source, ProductDTO target) {
        source.setName(target.getName());
        source.setPrice(target.getPrice());
        source.setAmount(target.getAmount());
        source.setImageUrl(target.getImageUrl());
        source.setDeliveryTime(target.getDeliveryTime());

        if (Objects.nonNull(target.getDescriptionDTO())) {
            source.setDescription(DescriptionUpdater.updateDescription(source.getDescription(), target.getDescriptionDTO()));
        }

        Subcategory subcategory = new Subcategory();
        subcategory.setId(target.getParent());

        source.setSubcategory(subcategory);
    }
}
