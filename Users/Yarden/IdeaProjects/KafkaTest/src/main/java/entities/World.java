package entities;


import java.awt.*;

/**
 * Created by Yarden on 08/01/2017.
 */
public class World {

    private String name;
    private Dimension size;

    public World() {} //required for Avro serualization

    public World(String name, Dimension size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
}
