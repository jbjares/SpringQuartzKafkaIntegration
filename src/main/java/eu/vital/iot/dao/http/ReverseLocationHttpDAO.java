package eu.vital.iot.dao.http;

import java.io.StringReader;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import eu.vital.iot.entity.document.Place;

@Repository
public class ReverseLocationHttpDAO implements HttpDAOConstants{
	
	public synchronized String getStreetName(String lat, String lon){

	RestTemplate restTemplate = new RestTemplate();
	//String url = "http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?location=41.015137,28.979530&distance=200&outSR=&f=pjson";
	String endpoint = "http://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+lon;

	ResponseEntity<String> result = null;
	
	
	//TODO Hardcoded interval to avoid problems with the rest api policy.
	try {
		Thread.sleep(1500);
		result = restTemplate.exchange(endpoint,
		HttpMethod.GET,null,String.class);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}catch(org.springframework.web.client.ResourceAccessException raex){
		raex.printStackTrace();
		return "";
	}
	
	if(result == null){
		return "";
	}
	
	String responseBody = result.getBody();
	Gson gson = new GsonBuilder().create();
	JsonReader reader = new JsonReader(new StringReader(responseBody));
	reader.setLenient(true);
	Place place = gson.fromJson(reader, Place.class);

	if(place==null || place.getAddress()==null || place.getAddress().getRoad()==null){
		return "";
	}
	
	return place.getAddress().getRoad();

}

	
//	public String getStreetName(String lat, String lon){
//		RestTemplate restTemplate = new RestTemplate();
//		String endpoint = "http://nominatim.openstreetmap.org/reverse?format=json&lat=54.9824031826&lon=9.2833114795"+lat+","+lon;
//
//		ResponseEntity<String> result = restTemplate.exchange(endpoint,
//		HttpMethod.GET,null,String.class);
//		String responseBody = result.getBody();
//		List<LinkedTreeMap> listObjTreeMap = new ArrayList<LinkedTreeMap>();
//		String objStr = responseBody;
//		if(objStr==null){
//			return "";
//		}
//		objStr = objStr.replace("renderReverse(","");
//		objStr = objStr.substring(objStr.indexOf("renderReverse(")+1,objStr.lastIndexOf(")"));
//		Gson gson = new Gson();
//		LinkedTreeMap objLinkedTreeMap = gson.fromJson(objStr, LinkedTreeMap.class);
//
//		ArrayList infoLinkedList =  (ArrayList) objLinkedTreeMap.get("results");
//		String providedLoc = infoLinkedList.get(0).toString();
//		//System.out.println(providedLoc);
//		providedLoc = providedLoc.substring(providedLoc.indexOf("locations=[")+"locations=[".length()+1,providedLoc.lastIndexOf("}"));
//		if("".equals(providedLoc)|| !providedLoc.contains(",")){
//			return "";
//		}
//		providedLoc = providedLoc.substring(0, providedLoc.indexOf(","));
//		if("street=".endsWith(providedLoc)||"".equals(providedLoc)||providedLoc==null){
//			return "";
//		}
//		String[] stringNameArr = providedLoc.split("=");
//		//System.out.println(providedLoc);
//		return stringNameArr[1];
//
//	}

}
