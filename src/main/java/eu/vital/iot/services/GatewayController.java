package eu.vital.iot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import eu.vital.iot.business.TrafficEventBusiness;
import eu.vital.iot.business.to.TrafficEventBusinessTO;
import eu.vital.iot.dao.http.ReverseLocationHttpDAO;
import eu.vital.iot.services.to.CachedMarkersTO;

@Controller
@RequestMapping("/gateway")
public class GatewayController {


		@Inject
		private TrafficEventBusiness trafficEventBusiness;
		
		@Inject
		private ReverseLocationHttpDAO reverseLocationHttpDAO;
	

		@RequestMapping(value="/cachedtMarkers", method = RequestMethod.POST)
		@ResponseBody
		public String cachedtMarkers() throws Exception {
			Map<String,String> parameters = new HashMap<String, String>();
			parameters.put("type", "vital:VitalSensor");
			parameters.put("status", "vital:Running");
			List<CachedMarkersTO> markersList = trafficEventBusiness.getFirstMarkers(parameters);
			Gson gson = new Gson();
		    JsonElement element = gson.toJsonTree(markersList , new TypeToken<List<CachedMarkersTO>>() {}.getType());

		    if (!element.isJsonArray()) {
		        throw new RuntimeException("Element is not an Array.");
		    }

		    JsonArray jsonArray = element.getAsJsonArray();
			
			return jsonArray.toString();
		}
		
		@RequestMapping(value="/cachedClusteredtMarkers", method = RequestMethod.POST)
		@ResponseBody
		public String cachedClusteredtMarkers() throws Exception {
			Map<String,List<TrafficEventBusinessTO>> map = trafficEventBusiness.getMarkersByStreetName();
			Gson gson = new Gson();
		    JsonElement element = gson.toJsonTree(map , new TypeToken<Map<String,List<TrafficEventBusinessTO>>>() {}.getType());		
			return element.toString();
		}

}
