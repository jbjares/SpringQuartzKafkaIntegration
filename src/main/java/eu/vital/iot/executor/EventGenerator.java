package eu.vital.iot.executor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import eu.vital.iot.entity.document.VitalSensor;
import eu.vital.iot.executor.kafka.KafkaEvents;

@Component("eventGenerator")
public class EventGenerator {
	
	@Autowired
	private KafkaEvents kafkaEvents;

    public void doStuff() throws ExecutionException, InterruptedException {
    	String result = UUID.randomUUID().toString();
    	System.out.println(result);
    	kafkaEvents.send(result);
    }
    
   

}
