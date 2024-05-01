package com.bagas.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class JsonFileFilter implements FilenameFilter {

	private List<String> names;
	
	public JsonFileFilter(List<String> names) {
		this.names = names.stream().map(s -> "db_" + s + ".json").toList();
	}

	@Override
	public boolean accept(File dir, String name) {
		return names.contains(name);
	}

}
