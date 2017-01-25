package entities;

import java.awt.*;

/**
 * Created by Yarden on 08/01/2017.
 */
public class Mammal extends Animal {

    private int legsNum;
    private Food food;
    private World world;

    public Mammal() {} // required for Avro serualization

    public Mammal(int id, String name, AnimalColor color, int legsNum, Food food, String worldName, Dimension worldSize) {
        super(id, name, color);
        this.legsNum = legsNum;
        this.food = food;
        this.world = new World(worldName, worldSize);
    }

    public int getLegsNum() {
        return legsNum;
    }

    public void setLegsNum(int legsNum) {
        this.legsNum = legsNum;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
