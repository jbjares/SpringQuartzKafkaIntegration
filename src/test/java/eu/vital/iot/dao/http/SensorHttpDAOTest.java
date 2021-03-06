package eu.vital.iot.dao.http;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.vital.iot.entity.document.VitalSensor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml" })
public class SensorHttpDAOTest {

	
	@Inject
	private SensorHttpDAO sensorHttpDAO;
	
	@Test
	public void testInjection(){
		Assert.assertNotNull(sensorHttpDAO);
	}

	@Test
	public void testGetEvents() throws Exception{
		List<VitalSensor> vitalSensorList = sensorHttpDAO.getVitalSensorList(null,null,null,null,null);
		System.out.println(vitalSensorList.size());
	}

}
