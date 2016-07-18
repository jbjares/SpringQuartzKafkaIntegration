package eu.vital.iot.dao.mongo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import eu.vital.iot.entity.document.VitalSensor;


@Repository
public class LogRealTimeEventMongoDAO implements Serializable{

	private static final long serialVersionUID = -5594202354959785802L;

	@Inject
	private VitalSensorMongoDAO vitalSensorMongoDAO;
	
	public void insert(List<VitalSensor> vitalSensorList) throws Exception {
		vitalSensorMongoDAO.insert(vitalSensorList);
		
	}

	

}
