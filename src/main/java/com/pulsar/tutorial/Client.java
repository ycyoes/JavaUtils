package com.pulsar.tutorial;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class Client {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.91.131:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("my_topic")
                .create();

        //You can then send messages to the broker and topic you specified:
        producer.send("My message".getBytes());



    }
}
