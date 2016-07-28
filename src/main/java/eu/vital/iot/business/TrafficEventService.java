package eu.vital.iot.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import eu.vital.iot.business.to.CachedMarkersTO;
import eu.vital.iot.business.to.ClusteredMarkersTO;
import eu.vital.iot.dao.mongo.VitalSensorMongoDAO;
import eu.vital.iot.entity.document.VitalSensor;

@Service
public class TrafficEventService {

	@Inject
	private VitalSensorMongoDAO vitalSensorMongoDAO;


	@SuppressWarnings("serial")
	public List<ClusteredMarkersTO> getMarkersGroupedByRoad() {
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("type", "vital:VitalSensor");
		//parameters.put("status", "vital:Running");        	
		Map<String,List<VitalSensor>> cached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParametersGroupByStreet(parameters);
		List<ClusteredMarkersTO> clusteredMarkersTOList = new ArrayList<ClusteredMarkersTO>(); 
		for(Map.Entry<String,List<VitalSensor>> entry:cached.entrySet()){
				ClusteredMarkersTO clusteredMarkersTO = new ClusteredMarkersTO();
				clusteredMarkersTO.setCount(entry.getValue().size());
				clusteredMarkersTO.setLat(entry.getValue().get(0).getTrafficEventBusiness().getLat());
				clusteredMarkersTO.setLon(entry.getValue().get(0).getTrafficEventBusiness().getLon());
				clusteredMarkersTO.setStreetName(entry.getValue().get(0).getTrafficEventBusiness().getStreetName());
				clusteredMarkersTO.setSpeedAvg(this.setSpeedAvg(entry.getValue()));
				clusteredMarkersTOList.add(clusteredMarkersTO);
			
		}
		return clusteredMarkersTOList;


	}
	
	@SuppressWarnings("serial")
	public List<CachedMarkersTO> getClusterMarkersGroupedByRoad() {
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("type", "vital:VitalSensor");
		//parameters.put("status", "vital:Running");        	
		Map<String,List<VitalSensor>> cached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParametersGroupByStreet(parameters);
		List<CachedMarkersTO> cachedMarkersTOList = new ArrayList<CachedMarkersTO>(); 
		Map<String,Integer> occurrecesMap = new HashMap<String,Integer>();
		for(Map.Entry<String,List<VitalSensor>> entry:cached.entrySet()){
			for(VitalSensor vs: entry.getValue()){
				CachedMarkersTO cachedMarkersTO = new CachedMarkersTO();
				String lat = vs.getTrafficEventBusiness().getLat();
				String lon = vs.getTrafficEventBusiness().getLon();
				String latlonKey = lat+";"+lon;
				if(!occurrecesMap.containsKey(latlonKey)){
					occurrecesMap.put(latlonKey,1);
				}
				if(occurrecesMap.containsKey(latlonKey)){
					int occur = occurrecesMap.get(latlonKey);
					occurrecesMap.put(latlonKey, occur+1);
				}
				
				cachedMarkersTO.setLat(vs.getTrafficEventBusiness().getLat());
				cachedMarkersTO.setLon(vs.getTrafficEventBusiness().getLon());
				cachedMarkersTO.setStreetName(vs.getTrafficEventBusiness().getStreetName());
				cachedMarkersTOList.add(cachedMarkersTO);
			
			}
		}
		return cachedMarkersTOList;


	}
	
	
	public String setSpeedAvg(List<VitalSensor> vsList) {
		int sum=0;
		for(VitalSensor vs:vsList){
			sum+=new Double(vs.getSsnObserves().get(0).getObservationList().get(0).getSsnObservationResult().getSsnHasValue().getValue());
		}
		return new Double(sum/(double)vsList.size()).toString();
	}


	public List<CachedMarkersTO> getFirstMarkers(Map<String, String> parameters) {
		List<VitalSensor> vsCached = vitalSensorMongoDAO
				.findCachedVitalSensorListByGenericParameters(parameters);
		List<CachedMarkersTO> cachedMarkersTOList = new ArrayList<CachedMarkersTO>();

		for (VitalSensor vs : vsCached) {
			CachedMarkersTO cachedMarkersTO = new CachedMarkersTO();
			if (vs == null || "".equals(vs)) {
				return null;
			}
			cachedMarkersTO.setLat(vs.getSsnObserves().get(0)
					.getObservationList().get(0).getDulHasLocation()
					.getGeoLat().toString());
			cachedMarkersTO.setLon(vs.getSsnObserves().get(0)
					.getObservationList().get(0).getDulHasLocation()
					.getGeoLong().toString());
			cachedMarkersTO.setSpeed(vs.getSsnObserves().get(0).getObservationList().get(0).getSsnObservationResult().getSsnHasValue().getValue().toString());
			cachedMarkersTO.setOcurrences(1);
			cachedMarkersTOList.add(cachedMarkersTO);
		}

		return cachedMarkersTOList;
	}

//	public List<CachedMarkersTO> getClusteredMarkers() {
//
//		Map<String,String> parameters = new HashMap<String,String>();
//		parameters.put("type", "vital:VitalSensor");
//		//parameters.put("status", "vital:Running");        	
//		Map<String,List<VitalSensor>> cached = vitalSensorMongoDAO.findCachedVitalSensorListByGenericParametersGroupByStreet(parameters);
//		List<CachedMarkersTO> cachedMarkersTOList = new ArrayList<CachedMarkersTO>();
//		for(Map.Entry<String,List<VitalSensor>> entry:cached.entrySet()){
////			System.out.println(entry.getKey());
////			System.out.println(entry.getValue());
////			System.out.println(entry.getValue().size());
////			System.out.println("\n");
//			for(VitalSensor vs:entry.getValue()){
//				CachedMarkersTO cachedMarkersTO = new CachedMarkersTO();
//				if (vs == null || "".equals(vs)) {
//					return null;
//				}
//				cachedMarkersTO.setLatitude(vs.getSsnObserves().get(0)
//						.getObservationList().get(0).getDulHasLocation()
//						.getGeoLat().toString());
//				cachedMarkersTO.setLongitude(vs.getSsnObserves().get(0)
//						.getObservationList().get(0).getDulHasLocation()
//						.getGeoLong().toString());
//				cachedMarkersTO.setMsg(vs.getSsnObserves().get(0).getSensorName());
//				cachedMarkersTO.setSpeed(vs.getSsnObserves().get(0).getObservationList().get(0).getSsnObservationResult().getSsnHasValue().getValue().toString());
//				cachedMarkersTOList.add(cachedMarkersTO);
//			}
//		}
//
//		return cachedMarkersTOList;
//		
//		
//	}

}
