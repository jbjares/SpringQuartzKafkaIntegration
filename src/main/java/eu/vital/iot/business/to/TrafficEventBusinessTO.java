package eu.vital.iot.business.to;

import java.io.Serializable;

public class TrafficEventBusinessTO implements Serializable{

	private static final long serialVersionUID = -9202555371560615373L;

	private String markers = new String();

	public String getMarkers() {
		return markers;
	}

	public void setMarkers(String markers) {
		this.markers = markers;
	}

	
	
}
