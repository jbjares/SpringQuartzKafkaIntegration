package eu.vital.iot.business.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CachedMarkersTO implements Serializable{
	
	private static final long serialVersionUID = 2329095081167954942L;

	
	
	private String lat;
	
	private String lon;
	
	private String msg;
	
	private String streetName;
	
	private String speed;
	
	private Integer ocurrences;
	
	
	
	
	public Integer getOcurrences() {
		return ocurrences;
	}

	public void setOcurrences(Integer ocurrences) {
		this.ocurrences = ocurrences;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}
	

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
