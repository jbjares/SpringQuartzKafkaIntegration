package eu.vital.iot.services.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CachedMarkersTO implements Serializable{
	
	private static final long serialVersionUID = 2329095081167954942L;

	private String latitude;
	
	private String longitude;
	
	private String msg;
	
	private String street;
	
	private List<CachedMarkersTO> clusteredCachedMarkersTO = new ArrayList<CachedMarkersTO>();

	
	
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	public List<CachedMarkersTO> getClusteredCachedMarkersTO() {
		return clusteredCachedMarkersTO;
	}

	public void setClusteredCachedMarkersTO(
			List<CachedMarkersTO> clusteredCachedMarkersTO) {
		this.clusteredCachedMarkersTO = clusteredCachedMarkersTO;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
