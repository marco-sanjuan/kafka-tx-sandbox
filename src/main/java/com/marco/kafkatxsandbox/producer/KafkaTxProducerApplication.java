package com.marco.kafkatxsandbox.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class KafkaTxProducerApplication implements CommandLineRunner {

    @Autowired
    private KafkaTxTest kafkaTxTest;

    public static void main(String[] args) {
        SpringApplication.run(KafkaTxProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IntStream.rangeClosed(1,10).forEach(i -> {
                try {
                    kafkaTxTest.produceTransactedMessageAndForce5ToThrowException(String.valueOf(i));
                } catch (RuntimeException e) {
                }
            }
        );
    }
}
