package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;

/**
 * Pet class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Pet {

    private static int incrementalId = 1;

    private String type;
    private String name;
    private String breed;
    private int age;
    private String colour;
    private Gender gender;
    private LocalDateTime dateRegistered;
    private int id;
    private int ownerId;

    public Pet(int id, String name, String type, String breed, int age, String colour, Gender gender, int ownerId,
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

//    public static Pet fromFile() {
//
//    }

    public static Pet newPet(Owner owner, String name, String type, String breed, int age, String colour,
                             Gender gender) {
        int id = incrementalId;
        ++incrementalId;
        LocalDateTime dateRegistered = LocalDateTime.now();
        return new Pet(id, name, type, breed, age, colour, gender, owner.getId(), dateRegistered);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
