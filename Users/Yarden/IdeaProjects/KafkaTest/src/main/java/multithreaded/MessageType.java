package multithreaded;

import entities.Animal;

/**
 * Created by Yarden on 25/01/2017.
 */
public class MessageType<T extends Animal> {
    private Class<?> type;
    private T message;
    public Class<?> getType(){
        return type;
    }

    public T getMessage() {
        return message;
    }
}
