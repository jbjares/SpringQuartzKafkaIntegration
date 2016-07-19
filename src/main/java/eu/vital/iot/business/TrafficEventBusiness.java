package eu.vital.iot.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import scala.Array;
import eu.vital.iot.business.to.TrafficEventBusinessTO;
import eu.vital.iot.dao.http.ReverseLocationHttpDAO;
import eu.vital.iot.dao.mongo.VitalSensorMongoDAO;
import eu.vital.iot.entity.document.VitalSensor;
import eu.vital.iot.services.to.CachedMarkersTO;

@Component
public class TrafficEventBusiness {

	@Inject
	private VitalSensorMongoDAO vitalSensorMongoDAO;
	
	@Inject
	private ReverseLocationHttpDAO reverseLocationHttpDAO;

	
	@SuppressWarnings("serial")
	public Map<String,List<TrafficEventBusinessTO>> getMarkersByStreetName(){
		Map<String,String> parameters = new HashMap<String, String>();
		parameters.put("type", "vital:VitalSensor");
		parameters.put("status", "vital:Running");
		List<CachedMarkersTO> markersList = getFirstMarkers(parameters);
		TrafficEventBusinessTO trafficEventBusinessTO = new TrafficEventBusinessTO();
		String lat = "";
		String lon = "";
		String street = "";
		
		Map<String,List<TrafficEventBusinessTO>> map = new HashMap<String, List<TrafficEventBusinessTO>>();
		for(CachedMarkersTO cachedMarkers: markersList){
			TrafficEventBusinessTO trafficEventBusinessInner = new TrafficEventBusinessTO();
			lat = cachedMarkers.getLatitude();
			lon = cachedMarkers.getLongitude();
			street = reverseLocationHttpDAO.getStreetName(lat, lon);
			trafficEventBusinessInner.setLat(lat);
			trafficEventBusinessInner.setLon(lon);
			trafficEventBusinessInner.setMarker(lat+","+lon);
			trafficEventBusinessInner.setStreetName(street);
			if(map.containsKey(street)){
				int count = map.get(street).size()+1;
				trafficEventBusinessInner.setOccurrence(count);
				map.get(street).add(trafficEventBusinessInner);
			}else{
				trafficEventBusinessInner.setOccurrence(1);
				map.put(street,new ArrayList<TrafficEventBusinessTO>());
				map.get(street).add(trafficEventBusinessInner);
			}
		}
		return map;
		
	}
	
	public TrafficEventBusinessTO getTrafficInformation(Map parameters) throws Exception{  
		TrafficEventBusinessTO trafficEventBusinessTO = new TrafficEventBusinessTO();
		List<VitalSensor> vsCached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParameters(parameters);
		StringBuilder sb = new StringBuilder();

		
		int i = 0;
		for(VitalSensor vs:vsCached){
			//System.out.println(i);
			if(vs==null || "".equals(vs)){
				return null;
			}
			if(i==0){
				sb.append("\"");
				sb.append("$scope.markers = ");
				sb.append("{ ");
			}
			sb.append("m"+i+" : { ");
			sb.append("lat: "+vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLat()+", ");
			sb.append("lng: "+vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLong()+", ");
			sb.append("lng: "+vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLong()+", ");
			sb.append("message: "+"\'"+vs.getSsnObserves().get(0).getSensorName()+"\' "+", ");
			sb.append("focus: true, draggable: false}");
			//if(vsCached.size()<(i-1)){
			//TODO Fix to do all the loop. I cant do it with my actual machine, I am facing out of memory if continue :(
			if(10==i){
				trafficEventBusinessTO.setMarker(sb.toString()+"}"+"\"");
				return trafficEventBusinessTO;

			}else{
				sb.append(", ");	
//				sb.append("\n ");
				trafficEventBusinessTO.setMarker(sb.toString());
				i++;
			}

		}
		
		throw new RuntimeException("Service failed, try again.");
	}




	public List<CachedMarkersTO> getFirstMarkers(Map<String, String> parameters) {
		List<VitalSensor> vsCached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParameters(parameters);
		List<CachedMarkersTO> cachedMarkersTOList = new ArrayList<CachedMarkersTO>();
		
		for(VitalSensor vs:vsCached){
			CachedMarkersTO cachedMarkersTO = new CachedMarkersTO();
			if(vs==null || "".equals(vs)){
				return null;
			}
			cachedMarkersTO.setLatitude(vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLat().toString());
			cachedMarkersTO.setLongitude(vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLong().toString());
			cachedMarkersTO.setMsg(vs.getSsnObserves().get(0).getSensorName());
			cachedMarkersTOList.add(cachedMarkersTO);
		}
		
		return cachedMarkersTOList;
	}
	
	public List<CachedMarkersTO> getClusteredMarkers(Map<String, String> parameters) {
		List<VitalSensor> vsCached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParameters(parameters);
		List<CachedMarkersTO> cachedMarkersTOList = new ArrayList<CachedMarkersTO>();
		
		for(VitalSensor vs:vsCached){
			CachedMarkersTO cachedMarkersTO = new CachedMarkersTO();
			if(vs==null || "".equals(vs)){
				return null;
			}
			cachedMarkersTO.setLatitude(vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLat().toString());
			cachedMarkersTO.setLongitude(vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLong().toString());
			cachedMarkersTO.setMsg(vs.getSsnObserves().get(0).getSensorName());
			cachedMarkersTOList.add(cachedMarkersTO);
		}
		
		return cachedMarkersTOList;
	}
	
}

