Camel Main
==========

This example shows how to run Camel standalone via the built-in Main class.

The example also demonstrates how you can configure the Camel application
via Camel built-in dependency-injection that supports binding via the
`@BindToRegistry`, `@BeanInject` and `@PropertyInject` annotations.

Also notice how you can configure Camel in the `application.properties` file.

=== How to run

You can run this example using

    mvn camel:run
    mvn clean package -DskipTests camel:run

=== More information

You can find more information about Apache Camel at the website: http://camel.apache.org/


# Kafka commands

zookeeper-server-start.bat E:\apache\kafka_2.12-2.3.0\config\zookeeper.properties
kafka-server-start.bat E:\apache\kafka_2.12-2.3.0\config\server.properties


# throws error
kafka-topics.bat --bootstrap-server localhost:2181 --list

# works
kafka-topics.bat --zookeeper localhost:2181 --list

kafka-topics.bat --zookeeper localhost:2181 --topic PrimaryTopic --create --partitions 3 --replication-factor 1
kafka-topics.bat --zookeeper localhost:2181 --topic SecondTopic --create --partitions 6 --replication-factor 1

kafka-topics.bat --zookeeper localhost:2181 --topic MyTopic --describe

# bug on Windows (Clean Zookeeper all folders)
kafka-topics.bat --zookeeper localhost:2181 --topic MyTopic --delete


kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list

kafka-consumer-groups.bat --bootstrap-server localhost:9092 --describe --group myIdGroupOne

kafka-consumer-groups.bat


# Kafka annotations

requestRequiredAcks - camel Property
 acks = 0 If set to zero then the producer will not wait for any acknowledgment from the server at all
 acks = 1 This will mean the leader will write the record to its local log but will respond without awaiting
          full acknowledgement from all followers. In this case should the leader fail immediately after
          acknowledging the record but before the followers have replicated it then the record will be lost
 acks =   all This means the leader will wait for the full set of in-sync replicas to acknowledge the record.
         This guarantees that the record will not be lost as long as at least one in-sync replica remains alive.
         This is the strongest available guarante