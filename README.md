# KafkaSample
Spring and Kafka Integrations Sample App

This is the initial commit to start with exploring Kafka with Spring

Commands
==================================
###Start zookeeper

	zookeeper-server-start.bat ../../config/zookeeper.properties
	
	
###Start Kafka

	kafka-server-start.bat ../../config/server.properties

###Create topic

	kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
	
###List all the topics

	kafka-topics.bat --list --zookeeper localhost:2181
	
###Console consumer

	kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Hello-Kafka
	
		This will read only the recent message
		
	kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
	
		This will read from beginning
		
###List all the consumer groups

	kafka-consumer-groups.bat --list --bootstrap-server localhost:9092

###kafka-consumer-groups.bat --zookeeper localhost:2181 --describe --group console-consumer-33113
	
