package com;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.*;

/**
 * @ProjectName: com
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class KafkaTest {

    @Test
    public void sendMessage() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        producer.send(new ProducerRecord<String, String>("test", "key", "value"));
        producer.close();
    }

    @Test
    public void consumerMessage() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test-consumer-group1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval", "100");

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        List<String> top = new ArrayList<String>();
        top.add("892c9a28-bdcc-48d1-aeb8-8d6bc9021495");
        kafkaConsumer.subscribe(top);

        ConsumerRecords s = kafkaConsumer.poll(100);
        Iterable<ConsumerRecord<String, String>> d = s.records("892c9a28-bdcc-48d1-aeb8-8d6bc9021495");
        for(ConsumerRecord<String,String> ss : d) {
            System.out.println(ss.key());
            System.out.println(ss.value());
        }

        System.out.println(s);
    }

}
