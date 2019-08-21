package com.marco.kafkatxsandbox.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	private static final Logger logger =  LoggerFactory.getLogger(KafkaProducer.class);

	private final KafkaTemplate<String,String> kafkaTemplate;

	@Autowired
	public KafkaProducer(final KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publish(final String message) {
		kafkaTemplate.sendDefault(message);
		logger.info("{} message sent.", message);
	}
}
