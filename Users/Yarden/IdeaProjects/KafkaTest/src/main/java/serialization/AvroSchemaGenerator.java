package serialization;

import entities.Mammal;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import utils.EntitiesFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yarden on 09/01/2017.
 */
public class AvroSchemaGenerator {

    public static void main(String[] args) {

        final String SCHEMA_DIR = "src\\main\\resources\\AvroSchemas";

        // one argument: a file name
        File file = new File(SCHEMA_DIR + "\\mammal.txt");
        // get the reflected schema for packets
        Schema schema = ReflectData.get().getSchema(Mammal.class);
        try {
            // create a file of Mammal
            DatumWriter<Mammal> writer = new ReflectDatumWriter<Mammal>(Mammal.class);
            DataFileWriter<Mammal> out = new DataFileWriter<Mammal>(writer)
                    .setCodec(CodecFactory.deflateCodec(9))
                    .create(schema, file);

            // write mammals to the file, odds with null timestamp
            for (int i = 0; i < 3; i++) {
                out.append(EntitiesFactory.createRandomMammal());
            }

            // close the output file
            out.close();


            // open a file of packets
            DatumReader<Mammal> reader = new ReflectDatumReader<Mammal>(Mammal.class);
            DataFileReader<Mammal> in = new DataFileReader<Mammal>(file, reader);

            // read 100 packets from the file & print them as JSON
            for (Mammal mammal : in) {
                System.out.println(ReflectData.get().toString(mammal));
            }

            // close the input file
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
