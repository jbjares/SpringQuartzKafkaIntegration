package eu.vital.iot.executor;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
