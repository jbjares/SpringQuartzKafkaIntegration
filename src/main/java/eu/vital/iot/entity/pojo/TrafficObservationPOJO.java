package eu.vital.iot.entity.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class TrafficObservationPOJO implements Serializable{
	
	private static final long serialVersionUID = 804311375478655144L;

	private ObjectId _id;
	
	private String color;
	
	private String speed;
	
	private String message;
	
	private Date time;
	
	private List<VitalSensorEventPOJO> sensorList;
	

	public List<VitalSensorEventPOJO> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<VitalSensorEventPOJO> sensorList) {
		this.sensorList = sensorList;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

	
	
}
