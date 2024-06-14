package com.bagas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionDTO {

    private Long id;

    private int weight;

    private int length;

    private int width;

    private Long parent;
}
