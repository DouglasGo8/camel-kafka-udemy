package com.udemy.camel.kafka;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

/**
 * @author dbatista
 */
public class MyRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {


        /*from("timer:foo?period={{myPeriod}}&fixedRate=true&repeatCount=5")
                .bean("myBean", "hello")
                .log("${body}")
                //.bean("myBean", "bye")
                //.setHeader(KafkaConstants.KEY, constant("Camel")) // Key of the message
                // Producer
                .to("kafka:PrimaryTopic?brokers=localhost:9092&requestRequiredAcks=all")
                .end();*/


        // Consumer
        from("kafka:PrimaryTopic?brokers=localhost:9092&seekTo=beginning&consumersCount=2&groupId=myIdGroupOne")
                .log("Message received from Kafka : ${body}")
                .log("--- ${threadName} ---")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}")
                .end();


    }


}
