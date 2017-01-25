package multithreaded;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yarden on 25/01/2017.
 * // TODO - create interface
 */
public class EventsManager {
    private Map<Class<? extends MessageType>, List<EventHandler>> listeners = new HashMap<>();
    private static final EventsManager instance = new EventsManager();

    private EventsManager(){}

    public static EventsManager getInstance(){return instance; }

    public <T> void register(Class<? extends T> clazz, EventHandler listener){
        listeners.get(clazz).add(listener);
    }

    public void fire(MessageType message){
        listeners.get(message.getType()).stream().forEach(listener -> listener.handle(message.getMessage()));
    }
}
