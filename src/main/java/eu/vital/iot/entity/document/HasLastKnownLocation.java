package eu.vital.iot.entity.document;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class HasLastKnownLocation implements Serializable{


	private static final long serialVersionUID = -3066764951816872680L;

	private ObjectId _id;
	
	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("geo_lat")
	@Expose
	private Double geoLat;
	@SerializedName("geo_long")
	@Expose
	private Double geoLong;
	
	private String contextID;
	

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

	public HasLastKnownLocation withType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * 
	 * @return The geoLat
	 */
	public Double getGeoLat() {
		return geoLat;
	}

	/**
	 * 
	 * @param geoLat
	 *            The geo:lat
	 */
	public void setGeoLat(Double geoLat) {
		this.geoLat = geoLat;
	}

	public HasLastKnownLocation withGeoLat(Double geoLat) {
		this.geoLat = geoLat;
		return this;
	}

	/**
	 * 
	 * @return The geoLong
	 */
	public Double getGeoLong() {
		return geoLong;
	}

	/**
	 * 
	 * @param geoLong
	 *            The geo:long
	 */
	public void setGeoLong(Double geoLong) {
		this.geoLong = geoLong;
	}

	public HasLastKnownLocation withGeoLong(Double geoLong) {
		this.geoLong = geoLong;
		return this;
	}

}