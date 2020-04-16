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


        from("timer:foo?period={{myPeriod}}&fixedRate=true")
                //.bean("myBean", "hello")
               .bean(MySimulatorTwitterBean::new)
                .log("${body}")
                // -------------- Producer -------------------
                // ---- default values to Serialization ----
                // keySerializerClass=org.apache.kafka.common.serialization.StringSerializer
                // serializerClass=org.apache.kafka.common.serialization.StringSerializer
                //.setHeader(KafkaConstants.KEY, constant("camel-kafka-tweet-KEY")) // Key of the message

                .to("kafka:twitter_tweets?brokers=localhost:9092&requestRequiredAcks=0&lingerMs=20&compressionCodec=snappy")
                // -------- Same result of Consumer headers --------
                //.process(exchange -> {
                //    @SuppressWarnings("unchecked") final List<RecordMetadata> recordMetadata = exchange.getIn()
                //            .getHeader(KafkaConstants.KAFKA_RECORDMETA, List.class);
                //    recordMetadata.forEach(r -> out.println("---->" + r.topic()));
                //})
                .end();


        // Consumer
        from("kafka:twitter_tweets?brokers=localhost:9092&seekTo=beginning&consumersCount=6&groupId=twitterTweetsGroup&autoOffsetReset=earliest&breakOnFirstError=true")
                .log("Message received from Kafka : ${body}")
                .log("--- ${threadName} ---")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}")
                .end();


    }


}
