package multithreaded;

import com.google.common.io.Resources;
import entities.Mammal;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.BytesSerializer;
import serialization.JacksonSerializer;
import utils.EntitiesFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yarden on 25/01/2017.
 */
public class ProducerByte {

    public static void main(String[] args) throws IOException {

        // set up the producer
        KafkaProducer<byte[], byte[]> producer;
        try (InputStream props = Resources.getResource("producerJackson.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            properties.put("key.serializer", ByteArraySerializer.class.getName());
            properties.put("value.serializer", ByteArraySerializer.class.getName());
            producer = new KafkaProducer<>(properties);
        }

        try {
            for (int i = 0; i < 10; i++) {

//                javafx.util.Pair<Integer,String> p = new javafx.util.Pair<>(i,"yardend");
//                Mammal mammal = EntitiesFactory.createRandomMammal();
                String mammal = "mammal" + i;
                String topic = "topic" + (( i % 3 ) + 1);
                // send lots of messages
                producer.send(new ProducerRecord<byte[], byte[]>(
                        topic,
                        mammal.getBytes()));
                System.out.println("sent message " + mammal.getBytes() + " to topic " + topic);

            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }


    }
}
