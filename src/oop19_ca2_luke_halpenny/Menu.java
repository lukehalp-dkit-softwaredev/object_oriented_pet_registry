package oop19_ca2_luke_halpenny;

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
    boolean running;

    public Menu() {
        this.input = new Scanner(System.in);
    }

    void printMenu() {
        System.out.println("-------- CA2 --------");
        System.out.println("Dundalk Pet Registry:");
        System.out.println("---------------------");
        System.out.println("[WIP] Options Here.");
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
                        System.out.println("[WIP] Option 1.");
                        break;
                    }
                    case '2': {
                        System.out.println("[WIP] Option 2.");
                        break;
                    }
                    case '3': {
                        System.out.println("[WIP] Option 3.");
                        break;
                    }
                    case '4': {
                        System.out.println("[WIP] Option 4.");
                        break;
                    }
                    case '5': {
                        System.out.println("[WIP] Option 5.");
                        break;
                    }
                    case '6': {
                        System.out.println("[WIP] Option 6.");
                        break;
                    }
                    case '7': {
                        System.out.println("[WIP] Option 7.");
                        break;
                    }
                    case '8': {
                        System.out.println("[WIP] Option 8.");
                        break;
                    }
                    case '9': {
                        System.out.println("[WIP] Option 9.");
                        break;
                    }
                    case 'A': {
                        System.out.println("[WIP] Option A.");
                        break;
                    }
                    case 'B': {
                        System.out.println("[WIP] Option B.");
                        break;
                    }
                    case 'C': {
                        System.out.println("[WIP] Option C.");
                        break;
                    }
                    case 'D': {
                        System.out.println("[WIP] Option D.");
                        break;
                    }
                    default: {
                        System.err.println(ERR_INVALID_CMD);
                    }
                }
            } else {
                System.err.println(ERR_INVALID_CMD);
            }
        }
    }

}
