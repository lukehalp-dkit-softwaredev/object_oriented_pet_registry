package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Pet class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Pet implements Comparable<Pet> {

    private static long incrementalId = 1;

    private String type;
    private String name;
    private String breed;
    private int age;
    private String colour;
    private Gender gender;
    private LocalDateTime dateRegistered;
    private long id;
    private long ownerId;

    public static long getIncrementalId() {
        return incrementalId;
    }

    public static void setIncrementalId(long incrementalId) {
        Pet.incrementalId = incrementalId;
    }

    public Pet(long id, String name, String type, String breed, int age, String colour, Gender gender, long ownerId,
               LocalDateTime dateRegistered) {
        this.setId(id);
        this.setOwnerId(ownerId);
        this.setName(name);
        this.setType(type);
        this.setBreed(breed);
        this.setAge(age);
        this.setColour(colour);
        this.setGender(gender);
        this.setDateRegistered(dateRegistered);
    }

    public static Pet newPet(Owner owner, String name, String type, String breed, int age, String colour,
                             Gender gender) {
        long id = incrementalId;
        ++incrementalId;
        LocalDateTime dateRegistered = LocalDateTime.now();
        return new Pet(id, name, type, breed, age, colour, gender, owner.getId(), dateRegistered);
    }

    public static Pet fromString(String data) {
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
        return new Pet(id, name, type, breed, age, colour, gender, ownerId, dateRegistered);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if(age < 0) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        this.age = age;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int compareTo(Pet o) {
        return (int) (this.getId() - o.getId());
    }

    public Comparator<Pet> sortByAge() {
        return new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                return o1.getAge() - o2.getAge();
            }
        };
    }

    public Comparator<Pet> sortByGender() {
        return new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                if(o1.getGender() == o2.getGender()) {
                    return 0;
                } else if(o1.getGender() == Gender.MALE && o2.getGender() == Gender.FEMALE) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
    }


}
