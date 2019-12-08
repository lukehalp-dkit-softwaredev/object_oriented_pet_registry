package tests;

import oop19_ca2_luke_halpenny.Main;
import oop19_ca2_luke_halpenny.Owner;
import oop19_ca2_luke_halpenny.Registry;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * UnitTests class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class MenuTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public MenuTests() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();


//    @Test(expected = ArithmeticException.class)
//    public void test() {
//
//    }

    public String getMenuString() {
        String menu1 = "-------- CA2 --------" + System.getProperty("line.separator");
        String menu2 = "Dundalk Pet Registry:" + System.getProperty("line.separator");
        String menu3 = "---------------------" + System.getProperty("line.separator");
        String menu4 = "1. Add pet\t\t\t2. Edit pet\t\t\t3. Delete pet\t\t4. Display pet" + System.getProperty("line.separator");
        String menu5 = "5. Add owner\t\t6. Edit owner\t\t7. Delete owner\t\t8. Display owner" + System.getProperty("line.separator");
        String menu6 = "9. Transfer pet\t\tA. List pets\t\tB. List owners pets\tC. List pet type" + System.getProperty("line.separator");
        String menu7 = "D. List owners\t\tE. Load from text\tF. Save to bin\t\tG. Load from bin" + System.getProperty("line.separator");
        String menu8 = "q: Quit" + System.getProperty("line.separator");
        String menu = menu1+menu2+menu3+menu4+menu5+menu6+menu7+menu8;

        String prompt = ">> ";

        String menuString = menu + prompt;
        return menuString;
    }

    @Test
    public void addOwnerSuccess() {
        systemInMock.provideLines("5","John", "john@example.com", "0123456789", "Address", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Name >> Email >> Phone Number >> Address >> ";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu+p1+menu+goodbye;
        String expErr = "";

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

    @Test
    public void addOwnerFail() {
        systemInMock.provideLines("5","John", "aaaaaaaa", "0123456789", "Address", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Name >> Email >> Phone Number >> Address >> ";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu+p1+menu+goodbye;
        String expErr = "\t[!] Invalid argument: Email not valid." + System.getProperty("line.separator");

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

    @Test
    public void addPetSuccess() {
        systemInMock.provideLines("E","1", "1000", "Squawk", "Bird", "Parrot", "4", "Green", "FEMALE", "Bird", "3.5", "Yes", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Loaded data!" + System.getProperty("line.separator");
        String p2 = "Owner ID >> Name >> Type >> Breed >> Age >> Colour >> Gender(MALE/FEMALE) >> Bird/Fish/Mammal >> "
                + "Wingspan >> Can Fly (Yes/No) >> ";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu+p1+menu+p2+menu+goodbye;
        String expErr = "";

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

    @Test
    public void addPetFail() {
        systemInMock.provideLines("E","1", "1000", "Squawk", "Bird", "Parrot", "blah", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Loaded data!" + System.getProperty("line.separator");
        String p2 = "Owner ID >> Name >> Type >> Breed >> Age >> ";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu+p1+menu+p2+menu+goodbye;
        String expErr = "\t[!] Not a valid number." + System.getProperty("line.separator");

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

    @Test
    public void showOwnerSuccess() {
        systemInMock.provideLines("E","8", "1000", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Loaded data!" + System.getProperty("line.separator");
        String p2 = "Owner ID >> " +
                    "                            ID | Name                 | Email Address                  | Phone Number         | Address                                                \n" +
                    "                          1000 | Luke                 | lukehalp@gmail.com             | 0867349466           | address                                                \n";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu+p1+menu+p2+menu+goodbye;
        String expErr = "";

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

    @Test
    public void showOwnerFail() {
        systemInMock.provideLines("E","8", "1", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Loaded data!" + System.getProperty("line.separator");
        String p2 = "Owner ID >> ";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu+p1+menu+p2+menu+goodbye;
        String expErr = "\t[!] Owner not found!" + System.getProperty("line.separator");

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

    @Test
    public void LoadFilesSuccess() {
        systemInMock.provideLines("E", "A", "ID", "D", "q");

        String[] args = null;
        Main.main(args);

        String menu = getMenuString();
        String p1 = "Loaded data!" + System.getProperty("line.separator");
        String p2 = "Sort by (ID/Age/Gender) >> " + "Pets:\n" +
                "                            ID | Name                 | Type            | Breed           | Age | Colour          | Gender | Date Registered         |                       Owner ID |\n" +
                "                           100 | Chika                | Bird            | Parrot          |   3 | Red             | FEMALE | 2018-05-10T00:00        |                           1000 | Wingspan: 3.500000 | Can Fly: true\n" +
                "                           101 | Doge                 | Dog             | Labrador        |   5 | Golden          | MALE   | 2018-06-05T00:00        |                           1000 | Neutered: true\n" +
                "                           102 | Nemo                 | Fish            | Goldfish        |   1 | Orange          | MALE   | 2019-02-22T00:00        |                           1000 | Water Type: SEAWATER\n";

        String p3 = "Owners:\n" +
                "                            ID | Name                 | Email Address                  | Phone Number         | Address                                                \n" +
                "                          1000 | Luke                 | lukehalp@gmail.com             | 0867349466           | address                                                \n";
        String goodbye = "Quitting.." + System.getProperty("line.separator");

        String expOutput = menu + p1 + menu + p2 + menu + p3 + menu + goodbye;
        String expErr = "";

        assertEquals(expOutput, outContent.toString());
        assertEquals(expErr, errContent.toString());
    }

//    @Test
//    public void LoadFilesFail() {
//        systemInMock.provideLines("E", "A", "ID", "D", "q");
//
//        String[] args = null;
//        Main.main(args);
//
//        String menu = getMenuString();
//        //String p1 = "Loaded data!" + System.getProperty("line.separator");
//        String p2 = "Sort by (ID/Age/Gender) >> " + "Pets:\n" +
//                "                            ID | Name                 | Type            | Breed           | Age | Colour          | Gender | Date Registered         |                       Owner ID |\n";
//
//        String p3 = "Owners:\n" +
//                "                            ID | Name                 | Email Address                  | Phone Number         | Address                                                \n";
//        String goodbye = "Quitting.." + System.getProperty("line.separator");
//
//        String expOutput = menu + menu + p2 + menu + p3 + menu + goodbye;
//        String expErr = "\t[!] Couldn't find owners file!" + System.getProperty("line.separator");
//
//        assertEquals(expOutput, outContent.toString());
//        assertEquals(expErr, errContent.toString());
//    }

//    @Test
//    public void LoadFilesFail2() {
//        systemInMock.provideLines("E", "A", "ID", "D", "q");
//
//        String[] args = null;
//        Main.main(args);
//
//        String menu = getMenuString();
//        //String p1 = "Loaded data!" + System.getProperty("line.separator");
//        String p2 = "Sort by (ID/Age/Gender) >> " + "Pets:\n" +
//                "                            ID | Name                 | Type            | Breed           | Age | Colour          | Gender | Date Registered         |                       Owner ID |\n";
//
//        String p3 = "Owners:\n" +
//                "                            ID | Name                 | Email Address                  | Phone Number         | Address                                                \n" +
//                "                          1000 | Luke                 | lukehalp@gmail.com             | 0867349466           | address                                                \n";
//        String goodbye = "Quitting.." + System.getProperty("line.separator");
//
//        String expOutput = menu + menu + p2 + menu + p3 + menu + goodbye;
//        String expErr = "\t[!] Couldn't find pets file!" + System.getProperty("line.separator");
//
//        assertEquals(expOutput, outContent.toString());
//        assertEquals(expErr, errContent.toString());
//    }

}
