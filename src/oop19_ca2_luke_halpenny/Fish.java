package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;
import java.util.Scanner;

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

    public Fish(long id, String name, String type, String breed, int age, String colour, Gender gender, long ownerId,
                LocalDateTime dateRegistered, WaterType waterType) {
        super(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
        this.setWaterType(waterType);
    }

    public static Fish newPet(Owner owner, String name, String type, String breed, int age, String colour,
                             Gender gender, WaterType waterType) {
        long id = Fish.getIncrementalId();
        Pet.setIncrementalId(Pet.getIncrementalId() + 1);
        LocalDateTime dateRegistered = LocalDateTime.now();
        return new Fish(id, name, type, breed, age, colour, gender, owner.getId(), dateRegistered, waterType);
    }

    public static Fish fromString(String data) {
        Scanner input = new Scanner(data);

        long id = input.nextLong();
        String name = input.next();
        String type = input.next();
        String breed = input.next();
        int age = input.nextInt();
        String colour = input.next();
        Gender gender = Gender.valueOf(input.next());
        long ownerId = input.nextLong();
        LocalDateTime dateRegistered = LocalDateTime.parse(input.next());
        WaterType waterType = WaterType.valueOf(input.next());
        return new Fish(id, name, type, breed, age, colour, gender, ownerId, dateRegistered, waterType);
    }

    public WaterType getWaterType() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }
}