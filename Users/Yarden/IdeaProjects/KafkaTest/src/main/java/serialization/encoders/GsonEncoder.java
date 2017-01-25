package serialization.encoders;

import com.google.gson.Gson;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

/**
 * serialize java objects to JSON by GSON
 * Created by Yarden on 04/01/2017.
 */
public class GsonEncoder implements Encoder<Object> {
    private final Gson gson = new Gson();

    public GsonEncoder(VerifiableProperties verifiableProperties) {
            /* This constructor must be present for successful compile. */
    }

    @Override
    public byte[] toBytes(Object object) {
        return gson.toJson(object).getBytes();
    }
}
