package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;

/**
 * Mammal class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Mammal extends Pet {

    private boolean neutered;

    public Mammal(int id, String name, String type, String breed, int age, String colour, Gender gender, int ownerId,
                  LocalDateTime dateRegistered, boolean neutered) {
        super(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
        this.setNeutered(neutered);
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }
}
