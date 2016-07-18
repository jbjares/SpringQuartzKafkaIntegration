
package eu.vital.iot.entity.document;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class Observation  implements Serializable {

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
    @SerializedName("ssn:observedBy")
    @Expose
    private String ssnObservedBy;
    @SerializedName("ssn:observationProperty")
    @Expose
    private SsnObservationProperty ssnObservationProperty;
    @SerializedName("ssn:observationResultTime")
    @Expose
    private SsnObservationResultTime ssnObservationResultTime;
    @SerializedName("dul:hasLocation")
    @Expose
    private DulHasLocation dulHasLocation;
    @SerializedName("ssn:observationResult")
    @Expose
    private SsnObservationResult ssnObservationResult;

	private String contextID;
	
	private String occurrence;
	
	
	public String getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
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
     * @return
     *     The context
     */
    public String getContext() {
        return context;
    }

    /**
     * 
     * @param context
     *     The @context
     */
    public void setContext(String context) {
        this.context = context;
    }



    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The ssnObservedBy
     */
    public String getSsnObservedBy() {
        return ssnObservedBy;
    }

    /**
     * 
     * @param ssnObservedBy
     *     The ssn:observedBy
     */
    public void setSsnObservedBy(String ssnObservedBy) {
        this.ssnObservedBy = ssnObservedBy;
    }

    /**
     * 
     * @return
     *     The ssnObservationProperty
     */
    public SsnObservationProperty getSsnObservationProperty() {
        return ssnObservationProperty;
    }

    /**
     * 
     * @param ssnObservationProperty
     *     The ssn:observationProperty
     */
    public void setSsnObservationProperty(SsnObservationProperty ssnObservationProperty) {
        this.ssnObservationProperty = ssnObservationProperty;
    }

    /**
     * 
     * @return
     *     The ssnObservationResultTime
     */
    public SsnObservationResultTime getSsnObservationResultTime() {
        return ssnObservationResultTime;
    }

    /**
     * 
     * @param ssnObservationResultTime
     *     The ssn:observationResultTime
     */
    public void setSsnObservationResultTime(SsnObservationResultTime ssnObservationResultTime) {
        this.ssnObservationResultTime = ssnObservationResultTime;
    }

    /**
     * 
     * @return
     *     The dulHasLocation
     */
    public DulHasLocation getDulHasLocation() {
        return dulHasLocation;
    }

    /**
     * 
     * @param dulHasLocation
     *     The dul:hasLocation
     */
    public void setDulHasLocation(DulHasLocation dulHasLocation) {
        this.dulHasLocation = dulHasLocation;
    }

    /**
     * 
     * @return
     *     The ssnObservationResult
     */
    public SsnObservationResult getSsnObservationResult() {
        return ssnObservationResult;
    }

    /**
     * 
     * @param ssnObservationResult
     *     The ssn:observationResult
     */
    public void setSsnObservationResult(SsnObservationResult ssnObservationResult) {
        this.ssnObservationResult = ssnObservationResult;
    }

}
