package com.marco.kafkatxsandbox.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KafkaTxTest {

    private static final Logger logger =  LoggerFactory.getLogger(KafkaTxTest.class);

    private KafkaProducer kafkaProducer;

    @Autowired
    public KafkaTxTest(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Transactional("chainedTx")
    public void produceTransactedMessageAndForce5ToThrowException(String message){
        kafkaProducer.publish(String.valueOf(message));
        if(message.equals("5")){
            logger.info("5 is not allowed, and transaction should be rollbacked");
            throw new RuntimeException("5 is not allowed, and transaction should be rollbacked");
        }
    }

}
