package serialization.decoders;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Yarden on 04/01/2017.
 */
public class JacksonDecoder implements Decoder<Object>{
    private static final Logger LOGGER = Logger.getLogger(JacksonDecoder.class);
    public JacksonDecoder(VerifiableProperties verifiableProperties) {
        /* This constructor must be present for successful compile. */
    }

    @Override
    public Object fromBytes(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(bytes, Object.class);
        } catch (IOException e) {
            LOGGER.error(String.format("Json processing failed for object: %s", bytes.toString()), e);
        }
        return null;
    }
}
