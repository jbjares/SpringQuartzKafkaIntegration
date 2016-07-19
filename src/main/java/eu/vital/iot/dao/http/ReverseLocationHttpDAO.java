package eu.vital.iot.dao.http;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

@Repository
public class ReverseLocationHttpDAO implements HttpDAOConstants{
	
	public String getStreetName(String lat, String lon){
		RestTemplate restTemplate = new RestTemplate();
		String endpoint = "https://www.mapquestapi.com/geocoding/v1/reverse?key=BdAsqr9iRyjncOWzSiShAoLmYuIVx6iw&callback=renderReverse&location="+lat+","+lon;

		ResponseEntity<String> result = restTemplate.exchange(endpoint,
		HttpMethod.GET,null,String.class);
		String responseBody = result.getBody();
		List<LinkedTreeMap> listObjTreeMap = new ArrayList<LinkedTreeMap>();
		String objStr = responseBody;
		//System.out.println(objStr);
		if(objStr==null){
			return "";
		}
		objStr = objStr.replace("renderReverse(","");
		objStr = objStr.substring(objStr.indexOf("renderReverse(")+1,objStr.lastIndexOf(")"));
		Gson gson = new Gson();
		LinkedTreeMap objLinkedTreeMap = gson.fromJson(objStr, LinkedTreeMap.class);

		ArrayList infoLinkedList =  (ArrayList) objLinkedTreeMap.get("results");
		String providedLoc = infoLinkedList.get(0).toString();
		providedLoc = providedLoc.substring(providedLoc.indexOf("locations=[")+"locations=[".length()+1,providedLoc.length());
		providedLoc = providedLoc.substring(0, providedLoc.indexOf(","));
		if("street=".endsWith(providedLoc)||"".equals(providedLoc)||providedLoc==null){
			return "";
		}
		String[] stringNameArr = providedLoc.split("=");
		//System.out.println(providedLoc);
		return stringNameArr[1];

	}

}
