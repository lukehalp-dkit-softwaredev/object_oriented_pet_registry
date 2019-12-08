package tests;

import oop19_ca2_luke_halpenny.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * UnitTests class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class UnitTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public UnitTests() {

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

    // OWNER

    @Test
    public void testOwnerNewOwner() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        assertNotNull(owner);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOwnerNewOwnerEmailFail() {
        Owner owner = Owner.newOwner("John", "john", "0123456789", "Address");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOwnerNewOwnerEmailFail2() {
        Owner owner = Owner.newOwner("John", "john@example..com", "0123456789", "Address");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOwnerNewOwnerTelephoneFail() {
        Owner owner = Owner.newOwner("John", "john@example.com", "abcdefg", "Address");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOwnerNewOwnerTelephoneFail2() {
        Owner owner = Owner.newOwner("John", "john@example.com", "+00000000000000000", "Address");
    }

    @Test
    public void testOwnerFromString() {
        Owner owner = Owner.fromString("1 John john@example.com 0123456789 Address");
        assertNotNull(owner);
    }

    @Test
    public void testOwnerToString() {
        Owner owner = Owner.fromString("1 John john@example.com 0123456789 Address");
        String expected = String.format("Owner(id=%d,name=%s,email=%s,telephone=%s,address=%s,#pets=%d)", 1,
                "John", "john@example.com", "0123456789", "Address", 0);
        assertEquals(expected, owner.toString());
    }

    // BIRD

    @Test
    public void testBirdNewPet() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, 3.5, true);
        assertNotNull(bird);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdNewPetAgeFail() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", -3, "Green", Gender.MALE, 3.5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdNewPetWingspanFail() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, -3.5, true);
    }

    @Test
    public void testBirdFromString() {
        Bird bird = Bird.fromString("100 Squawk Bird Parrot 3 Green MALE 1 2018-05-10T00:00:00 3.5 true");
        assertNotNull(bird);
    }

    @Test
    public void testBirdToString() {
        Bird bird = Bird.fromString("100 Squawk Bird Parrot 3 Green MALE 1 2018-05-10T00:00:00 3.5 true");
        String expected = String.format("%s(id=%d,name=%s,type=%s,breed=%s,age=%d,colour=%s,gender=%s,dateRegistered=%s)",
                "Bird", 100,
                "Squawk", "Bird", "Parrot", 3, "Green",
                "MALE", "2018-05-10T00:00:00")
                + String.format("(wingspan=%.2f,canFly=%s)",
                3.5, "true");
        assertEquals(expected, bird.toString());
    }

    // FISH

    @Test
    public void testFishNewPet() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Fish fish = Fish.newPet(owner, "Nemo", "Fish", "Goldfish", 3, "Orange", Gender.MALE, WaterType.FRESHWATER);
        assertNotNull(fish);
    }

    @Test
    public void testFishFromString() {
        Fish fish = Fish.fromString("101 Nemo Fish Goldfish 3 Orange MALE 1 2018-05-10T00:00:00 FRESHWATER");
        assertNotNull(fish);
    }

    @Test
    public void testFishToString() {
        Fish fish = Fish.fromString("101 Nemo Fish Goldfish 3 Orange MALE 1 2018-05-10T00:00:00 FRESHWATER");
        String expected = String.format("%s(id=%d,name=%s,type=%s,breed=%s,age=%d,colour=%s,gender=%s,dateRegistered=%s)",
                "Fish", 101,
                "Nemo", "Fish", "Goldfish", 3, "Orange",
                "MALE", "2018-05-10T00:00:00")
                + String.format("(waterType=%s)",
                "FRESHWATER");
        assertEquals(expected, fish.toString());
    }

    // MAMMAL

    @Test
    public void testMammalNewPet() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Mammal mammal = Mammal.newPet(owner, "Buddy", "Dog", "Labrador", 3, "Gold", Gender.MALE, true);
        assertNotNull(mammal);
    }

    @Test
    public void testMammalFromString() {
        Mammal mammal = Mammal.fromString("102 Buddy Dog Labrador 3 Gold MALE 1 2018-05-10T00:00:00 True");
        assertNotNull(mammal);
    }

    @Test
    public void testMammalToString() {
        Mammal mammal = Mammal.fromString("102 Buddy Dog Labrador 3 Gold MALE 1 2018-05-10T00:00:00 True");
        String expected = String.format("%s(id=%d,name=%s,type=%s,breed=%s,age=%d,colour=%s,gender=%s,dateRegistered=%s)",
                "Mammal", 102,
                "Buddy", "Dog", "Labrador", 3, "Gold",
                "MALE", "2018-05-10T00:00:00")
                + String.format("(neutered=%s)",
                "true");
        assertEquals(expected, mammal.toString());
    }

    // OWNER + PET

    @Test
    public void testOwnerAddPet() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, 3.5, true);
        owner.addPet(bird);
        ArrayList<Pet> expected = new ArrayList<Pet>();
        expected.add(bird);
        assertEquals(expected, owner.getPets());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOwnerAddPetFail() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, 3.5, true);
        owner.addPet(bird);
        owner.addPet(bird);
    }

    @Test
    public void testOwnerGetPet() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, 3.5, true);
        owner.addPet(bird);
        Pet newBird = owner.getPet(bird.getId());
        assertEquals(bird, newBird);
    }

    @Test
    public void testOwnerGetPetFail() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, 3.5, true);
        Pet newBird = owner.getPet(1);
        assertNull(newBird);
    }

    @Test
    public void testOwnerRemovePet() {
        Owner owner = Owner.newOwner("John", "john@example.com", "0123456789", "Address");
        Bird bird = Bird.newPet(owner, "Squawk", "Bird", "Parrot", 3, "Green", Gender.MALE, 3.5, true);
        owner.addPet(bird);
        owner.removePet(bird);
        ArrayList<Pet> expected = new ArrayList<Pet>();
        assertEquals(expected, owner.getPets());
    }

}
