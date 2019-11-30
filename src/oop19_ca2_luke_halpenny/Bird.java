package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;
import java.util.Scanner;

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

    public Bird(long id, String name, String type, String breed, int age, String colour, Gender gender, long ownerId,
                LocalDateTime dateRegistered, double wingspan, boolean canFly) {
        super(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
        this.setWingspan(wingspan);
        this.setCanFly(canFly);
    }

    public static Bird newPet(Owner owner, String name, String type, String breed, int age, String colour,
                                Gender gender, double wingspan, boolean canFly) {
        long id = Fish.getIncrementalId();
        Pet.setIncrementalId(Pet.getIncrementalId() + 1);
        LocalDateTime dateRegistered = LocalDateTime.now();
        return new Bird(id, name, type, breed, age, colour, gender, owner.getId(), dateRegistered, wingspan, canFly);
    }

    public static Bird fromString(String data) {
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
        double wingspan = input.nextDouble();
        boolean canFly = input.nextBoolean();
        return new Bird(id, name, type, breed, age, colour, gender, ownerId, dateRegistered, wingspan, canFly);
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

    public boolean canFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public boolean equals(Bird o) {
        return super.equals(o) && this.getWingspan() == o.getWingspan() && this.canFly() == o.canFly();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 29 * (int) (this.getWingspan() * 10000) + 31 * (this.canFly() ? 1 : 0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(wingspan=%.2f,canFly=%s)",
                this.getWingspan(), this.canFly());
    }
}
