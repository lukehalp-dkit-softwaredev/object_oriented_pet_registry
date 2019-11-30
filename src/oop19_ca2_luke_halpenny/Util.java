package oop19_ca2_luke_halpenny;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Util class.
 *
 * <p>Generic utility class.</p>
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0.1
 */
public class Util {

    public static final int PROGRAM_ID = 0b10101;
    public static int cumulativeId = 0;
    public static LocalDateTime startTime = LocalDateTime.of(2019, 11, 30, 0, 0);

    /**
     * Reads in lines from a file, returning them as a String[] object. Designed for reading text files.
     * Throws FileNotFoundException if the file does not exist.
     * @param filePath Path to the file being read from
     * @return String[] of lines from file.
     */
    public static String[] readLines(String filePath) throws FileNotFoundException {
        File fileObj = new File(filePath);
        Scanner input = new Scanner(fileObj);
        int count = 0;
        String fullRead = "";
        while(input.hasNextLine()) {
            fullRead += input.nextLine() + "\n";
            count++;
        }
        String[] out = new String[count];
        input.close();
        input = new Scanner(fullRead);
        for (int i = 0; i < count; i++) {
            out[i] = input.nextLine();
        }
        return out;
    }

    /**
     * Generates non-sequential unique ID's using the current time. Based on Twitter and Discord's Snowflake ID system.
     * @return a non-sequential unique ID.
     */
    public static long generateId() {
        long timeNowMilis = LocalDateTime.now().toLocalTime().toNanoOfDay() / 1000000;
        long timeStartMilis = startTime.toLocalTime().toNanoOfDay() / 1000000;
        long timeSinceStartMilis = timeNowMilis - timeStartMilis;
        long timePart = (timeSinceStartMilis & 0x3FFFFFFFFFFL) << 22; // 42 bits, shifted to fill high end of number.
        long processPart = PROGRAM_ID << 17;
        long cumulativeIdPart = (cumulativeId % 0x1FFFF);
        long id = timePart + processPart + cumulativeIdPart;
        System.out.println(id);
        return id;
    }

}
