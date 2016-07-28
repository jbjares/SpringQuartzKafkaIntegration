package eu.vital.iot.dao.mongo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.vital.iot.dao.http.SensorHttpDAO;
import eu.vital.iot.entity.document.VitalSensor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml" })
public class VitalSensorMongoDAOTest {
	
	
	@Inject
	private VitalSensorMongoDAO vitalSensorMongoDAO;
	
	
	@Test 
	public void testInjection(){
		Assert.assertNotNull(vitalSensorMongoDAO);
	}

	@Ignore
	@Test
	public void testVitalSensorList() throws Exception{

		for(int i=0;i<600;i++){
			Map<String,String> parameters = new HashMap<String,String>();
			parameters.put("type", "vital:VitalSensor");
			parameters.put("status", "vital:Running");        	
			List<VitalSensor> cached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParameters(parameters);
			for(VitalSensor vs:cached){
				System.out.println(vs.getSsnObserves().get(i).getObservationList().get(i).getSsnObservationResult().getSsnHasValue().getValue());				
			}
		}
	}

	@Test
	public void testVitalSensorCluster() throws Exception{

			Map<String,String> parameters = new HashMap<String,String>();
			parameters.put("type", "vital:VitalSensor");
			//parameters.put("status", "vital:Running");        	
			Map<String,List<VitalSensor>> cached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParametersGroupByStreet(parameters);
			
			for(Map.Entry<String,List<VitalSensor>> entry:cached.entrySet()){
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				System.out.println(entry.getValue().size());
				System.out.println("\n");
			}
		
	}


}
