package uk.sk.kafka.messaging.KafkaSample.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@Value(value = "${kafka.groupid}") private String groupId;
	
	@KafkaListener(topics = "Hello-Kafka", group = "HE_S")
	public void listen(String message) {
	    System.out.println("Received Messasge in group foo: " + message);
	}
	
}
