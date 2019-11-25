package oop19_ca2_luke_halpenny;

import java.io.FileNotFoundException;
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
                    case 'q': {
                        this.quit();
                        break;
                    }
                    case '1': {
                        // Add pet
                        System.out.println("[WIP] Option 1.");
                        System.out.print("Owner ID >> ");
                        long owner_id = input.nextLong();
                        input.nextLine();
                        Owner owner = this.registry.findOwnerById(owner_id);
                        if(owner == null) {
                            System.err.println("\t[!] Owner not found!");
                            break;
                        }
                        System.out.print("Name >> ");
                        String name = input.nextLine();
                        System.out.print("Type >> ");
                        String type = input.nextLine();
                        System.out.print("Breed >> ");
                        String breed = input.nextLine();
                        System.out.print("Age >> ");
                        int age = input.nextInt();
                        input.nextLine();
                        System.out.print("Colour >> ");
                        String colour = input.nextLine();
                        System.out.print("Gender(MALE/FEMALE) >> ");
                        String gender = input.nextLine();
                        System.out.print("Bird/Fish/Mammal >> ");
                        String classtype = input.nextLine();
                        if(classtype.equalsIgnoreCase("Bird")) {
                            System.out.print("Wingspan >> ");
                            double wingspan = input.nextDouble();
                            input.nextLine();
                            System.out.print("Can Fly(True/False) >> ");
                            boolean canFly = input.nextBoolean();
                            input.nextLine();
                            Bird bird = Bird.newPet(owner, name, type, breed, age, colour, Gender.valueOf(gender), wingspan, canFly);
                            this.registry.addPet(bird, owner);
                        } else if(classtype.equalsIgnoreCase("Fish")) {
                            System.out.print("Water Type(SEAWATER/FRESHWATER/BRACKISH) >> ");
                            WaterType waterType = WaterType.valueOf(input.nextLine());
                            Fish fish = Fish.newPet(owner, name, type, breed, age, colour, Gender.valueOf(gender), waterType);
                            this.registry.addPet(fish, owner);
                        } else if(classtype.equalsIgnoreCase("Mammal")) {
                            System.out.print("Neutered (True/False) >> ");
                            boolean isNeutered = input.nextBoolean();
                            input.nextLine();
                            Mammal mammal = Mammal.newPet(owner, name, type, breed, age, colour, Gender.valueOf(gender), isNeutered);
                            this.registry.addPet(mammal, owner);
                        } else {
                            System.err.println("\t[!] Invalid animal type!");
                        }

                        break;
                    }
                    case '2': {
                        // Edit pet
                        System.out.print("Pet ID >> ");
                        long id = input.nextLong();
                        input.nextLine();
                        Pet pet = this.registry.findPetById(id);
                        if(pet == null) {
                            System.err.println("\t[!] Pet not found!");
                            break;
                        }
                        System.out.println("Enter new details (leave blank for unchanged)");
                        System.out.print("Name >> ");
                        String name = input.nextLine();
                        System.out.println(name.equals(""));
                        if(!name.equals("")) { pet.setName(name); }
                        System.out.print("Type >> ");
                        String type = input.nextLine();
                        if(!type.equals("")) { pet.setType(type); }
                        System.out.print("Breed >> ");
                        String breed = input.nextLine();
                        if(!breed.equals("")) { pet.setBreed(breed); }
                        System.out.print("Age >> ");
                        if(input.hasNextInt()) {
                            int age = input.nextInt();
                            input.nextLine();
                            pet.setAge(age);
                        }
                        System.out.print("Colour >> ");
                        String colour = input.nextLine();
                        if(!colour.equals("")) { pet.setColour(colour); }
                        System.out.print("Gender(MALE/FEMALE) >> ");
                        String gender = input.nextLine();
                        if(!gender.equals("")) { pet.setGender(Gender.valueOf(gender)); }
                        if(pet instanceof Bird) {
                            System.out.print("Wingspan >> ");
                            if(input.hasNextDouble()) {
                                double wingspan = input.nextDouble();
                                input.nextLine();
                                ((Bird) pet).setWingspan(wingspan);
                            }
                            System.out.print("Can Fly(True/False) >> ");
                            if(input.hasNextBoolean()) {
                                boolean canFly = input.nextBoolean();
                                input.nextLine();
                                ((Bird) pet).setCanFly(canFly);
                            }
                        } else if(pet instanceof Fish) {
                            System.out.print("Water Type(SEAWATER/FRESHWATER/BRACKISH) >> ");
                            String waterString = input.nextLine();
                            if(!waterString.equals("")) {
                                WaterType waterType = WaterType.valueOf(waterString);
                                ((Fish) pet).setWaterType(waterType);
                            }
                        } else if(pet instanceof Mammal) {
                            System.out.print("Neutered (True/False) >> ");
                            if(input.hasNextBoolean()) {
                                boolean isNeutered = input.nextBoolean();
                                input.nextLine();
                                ((Mammal) pet).setNeutered(isNeutered);
                            }
                        } else {
                            System.err.println("\t[!] Uh Oh! Shouldn't be here!");
                        }
                        break;
                    }
                    case '3': {
                        // Delete pet
                        System.out.println("[WIP] Option 3.");
                        break;
                    }
                    case '4': {
                        // Print pet
                        System.out.println("[WIP] Option 4.");
                        break;
                    }
                    case '5': {
                        // Add owner
                        System.out.println("[WIP] Option 5.");
                        break;
                    }
                    case '6': {
                        // Edit owner
                        System.out.println("[WIP] Option 6.");
                        break;
                    }
                    case '7': {
                        // Delete owner
                        System.out.println("[WIP] Option 7.");
                        break;
                    }
                    case '8': {
                        // Print owner
                        System.out.println("[WIP] Option 8.");
                        break;
                    }
                    case '9': {
                        // Transfer pet
                        System.out.println("[WIP] Option 9.");
                        break;
                    }
                    case 'A': {
                        // List pets
                        this.registry.displayPets();
                        break;
                    }
                    case 'B': {
                        // List owners pets
                        System.out.println("[WIP] Option B.");
                        break;
                    }
                    case 'C': {
                        // List pet type
                        System.out.println("[WIP] Option C.");
                        break;
                    }
                    case 'D': {
                        // List owners
                        this.registry.displayOwners();
                        break;
                    }
                    case 'E': {
                        // Load from text
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
                        break;
                    }
                    case 'F': {
                        // Load from bin
                        System.out.println('E');
                        break;
                    }
                    case 'G': {
                        // Save to bin
                        System.out.println('F');
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

}
