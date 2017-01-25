package serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Yarden on 08/01/2017.
 */
public class JacksonSerializer implements Serializer<Object>, Deserializer<Object> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        if(mapper == null)
            mapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String s, Object o) {
        try {
            return mapper.writeValueAsBytes(o);
        }
        catch(JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, Map.class);
        }
        catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void close() {
        mapper = null;
    }
}
