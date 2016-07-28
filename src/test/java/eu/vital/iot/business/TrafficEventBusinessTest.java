package eu.vital.iot.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.vital.iot.business.to.ClusteredMarkersTO;
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
	private TrafficEventService trafficEventBusiness;
	
	
	@Test 
	public void testInjection(){
		Assert.assertNotNull(sensorHttpDAO);
		Assert.assertNotNull(logRealTimeEventDAO);
	}
	
	//@Ignore
	@Test
	public void testLogRealTimeEvents() throws Exception{
		List<VitalSensor> vitalSensorList = sensorHttpDAO.getVitalSensorList(null,null,null,null,null);
		logRealTimeEventDAO.insert(vitalSensorList);
		System.out.println(vitalSensorList.size());
	}


	

//	@Ignore
//	@Test
//	public void testStreetName() throws Exception{
//		List<ClusteredMarkersTO> list = trafficEventBusiness.getClusters();
//		System.out.println(list.size());
//		Map<String, Integer> names= new HashMap<String, Integer>();
//		for(ClusteredMarkersTO cm:list){
//			if(names.containsKey(cm.getStreetName())){
//				names.put(cm.getStreetName(),cm.getCount()+1);
//			}else{
//				names.put(cm.getStreetName(),1);
//			}
//			
//		}
//		System.out.println(names);
//	}
	
}
