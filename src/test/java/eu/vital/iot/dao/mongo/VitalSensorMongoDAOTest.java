package eu.vital.iot.dao.mongo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
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
	
	
//	public List<VitalSensor> findCachedVitalSensorListByGenericParameters(String contextID, Map<String,String> parameters){
//		Query query = new Query();
//		if(parameters==null || parameters.isEmpty()){
//			return Collections.EMPTY_LIST;
//		}
//		query.addCriteria(
//				Criteria.where("contextID").regex(contextID, "i"));
//		for(Map.Entry<String,String> entry:parameters.entrySet()){
//			query.addCriteria(
//					Criteria.where(entry.getKey()).regex(entry.getValue(), "i"));	
//		}
//		
//		List<VitalSensor> vitalSensorCache = mongoOperations.find(query, VitalSensor.class,VitalSensor.class.getSimpleName());
//
//		return vitalSensorCache;
//	}
	
	@Inject
	private VitalSensorMongoDAO vitalSensorMongoDAO;
	
	
	@Test 
	public void testInjection(){
		Assert.assertNotNull(vitalSensorMongoDAO);
	}
	
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


}
