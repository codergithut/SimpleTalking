package kafka;

/**
 * @ProjectName: com.kafka
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/8
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/8
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
import java.util.Arrays;
import java.util.Properties;

import com.tianjian.kafka.bean.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class UserKafkaConsumer extends Thread {

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");//xxx是服务器集群的ip
        properties.put("group.id", "test-consumer-group1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //properties.put("interceptor.classes", "com.tianjian.kafka.bean.Message.class");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("37a121df05cb9780c10cd84baa94c43a"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("-----------------");
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();
            }
        }

    }
}

