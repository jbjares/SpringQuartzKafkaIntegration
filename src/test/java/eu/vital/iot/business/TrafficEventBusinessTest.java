package eu.vital.iot.business;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.vital.iot.business.to.TrafficEventBusinessTO;
import eu.vital.iot.dao.http.SensorHttpDAO;
import eu.vital.iot.dao.mongo.LogRealTimeEventMongoDAO;
import eu.vital.iot.entity.document.VitalSensor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml" })
public class TrafficEventBusinessTest {
	
	@Inject
	private SensorHttpDAO sensorHttpDAO;
	
	
	@Inject
	private LogRealTimeEventMongoDAO logRealTimeEventDAO;
	
	@Inject
	private TrafficEventBusiness trafficEventBusiness;
	
	
	@Test 
	public void testInjection(){
		Assert.assertNotNull(sensorHttpDAO);
		Assert.assertNotNull(logRealTimeEventDAO);
	}
	
	@Ignore
	@Test
	public void testLogRealTimeEvents() throws Exception{
		List<VitalSensor> vitalSensorList = sensorHttpDAO.getVitalSensorList(null,null,null,null,null);
		logRealTimeEventDAO.insert(vitalSensorList);
		System.out.println(vitalSensorList.size());
	}

	@Test
	public void testStreetName() throws Exception{
		Map<String,List<TrafficEventBusinessTO>> map = trafficEventBusiness.getMarkersByStreetName();
		for(Map.Entry<String,List<TrafficEventBusinessTO>> entry:map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("\n");
		}
	}
	
}
