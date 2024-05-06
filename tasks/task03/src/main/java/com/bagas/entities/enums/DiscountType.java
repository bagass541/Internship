package com.bagas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiscountType {
	ONE("ONE"),
	MANY("MANY");

	private final String typeStr;
}
