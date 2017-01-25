package serialization.decoders;

import kafka.serializer.Decoder;

/**
 * Created by Yarden on 04/01/2017.
 */
public class GsonDecoder implements Decoder<String>{


    @Override
    public String fromBytes(byte[] bytes) {
        return null;
    }
}
