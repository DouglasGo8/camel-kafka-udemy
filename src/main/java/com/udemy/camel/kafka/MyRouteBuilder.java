package com.udemy.camel.kafka;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.record.Record;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author dbatista
 */
public class MyRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {


        from("timer:foo?period={{myPeriod}}&fixedRate=true&repeatCount=5")
                .bean("myBean", "hello")
                .log("${body}")
                //.bean("myBean", "bye")
                // Producer
                // default values to Serialization
                //keySerializerClass=org.apache.kafka.common.serialization.StringSerializer
                //serializerClass=org.apache.kafka.common.serialization.StringSerializer
                .setHeader(KafkaConstants.KEY, constant("Camel")) // Key of the message
                .to("kafka:myTopic?brokers=localhost:9092&requestRequiredAcks=all")
                // Same result of Consumer headers
                .process(exchange -> {
                    @SuppressWarnings("unchecked") final List<RecordMetadata> recordMetadata = exchange.getIn()
                            .getHeader(KafkaConstants.KAFKA_RECORDMETA, List.class);
                    recordMetadata.forEach(r -> out.println("---->" + r.topic()));
                })
                .end();


        // Consumer
        from("kafka:myTopic?brokers=localhost:9092&seekTo=beginning&consumersCount=2&groupId=myGroup&autoOffsetReset=earliest&breakOnFirstError=true")
                .log("Message received from Kafka : ${body}")
                .log("--- ${threadName} ---")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}")
                .end();


    }


}
