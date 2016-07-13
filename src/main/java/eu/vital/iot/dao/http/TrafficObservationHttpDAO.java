package eu.vital.iot.dao.http;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import eu.vital.iot.entity.document.DulHasLocation;
import eu.vital.iot.entity.document.Observation;
import eu.vital.iot.entity.document.SsnHasValue;
import eu.vital.iot.entity.document.SsnObserv;
import eu.vital.iot.entity.document.SsnObservationResult;
import eu.vital.iot.entity.document.SsnObservationResultTime;
import eu.vital.iot.entity.document.VitalSensor;

@Repository
public class TrafficObservationHttpDAO implements HttpDAOConstants{
	
	@Inject
	private SensorHttpDAO sensorHttpDAO;


	public List<Observation> getTrafficObservationList() throws IOException {
		String uri = HTTP_VITAL_INTEGRATION_ATOSRESEARCH_EU_8280_HIREPLYPPI_SENSOR_OBSERVATION;
		List<VitalSensor> vitalsensorList = sensorHttpDAO.getVitalSensorList();
		List<Observation> observationList = new ArrayList<Observation>();
		for (VitalSensor vs : vitalsensorList) {
			for (SsnObserv ssnobs : vs.getSsnObserves()) {

				
				RestTemplate restTemplate = new RestTemplate();

				HttpHeaders headers = new HttpHeaders();
				headers.setCacheControl(NO_CACHE);
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				if("".equals(ssnobs.getSensorName()) || ssnobs.getSensorName()==null){
					continue;
				}
				System.out.println(ssnobs.getSensorName());
				String requestBody = "{ \"sensor\": ["+"\""+ssnobs.getSensorName()+"\""+"],\"property\": "+"\""+ssnobs.getProperty()+"\""+"}";
				HttpEntity<String> entity = new HttpEntity<String>(requestBody,
						headers);
				String responseBody = "";
				try{
					ResponseEntity<String> result = restTemplate.exchange(uri,
							HttpMethod.POST, entity, String.class);
					responseBody = result.getBody();
				}catch(Exception e){
					System.out.println("======================================");
					System.out.println(responseBody);
					System.out.println(requestBody);
					System.out.println(headers);
					System.out.println("======================================");
				}
				
				List<LinkedTreeMap> obsList = new ArrayList<LinkedTreeMap>();

				Gson gson = new Gson();
				JsonReader reader = new JsonReader(new StringReader(
						responseBody));
				reader.setLenient(true);
				obsList = gson.fromJson(reader, ArrayList.class);

				for(LinkedTreeMap obsLinkedTreeMap:obsList){
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
					observationList.add(observation);
				}

			}

		}
		return observationList;
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

}
