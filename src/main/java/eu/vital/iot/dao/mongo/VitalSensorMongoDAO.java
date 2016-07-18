package eu.vital.iot.dao.mongo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import eu.vital.iot.entity.document.VitalSensor;

@Repository
public class VitalSensorMongoDAO implements Serializable{
	private static final long serialVersionUID = 7458393673213096920L;

	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired 
	public GridFsTemplate fsTemplate;

	
	public void insert(List<VitalSensor> vitalSensorList) throws Exception {
		
		for(VitalSensor vs:vitalSensorList){
			mongoOperations.insert(vs,VitalSensor.class.getSimpleName());			
		}
		 
	}
	
	public List<VitalSensor> findCachedVitalSensorListByGenericParameters(Map<String,String> parameters){
		Query query = new Query();
		if(parameters==null || parameters.isEmpty()){
			return Collections.EMPTY_LIST;
		}

		for(Map.Entry<String,String> entry:parameters.entrySet()){
			query.addCriteria(
					Criteria.where(entry.getKey()).regex(entry.getValue(), "i"));	
		}
		
		List<VitalSensor> vitalSensorCache = mongoOperations.find(query, VitalSensor.class,VitalSensor.class.getSimpleName());

		return vitalSensorCache;
	}

}
