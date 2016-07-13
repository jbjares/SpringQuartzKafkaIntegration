package eu.vital.iot.entity.document;



import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class SsnHasValue  implements Serializable {

	private static final long serialVersionUID = -3066764951816872680L;

	@SerializedName("id")
	@Expose
	private ObjectId _id;

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("qudt:unit")
    @Expose
    private String qudtUnit;

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
     *     The value
     */
    public Double getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The qudtUnit
     */
    public String getQudtUnit() {
        return qudtUnit;
    }

    /**
     * 
     * @param qudtUnit
     *     The qudt:unit
     */
    public void setQudtUnit(String qudtUnit) {
        this.qudtUnit = qudtUnit;
    }

}
