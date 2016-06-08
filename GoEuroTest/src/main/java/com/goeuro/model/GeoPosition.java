package com.goeuro.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author wamalalawrence 2016-06-06:25:30
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoPosition implements Serializable{
	
	private static final long serialVersionUID = 4389929533727342298L;
	
	private long id;
	private double latitude;
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GeoPosition [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@JsonProperty("_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
