package com.goeuro.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author wamalalawrence 2016-06-06:20:24
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable{

	private static final long serialVersionUID = -7375645967756029349L;
	
	private long id;
	private String key;
	private String name;
	private String fullName;
	private String iata_airport_code;
	private String type;
	private String country;
	private GeoPosition geoPosition;
	private long locationId;
	private boolean inEurope;
	private Integer countryId;
	private String countryCode;
	private boolean coreCountry;
	private Double distance;
	private Map<String, String> names;
	private Map<String, List<String>>  alternativeNames;

	@JsonProperty("_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIata_airport_code() {
		return iata_airport_code;
	}

	public void setIata_airport_code(String iata_airport_code) {
		this.iata_airport_code = iata_airport_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("geo_position")
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public boolean isInEurope() {
		return inEurope;
	}

	public void setInEurope(boolean inEurope) {
		this.inEurope = inEurope;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isCoreCountry() {
		return coreCountry;
	}

	public void setCoreCountry(boolean coreCountry) {
		this.coreCountry = coreCountry;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Map<String, String> getNames() {
		return names;
	}

	public void setNames(Map<String, String> names) {
		this.names = names;
	}

	public Map<String, List<String>>  getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(Map<String, List<String>>alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", key=" + key + ", name=" + name + ", fullName=" + fullName
				+ ", iata_airport_code=" + iata_airport_code + ", type=" + type + ", country=" + country
				+ ", geoPosition=" + geoPosition + ", locationId=" + locationId + ", inEurope=" + inEurope
				+ ", countryId=" + countryId + ", countryCode=" + countryCode + ", coreCountry=" + coreCountry
				+ ", distance=" + distance + ", names=" + names + ", alternativeNames=" + alternativeNames + "]";
	}
	
	
}
