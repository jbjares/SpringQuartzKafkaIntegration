package eu.vital.iot.business.to;

import java.io.Serializable;
import java.util.List;

import eu.vital.iot.entity.pojo.TrafficObservationPOJO;

public class TrafficEventBusinessTO implements Serializable{
	
	private static final long serialVersionUID = 7808022255570292277L;
	
	private List<TrafficObservationPOJO> trafficObservationPOJOList;
	public List<TrafficObservationPOJO> getTrafficObservationPOJOList() {
		return trafficObservationPOJOList;
	}
	public void setTrafficObservationPOJOList(
			List<TrafficObservationPOJO> trafficObservationPOJOList) {
		this.trafficObservationPOJOList = trafficObservationPOJOList;
	}

	
}
