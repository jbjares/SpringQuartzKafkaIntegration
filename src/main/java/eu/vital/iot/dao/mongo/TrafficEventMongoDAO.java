package eu.vital.iot.dao.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import eu.vital.iot.entity.document.VitalSensor;
import eu.vital.iot.entity.pojo.TrafficObservationPOJO;

@Repository
public class TrafficEventMongoDAO implements Serializable{

	private static final long serialVersionUID = 7458393673213096920L;

	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired 
	public GridFsTemplate fsTemplate;

	
	
	public String insert(TrafficObservationPOJO trafficObservationPOJO) throws Exception {
		 mongoOperations.insert(trafficObservationPOJO,TrafficObservationPOJO.class.getSimpleName());
		 
		 Query query = new Query();
			query.addCriteria(Criteria.where("contextID").regex(trafficObservationPOJO.getContextID(), "i"));
			
		TrafficObservationPOJO	trafficObservationPOJODBResult = mongoOperations.
				findOne(query, TrafficObservationPOJO.class,TrafficObservationPOJO.class.getSimpleName());
			
		 return trafficObservationPOJODBResult.getContextID();
	}
	
//	@Override
//	public String updateInstanceName(ConfigFileTO configFileTO) throws Exception {
//		if(configFileTO.getInstanceName()==null || "".equals(configFileTO.getInstanceName()) || configFileTO.get_id()==null){
//			return null;
//		}
//		 Query query = new Query();
//			query.addCriteria(Criteria.where("projectName").regex(configFileTO.getProjectName(), CASE_INSENSITIVE))
//			.addCriteria(Criteria.where("simulationName").regex(configFileTO.getSimulationName(), CASE_INSENSITIVE));
//		
//		Update update=new Update();
//		update.set("instanceName",configFileTO.getInstanceName());
//		mongoOperations.updateFirst(query,update, ConfigFileTO.class);
//		 return configFileTO.get_id().toString();
//	}
//	
//	@Override
//	public List<InputStream> findCFGFile(String projectName,String simulationName) throws Exception {
//		List<InputStream> result = new ArrayList<InputStream>();
//		Query query = new Query();
//		query.addCriteria(Criteria.where("projectName").regex(projectName, CASE_INSENSITIVE))
//		.addCriteria(Criteria.where("simulationName").regex(simulationName, CASE_INSENSITIVE));
//		List<ConfigFileTO> configFileTOList = mongoOperations.find(query, ConfigFileTO.class,ConfigFileTO.class.getSimpleName());
//		if(configFileTOList==null || configFileTOList.isEmpty()){
//			return null;
//		}
//		for(ConfigFileTO cfg:configFileTOList){
//			ObjectId id = cfg.getCfgFileID();
//			GridFSDBFile file = fsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
//			result.add(file.getInputStream());
//		}
//		return result;
//	}
//	
//	@Override
//	public List<InputStream> findCFGFile(String projectName,String simulationName, String instanceName) throws Exception {
//		List<InputStream> result = new ArrayList<InputStream>();
//		Query query = new Query();
//		query.addCriteria(Criteria.where("projectName").regex(projectName, CASE_INSENSITIVE))
//		.addCriteria(Criteria.where("simulationName").regex(simulationName, CASE_INSENSITIVE))
//		.addCriteria(Criteria.where("instanceName").regex(instanceName, CASE_INSENSITIVE));
//		List<ConfigFileTO> configFileTOList = mongoOperations.find(query, ConfigFileTO.class,ConfigFileTO.class.getSimpleName());
//		if(configFileTOList==null || configFileTOList.isEmpty()){
//			return null;
//		}
//		for(ConfigFileTO cfg:configFileTOList){
//			ObjectId id = cfg.getCfgFileID();
//			GridFSDBFile file = fsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
//			result.add(file.getInputStream());
//		}
//		return result;
//	}
//	
//
//	
//	
//	@Override
//	public List<ConfigFileTO> findConfigTO(String projectName,String simulationName) throws Exception {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("projectName").regex(projectName, CASE_INSENSITIVE))
//		.addCriteria(Criteria.where("simulationName").regex(simulationName, CASE_INSENSITIVE));
//		List<ConfigFileTO> configFileTOList = mongoOperations.find(query, ConfigFileTO.class,ConfigFileTO.class.getSimpleName());
//		return configFileTOList;
//	}
//
//
//	@Override
//	public void removeAll(String projectName, String simulationName) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("projectName").regex(projectName, CASE_INSENSITIVE))
//		.addCriteria(Criteria.where("simulationName").regex(simulationName, CASE_INSENSITIVE));
//		mongoOperations.remove(query, ConfigFileTO.class,ConfigFileTO.class.getSimpleName());
//		
//	}
//
//	@Override
//	public InputStream findCFGFile(ConfigFileTO configFileTO) {
//		ObjectId id = configFileTO.getCfgFileID();
//		GridFSDBFile file = fsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
//		return file.getInputStream();
//	}
//
//	@Override
//	public void updateInstanceName(String projectName, String simulationName,String instanceName) {
//
//            System.err.println("Project" + projectName + " inst=" + " sim=" + simulationName);
//            
//            if(instanceName==null || "".equals(instanceName) ){
//			return;
//		}
//		 Query query = new Query();
//			query.addCriteria(Criteria.where("projectName").regex(projectName, CASE_INSENSITIVE))
//			.addCriteria(Criteria.where("simulationName").regex(simulationName, CASE_INSENSITIVE));
//		
//		Update update=new Update();
//		update.set("instanceName",instanceName);
//		mongoOperations.updateFirst(query,update, ConfigFileTO.class);
//	}
//	
}
