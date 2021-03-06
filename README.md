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
<cite>
requestRequiredAcks - camel Property
 acks = 0 If set to zero then the producer will not wait for any acknowledgment from the server at all
 acks = 1 This will mean the leader will write the record to its local log but will respond without awaiting
          full acknowledgement from all followers. In this case should the leader fail immediately after
          acknowledging the record but before the followers have replicated it then the record will be lost
 acks =   all This means the leader will wait for the full set of in-sync replicas to acknowledge the record.
         This guarantees that the record will not be lost as long as at least one in-sync replica remains alive.
         This is the strongest available guarante
</cite>
 ---
<code>bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic twitter_tweets --partitions 6 --replication-factor 1</code>

 <table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Default</th>
        <th>Type</th>
    </tr>
    <tr>
        <td>additionalProperties(common)</td>
        <td rowspan="2">Sets additional properties for either kafka consumer or kafka producer in case they can’t be set directly on the camel configurations (e.g: new Kafka properties that are not reflected yet in Camel configurations), the properties have to be prefixed with additionalProperties.. E.g: additionalProperties.transactional.id=12345&additionalProperties.schema.registry.url=\http://localhost:8811/avro</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>retries (producer)</td>
        <td>Setting a value greater than zero will cause the client to resend any record whose send fails with a potentially transient error. Note that this retry is no different than if the client resent the record upon receiving the error. Allowing retries will potentially change the ordering of records because if two records are sent to a single partition, and the first fails and is retried but the second succeeds, then the second record may appear first.</td>
        <td>0</td>
        <td>Integer</td>
    </tr>
    <tr>
        <td>enableIdempotence (producer)</td>
        <td>If set to 'true' the producer will ensure that exactly one copy of each message is written in the stream. If 'false', producer retries may write duplicates of the retried message in the stream. If set to true this option will require max.in.flight.requests.per.connection to be set to 1 and retries cannot be zero and additionally acks must be set to 'all'.</td>
        <td>false</td>
        <td>boolean</td>
    </tr>
    <tr>
        <td>compressionCodec (producer)</td>
        <td>This parameter allows you to specify the compression codec for all data generated by this producer. Valid values are none, gzip and snappy. The value can be one of: none, gzip, snappy, lz4</td>
        <td>none</td>
        <td>String</td>
    </tr>
 </table>