package com.bagas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyType {
	EUR("EUR"),
	USD("USD"),
	Br("Br");

	private final String currencyTypeStr;
}
