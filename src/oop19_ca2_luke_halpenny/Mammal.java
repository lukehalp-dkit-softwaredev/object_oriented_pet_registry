package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;
import java.util.Scanner;

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

    public Mammal(long id, String name, String type, String breed, int age, String colour, Gender gender, long ownerId,
                  LocalDateTime dateRegistered, boolean neutered) {
        super(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
        this.setNeutered(neutered);
    }

    public static Mammal newPet(Owner owner, String name, String type, String breed, int age, String colour,
                              Gender gender, boolean isNeutered) {
        long id = Fish.getIncrementalId();
        Pet.setIncrementalId(Pet.getIncrementalId() + 1);
        LocalDateTime dateRegistered = LocalDateTime.now();
        return new Mammal(id, name, type, breed, age, colour, gender, owner.getId(), dateRegistered, isNeutered);
    }

    public static Mammal fromString(String data) {
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
        boolean isNeutered = input.nextBoolean();
        return new Mammal(id, name, type, breed, age, colour, gender, ownerId, dateRegistered, isNeutered);
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public boolean equals(Mammal o) {
        return super.equals(o) && this.isNeutered() == o.isNeutered();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 29 * (this.isNeutered() ? 1 : 0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(neutered=%s)",
                this.isNeutered());
    }
}
