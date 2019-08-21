package com.marco.kafkatxsandbox.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return jpaTransactionManager(entityManagerFactory);
	}

	@Bean("chainedTx")
	public ChainedTransactionManager chainedTransactionManager(final ProducerFactory producerFactory, final EntityManagerFactory entityManagerFactory) {
		final KafkaTransactionManager kafkaTransactionManager = kafkaTransactionManager(producerFactory);
		final JpaTransactionManager jpaTransactionManager = jpaTransactionManager(entityManagerFactory);
		//Transactions are opent on passed order and closed in reverse order (Open 1,2,3 Closed 3,2,1)
		return new ChainedTransactionManager(kafkaTransactionManager, jpaTransactionManager);
	}

	private JpaTransactionManager jpaTransactionManager(final EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private KafkaTransactionManager kafkaTransactionManager(final ProducerFactory producerFactory) {
		final KafkaTransactionManager transactionManager = new KafkaTransactionManager(producerFactory);
		transactionManager.setNestedTransactionAllowed(true);
		return transactionManager;
	}
}
