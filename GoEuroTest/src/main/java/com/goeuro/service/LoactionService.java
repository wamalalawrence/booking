package com.goeuro.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.goeuro.dao.LocationDAO;
import com.goeuro.model.CustomAplicationException;
import com.goeuro.model.GeoPosition;
import com.goeuro.model.Location;


@Service
public class LoactionService implements LocationDAO {

	final static Logger logger = LoggerFactory.getLogger(LoactionService.class);
	private static RestTemplate restTemplate;
	
	@Value(value = "${api.goeuro.location.url}")
	private String goeuroApiUrl;
	
	@Value(value = "${csv.file.location}")
	private String csvFileLocation;

	static {
		restTemplate = new RestTemplate();
	}

	@Override
	public List<Location> getLocation(String cityName) throws CustomAplicationException {
		try {
			ResponseEntity<List<Location>> locationResponseEntity = restTemplate.exchange(goeuroApiUrl + cityName,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {
					});
			List<Location> locations = locationResponseEntity.getBody();
			return locations;

		} catch (HttpStatusCodeException exception) {
			List<String> customHeader = exception.getResponseHeaders().get("x-app-err-id");
			String svcErrorMessageID = "";
			if (customHeader != null) {
				svcErrorMessageID = customHeader.get(0);
			}
			throw new CustomAplicationException(svcErrorMessageID, exception, exception.getMessage());
		} catch(ResourceAccessException rae){
			throw new CustomAplicationException(rae.getMessage());
		}
	}

	@Override
	public void save(List<Location> locations, String cityName) throws CustomAplicationException {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(csvFileLocation+cityName+".csv");
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write location objects to CSV file
			for (Location location : locations) {
				fileWriter.append(String.valueOf(location.getId()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(location.getName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(location.getType());
				fileWriter.append(COMMA_DELIMITER);

				GeoPosition geoPosition = location.getGeoPosition();
				String latitude = (geoPosition != null) ? String.valueOf(geoPosition.getLatitude()) : "0";
				fileWriter.append(latitude);
				fileWriter.append(COMMA_DELIMITER);

				String longitude = (geoPosition != null) ? String.valueOf(geoPosition.getLongitude()) : "0";
				fileWriter.append(longitude);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			logger.info("successfuly created csv file!");
		} catch (IOException e) {
			throw new CustomAplicationException(e.getMessage());
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				logger.error("Error while flushing/closing fileWriter");
				e.printStackTrace();
			}
		}
	}

}
