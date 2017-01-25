package producer;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yarden on 03/01/2017.
 */
public class ProducerMain {
    public static void main(String[] args) throws IOException {
//        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
//        StringProducer producerThread = new StringProducer(KafkaProperties.TOPIC, isAsync);
//        producerThread.start();

        // set up the producer
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }

        try {
            for (int i = 0; i < 1000; i++) {
                // send lots of messages
                producer.send(new ProducerRecord<String, String>(
                        "fast-messages",
                        "yardend" + i));
                System.out.println("sent message yardend" + i);

//                producer.send(new ProducerRecord<String, String>(
//                        "fast-messages",
//                        String.format("{\"type\":\"test\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));

                // every so often send to a different topic
//                if (i % 1000 == 0) {
//                    producer.send(new ProducerRecord<String, String>(
//                            "fast-messages",
//                            String.format("{\"type\":\"marker\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
//                    producer.send(new ProducerRecord<String, String>(
//                            "summary-markers",
//                            String.format("{\"type\":\"other\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
//                    producer.flush();
//                    System.out.println("Sent msg number " + i);
//                }
            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }


    }
}
