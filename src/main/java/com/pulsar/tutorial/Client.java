package com.pulsar.tutorial;

import org.apache.pulsar.client.api.*;

public class Client {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.253.128:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("my_topic")
                .create();

        //You can then send messages to the broker and topic you specified:
        producer.send("------------------My message".getBytes());

        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-scription")
                .subscribe();

        while (true) {
            //Wait for a message
            Message msg = consumer.receive();

            try {
                //do something with the message
                System.out.println("---------------Message received: " + new String(msg.getData()));

                //Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                //Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }



    }
}
