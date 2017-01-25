package entities;

/**
 * Created by Yarden on 08/01/2017.
 */
public class Insects extends Animal{


    public Insects() {} // required for Avro serualization

    public Insects(int id, String name, AnimalColor color) {
        super(id, name, color);
    }
}
