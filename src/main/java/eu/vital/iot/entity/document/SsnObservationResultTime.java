package eu.vital.iot.entity.document;



import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class SsnObservationResultTime  implements Serializable {

	private static final long serialVersionUID = -3066764951816872680L;

	@SerializedName("id")
	@Expose
	private ObjectId _id;

    @SerializedName("time:inXSDDateTime")
    @Expose
    private String timeInXSDDateTime;

	private String contextID;
	
	
	
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
     *     The timeInXSDDateTime
     */
    public String getTimeInXSDDateTime() {
        return timeInXSDDateTime;
    }

    /**
     * 
     * @param timeInXSDDateTime
     *     The time:inXSDDateTime
     */
    public void setTimeInXSDDateTime(String timeInXSDDateTime) {
        this.timeInXSDDateTime = timeInXSDDateTime;
    }

}
