package eu.vital.iot.executor;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.vital.iot.business.TrafficEventService;
import eu.vital.iot.executor.kafka.KafkaEvents;

@Component("eventGenerator")
public class EventGenerator {

	private static final int _600 = 600;

	@Autowired
	private KafkaEvents kafkaEvents;

	@Inject
	private TrafficEventService trafficEventBusiness;

	public void doStuff() throws Exception {


//		Map< String, String> parameters = new HashMap< String, String>();
//		parameters.put("type", "vital:VitalSensor");
//		parameters.put("status", "vital:Running");
//		List<VitalSensor> vsList = trafficEventBusiness.getTrafficInformation(parameters);
//		int i = 1;
//    	for(VitalSensor vs:vsList){
//        	StringBuilder sb = new StringBuilder();
//    		try{
//        	if(vs==null || 
//        			vs==null || 
//        			vs.getSsnObserves().get(0)==null || 
//        			vs.getSsnObserves().get(0).getObservationList().get(0)==null ||
//        		    vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation()==null){
//        		continue;
//        	}
//
//        	}catch(Exception e){
//        		continue;
//        	}
//        	sb.append("{m"+i+": {");
//        	sb.append("lat: "+vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLat()+",");
//        	sb.append("lng: "+vs.getSsnObserves().get(0).getObservationList().get(0).getDulHasLocation().getGeoLong()+",");
//        	sb.append("message:"+vs.getSsnObserves().get(0).getSensorName()+",");
//        	sb.append("focus: true,");
//        	sb.append("draggable: false");
//        	sb.append("}");
//        	sb.append("}");
//        	i++;
//        	Gson gson = new Gson();
//        	System.out.println(gson.toJson(sb.toString()));
//        	kafkaEvents.send(gson.toJson(sb.toString()));
//    	}
//    	//sb.append(TEMPLATE);

    }

	private static final String TEMPLATE = "                Istanbul: {"
			+ "                    lat: 41.015137,"
			+ "                    lng: 28.979530,"
			+ "                    zoom: 10"
			+ "                },"
			+ "                defaults: {"
			+ "                    minZoom: 1,"
			+ "                    maxZoom: 15,"
			+ "                    scrollWheelZoom: true"
			+ "                },"
			+ "                layers: {"
			+ "                    baselayers: {"
			+ "                        osm: {"
			+ "                            name: 'OpenStreetMap',"
			+ "                            type: 'xyz',"
			+ "                            url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',"
			+ "                            layerOptions: {"
			+ "                                subdomains: ['a', 'b', 'c'],"
			+ "                                attribution: '&copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',"
			+ "                                continuousWorld: true"
			+ "                            }"
			+ "                        }"
			+ "                    }"
			+ "                    ,"
			+ "                    overlays: {"
			+ "                        sensors: {"
			+ "                            name: 'Sensors',"
			+ "                            type: 'markercluster',"
			+ "                            visible: true,"
			+ "                            'layerParams': {},"
			+ "                            'layerOptions': {'disableClusteringAtZoom': 10}"
			+ "                        }" + "                    }"
			+ "                }," + "                events: {"
			+ "                    map: {"
			+ "                        enable: ['click', 'popupopen'],"
			+ "                        logic: 'emit'"
			+ "                    }" + "                }"
			+ "            }";

}
