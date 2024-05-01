package com.bagas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShowForType {
	ID("ID"),
	NAME("NAME");

	private final String showForTypeStr;
}
