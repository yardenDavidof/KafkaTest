package utils;

import entities.AnimalColor;
import entities.Food;
import entities.Mammal;

import java.awt.*;
import java.util.Random;

/**
 * Created by Yarden on 08/01/2017.
 */
public class EntitiesFactory {

    private static final int NUM_OF_ENTITIES = 1000;
    private static Random random = new Random();

    public static Mammal createRandomMammal(){
        int id = random.nextInt(NUM_OF_ENTITIES);
        AnimalColor color = AnimalColor.values()[random.nextInt(AnimalColor.values().length)];
        int legsNum = id%7 + 1;
        Food food = Food.values()[random.nextInt(Food.values().length)];
        Dimension worldSize = new Dimension(random.nextInt(1500),random.nextInt(1500));
        return new Mammal(id, "mammal " + id, color,legsNum,food,"world " + id, worldSize);
    }

}
