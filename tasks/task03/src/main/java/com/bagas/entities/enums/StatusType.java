package com.bagas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum StatusType {
	IN_PROGRESS("IN_PROGRESS"),
	DONE("DONE");

	private final String statusTypeStr;

	public static StatusType getStatusType(BigDecimal debt) {
		if (debt.compareTo(BigDecimal.ZERO) < 0) {
			return StatusType.DONE;
		}

		return StatusType.IN_PROGRESS;
	}
}
