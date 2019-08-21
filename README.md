# Testing Kafka transactional

### Start Kafka

`bin/zookeeper-server-start.sh config/zookeeper.properties`

`bin/kafka-server-start.sh config/server.properties`

### Create topic

`bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic kafka-tx-topic`

### Start services:

1. Start `KafkaTxProducerApplication` to produce messages.
1. Check console output.
1. Start `KafkaTxConsumerApplication` to consume messages.
1. Check console output: only messages sent over commited transactions should be consumed (due to `isolation.level: read_committed` consumer configuration)