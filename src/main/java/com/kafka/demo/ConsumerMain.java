package com.kafka.demo;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerMain {

    public static void main(String[] args) {
        Consumer<String, String> consumer = createConsumer();
        consumer.subscribe(Collections.singleton("TestTopic"));
        while (true) {
            ConsumerRecords records = consumer.poll(Duration.ofSeconds(1000));
            if (!records.isEmpty()) {
                records.forEach(new java.util.function.Consumer<ConsumerRecord>() {
                    @Override
                    public void accept(ConsumerRecord record) {
                        System.out.println(record.key() + "\t" + record.value());
                    }
                });
            } else {
                System.out.println("consumer - no record");
            }

        }
    }
    private static Consumer<String, String> createConsumer() {
        //设置Producer属性
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.253.129:9092");    //设置Broker的地址
        properties.put("group.id", "demo-consumer-group");    //消费者分组
        properties.put("auto.offset.reset", "earliest");    //设置消费者分组最初的消费进度为earliest
        properties.put("enable.auto.commit", true);   //是否自动提交消费进度
        properties.put("auto.commit.interval.ms", "1000");   //自动提交消费进度频率
        properties.put("key.deserializer", StringDeserializer.class.getName()); //消息的key的反序列化方式
        properties.put("value.deserializer", StringDeserializer.class.getName());   //消息的value的反序列化方式

        //创建KafkaProducer对象
        return new KafkaConsumer<String, String>(properties);
    }
}
