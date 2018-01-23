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

	
## Messages Retention
Apache Kafka uses Log data structure to manage its messages. Log data structure is basically an ordered set of Segments whereas a Segment is a collection of messages. Apache Kafka provides retention at Segment level instead of at Message level. Hence, Kafka keeps on removing Segments from its end as these violate retention policies.

Apache Kafka provides us with the following retention policies -
  Time based Retention
  Size based Retention
  
###### Time based Retention Policy
  Under this policy, we configure the maximum time a Segment (hence messages) can live for. Once a Segment has spanned configured retention time, it is marked for deletion or compaction depending on configured cleanup policy. Default retention time for Segments is 7 days.
  
  Here are the parameters (in decreasing order of priority) that you can set in your Kafka broker properties file:

	# Configures retention time in milliseconds
	log.retention.ms=1680000

	# Used if log.retention.ms is not set
	log.retention.minutes=1680

	# Used if log.retention.minutes is not set
	log.retention.hours=168
  Apart from Kafka Broker level configuration, it is also possible to configure retention time by means of Topic level configuration using alter command. E.g.. below command can be used to set retention time as 1680 seconds for a Topic with name my-topic:

	./bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic my-topic --config retention.ms=1680000
	
	If required, it is possible to remove Topic level retention time configuration using below command -
	./bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic my-topic  --delete-config retention.ms
	
	Note: Topic level configuration will always override Broker level configurations.
	
###### Size based Retention Policy
  In this policy, we configure the maximum size of a Log data structure for a Topic partition. Once Log size reaches this size, it starts removing Segments from its end. This policy is not popular as this does not provide good visibility about message expiry. However it can come handy in a scenario where we need to control the size of a Log due to limited disk space.

  Here are the parameters that you can set in your Kafka broker properties file:

	# Configures maximum size of a Log
	log.retention.bytes=104857600
  Apart from Kafka Broker level configuration, it is also possible to configure retention size by means of Topic level configuration using alter command. E.g.. below command can be used to set retention size as 100MB for a Topic with name my-topic:

	./bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic my-topic --config retention.bytes=104857600
	
	If required, it is possible to remove Topic level retention time configuration using below command -
	./bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic my-topic  --delete-config retention.bytes
