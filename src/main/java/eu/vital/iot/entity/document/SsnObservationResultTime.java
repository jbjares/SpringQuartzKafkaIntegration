package eu.vital.iot.entity.document;



import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class SsnObservationResultTime  implements Serializable {

	private static final long serialVersionUID = -3066764951816872680L;


    @SerializedName("time:inXSDDateTime")
    @Expose
    private Date timeInXSDDateTime;

	private String contextID;
	
	
	
	public String getContextID() {
		return contextID;
	}

	public void setContextID(String contextID) {
		this.contextID = contextID;
	}

	public Date getTimeInXSDDateTime() {
		return timeInXSDDateTime;
	}

	public void setTimeInXSDDateTime(Date timeInXSDDateTime) {
		this.timeInXSDDateTime = timeInXSDDateTime;
	}
	
	

 
}
