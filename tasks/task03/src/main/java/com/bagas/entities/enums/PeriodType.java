package com.bagas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PeriodType {
	DAY("DAY"),
	WEEK("WEEK"),
	MONTH("MONTH"),
	YEAR("YEAR");

	private final String periodTypeStr;
}
