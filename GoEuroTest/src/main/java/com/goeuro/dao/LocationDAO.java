package com.goeuro.dao;

import java.util.List;

import com.goeuro.model.CustomAplicationException;
import com.goeuro.model.Location;

public interface LocationDAO {

	// Delimiter used in CSV file
	public static final String COMMA_DELIMITER = ",";
	public static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	public static final String FILE_HEADER = "_id,name,type,latitude,longitude";

	public List<Location> getLocation(String cityName) throws CustomAplicationException;

	public void save(List<Location> locations, String cityName) throws CustomAplicationException;
}
