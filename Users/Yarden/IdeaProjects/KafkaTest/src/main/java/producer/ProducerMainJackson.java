package producer;

import com.google.common.io.Resources;
import entities.Mammal;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import serialization.JacksonSerializer;
import utils.EntitiesFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yarden on 03/01/2017.
 */
public class ProducerMainJackson {
    public static void main(String[] args) throws IOException {

        // set up the producer
        KafkaProducer<String, Object> producer;
        try (InputStream props = Resources.getResource("producerJackson.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            properties.put("key.serializer", JacksonSerializer.class.getName());
            properties.put("value.serializer", JacksonSerializer.class.getName());
            producer = new KafkaProducer<>(properties);
        }

        try {
            for (int i = 0; i < 1000; i++) {

//                javafx.util.Pair<Integer,String> p = new javafx.util.Pair<>(i,"yardend");
                Mammal mammal = EntitiesFactory.createRandomMammal();

                // send lots of messages
                producer.send(new ProducerRecord<String, Object>(
                        "fast-messages",
                        mammal));
                System.out.println("sent message " + mammal);

            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }


    }
}
