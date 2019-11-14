package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;

/**
 * Bird class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Bird extends Pet {

    private double wingspan;
    private boolean canFly;

    public Bird(int id, String name, String type, String breed, int age, String colour, Gender gender, int ownerId,
                LocalDateTime dateRegistered, double wingspan, boolean canFly) {
        super(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
        this.setWingspan(wingspan);
        this.setCanFly(canFly);
    }

    public double getWingspan() {
        return wingspan;
    }

    public void setWingspan(double wingspan) {
        if(wingspan < 0) {
            throw new IllegalArgumentException("Wingspan must be positive.");
        }
        this.wingspan = wingspan;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }
}
