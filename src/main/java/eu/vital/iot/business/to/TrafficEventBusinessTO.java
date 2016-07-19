package eu.vital.iot.business.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrafficEventBusinessTO implements Serializable{

	private static final long serialVersionUID = -9202555371560615373L;

	private String marker = new String();
	
	private String lat = new String();
	
	private String lon = new String();
	
	private String streetName = new String();
	
	private Integer occurrence;
	
	private Map<String,List<TrafficEventBusinessTO>> map = new HashMap<String, List<TrafficEventBusinessTO>>();

	
	
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

	public Map<String, List<TrafficEventBusinessTO>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<TrafficEventBusinessTO>> map) {
		this.map = map;
	}



	
}
