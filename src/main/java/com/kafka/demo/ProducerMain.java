package com.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建KafkaProducer对象
        Producer<String, String> producer = createProducer();

        //创建消息，传入的三个参数分别是Topic,消息的key，消息的message
        ProducerRecord<String, String> message = new ProducerRecord<>("TestTopic", "key", "ycyoes");

        //同步发送消息
        Future<RecordMetadata> sendResultFuture = producer.send(message);
        RecordMetadata result = sendResultFuture.get();
        System.out.println("message sent to " + result.topic() + ", partition " + result.partition() + ", offset " + result.offset());
    }

    private static Producer<String, String> createProducer() {
        //设置Producer属性
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.253.129:9092");    //设置Broker的地址
        properties.put("acks", "1");    //0-不应答，1-leader应答 all-所有leader和follower应答
        properties.put("retries", 3);   //发送失败时，重试发送的次数
        properties.put("key.serializer", StringSerializer.class.getName()); //消息的key的序列化方式
        properties.put("value.serializer", StringSerializer.class.getName());   //消息的value的序列化方式

        //创建KafkaProducer对象
        return new KafkaProducer<String, String>(properties);
    }
}
