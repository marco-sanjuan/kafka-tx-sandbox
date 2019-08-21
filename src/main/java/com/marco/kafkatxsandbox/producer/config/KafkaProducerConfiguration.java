package com.marco.kafkatxsandbox.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.producer.client-id}")
	private String clientId;
	
	@Value("${spring.kafka.producer.transactionId-preffix}")
	private String transactionIdPreffix;
	
	@Bean
	public DefaultKafkaProducerFactory producerFactory() {
		final DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory(producerConfigurations());
		producerFactory.setTransactionIdPrefix(transactionIdPreffix);
		return producerFactory;
	}

	private Map<String,Object> producerConfigurations() {
		final Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
		return props;
	}
}