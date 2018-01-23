# KafkaSample
Spring and Kafka Integrations Sample App

This is the initial commit to start with exploring Kafka with Spring

Commands
==================================
###### Start zookeeper

	zookeeper-server-start.bat ../../config/zookeeper.properties
	
	
###### Start Kafka

	kafka-server-start.bat ../../config/server.properties

###### Create topic

	kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
	
###### List all the topics

	kafka-topics.bat --list --zookeeper localhost:2181
	
###### Console consumer

	kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Hello-Kafka
	
		This will read only the recent message
		
	kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
	
		This will read from beginning
		
###### List all the consumer groups

	kafka-consumer-groups.bat --list --bootstrap-server localhost:9092

###### kafka-consumer-groups.bat --zookeeper localhost:2181 --describe --group console-consumer-33113

## Partitions
Apache Kafka provides the concept of Partitions in a Topic. While Topic is mainly used to categorize stream of messages, Partitions enable parallel processing of a Topic stream at consumer side. In case of multiple partitions, a consumer in a group pulls the messages from one of the Topic partitions.

However, while paritiions speed up the processing at consumer side, it violates message ordering guarantees. Hence partitions should only be used when there is no requirement of processing Topic messages in the order that these were received in. Having said that messages from a particular partition will still be in order.

        ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --topic my-topic --replication-factor 1 --partitions 2
	This will create a topic named 'my-topic' with two partitions
	
###### Adding Partitions to a Topic
./bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic my-topic --partitions 3

###### Verify/View Partitions
./bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic my-topic

	
