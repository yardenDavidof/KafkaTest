package entities;

/**
 * Created by Yarden on 08/01/2017.
 */
public class Animal {

    private int id;
    private String name;
    private AnimalColor color;

    public Animal() {} // required for Avro serualization

    public Animal(int id, String name, AnimalColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalColor getColor() {
        return color;
    }

    public void setColor(AnimalColor color) {
        this.color = color;
    }
}
