package consumer;

import entities.Mammal;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by Yarden on 09/01/2017.
 */
public class AvroConsumer {

    public static void main(String[] args){

        Logger logger = Logger.getLogger(AvroConsumer.class);

        final String SCHEMA_URL = "src\\main\\resources\\AvroSchemas\\";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "CountryCounter");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", KafkaAvroDeserializer.class.getName());
        props.put("schema.registry.url", SCHEMA_URL);

        String topic = "fast-messages";

        KafkaConsumer<String, Mammal> consumer = new KafkaConsumer(props);
        consumer.subscribe(Collections.singletonList(topic));

        logger.info("start reading topic " + topic);

        while (true) {
            ConsumerRecords<String, Mammal> records = consumer.poll(1000);

            for (ConsumerRecord<String, Mammal> record: records) {
                logger.info("message recieved : " + record.value());
            }
            consumer.commitSync();
        }

    }
}
