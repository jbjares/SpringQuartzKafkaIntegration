package eu.vital.iot.entity.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class VitalSensor implements Serializable {

	private static final long serialVersionUID = -3066764951816872680L;

	@SerializedName("id")
	@Expose
	private ObjectId _id;
	@SerializedName("@context")
	@Expose
	private String context;

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("status")
	@Expose
	private String status;

	@SerializedName("ssn:observes")
	@Expose
	private List<SsnObserv> ssnObserves = new ArrayList<SsnObserv>();
	
	@SerializedName("contextID")
	@Expose
	private String contextID;
	
	private TrafficEvent trafficEventBusiness;
	
	
	private String locationName;
	
	
	
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public TrafficEvent getTrafficEventBusiness() {
		return trafficEventBusiness;
	}

	public void setTrafficEventBusiness(TrafficEvent trafficEventBusiness) {
		this.trafficEventBusiness = trafficEventBusiness;
	}

	public String getContextID() {
		return contextID;
	}

	public void setContextID(String contextID) {
		this.contextID = contextID;
	}
	

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	/**
	 * 
	 * @return The context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * 
	 * @param context
	 *            The @context
	 */
	public void setContext(String context) {
		this.context = context;
	}

	public VitalSensor withContext(String context) {
		this.context = context;
		return this;
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

	public VitalSensor withType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public VitalSensor withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public VitalSensor withDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * 
	 * @return The status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public VitalSensor withStatus(String status) {
		this.status = status;
		return this;
	}


	/**
	 * 
	 * @return The ssnObserves
	 */
	public List<SsnObserv> getSsnObserves() {
		return ssnObserves;
	}

	/**
	 * 
	 * @param ssnObserves
	 *            The ssn:observes
	 */
	public void setSsnObserves(List<SsnObserv> ssnObserves) {
		this.ssnObserves = ssnObserves;
	}

	public VitalSensor withSsnObserves(List<SsnObserv> ssnObserves) {
		this.ssnObserves = ssnObserves;
		return this;
	}
}