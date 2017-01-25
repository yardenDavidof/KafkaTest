package multithreaded;

/**
 * Created by Yarden on 25/01/2017.
 */
public class Main {
    public static void main(String[] args) {
        String zooKeeper = "localhost:2181";
        String groupId = "group1";
        String topic = "topic1,topic2,topic3";
        int threads = 3;

        ConsumerReader example = new ConsumerReader(zooKeeper, groupId, topic);
        example.run(threads);
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ie) {
//
//        }
//        example.shutdown();
    }
}
