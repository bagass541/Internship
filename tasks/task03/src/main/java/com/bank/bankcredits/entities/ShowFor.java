package com.bank.bankcredits.entities;

import java.util.List;

import com.bank.bankcredits.entities.enums.ShowForType;

import lombok.Data;

@Data
public class ShowFor {

	private ShowForType type;
	
	private List<Integer> users;
}
