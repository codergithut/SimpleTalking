package kafka;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import com.tianjian.kafka.bean.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

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



public class ProducerDemo {

    private final KafkaProducer<String, Message> producer;

    public final static String TOPIC = "test5";

    private ProducerDemo() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");//xxx服务器ip
        //props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
        props.put("retries", 0);//retries = MAX 无限重试，直到你意识到出现了问题:)
        props.put("batch.size", 16384);//producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        //batch.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        props.put("linger.ms", 1);//延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        props.put("buffer.memory", 33554432);//producer可以用来缓存数据的内存大小。
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");

        producer = new KafkaProducer<String, Message>(props);
    }

    public void produce() {
        int messageNo = 1;
        final int COUNT = 5;

        while(messageNo < COUNT) {
            Message message = new Message();
            message.setId(1l + messageNo);
            message.setMsg("49f450fb6dc687fc42e22678089f9663");
            message.setSendTime(new Date());
            try {
                producer.send(new ProducerRecord<String, Message>(TOPIC, message));
            } catch (Exception e) {
                e.printStackTrace();
            }

            messageNo++;
        }

        producer.close();
    }

    public static void main(String[] args) {
        new ProducerDemo().produce();
    }
}

