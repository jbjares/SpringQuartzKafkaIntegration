package eu.vital.iot.business.to;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.vital.iot.entity.document.VitalSensor;

@Document
public class ClusteredMarkersTO implements Serializable {
	
	private static final long serialVersionUID = 4153673365528228142L;

	private ObjectId _id;
	
	private String streetName;
	
	private String lon;
	
	private String lat;
	
	private int count;
	
	private String speedAvg;
	
	private String msg; 
	
	

	public ObjectId get_id() {
		return _id;
	}


	public void set_id(ObjectId _id) {
		this._id = _id;
	}


	public String getSpeedAvg() {
		return speedAvg;
	}


	public void setSpeedAvg(String speedAvg) {
		this.speedAvg = speedAvg;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}




	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getLon() {
		return lon;
	}


	public void setLon(String lon) {
		this.lon = lon;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}






	

}
