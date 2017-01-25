package multithreaded;

import entities.Animal;

/**
 * Created by Yarden on 25/01/2017.
 */
public interface EventHandler<T extends Animal> {
     void handle(T message);
}
