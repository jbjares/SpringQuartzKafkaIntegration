package eu.vital.iot.dao.http;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import eu.vital.iot.entity.document.HasLastKnownLocation;
import eu.vital.iot.entity.document.SsnObserv;
import eu.vital.iot.entity.document.VitalSensor;
import eu.vital.iot.entity.pojo.VitalSensorEventPOJO;

@Repository
public class SensorHttpDAO implements HttpDAOConstants{

	
	
		public List<VitalSensor> getVitalSensorList() throws IOException{
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
				LinkedTreeMap hasLastKnownLocationTreeMap = (LinkedTreeMap) vsMap.get(HAS_LAST_KNOWN_LOCATION);

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
			for(LinkedTreeMap ssno:ssnObservList){
				SsnObserv ssnObserv = new SsnObserv();
				String urlID = ssno.get(ID).toString();
				if(!"".equals(urlID)&&urlID!=null&&urlID.endsWith(SPEED)&&!urlID.endsWith(REVERSE_SPEED)){
					ssnObserv.setContextID(urlID);
					ssnObserv.setSensorName(getSensorName(urlID));
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
