package com.bagas.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeComputer {
    LOW("LOW"), MID("MID"), HIGH("HIGH");

    private final String typeStr;
}
