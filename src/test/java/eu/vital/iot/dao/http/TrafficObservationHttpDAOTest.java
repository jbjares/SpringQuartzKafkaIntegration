package eu.vital.iot.dao.http;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml" })
public class TrafficObservationHttpDAOTest {
	
	@Inject
	private SensorHttpDAO sensorHttpDAO;
	
	@Inject
	private TrafficObservationHttpDAO trafficObservationHttpDAO;
	
	@Test
	public void testInjection(){
		Assert.assertNotNull(sensorHttpDAO);
		Assert.assertNotNull(trafficObservationHttpDAO);
	}

	@Test
	public void getTrafficObservationList() throws IOException{
		Assert.assertNotNull(trafficObservationHttpDAO.getTrafficObservationList());
	}
}
