package eu.vital.iot.entity.document;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class SsnObserv implements Serializable {

	private static final long serialVersionUID = -3066764951816872680L;

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("id")
	@Expose
	private ObjectId _id;
	
	private String sensorName;
	
	private String property;

	private String contextID;

	
	
	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getContextID() {
		return contextID;
	}

	public void setContextID(String contextID) {
		this.contextID = contextID;
	}

	/**
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public SsnObserv withType(String type) {
		this.type = type;
		return this;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	

}
