package eu.vital.iot.entity.document;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TrafficEvent implements Serializable{

	private static final long serialVersionUID = -9202555371560615373L;

	private String marker = new String();
	
	private String lat = new String();
	
	private String lon = new String();
	
	private String streetName = new String();
	
	private Integer occurrence;


	public TrafficEvent(String latStr, String longStr, String street) {
		this.lat = latStr;
		this.lon = longStr;
		this.streetName = street;
	}
	
	public TrafficEvent() {}

	public Integer getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Integer occurrence) {
		this.occurrence = occurrence;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	
}
