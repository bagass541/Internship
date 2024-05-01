package com.bagas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SexType {
	MALE("MALE"),
	FEMALE("FEMALE"),
	ANY("ANY");

	private final String sexTypeStr;
}
