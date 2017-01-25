package entities;

/**
 * Created by Yarden on 08/01/2017.
 */
public enum Food {
    GRASS("grass"),
    ANIMALS("animals"),
    HUMANS("humans"),
    INSECS("insecs");

    private final String name;

    Food(String name) {
        this.name = name;
    }
}
