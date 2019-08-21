package com.marco.kafkatxsandbox.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	private static final Logger logger =  LoggerFactory.getLogger(KafkaConsumer.class);

	public KafkaConsumer() {
	}

	@KafkaListener(groupId = "${spring.kafka.template.groupId}", topics = "${spring.kafka.template.default-topic}")
	public void listen(final String record) {
		logger.info("Received event: {}", record);
	}
}
