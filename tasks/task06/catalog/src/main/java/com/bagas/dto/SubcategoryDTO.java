package com.bagas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryDTO {

    private Long id;

    private String name;

    private Long parent;

    private List<ProductDTO> productsDTO;
}
