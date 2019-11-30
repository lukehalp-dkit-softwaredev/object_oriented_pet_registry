package oop19_ca2_luke_halpenny;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Menu {

    public static final String CMD_PREFIX = ">> ";
    public static final String QUIT_MSG = "Quitting..";
    public static final String ERR_INVALID_CMD = "\t[!] Invalid command entered!";
    public static final String ERR_FILE_NOT_FOUND = "\t[!] File not found!";

    Scanner input;
    Registry registry;
    boolean running;

    public Menu() {
        this.input = new Scanner(System.in);
        this.registry = new Registry();
    }

    void printMenu() {
        System.out.println("-------- CA2 --------");
        System.out.println("Dundalk Pet Registry:");
        System.out.println("---------------------");
        System.out.print("1. Add pet\t\t\t");
        System.out.print("2. Edit pet\t\t\t");
        System.out.print("3. Delete pet\t\t");
        System.out.print("4. Display pet\n");
        System.out.print("5. Add owner\t\t");
        System.out.print("6. Edit owner\t\t");
        System.out.print("7. Delete owner\t\t");
        System.out.print("8. Display owner\n");
        System.out.print("9. Transfer pet\t\t");
        System.out.print("A. List pets\t\t");
        System.out.print("B. List owners pets\t");
        System.out.print("C. List pet type\n");
        System.out.print("D. List owners\t\t");
        System.out.print("E. Load from text\t");
        System.out.print("F. Save to bin\t\t");
        System.out.print("G. Load from bin\n");
        System.out.print("q: Quit\n");
    }

    void quit() {
        this.running = false;
        System.out.println(QUIT_MSG);
    }

    public void run() {
        this.running = true;
        while(this.running) {
            printMenu();
            System.out.print(CMD_PREFIX);
            String cmdLine = this.input.nextLine();
            if(cmdLine.length() == 1) {
                char cmd = cmdLine.toUpperCase().charAt(0);
                switch(cmd) {
                    case 'Q': {
                        this.quit();
                        break;
                    }
                    case '1': {
                        addPet();
                        break;
                    }
                    case '2': {
                        editPet();
                        break;
                    }
                    case '3': {
                        deletePet();
                        break;
                    }
                    case '4': {
                        printPet();
                        break;
                    }
                    case '5': {
                        addOwner();
                        break;
                    }
                    case '6': {
                        editOwner();
                        break;
                    }
                    case '7': {
                        deleteOwner();
                        break;
                    }
                    case '8': {
                        printOwner();
                        break;
                    }
                    case '9': {
                        transferPet();
                        break;
                    }
                    case 'A': {
                        listPets();
                        break;
                    }
                    case 'B': {
                        listOwnersPets();
                        break;
                    }
                    case 'C': {
                        listPetType();
                        break;
                    }
                    case 'D': {
                        this.registry.displayOwners();
                        break;
                    }
                    case 'E': {
                        loadFromText();
                        break;
                    }
                    case 'F': {
                        saveToBin();
                        break;
                    }
                    case 'G': {
                        loadFromBin();
                        break;
                    }
                    default: {
                        System.err.println(ERR_INVALID_CMD);
                        break;
                    }
                }
            } else {
                System.err.println(ERR_INVALID_CMD);
            }
        }
    }

    public void addPet() {
        System.out.print("Owner ID >> ");
        long owner_id;
        if(input.hasNextLong()) {
            owner_id = input.nextLong();
        } else {
            System.err.println("\t[!] Invalid Owner ID!");
            System.out.println();
            input.nextLine();
            return;
        }
        input.nextLine();
        Owner owner = this.registry.findOwnerById(owner_id);
        if(owner == null) {
            System.err.println("\t[!] Owner not found!");
            return;
        }
        System.out.print("Name >> ");
        String name = input.nextLine();
        System.out.print("Type >> ");
        String type = input.nextLine();
        System.out.print("Breed >> ");
        String breed = input.nextLine();
        System.out.print("Age >> ");
        int age;
        try {
            age = input.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\t[!] Not a valid number.");
            input.nextLine();
            return;
        }
        input.nextLine();
        System.out.print("Colour >> ");
        String colour = input.nextLine();
        System.out.print("Gender(MALE/FEMALE) >> ");
        String genderStr = input.nextLine();
        Gender gender;
        if(genderStr.equalsIgnoreCase("male")) {
            gender = Gender.MALE;
        } else if (genderStr.equalsIgnoreCase("female")) {
            gender = Gender.FEMALE;
        } else {
            System.err.println("\t[!] Invalid Gender.");
            return;
        }
        System.out.print("Bird/Fish/Mammal >> ");
        String classtype = input.nextLine();
        if(classtype.equalsIgnoreCase("Bird")) {
            System.out.print("Wingspan >> ");
            double wingspan = input.nextDouble();
            input.nextLine();
            System.out.print("Can Fly (Yes/No) >> ");
            String canFlyStr = input.nextLine();
            boolean canFly;
            if(canFlyStr.equalsIgnoreCase("yes")) {
                canFly = true;
            } else if (canFlyStr.equalsIgnoreCase("no")) {
                canFly = false;
            } else {
                System.err.println("\t[!] Invalid Option.");
                return;
            }
            Bird bird = Bird.newPet(owner, name, type, breed, age, colour, gender, wingspan, canFly);
            this.registry.addPet(bird, owner);
        } else if(classtype.equalsIgnoreCase("Fish")) {
            System.out.print("Water Type(SEAWATER/FRESHWATER/BRACKISH) >> ");
            String waterTypeStr = input.nextLine();
            WaterType waterType;
            if(waterTypeStr.equalsIgnoreCase("seawater")) {
                waterType = WaterType.SEAWATER;
            } else if(waterTypeStr.equalsIgnoreCase("freshwater")) {
                waterType = WaterType.FRESHWATER;
            } else if(waterTypeStr.equalsIgnoreCase("brackish")) {
                waterType = WaterType.BRACKISH;
            } else {
                System.err.println("\t[!] Invalid option.");
                return;
            }
            Fish fish = Fish.newPet(owner, name, type, breed, age, colour, gender, waterType);
            this.registry.addPet(fish, owner);
        } else if(classtype.equalsIgnoreCase("Mammal")) {
            System.out.print("Neutered (Yes/No) >> ");
            String isNeuteredStr = input.nextLine();
            boolean isNeutered;
            if(isNeuteredStr.equalsIgnoreCase("yes")) {
                isNeutered = true;
            } else if (isNeuteredStr.equalsIgnoreCase("no")) {
                isNeutered = false;
            } else {
                System.err.println("\t[!] Invalid Option.");
                return;
            }
            Mammal mammal = Mammal.newPet(owner, name, type, breed, age, colour, gender, isNeutered);
            this.registry.addPet(mammal, owner);
        } else {
            System.err.println("\t[!] Invalid animal type.");
        }
    }

    public void editPet() {
        System.out.print("Pet ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Pet pet = this.registry.findPetById(id);
        if(pet == null) {
            System.err.println("\t[!] Pet not found!");
            return;
        }
        System.out.println("Enter new details (leave blank for unchanged)");
        System.out.printf("Name [%s]>> ", pet.getName());
        String name = input.nextLine();
        if(!name.equals("")) { pet.setName(name); }
        System.out.printf("Type [%s]>> ", pet.getType());
        String type = input.nextLine();
        if(!type.equals("")) { pet.setType(type); }
        System.out.printf("Breed [%s]>> ", pet.getBreed());
        String breed = input.nextLine();
        if(!breed.equals("")) { pet.setBreed(breed); }
        System.out.printf("Age [%d]>> ", pet.getAge());
        String agestr = input.nextLine();
        try {
            int age = Integer.parseInt(agestr);
            pet.setAge(age);
        } catch (NumberFormatException e) {

        }
        System.out.printf("Colour [%s]>> ", pet.getColour());
        String colour = input.nextLine();
        if(!colour.equals("")) { pet.setColour(colour); }
        System.out.printf("Gender(MALE/FEMALE) [%s]>> ", pet.getGender());
        String gender = input.nextLine();
        if(!gender.equals("")) {
            if(gender.equalsIgnoreCase("male")) {
                pet.setGender(Gender.MALE);
            } else if(gender.equalsIgnoreCase("female")) {
                pet.setGender(Gender.FEMALE);
            } else {
                System.err.println("\t[!] Invalid gender, leaving unchanged.");
            }
        }
        if(pet instanceof Bird) {
            System.out.printf("Wingspan [%.2f]>> ", ((Bird) pet).getWingspan());
            String wingspanstr = input.nextLine();
            try {
                double wingspan = Double.parseDouble(wingspanstr);
                ((Bird) pet).setWingspan(wingspan);
            } catch (NumberFormatException e) {
                System.err.println("\t[!] invalid number, leaving unchanged.");
            }
            System.out.printf("Can Fly(Yes/No) [%s]>> ", ((Bird) pet).canFly());
            String canFlystr = input.nextLine();
            if(!canFlystr.equals("")) {
                if(canFlystr.equalsIgnoreCase("yes")) {
                    ((Bird) pet).setCanFly(true);
                } else if(canFlystr.equalsIgnoreCase("no")) {
                    ((Bird) pet).setCanFly(false);
                } else {
                    System.err.println("\t[!] Invalid option, leaving unchanged.");
                }
            }
        } else if(pet instanceof Fish) {
            System.out.printf("Water Type(SEAWATER/FRESHWATER/BRACKISH) [%s]>> ", ((Fish) pet).getWaterType());
            String waterString = input.nextLine();
            if(!waterString.equals("")) {
                if(waterString.equalsIgnoreCase("seawater")) {
                    ((Fish) pet).setWaterType(WaterType.SEAWATER);
                } else if (waterString.equalsIgnoreCase("freshwater")) {
                    ((Fish) pet).setWaterType(WaterType.FRESHWATER);
                } else if (waterString.equalsIgnoreCase("brackish")) {
                    ((Fish) pet).setWaterType(WaterType.BRACKISH);
                } else {
                    System.err.println("\t[!] Invalid option, leaving unchanged.");
                }

            }
        } else if(pet instanceof Mammal) {
            System.out.printf("Neutered (True/False) [%s]>> ", ((Mammal) pet).isNeutered());
            String isNeuteredStr = input.nextLine();
            if(!isNeuteredStr.equals("")) {
                if(isNeuteredStr.equalsIgnoreCase("yes")) {
                    ((Mammal) pet).setNeutered(true);
                } else if(isNeuteredStr.equalsIgnoreCase("no")) {
                    ((Mammal) pet).setNeutered(false);
                } else {
                    System.err.println("\t[!] Invalid option, leaving unchanged.");
                }
            }
        } else {
            System.err.println("\t[!] Uh Oh! Shouldn't be here!");
        }
    }

    public void deletePet() {
        System.out.print("Pet ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Pet pet = this.registry.findPetById(id);
        if(pet == null) {
            System.err.println("\t[!] Pet not found!");
            return;
        }
        this.registry.displayPet(pet.getId());
        System.out.println("Are you sure you want to delete this pet?");
        System.out.print("Yes/No >> ");
        String response = input.nextLine();
        if(response.equalsIgnoreCase("yes")) {
            this.registry.deletePet(pet.getId());
            System.out.println("Pet deleted");
        } else {
            System.out.println("Pet not deleted.");
        }
    }

    public void printPet() {
        System.out.print("Pet ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Pet pet = this.registry.findPetById(id);
        if(pet == null) {
            System.err.println("\t[!] Pet not found!");
            return;
        }
        this.registry.displayPet(pet.getId());
    }

    public void addOwner() {
        System.out.print("Name >> ");
        String name = input.nextLine();
        System.out.print("Email >> ");
        String email = input.nextLine();
        System.out.print("Phone Number >> ");
        String telephone = input.nextLine();
        System.out.print("Address >> ");
        String address = input.nextLine();
        try {
            Owner owner = Owner.newOwner(name, email, telephone, address);
            this.registry.addOwner(owner);
        } catch (IllegalArgumentException e) {
            System.err.println("\t[!] Invalid argument: " + e.getMessage());
        }
    }

    public void editOwner() {
        System.out.print("Owner ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Owner owner = this.registry.findOwnerById(id);
        if(owner == null) {
            System.err.println("\t[!] Owner not found!");
            return;
        }
        try {
            System.out.println("Enter new details (leave blank for unchanged)");
            System.out.printf("Name [%s]>> ", owner.getName());
            String name = input.nextLine();
            if(!name.equals("")) { owner.setName(name); }
            System.out.printf("Email [%s]>> ", owner.getEmail());
            String email = input.nextLine();
            if(!email.equals("")) { owner.setEmail(email); }
            System.out.printf("Phone Number [%s]>> ", owner.getTelephone());
            String telephone = input.nextLine();
            if(!telephone.equals("")) { owner.setTelephone(telephone); }
            System.out.printf("Address [%s]>> ", owner.getAddress());
            String address = input.nextLine();
            if(!address.equals("")) { owner.setAddress(address); }
        } catch(IllegalArgumentException e) {
            System.err.println("\t[!] Invalid argument: " + e.getMessage());
        }
    }

    public void deleteOwner() {
        System.out.print("Owner ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Owner owner = this.registry.findOwnerById(id);
        if(owner == null) {
            System.err.println("\t[!] Owner not found!");
            return;
        }
        this.registry.displayOwner(owner.getId());
        System.out.println("Are you sure you want to delete this owner?");
        System.out.print("Yes/No >> ");
        String response = input.nextLine();
        if(response.equalsIgnoreCase("yes")) {
            this.registry.deleteOwner(owner.getId());
            System.out.println("Owner deleted");
        } else {
            System.out.println("Owner not deleted.");
        }
    }

    public void printOwner() {
        System.out.print("Owner ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Owner owner = this.registry.findOwnerById(id);
        if(owner == null) {
            System.err.println("\t[!] Owner not found!");
            return;
        }
        this.registry.displayOwner(owner.getId());
    }

    public void transferPet() {
        System.out.print("Pet ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Pet pet = this.registry.findPetById(id);
        if(pet == null) {
            System.err.println("\t[!] Pet not found!");
            return;
        }
        System.out.print("New Owner ID >> ");
        long oid = input.nextLong();
        input.nextLine();
        Owner owner = this.registry.findOwnerById(oid);
        if(owner == null) {
            System.err.println("\t[!] Owner not found!");
            return;
        }
        this.registry.displayPet(pet.getId());
        this.registry.displayOwner(pet.getOwnerId());
        this.registry.displayOwner(owner.getId());
        System.out.println("Confirm Transfer?");
        System.out.print("Yes/No >> ");
        String ans = input.nextLine();
        if(ans.equalsIgnoreCase("yes")) {
            this.registry.transferPet(pet.getId(), pet.getOwnerId(), owner.getId());
            System.out.println("Pet transfered.");
        } else {
            System.out.println("Pet not transfered.");
        }
    }

    public void listPets() {
        System.out.print("Sort by (ID/Age/Gender) >> ");
        String type = input.nextLine();
        if(type.equalsIgnoreCase("id")) {
            this.registry.displayPets();
        } else if(type.equalsIgnoreCase("age")) {
            this.registry.displayPetsByAge();
        } else if(type.equalsIgnoreCase("gender")) {
            this.registry.displayPetsByGender();
        } else {
            System.out.println("Invalid option, sorting by ID.");
            this.registry.displayPets();
        }

    }

    public void listOwnersPets() {
        System.out.print("Owner ID >> ");
        long id = input.nextLong();
        input.nextLine();
        Owner owner = this.registry.findOwnerById(id);
        if(owner == null) {
            System.err.println("\t[!] Owner not found!");
            return;
        }
        this.registry.displayOwnersPets(id);
    }

    public void listPetType() {
        System.out.print("Bird/Fish/Mammal >> ");
        String type = input.nextLine();
        if(type.equalsIgnoreCase("bird")) {
            this.registry.displayBirds();
        } else if(type.equalsIgnoreCase("fish")) {
            this.registry.displayFish();
        } else if(type.equalsIgnoreCase("mammal")) {
            this.registry.displayMammals();
        }
    }

    public void loadFromText() {
        try {
            this.registry.loadOwners("owners.txt");
        } catch (FileNotFoundException e) {
            System.err.println("\t[!] Couldn't find owners file!");
        }
        try {
            this.registry.loadPets("pets.txt");
        } catch (FileNotFoundException e) {
            System.err.println("\t[!] Couldn't find pets file!");
        }
    }

    public void saveToBin() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("registry.dat"));
            out.writeObject(this.registry);
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.err.println("\t[!] File not found!");
        } catch (IOException e) {
            System.err.println("\t[!] Couldn't write file!");
        }
    }

    public void loadFromBin() {
        try {
            File f = new File("registry.dat");
            if (f.exists()) {
                ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(f));
                this.registry = (Registry) in.readObject();
                in.close();
            }
            System.out.println("Loaded!");
        } catch (FileNotFoundException e) {
            System.err.println("\t[!] File not found!");
        } catch (IOException e) {
            System.err.println("\t[!] Couldn't read file!");
        } catch (ClassNotFoundException e) {
            System.err.println("\t[!] Data file not valid!");
        }
    }

}
