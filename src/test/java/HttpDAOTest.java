import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import eu.vital.iot.entity.document.DulHasLocation;
import eu.vital.iot.entity.document.HasLastKnownLocation;
import eu.vital.iot.entity.document.Observation;
import eu.vital.iot.entity.document.SsnHasValue;
import eu.vital.iot.entity.document.SsnObserv;
import eu.vital.iot.entity.document.SsnObservationProperty;
import eu.vital.iot.entity.document.SsnObservationResult;
import eu.vital.iot.entity.document.SsnObservationResultTime;
import eu.vital.iot.entity.document.VitalSensor;
import eu.vital.iot.entity.pojo.VitalSensorEventPOJO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml" })
public class HttpDAOTest {

	private static final String SSN_OBSERVATION_RESULT = "ssn:observationResult";
	private static final String DUL_HAS_LOCATION = "dul:hasLocation";
	private static final String SSN_OBSERVED_BY = "ssn:observedBy";
	private static final String HTTP_VITAL_INTEGRATION_ATOSRESEARCH_EU_8280_HIREPLYPPI_SENSOR_OBSERVATION = "http://vital-integration.atosresearch.eu:8280/hireplyppi/sensor/observation";
	private static final String SSN_HAS_VALUE = "ssn:hasValue=";
	private static final String SENSOR = "sensor";
	private static final String SSN_OBSERVES = "ssn:observes";
	private static final String REVERSE_SPEED = "ReverseSpeed";
	private static final String SPEED = "Speed";
	private static final String HAS_LAST_KNOWN_LOCATION = "hasLastKnownLocation";
	private static final String STATUS = "status";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String ID = "id";
	private static final String TYPE = "type";
	private static final String CONTEXT = "@context";
	private static final String END_POINT_VITALSENSOR = "http://vital-integration.atosresearch.eu:8280/hireplyppi/sensor/metadata";
	private static final String NO_CACHE = "no-cache";
	private static final String TYPE_VITAL_VITAL_SENSOR = "{\"type\":[\"vital:VitalSensor\"]}";

	@Test
	public void testGetTrafficObservationList() throws IOException {
		String uri = HTTP_VITAL_INTEGRATION_ATOSRESEARCH_EU_8280_HIREPLYPPI_SENSOR_OBSERVATION;
		List<VitalSensor> vitalsensorList = getVitalSensor();
		for (VitalSensor vs : vitalsensorList) {
			for (SsnObserv ssnobs : vs.getSsnObserves()) {

				
				RestTemplate restTemplate = new RestTemplate();

				HttpHeaders headers = new HttpHeaders();
				headers.setCacheControl(NO_CACHE);
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				String requestBody = "{ \"sensor\": ["+"\""+ssnobs.getSensorName()+"\""+"],\"property\": "+"\""+ssnobs.getProperty()+"\""+"}";
				HttpEntity<String> entity = new HttpEntity<String>(requestBody,
						headers);

				ResponseEntity<String> result = restTemplate.exchange(uri,
						HttpMethod.POST, entity, String.class);
				String responseBody = result.getBody();
				List<LinkedTreeMap> observationList = new ArrayList<LinkedTreeMap>();

				Gson gson = new Gson();
				JsonReader reader = new JsonReader(new StringReader(
						responseBody));
				reader.setLenient(true);
				observationList = gson.fromJson(reader, ArrayList.class);

				for(LinkedTreeMap obsLinkedTreeMap:observationList){
					String context = obsLinkedTreeMap.get(CONTEXT).toString();
					String contextID = obsLinkedTreeMap.get(ID).toString();
					String type = obsLinkedTreeMap.get(TYPE).toString();
					String ssn_observed = obsLinkedTreeMap.get(SSN_OBSERVED_BY).toString();
//					String ssn_observationResultTime = obsLinkedTreeMap.get("ssn:observationResultTime").toString();//{time:inXSDDateTime=2016-07-13T19:52:00Z}
					String dul_hasLocation = obsLinkedTreeMap.get(DUL_HAS_LOCATION).toString();//{type=geo:Point, geo:lat=41.09301817, geo:long=29.0270595, geo:alt=0.0}
					String ssn_observationResult = obsLinkedTreeMap.get(SSN_OBSERVATION_RESULT).toString();//{type=ssn:SensorOutput, ssn:hasValue={type=ssn:ObservationValue, value=66.0, qudt:unit=qudt:KilometerPerHour}}
					
//					SsnObservationProperty ssnObservationProperty = fillSsnObservationProperty(ssn_observationProperty);
//					SsnObservationResultTime ssnObservationResultTime = fillSsnObservationResultTime(ssn_observationResultTime);
					SsnObservationResult ssnObservationResult = fillSsnObservationResult(ssn_observationResult);
					DulHasLocation dulHasLocation = fillDulHasLocation(dul_hasLocation);
					
					Observation observation = new Observation();
					observation.setContext(context);
					observation.setContextID(contextID);
					observation.setDulHasLocation(dulHasLocation);
					observation.setSsnObservationResult(ssnObservationResult);
					observation.setSsnObservedBy(ssn_observed);
					observation.setType(type);
				}

			}

		}
	}

	private SsnObservationResultTime fillSsnObservationResultTime(String ssn_observationResultTime) {
		// TODO Auto-generated method stub
		return null;
	}

	private DulHasLocation fillDulHasLocation(String dul_hasLocation) {
		String ssnHasValueStr = dul_hasLocation.replace(":", "_");
		ssnHasValueStr = ssnHasValueStr.replace("=", ":");
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(
				ssnHasValueStr));
		reader.setLenient(true);
		DulHasLocation dulHasLocation = gson.fromJson(reader, DulHasLocation.class);

		return dulHasLocation;
	}

	private SsnObservationResult fillSsnObservationResult(String ssn_observationResult) {
		String ssnHasValueStr = ssn_observationResult.substring(ssn_observationResult.indexOf(SSN_HAS_VALUE)+SSN_HAS_VALUE.length(), ssn_observationResult.lastIndexOf("}"));
		//ssn:hasValue={type=ssn:ObservationValue, value=93.0, qudt:unit=qudt:KilometerPerHour}
		ssnHasValueStr = ssnHasValueStr.replace(":", "_");
		ssnHasValueStr = ssnHasValueStr.replace("=", ":");
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(
				ssnHasValueStr));
		reader.setLenient(true);
		SsnHasValue ssnHasValue = gson.fromJson(reader, SsnHasValue.class);
		SsnObservationResult ssnObservationResult = new SsnObservationResult();
		ssnObservationResult.setSsnHasValue(ssnHasValue);
		return ssnObservationResult;
	}

	@Test
	public void testGetVitalSensorList() throws IOException {
		Assert.notEmpty(getVitalSensor());
	}

	private List<VitalSensor> getVitalSensor() throws IOException {
		String uri = END_POINT_VITALSENSOR;

		List<VitalSensor> vitalSensorEventList = new ArrayList<VitalSensor>();
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(NO_CACHE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String requestBody = TYPE_VITAL_VITAL_SENSOR;
		HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

		ResponseEntity<String> result = restTemplate.exchange(uri,
				HttpMethod.POST, entity, String.class);
		String responseBody = result.getBody();
		List<LinkedTreeMap> vitalSensorList = new ArrayList<LinkedTreeMap>();

		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(responseBody));
		reader.setLenient(true);
		vitalSensorList = gson.fromJson(reader, ArrayList.class);

		for (LinkedTreeMap vsMap : vitalSensorList) {
			VitalSensor vitalSensor = new VitalSensor();
			vitalSensor.setContext(vsMap.get(CONTEXT).toString());
			vitalSensor.setType(vsMap.get(TYPE).toString());
			vitalSensor.setDescription(vsMap.get(DESCRIPTION).toString());
			vitalSensor.setName(vsMap.get(NAME).toString());
			vitalSensor.setStatus(vsMap.get(STATUS).toString());
			LinkedTreeMap hasLastKnownLocationTreeMap = (LinkedTreeMap) vsMap
					.get(HAS_LAST_KNOWN_LOCATION);

			if (hasLastKnownLocationTreeMap != null) {
				List<HasLastKnownLocation> hlklList = getHasLastlocation(hasLastKnownLocationTreeMap
						.toString());
			}

			List<LinkedTreeMap> ssnObservList = (List) vsMap.get(SSN_OBSERVES);
			if (ssnObservList != null) {
				vitalSensor.setSsnObserves(getSSNObserves(ssnObservList));
			}

			vitalSensorEventList.add(vitalSensor);
		}
		return vitalSensorEventList;
	}

	private List<SsnObserv> getSSNObserves(List<LinkedTreeMap> ssnObservList) {
		List<SsnObserv> ssnObsList = new ArrayList<SsnObserv>();
		for (LinkedTreeMap ssno : ssnObservList) {
			SsnObserv ssnObserv = new SsnObserv();
			String urlID = ssno.get(ID).toString();
			if (!"".equals(urlID) && urlID != null && urlID.endsWith(SPEED)
					&& !urlID.endsWith(REVERSE_SPEED)) {
				ssnObserv.setContextID(urlID);
				ssnObserv.setSensorName(getSensorName(urlID));
				
			}else{
				continue;
			}
			ssnObserv.setProperty(SPEED);
			ssnObserv.setType(SENSOR);
			ssnObsList.add(ssnObserv);
		}

		return ssnObsList;
	}

	private String getSensorName(String sensorName) {
		String result = sensorName.substring(sensorName.indexOf(SENSOR)
				+ SENSOR.length() + 1, sensorName.indexOf(SPEED) - 1);
		return result;
	}

	private List<HasLastKnownLocation> getHasLastlocation(String responseBody)
			throws IOException {
		List<HasLastKnownLocation> hasLastKnownLocationList = new ArrayList<HasLastKnownLocation>();
		String hlklAsStr = responseBody.replace(":", "_");
		hlklAsStr = hlklAsStr.replace("=", ":");
		hlklAsStr = hlklAsStr.replace("{", "");
		hlklAsStr = hlklAsStr.replace("}", "");
		String[] hlklAsArr = hlklAsStr.split(",");

		for (String str : hlklAsArr) {
			String[] hlklAsArrOfArr = str.split("=");
			for (String strOfStr : hlklAsArrOfArr) {
				HasLastKnownLocation hasLastKnownLocation = new HasLastKnownLocation();
				String[] hlklAsArrOfArrOfArr = str.split(":");

				String type = "";
				if (hlklAsArrOfArrOfArr.length >= 1) {
					type = hlklAsArrOfArrOfArr[1];
				}
				String geoLat = "";
				if (hlklAsArrOfArrOfArr.length >= 3) {
					geoLat = hlklAsArrOfArrOfArr[3];
				}
				String geoLong = "";
				if (hlklAsArrOfArrOfArr.length >= 5) {
					geoLong = hlklAsArrOfArrOfArr[5];
				}

				hasLastKnownLocation.setType(type);
				String latStr = geoLat != null ? geoLat : "";
				if (!"".equals(latStr) && latStr != null) {
					hasLastKnownLocation.setGeoLat(new Double(latStr));
				}
				String longStr = geoLong != null ? geoLong : "";
				if (!"".equals(longStr) && longStr != null) {
					hasLastKnownLocation.setGeoLong(new Double(geoLong));
				}

				hasLastKnownLocationList.add(hasLastKnownLocation);
			}
		}
		return hasLastKnownLocationList;
	}
	


}
