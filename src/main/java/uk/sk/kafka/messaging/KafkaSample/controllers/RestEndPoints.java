package uk.sk.kafka.messaging.KafkaSample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndPoints {
	@Autowired private KafkaTemplate<String, String> template;
	
	@RequestMapping(method= {RequestMethod.POST})
	public ResponseEntity<?> publish(@RequestParam(value="val") String value) {
		template.send("Hello-Kafka", value);
		return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
	}
}