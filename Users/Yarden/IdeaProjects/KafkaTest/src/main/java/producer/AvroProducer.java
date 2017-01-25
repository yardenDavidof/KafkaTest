package producer;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import entities.Mammal;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import kafka.consumer.Consumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import utils.EntitiesFactory;

import java.util.Properties;

/**
 * Created by Yarden on 09/01/2017.
 */
public class AvroProducer {
    public static void main(String[] args){


        final String SCHEMA_URL = "src\\main\\resources\\AvroSchemas\\";
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", SCHEMA_URL);

        String topic = "fast-messages";
        int wait = 500;

        Producer<String, Mammal> producer = new KafkaProducer<>(props);

        for(int i = 0; i < 4; i++)
        {
            Mammal mammal = EntitiesFactory.createRandomMammal();
            System.out.println("Generated customer " + mammal.toString());
            ProducerRecord<String, Mammal> record =
                    new ProducerRecord<>(topic, mammal.getName(), mammal);
            producer.send(record);
        }
    }

}
