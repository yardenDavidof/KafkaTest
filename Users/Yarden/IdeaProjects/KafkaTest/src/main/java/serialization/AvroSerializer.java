package serialization;

import entities.Animal;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by Yarden on 09/01/2017.
 */
public class AvroSerializer<T extends Animal> implements Serializer<T>, Deserializer<T>{

    private Class<T> entityClass;
    private Schema schema;
    private ByteArrayOutputStream bout;
    private DatumWriter<T> writer;
    private BinaryEncoder binEncoder;

    public AvroSerializer(){}

    public AvroSerializer(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {
//        schema = ReflectData.get().getSchema(entityClass);
        bout = new ByteArrayOutputStream();
        writer = new ReflectDatumWriter<T>(schema);
        binEncoder = EncoderFactory.get().binaryEncoder(bout, null);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {

        return null;
    }

    @Override
    public byte[] serialize(String s, T message) {
        initSchema((Class<T>) message.getClass()); // TODO - move to configure !

        try {
            writer.write(message, binEncoder);
            binEncoder.flush();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        return bout.toByteArray();
    }

    private void initSchema(Class<T> entityClass){
        if (schema == null) {
            schema = ReflectData.get().getSchema(entityClass);
            this.entityClass = entityClass;
        }

    }

    @Override
    public void close() {
        schema = null;

    }

    private static <V> byte[] toBytesGeneric(final V v, final Class<V> cls) {
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final Schema schema = ReflectData.get().getSchema(cls);
        final DatumWriter<V> writer = new ReflectDatumWriter<V>(schema);
        final BinaryEncoder binEncoder = EncoderFactory.get().binaryEncoder(bout, null);
        try {
            writer.write(v, binEncoder);
            binEncoder.flush();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }


        return bout.toByteArray();
    }
}
