package eu.vital.iot.entity.pojo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class VitalSensorEventPOJO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7622227407129882404L;
	private ObjectId _id;
	private String name;
	private String status;
	private Double latitude;
	private Double longitude;
	private Integer speed;
	private String context;
	private String type;
	private String description;
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	

	
	
}
