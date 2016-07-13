package eu.vital.iot.entity.document;



import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Document
public class DulHasLocation  implements Serializable {

	private static final long serialVersionUID = -3066764951816872680L;

	@SerializedName("id")
	@Expose
	private ObjectId _id;

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("geo:lat")
    @Expose
    private Double geoLat;
    @SerializedName("geo:long")
    @Expose
    private Double geoLong;
    @SerializedName("geo:alt")
    @Expose
    private Double geoAlt;

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
     *     The geoLat
     */
    public Double getGeoLat() {
        return geoLat;
    }

    /**
     * 
     * @param geoLat
     *     The geo:lat
     */
    public void setGeoLat(Double geoLat) {
        this.geoLat = geoLat;
    }

    /**
     * 
     * @return
     *     The geoLong
     */
    public Double getGeoLong() {
        return geoLong;
    }

    /**
     * 
     * @param geoLong
     *     The geo:long
     */
    public void setGeoLong(Double geoLong) {
        this.geoLong = geoLong;
    }

    /**
     * 
     * @return
     *     The geoAlt
     */
    public Double getGeoAlt() {
        return geoAlt;
    }

    /**
     * 
     * @param geoAlt
     *     The geo:alt
     */
    public void setGeoAlt(Double geoAlt) {
        this.geoAlt = geoAlt;
    }

}
