package eu.vital.iot.executor.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("kafkaEvents")
@Configuration
public class KafkaEvents {

	@Value("localhost:9092")
	private String brokerList;

	@Value("events")
	private String topic;

	private Producer<String, String> producer;

	public KafkaEvents() {
	}

	//PostConstruct
	public void initIt() {
		Properties kafkaProps = new Properties();

		kafkaProps.put("bootstrap.servers", brokerList);

		kafkaProps.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("acks", "1");

		kafkaProps.put("retries", "1");
		kafkaProps.put("linger.ms", 5);

		producer = new KafkaProducer<>(kafkaProps);

	}

	public void send(String value) throws ExecutionException,
			InterruptedException {
		sendSync(value);
	}

	private void sendSync(String value) throws ExecutionException,
			InterruptedException {
		ProducerRecord<String, String> record = new ProducerRecord<>(topic,
				value);
		producer.send(record).get();

	}

}
