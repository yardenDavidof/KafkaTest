package multithreaded;

import entities.Mammal;

/**
 * Created by Yarden on 25/01/2017.
 */
public class MammalHandler implements EventHandler<Mammal> {

    @Override
    public void handle(Mammal message) {
        System.out.println("in mammal handler. message is: " + message);
    }
}
