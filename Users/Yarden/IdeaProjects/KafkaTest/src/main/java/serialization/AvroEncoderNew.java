package serialization;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yarden on 25/01/2017.
 */
public class AvroEncoderNew {

    public static <T> byte[] toBinary(T entity) {
        Schema schema = ReflectData.get().getSchema(entity.getClass());
        DatumWriter<T> writer = new SpecificDatumWriter<T>(schema);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        try {
            writer.write(entity, encoder);
            encoder.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static <T> T fromBinary(byte[] bytes) {
        Schema schema = null;
        try {
            // TODO- get schemas from some dir
            schema = new Schema.Parser().parse(new File("src/test_schema.avsc"));
            DatumReader<T> reader = new SpecificDatumReader<T>(schema);
            Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
            T entity = null;
            entity = reader.read(null, decoder);
            return entity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
