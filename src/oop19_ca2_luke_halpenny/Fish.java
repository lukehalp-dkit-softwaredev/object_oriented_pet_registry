package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;

/**
 * Fish class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Fish extends Pet {

    private WaterType waterType;

    public Fish(int id, String name, String type, String breed, int age, String colour, Gender gender, int ownerId,
                LocalDateTime dateRegistered, WaterType waterType) {
        super(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
        this.setWaterType(waterType);
    }

    public WaterType getWaterType() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }
}