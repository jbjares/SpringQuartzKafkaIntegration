package eu.vital.iot.business;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vital.iot.business.to.TrafficEventBusinessTO;
import eu.vital.iot.dao.http.SensorHttpDAO;
import eu.vital.iot.dao.http.TrafficObservationHttpDAO;

@Service
public class TrafficEventBusiness {

	@Inject
	private SensorHttpDAO sensorHttpDAO;
	
	@Inject
	private TrafficObservationHttpDAO trafficObservationHttpDAO;
	
	private TrafficEventBusinessTO trafficEventBusinessTO;
	
	public TrafficEventBusinessTO getSensorTrafficData(){
		
		
	}

	public TrafficEventBusinessTO getTrafficEventBusinessTO() {
		return trafficEventBusinessTO;
	}

	public void setTrafficEventBusinessTO(
			TrafficEventBusinessTO trafficEventBusinessTO) {
		this.trafficEventBusinessTO = trafficEventBusinessTO;
	}
	
	
	
	
}
