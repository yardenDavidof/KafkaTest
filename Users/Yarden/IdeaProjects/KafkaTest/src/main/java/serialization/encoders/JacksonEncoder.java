package serialization.encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;
import org.apache.log4j.Logger;

/**
 * Created by Yarden on 04/01/2017.
 */
public class JacksonEncoder implements Encoder<Object>{

    private static final Logger LOGGER = Logger.getLogger(JacksonEncoder.class);

    public JacksonEncoder(VerifiableProperties verifiableProperties) {
            /* This constructor must be present for successful compile. */
    }
    public JacksonEncoder() {
            /* This constructor must be present for successful compile. */
    }

    @Override
    public byte[] toBytes(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object).getBytes();
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Json processing failed for object: %s", object.getClass().getName()), e);
        }
        return "".getBytes();
    }

}
