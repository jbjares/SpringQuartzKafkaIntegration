package eu.vital.iot.business;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	
	@Test 
	public void testInjection(){
		Assert.assertNotNull(sensorHttpDAO);
		Assert.assertNotNull(logRealTimeEventDAO);
	}
	
	@Test
	public void testLogRealTimeEvents() throws Exception{
		List<VitalSensor> vitalSensorList = sensorHttpDAO.getVitalSensorList(null,null,null,null,null);
		logRealTimeEventDAO.insert(vitalSensorList);
		System.out.println(vitalSensorList.size());
	}

}
