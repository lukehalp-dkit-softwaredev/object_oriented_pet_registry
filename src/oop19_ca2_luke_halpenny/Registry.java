package oop19_ca2_luke_halpenny;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Registry class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Registry {

    private ArrayList<Owner> owners;

    public Registry() {
        this.owners = new ArrayList<>();
    }

    public void addOwner(Owner owner) {
        this.owners.add(owner);
    }

    public Owner findOwnerById(long id) {
        for (int i = 0; i < this.owners.size(); i++) {
            Owner owner = this.owners.get(i);
            if(owner.getId() == id) {
                return owner;
            }
        }
        return null;
    }

    public ArrayList<Owner> listOwners() {
        return this.owners;
    }

    public void loadOwners(String filename) throws FileNotFoundException {
        try {
            String[] lines = Util.readLines(filename);
            for (int i = 0; i < lines.length; i++) {
                String data = lines[i];
                Owner owner = Owner.fromString(data); //TODO: validate data
                this.addOwner(owner);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cant find owners file.");
        }
    }

    public void addPet(Pet pet, Owner owner) { owner.addPet(pet); }

    public Pet findPetById(long id) {
        for (int i = 0; i < this.owners.size(); i++) {
            Owner owner = this.owners.get(i);
            Pet pet = owner.getPet(id);
            if(pet != null) {
                return pet;
            }
        }
        return null;
    }

    public ArrayList<Pet> listPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        for (int i = 0; i < this.owners.size(); i++) {
            Owner owner = this.owners.get(i);
            for (int j = 0; j < owner.getPets().size(); j++) {
                pets.add(owner.getPets().get(j));
            }
        }
        return pets;
    }

    public void loadPets(String filename) throws FileNotFoundException {
        try {
            String[] lines = Util.readLines(filename);
            for (int i = 0; i < lines.length; i++) {
                String data = lines[i];
                Pet pet = null; //TODO: validate data
                if(data.startsWith("Bird")) {
                    pet = Bird.fromString(data.substring(5));
                } else if(data.startsWith("Mammal")) {
                    pet = Mammal.fromString(data.substring(7));
                } else if(data.startsWith(("Fish"))) {
                    pet = Fish.fromString(data.substring(5));
                } else {
                    throw new InputMismatchException("Invalid data found.");
                }
                Owner owner = findOwnerById(pet.getOwnerId());
                if(owner != null) {
                    this.addPet(pet, owner);
                } else {
                    throw new InputMismatchException(String.format("No owner exists with id %d", pet.getOwnerId()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cant find pets file.");
        }
    }

}
