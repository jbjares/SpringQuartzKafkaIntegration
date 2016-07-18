package eu.vital.iot.dao.http;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import eu.vital.iot.entity.document.SsnObserv;

public class ReverseLocationHttpDAO implements HttpDAOConstants{
	
	public void getReverseLocation(String lat, String lon){
		RestTemplate restTemplate = new RestTemplate();
		String endpoint = "https://www.mapquestapi.com/geocoding/v1/reverse?key=BdAsqr9iRyjncOWzSiShAoLmYuIVx6iw&callback=renderReverse&location="+lat+","+lon;

		ResponseEntity<String> result = restTemplate.exchange(endpoint,
		HttpMethod.GET,null,String.class);
		String responseBody = result.getBody();
		List<LinkedTreeMap> listObjTreeMap = new ArrayList<LinkedTreeMap>();
		String objStr = responseBody;
		objStr = objStr.replace("renderReverse(","");
		objStr = objStr.substring(objStr.indexOf("renderReverse(")+1,objStr.lastIndexOf(")"));
		Gson gson = new Gson();
		LinkedTreeMap objLinkedTreeMap = gson.fromJson(objStr, LinkedTreeMap.class);

		ArrayList infoLinkedList =  (ArrayList) objLinkedTreeMap.get("results");
		System.out.println(infoLinkedList.get(0));
		
//		for(LinkedTreeMap map:objLinkedTreeMap){
//	
//		}

	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		String obj = "renderReverse({\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\\u00A9 2016 MapQuest, Inc.\",\"imageUrl\":\"https://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2016 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.053116,\"lng\":-76.313603}},\"locations\":[{\"street\":\"1101 N Charlotte St\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Lancaster\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Lancaster\",\"adminArea4Type\":\"County\",\"adminArea3\":\"PA\",\"adminArea3Type\":\"State\",\"adminArea1\":\"US\",\"adminArea1Type\":\"Country\",\"postalCode\":\"17603\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"R\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.053356,\"lng\":-76.313641},\"displayLatLng\":{\"lat\":40.053356,\"lng\":-76.313641},\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v4/getmap?key=BdAsqr9iRyjncOWzSiShAoLmYuIVx6iw&type=map&size=225,160&pois=purple-1,40.053356,-76.313641,0,0,|&center=40.053356,-76.313641&zoom=15&rand=713348111\"}]}]})";
//		obj = obj.replace("renderReverse(","");
//		obj = obj.substring(obj.indexOf("renderReverse(")+1,obj.lastIndexOf(")"));
//		//System.out.println(obj);
//		
//		Gson gson = new Gson();
//		LinkedTreeMap objLinkedTreeMap = gson.fromJson(obj, LinkedTreeMap.class);
//		System.out.println(objLinkedTreeMap.getClass());
		ReverseLocationHttpDAO dao = new ReverseLocationHttpDAO();
		dao.getReverseLocation("40.053356","-76.313641");

	}

}
