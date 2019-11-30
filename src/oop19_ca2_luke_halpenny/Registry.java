package oop19_ca2_luke_halpenny;

import java.io.FileNotFoundException;
import java.io.Serializable;
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
public class Registry implements Serializable {

    private ArrayList<Owner> owners;

    public Registry() {
        this.owners = new ArrayList<>();
    }

    public void addOwner(Owner owner) {
        if(this.listOwners().contains(owner)) {
            throw new IllegalArgumentException("Owner already exists!");
        }
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

    public void deleteOwner(long id) {
        Owner owner = this.findOwnerById(id);
        if(owner == null) {
            throw new IllegalArgumentException("Owner not found.");
        }
        this.owners.remove(owner);
    }

    public ArrayList<Owner> listOwners() {
        return this.owners;
    }

    public void displayOwner(long id) {
        System.out.printf("%30s | %-20s | %-30s | %-20s | %-55s\n", "ID", "Name", "Email Address", "Phone Number", "Address");
        Owner owner = this.findOwnerById(id);
        System.out.printf("%30d | %-20s | %-30s | %-20s | %-55s\n", owner.getId(), owner.getName(), owner.getEmail(), owner.getTelephone(), owner.getAddress());
    }

    public void displayOwners() {
        System.out.println("Owners:");
        System.out.printf("%30s | %-20s | %-30s | %-20s | %-55s\n", "ID", "Name", "Email Address", "Phone Number", "Address");
        for (int i = 0; i < this.owners.size(); i++) {
            Owner owner = this.owners.get(i);
            System.out.printf("%30d | %-20s | %-30s | %-20s | %-55s\n", owner.getId(), owner.getName(), owner.getEmail(), owner.getTelephone(), owner.getAddress());
        }
    }

    public void transferPet(long petId, long oldOwnerId, long newOwnerId) {
        Pet pet = this.findPetById(petId);
        if(pet == null) {
            throw new IllegalArgumentException("Pet not found");
        }
        Owner oldOwner = this.findOwnerById(oldOwnerId);
        if(oldOwner == null) {
            throw new IllegalArgumentException("Old owner not found");
        }
        Owner newOwner = this.findOwnerById(newOwnerId);
        if(newOwner == null) {
            throw new IllegalArgumentException("New owner not found");
        }
        pet.setOwnerId(newOwnerId);
        oldOwner.removePet(pet);
        newOwner.addPet(pet);
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

    public void addPet(Pet pet, Owner owner) {
        if(this.listPets().contains(pet)) {
            throw new IllegalArgumentException("Pet already exists!");
        }
        owner.addPet(pet);
    }

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

    public void deletePet(long id) {
        for (int i = 0; i < this.owners.size(); i++) {
            Owner owner = this.owners.get(i);
            Pet pet = owner.getPet(id);
            if(pet == null) {
                throw new IllegalArgumentException("Pet not found");
            }
            if(pet != null) {
                owner.removePet(pet);
            }
        }
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

    public void displayPet(long id) {
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        Pet pet = this.findPetById(id);
        if(pet == null) {
            throw new IllegalArgumentException("Pet not found.");
        }
        if(pet instanceof Bird) {
            Bird bird = (Bird) pet;
            System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Wingspan: %8f | Can Fly: %s\n",
                    bird.getId(), bird.getName(), bird.getType(), bird.getBreed(), bird.getAge(),
                    bird.getColour(), bird.getGender().toString(), bird.getDateRegistered().toString(),
                    bird.getOwnerId(), bird.getWingspan(), bird.canFly());
        } else if(pet instanceof  Mammal) {
            Mammal mammal = (Mammal) pet;
            System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Neutered: %s\n",
                    mammal.getId(), mammal.getName(), mammal.getType(), mammal.getBreed(), mammal.getAge(),
                    mammal.getColour(), mammal.getGender().toString(), mammal.getDateRegistered().toString(),
                    mammal.getOwnerId(), mammal.isNeutered());
        } else if (pet instanceof Fish) {
            Fish fish = (Fish) pet;
            System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Water Type: %s\n",
                    fish.getId(), fish.getName(), fish.getType(), fish.getBreed(), fish.getAge(),
                    fish.getColour(), fish.getGender().toString(), fish.getDateRegistered().toString(),
                    fish.getOwnerId(), fish.getWaterType().toString());
        }
    }

    public void displayPets() {
        System.out.println("Pets:");
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        for (int i = 0; i < this.listPets().size(); i++) {
            Pet pet = this.listPets().get(i);
            if(pet instanceof Bird) {
                Bird bird = (Bird) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Wingspan: %8f | Can Fly: %s\n",
                        bird.getId(), bird.getName(), bird.getType(), bird.getBreed(), bird.getAge(),
                        bird.getColour(), bird.getGender().toString(), bird.getDateRegistered().toString(),
                        bird.getOwnerId(), bird.getWingspan(), bird.canFly());
            } else if(pet instanceof  Mammal) {
                Mammal mammal = (Mammal) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Neutered: %s\n",
                        mammal.getId(), mammal.getName(), mammal.getType(), mammal.getBreed(), mammal.getAge(),
                        mammal.getColour(), mammal.getGender().toString(), mammal.getDateRegistered().toString(),
                        mammal.getOwnerId(), mammal.isNeutered());
            } else if (pet instanceof Fish) {
                Fish fish = (Fish) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Water Type: %s\n",
                        fish.getId(), fish.getName(), fish.getType(), fish.getBreed(), fish.getAge(),
                        fish.getColour(), fish.getGender().toString(), fish.getDateRegistered().toString(),
                        fish.getOwnerId(), fish.getWaterType().toString());
            }
        }
    }

    public void displayPetsByAge() {
        System.out.println("Pets:");
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        ArrayList<Pet> pets = this.listPets();
        pets.sort(Pet.sortByAge());
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if(pet instanceof Bird) {
                Bird bird = (Bird) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Wingspan: %8f | Can Fly: %s\n",
                        bird.getId(), bird.getName(), bird.getType(), bird.getBreed(), bird.getAge(),
                        bird.getColour(), bird.getGender().toString(), bird.getDateRegistered().toString(),
                        bird.getOwnerId(), bird.getWingspan(), bird.canFly());
            } else if(pet instanceof  Mammal) {
                Mammal mammal = (Mammal) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Neutered: %s\n",
                        mammal.getId(), mammal.getName(), mammal.getType(), mammal.getBreed(), mammal.getAge(),
                        mammal.getColour(), mammal.getGender().toString(), mammal.getDateRegistered().toString(),
                        mammal.getOwnerId(), mammal.isNeutered());
            } else if (pet instanceof Fish) {
                Fish fish = (Fish) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Water Type: %s\n",
                        fish.getId(), fish.getName(), fish.getType(), fish.getBreed(), fish.getAge(),
                        fish.getColour(), fish.getGender().toString(), fish.getDateRegistered().toString(),
                        fish.getOwnerId(), fish.getWaterType().toString());
            }
        }
    }

    public void displayPetsByGender() {
        System.out.println("Pets:");
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        ArrayList<Pet> pets = this.listPets();
        pets.sort(Pet.sortByGender());
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if(pet instanceof Bird) {
                Bird bird = (Bird) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Wingspan: %8f | Can Fly: %s\n",
                        bird.getId(), bird.getName(), bird.getType(), bird.getBreed(), bird.getAge(),
                        bird.getColour(), bird.getGender().toString(), bird.getDateRegistered().toString(),
                        bird.getOwnerId(), bird.getWingspan(), bird.canFly());
            } else if(pet instanceof  Mammal) {
                Mammal mammal = (Mammal) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Neutered: %s\n",
                        mammal.getId(), mammal.getName(), mammal.getType(), mammal.getBreed(), mammal.getAge(),
                        mammal.getColour(), mammal.getGender().toString(), mammal.getDateRegistered().toString(),
                        mammal.getOwnerId(), mammal.isNeutered());
            } else if (pet instanceof Fish) {
                Fish fish = (Fish) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Water Type: %s\n",
                        fish.getId(), fish.getName(), fish.getType(), fish.getBreed(), fish.getAge(),
                        fish.getColour(), fish.getGender().toString(), fish.getDateRegistered().toString(),
                        fish.getOwnerId(), fish.getWaterType().toString());
            }
        }
    }

    public void displayOwnersPets(long id) {
        Owner owner = this.findOwnerById(id);
        if (owner == null) {
            return;
        }
        ArrayList<Pet> pets = owner.getPets();
        System.out.printf("%s's Pets:\n", owner.getName());
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if(pet instanceof Bird) {
                Bird bird = (Bird) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Wingspan: %8f | Can Fly: %s\n",
                        bird.getId(), bird.getName(), bird.getType(), bird.getBreed(), bird.getAge(),
                        bird.getColour(), bird.getGender().toString(), bird.getDateRegistered().toString(),
                        bird.getOwnerId(), bird.getWingspan(), bird.canFly());
            } else if(pet instanceof  Mammal) {
                Mammal mammal = (Mammal) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Neutered: %s\n",
                        mammal.getId(), mammal.getName(), mammal.getType(), mammal.getBreed(), mammal.getAge(),
                        mammal.getColour(), mammal.getGender().toString(), mammal.getDateRegistered().toString(),
                        mammal.getOwnerId(), mammal.isNeutered());
            } else if (pet instanceof Fish) {
                Fish fish = (Fish) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Water Type: %s\n",
                        fish.getId(), fish.getName(), fish.getType(), fish.getBreed(), fish.getAge(),
                        fish.getColour(), fish.getGender().toString(), fish.getDateRegistered().toString(),
                        fish.getOwnerId(), fish.getWaterType().toString());
            }
        }
    }

    public void displayBirds() {
        System.out.println("Birds:");
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        for (int i = 0; i < this.listPets().size(); i++) {
            Pet pet = this.listPets().get(i);
            if(pet instanceof Bird) {
                Bird bird = (Bird) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Wingspan: %8f | Can Fly: %s\n",
                        bird.getId(), bird.getName(), bird.getType(), bird.getBreed(), bird.getAge(),
                        bird.getColour(), bird.getGender().toString(), bird.getDateRegistered().toString(),
                        bird.getOwnerId(), bird.getWingspan(), bird.canFly());
            }
        }
    }

    public void displayFish() {
        System.out.println("Fish:");
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        for (int i = 0; i < this.listPets().size(); i++) {
            Pet pet = this.listPets().get(i);
            if (pet instanceof Fish) {
                Fish fish = (Fish) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Water Type: %s\n",
                        fish.getId(), fish.getName(), fish.getType(), fish.getBreed(), fish.getAge(),
                        fish.getColour(), fish.getGender().toString(), fish.getDateRegistered().toString(),
                        fish.getOwnerId(), fish.getWaterType().toString());
            }
        }
    }

    public void displayMammals() {
        System.out.println("Mammals:");
        System.out.printf("%30s | %-20s | %-15s | %-15s | %3s | %-15s | %-6s | %-23s | %30s |\n",
                "ID", "Name", "Type", "Breed", "Age", "Colour", "Gender", "Date Registered", "Owner ID");
        for (int i = 0; i < this.listPets().size(); i++) {
            Pet pet = this.listPets().get(i);
            if(pet instanceof  Mammal) {
                Mammal mammal = (Mammal) pet;
                System.out.printf("%30d | %-20s | %-15s | %-15s | %3d | %-15s | %-6s | %-23s | %30d | Neutered: %s\n",
                        mammal.getId(), mammal.getName(), mammal.getType(), mammal.getBreed(), mammal.getAge(),
                        mammal.getColour(), mammal.getGender().toString(), mammal.getDateRegistered().toString(),
                        mammal.getOwnerId(), mammal.isNeutered());
            }
        }
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
