package consumer;

import com.google.common.io.Resources;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import serialization.JacksonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Yarden on 03/01/2017.
 */
public class ConsumerJacksonMain {
    public static void main(String[] args) throws IOException {


//        System.out.println(KafkaAvroSerializer.class.getName());
        // and the consumer
        KafkaConsumer<String, Object> consumer;
        try (InputStream props = Resources.getResource("consumerJackson.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            properties.put("deserializer.class" , JacksonSerializer.class.getName());
            properties.put("key.deserializer" , JacksonSerializer.class.getName());
            properties.put("value.deserializer" , JacksonSerializer.class.getName());

            if (properties.getProperty("group.id") == null) {
                properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
            }
            consumer = new KafkaConsumer<>(properties);
        }
        consumer.subscribe(Arrays.asList("fast-messages", "summary-markers"));

        while (true) {
            ConsumerRecords<String, Object> records = consumer.poll(200);
            for (ConsumerRecord<String, Object> record : records) {

                System.out.println("message recieved: " + record.value());
            }
        }

    }
}
