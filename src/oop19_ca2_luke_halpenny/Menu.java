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
        System.out.println("1. Add pet");
        System.out.println("2. Edit pet");
        System.out.println("3. Delete pet");
        System.out.println("4. Display pet");
        System.out.println("5. Add owner");
        System.out.println("6. Edit owner");
        System.out.println("7. Delete owner");
        System.out.println("8. Display owner");
        System.out.println("9. Transfer pet");
        System.out.println("A. List pets");
        System.out.println("B. List owners pets");
        System.out.println("C. List pet type");
        System.out.println("D. Load from text");
        System.out.println("E. Save to bin");
        System.out.println("F. Load from bin");
        System.out.println("[WIP] Options Here.");
        System.out.println("q: Quit");
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
                char cmd = cmdLine.charAt(0);
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
                        Owner owner = this.registry.findOwnerById(owner_id);
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
                        System.out.print("Gender >> ");
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
                        } else if(classtype.equalsIgnoreCase("Fish")) {
                            System.out.print("Water Type(SEAWATER/FRESHWATER/BRACKISH) >> ");
                            WaterType waterType = WaterType.valueOf(input.nextLine());
                            Fish fish = Fish.newPet(owner, name, type, breed, age, colour, Gender.valueOf(gender), waterType);
                        } else if(classtype.equalsIgnoreCase("Mammal")) {
                            System.out.print("Neutered (True/False) >> ");
                            boolean isNeutered = input.nextBoolean();
                            input.nextLine();
                            Mammal mammal = Mammal.newPet(owner, name, type, breed, age, colour, Gender.valueOf(gender), isNeutered);
                        } else {

                        }

                        break;
                    }
                    case '2': {
                        // Edit pet
                        System.out.println("[WIP] Option 2.");
                        this.registry.displayOwners();
                        break;
                    }
                    case '3': {
                        // Delete pet
                        System.out.println("[WIP] Option 3.");
                        this.registry.displayPets();
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
                        System.out.println("[WIP] Option A.");
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
                    case 'E': {
                        // Load from bin
                        System.out.println('E');
                        break;
                    }
                    case 'F': {
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
