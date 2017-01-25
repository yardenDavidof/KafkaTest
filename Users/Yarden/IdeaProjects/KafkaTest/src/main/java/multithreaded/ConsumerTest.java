package multithreaded;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * Created by Yarden on 25/01/2017.
 */
public class ConsumerTest implements Runnable {

    private KafkaStream m_stream;
    private int m_threadNumber;
    private String topic;

    public ConsumerTest(KafkaStream a_stream, int a_threadNumber, String topic) {
        m_threadNumber = a_threadNumber;
        m_stream = a_stream;
        this.topic = topic;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext() || true)
            System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()) + " from topic: " + topic);
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}
