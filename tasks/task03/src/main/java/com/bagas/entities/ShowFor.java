package com.bagas.entities;

import java.util.List;

import com.bagas.entities.enums.ShowForType;
import lombok.Data;

@Data
public class ShowFor {

	private ShowForType type;
	
	private List<Integer> users;
}
