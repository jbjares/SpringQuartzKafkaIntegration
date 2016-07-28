package eu.vital.iot.dao.http;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
import eu.vital.iot.entity.document.HasLastKnownLocation;
import eu.vital.iot.entity.document.Observation;
import eu.vital.iot.entity.document.SsnHasValue;
import eu.vital.iot.entity.document.SsnObserv;
import eu.vital.iot.entity.document.SsnObservationResult;
import eu.vital.iot.entity.document.SsnObservationResultTime;
import eu.vital.iot.entity.document.TrafficEvent;
import eu.vital.iot.entity.document.VitalSensor;

@Repository
public class SensorHttpDAO implements HttpDAOConstants{

	
		@Inject
		private ReverseLocationHttpDAO reverseLocationHttpDAO; 
	
		private static final String ONE = "1";
		private static final String SSN_OBSERVATION_RESULT_TIME = "ssn:observationResultTime";

		public List<VitalSensor> getVitalSensorList(List<VitalSensor> vitalsensorList,List<LinkedTreeMap> vsMapList, SsnObserv ssnObserv, Integer countObs,Integer countVsMap) throws Exception{
			if(countObs==null){
				countObs=1;
			}
			if(ssnObserv==null){
				ssnObserv = new SsnObserv();
			}

			if(vitalsensorList==null || vitalsensorList.isEmpty()){
				vitalsensorList = new ArrayList<VitalSensor>();
			}
			

			if(countVsMap==null){
				countVsMap = 1;
			}
			
			if(vsMapList==null || vsMapList.isEmpty()){
				vsMapList = getVsMapList();
			}

			if((countVsMap)>=vsMapList.size()){
				return vitalsensorList;
			}
			LinkedTreeMap vsMap = vsMapList.get(countVsMap-1);
			

			VitalSensor vitalSensor = new VitalSensor();
			vitalSensor.setContext(vsMap.get(CONTEXT).toString());
			vitalSensor.setType(vsMap.get(TYPE).toString());
			vitalSensor.setDescription(vsMap.get(DESCRIPTION).toString());
			vitalSensor.setName(vsMap.get(NAME).toString());
			vitalSensor.setStatus(vsMap.get(STATUS).toString());


			LinkedTreeMap hasLastKnownLocationTreeMap = (LinkedTreeMap) vsMap.get(HAS_LAST_KNOWN_LOCATION);

			if (hasLastKnownLocationTreeMap != null) {
				List<HasLastKnownLocation> hlklList = getHasLastlocation(hasLastKnownLocationTreeMap.toString(),vitalSensor);

			}
			
			
			List<SsnObserv> ssnObservList = vitalSensor.getSsnObserves();
			if(vitalSensor.getSsnObserves()==null || vitalSensor.getSsnObserves().isEmpty()){
				ssnObservList = getSSNObserves((List<LinkedTreeMap>) vsMap.get(SSN_OBSERVES));		
				vitalSensor.setSsnObserves(ssnObservList);
			}

			if(countObs<=vitalSensor.getSsnObserves().size()){
				ssnObserv = vitalSensor.getSsnObserves().get(countObs-1);
				fillObservsations(ssnObserv,vitalSensor);
				vitalSensor.setContextID(ssnObserv.getContextID());
				countObs++;
				vitalsensorList.add(vitalSensor);
				if(vitalSensor.getSsnObserves().size()>(countObs-1)){
					countVsMap++;
					getVitalSensorList(vitalsensorList,vsMapList,vitalSensor.getSsnObserves().get(countObs-1),countObs,countVsMap);							
				}
			}

			
			if(countObs>vitalSensor.getSsnObserves().size()){
				countObs = 1;
				countVsMap++;
				getVitalSensorList(vitalsensorList,vsMapList,null,countObs,countVsMap);							
			}

		return vitalsensorList;
		}

		private List<LinkedTreeMap> getVsMapList() {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(NO_CACHE);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			String requestBody = TYPE_VITAL_VITAL_SENSOR;
			HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

			ResponseEntity<String> result = restTemplate.exchange(END_POINT_VITALSENSOR,
					HttpMethod.POST, entity, String.class);
			String responseBody = result.getBody();
			List<LinkedTreeMap> vitalSensorListLinkedTreeMap = new ArrayList<LinkedTreeMap>();

			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new StringReader(responseBody));
			reader.setLenient(true);
			vitalSensorListLinkedTreeMap = gson.fromJson(reader, ArrayList.class);
			return vitalSensorListLinkedTreeMap;
		}

		private List<SsnObserv> getSSNObserves(List<LinkedTreeMap> ssnObservList) {
			List<SsnObserv> ssnObsList = new ArrayList<SsnObserv>();
			for(LinkedTreeMap ssno:ssnObservList){
				SsnObserv ssnObserv = new SsnObserv();
				String urlID = ssno.get(ID).toString();
				if(!"".equals(urlID)&&urlID!=null&&urlID.endsWith(SPEED)&&!urlID.endsWith(REVERSE_SPEED)){
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
			String result = sensorName.substring(sensorName.indexOf(SENSOR)+SENSOR.length()+1,sensorName.indexOf(SPEED)-1);
			return result;
		}

		private List<HasLastKnownLocation> getHasLastlocation(String responseBody,VitalSensor vitalSensor)
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
					if ("".equals(latStr) || latStr == null) {
						return Collections.EMPTY_LIST;
					}
					hasLastKnownLocation.setGeoLat(new Double(latStr));
					String longStr = geoLong != null ? geoLong : "";
					if ("".equals(longStr) || longStr == null) {
						return Collections.EMPTY_LIST;
					}
					hasLastKnownLocation.setGeoLong(new Double(geoLong));

					hasLastKnownLocationList.add(hasLastKnownLocation);
				}
			}
			return hasLastKnownLocationList;
		}

		
		public void fillObservsations(SsnObserv ssnobs,VitalSensor vitalSensor) throws Exception{
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(NO_CACHE);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			if("".equals(ssnobs.getSensorName()) || ssnobs.getSensorName()==null){
				return;
			}
			String requestBody = "{ \"sensor\": ["+"\""+ssnobs.getSensorName()+"\""+"],\"property\": "+"\""+ssnobs.getProperty()+"\""+"}";
			HttpEntity<String> entity = new HttpEntity<String>(requestBody,
					headers);
			String responseBody = "";
				ResponseEntity<String> result = restTemplate.exchange(HTTP_VITAL_INTEGRATION_ATOSRESEARCH_EU_8280_HIREPLYPPI_SENSOR_OBSERVATION,
						HttpMethod.POST, entity, String.class);
				responseBody = result.getBody();

			
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
				String dul_hasLocation = obsLinkedTreeMap.get(DUL_HAS_LOCATION).toString();//{type=geo:Point, geo:lat=41.09301817, geo:long=29.0270595, geo:alt=0.0}
				String ssn_observationResult = obsLinkedTreeMap.get(SSN_OBSERVATION_RESULT).toString();//{type=ssn:SensorOutput, ssn:hasValue={type=ssn:ObservationValue, value=66.0, qudt:unit=qudt:KilometerPerHour}}
				String ssn_observationResultTime= obsLinkedTreeMap.get(SSN_OBSERVATION_RESULT_TIME).toString();//{time:inXSDDateTime=2016-07-15T19:03:00Z}

				SsnObservationResult ssnObservationResult = fillSsnObservationResult(ssn_observationResult);
				DulHasLocation dulHasLocation = fillDulHasLocation(dul_hasLocation,vitalSensor);
				
				ssn_observationResultTime = ssn_observationResultTime.substring(ssn_observationResultTime.indexOf(TIME_IN_XSD_DATE_TIME)+TIME_IN_XSD_DATE_TIME.length(),ssn_observationResultTime.lastIndexOf("}"));
				SsnObservationResultTime observationResultTime = new SsnObservationResultTime();
			    String pattern=DATE_PATTERN;
			    SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			    Date d=sdf.parse(ssn_observationResultTime);
				observationResultTime.setTimeInXSDDateTime(d);
				observationResultTime.setContextID(contextID);
				Observation observation = new Observation();
				observation.setContext(context);
				observation.setContextID(contextID);
				observation.setDulHasLocation(dulHasLocation);
				observation.setSsnObservationResult(ssnObservationResult);
				observation.setSsnObservedBy(ssn_observed);
				observation.setType(type);
				observation.setOccurrence(ONE);
				ssnobs.getObservationList().add(observation);
			}
			
		}



		private DulHasLocation fillDulHasLocation(String dul_hasLocation,VitalSensor vitalSensor) {
			String ssnHasValueStr = dul_hasLocation.replace(":", "_");
			ssnHasValueStr = ssnHasValueStr.replace("=", ":");
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new StringReader(ssnHasValueStr));
			reader.setLenient(true);
			DulHasLocation dulHasLocation = gson.fromJson(reader, DulHasLocation.class);
			String street = null;
			street = reverseLocationHttpDAO.getStreetName(dulHasLocation.getGeoLat().toString(), dulHasLocation.getGeoLong().toString());				
			vitalSensor.setTrafficEventBusiness(new TrafficEvent(dulHasLocation.getGeoLat().toString(), dulHasLocation.getGeoLong().toString(),street));
			return dulHasLocation;
		}

		private SsnObservationResult fillSsnObservationResult(String ssn_observationResult) {
			String ssnHasValueStr = ssn_observationResult.substring(ssn_observationResult.indexOf(SSN_HAS_VALUE)+SSN_HAS_VALUE.length(), ssn_observationResult.lastIndexOf("}"));
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
