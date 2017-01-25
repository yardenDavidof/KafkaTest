package multithreaded;

import com.sun.org.apache.xpath.internal.SourceTree;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yarden on 25/01/2017.
 */
public class ConsumerReader {


    private final ConsumerConnector consumer;
    private final List<String> topics;
    private ExecutorService executor;

    public ConsumerReader(String a_zookeeper, String a_groupId, String a_topics) {
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper,a_groupId));
        this.topics = Arrays.asList(a_topics.split(","));
    }


    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
        try {
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted during shutdown, exiting uncleanly");
        }
    }

    public void run(int a_numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topics.stream().forEach(topic->{
            topicCountMap.put(topic, topicCountMap.size() + 2);
            System.out.println("topic " + topic + " with " + (int)(topicCountMap.size() + 2) + "threads");
        });
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);

        topics.stream().forEach(topic->{
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

            // now launch all the threads
            //
            executor = Executors.newFixedThreadPool(topicCountMap.get(topic));

            // now create an object to consume the messages
            //
            int threadNumber = 0;
            for (final KafkaStream stream : streams) {
                System.out.println("submit thread " + threadNumber + " of topic " + topic);
                executor.submit(new ConsumerTest(stream, threadNumber,topic));
                threadNumber++;
            }
        });


    }

    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");

        return new ConsumerConfig(props);
    }
}
