package entities;

/**
 * Created by Yarden on 08/01/2017.
 */
public enum AnimalColor {
    RED("red"),
    BROWN("brown"),
    WHITE("white");


    private final String name;

    AnimalColor(String name) {
        this.name = name;
    }
}
