package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GasPlanetTest {

    private GasPlanet gasPlanet;

    // This will run before each test to initialize a GasPlanet instance
    @BeforeEach
    public void setUp() {
        // Initialize a PlanetarySystem with both required parameters
        PlanetarySystem testPlanetarySystem = new PlanetarySystem("Test System", "Sun");

        // Initialize GasPlanet with dummy values
        gasPlanet = new GasPlanet(
                "Test Gas Planet",
                1.2e24, // mass
                120000,  // diameter
                testPlanetarySystem,
                150, // averageTemperature
                "Gas", // surfaceType
                false, // hasLiquidWater
                "Hydrogen, Helium", // gasComposition
                "Iron, Nickel", // coreComposition
                10.5 // radiationLevel
        );
    }

    @Test
    public void testConstructorInitialization() {
        assertEquals("Test Gas Planet", gasPlanet.getName());
        assertEquals(1.2e24, gasPlanet.getMass());
        assertEquals(120000, gasPlanet.getDiameter());
        assertEquals(150, gasPlanet.getAverageTemperature());
        assertEquals("Gas", gasPlanet.getSurfaceType());
        assertFalse(gasPlanet.hasLiquidWater());
        assertEquals("Hydrogen, Helium", gasPlanet.getGasComposition());
        assertEquals("Iron, Nickel", gasPlanet.getCoreComposition());
        assertEquals(10.5, gasPlanet.getRadiationLevel());
    }

    @Test
    public void testSettersAndGetters() {
        gasPlanet.setGasComposition("Methane, Ammonia");
        gasPlanet.setCoreComposition("Rock, Iron");
        gasPlanet.setRadiationLevel(20.0);

        assertEquals("Methane, Ammonia", gasPlanet.getGasComposition());
        assertEquals("Rock, Iron", gasPlanet.getCoreComposition());
        assertEquals(20.0, gasPlanet.getRadiationLevel());
    }

    @Test
    public void testDisplayInfo() {
        String expectedInfo = "Name: Test Gas Planet, Gas Composition: Hydrogen, Helium, Core Composition: Iron, Nickel, Radiation Level: 10.5";
        assertEquals(expectedInfo, gasPlanet.displayInfo());
    }

    @Test
    public void testClassifyBody() {
        String expectedClassification = "Gas Planet";
        assertEquals(expectedClassification, gasPlanet.classifyBody());
    }

    @Test
    public void testToString() {
        // Corrected expected string to match the format used in GasPlanet's toString method
        String expectedToString = "Name: Test Gas Planet\n" +
                "Mass: 1.2E24kg\n" +
                "Diameter: 120000.0km\n" +
                "PlanetarySystem Name: Test System, orbits around: SUN\n" + // Correctly formatted planetary system
                "Surface Type: Gas\n" +
                "Avg Temp: 150.0°C\n" +
                "Has Liquid Water: false\n" +
                "Gas Composition: Hydrogen, Helium\n" + // Assuming multiple gases are represented as a comma-separated list
                "Core Composition: Iron, Nickel\n" + // Assuming multiple core elements
                "Radiation Level: 10.5";

        // Compare the actual output of toString() with the expected output
        assertEquals(expectedToString, gasPlanet.toString());
    }
}