package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {

    @Test
    public void testConstructorAndGetters() {
        PlanetarySystem solarSystem = new PlanetarySystem("Solar System", "Sun");
        TestPlanet earth = new TestPlanet("Earth", 5.972e24, 12742, solarSystem, 15, "rocky", true);

        assertEquals("Earth", earth.getName());
        assertEquals(5.972e24, earth.getMass());
        assertEquals(12742, earth.getDiameter());
        assertEquals("rocky", earth.getSurfaceType());
        assertEquals(15, earth.getAverageTemperature());
        assertTrue(earth.hasLiquidWater());
        assertEquals(solarSystem, earth.getPlanetarySystem());
    }

    @Test
    public void testSurfaceTypeTruncation() {
        PlanetarySystem system = new PlanetarySystem("Alpha System", "Star-A");
        String longSurface = "volcanicAndMetallicAndMore";
        TestPlanet planet = new TestPlanet("PlanetX", 1.0e22, 5000, system, 100, longSurface, false);

        // Using the setter to change the surfaceType
        planet.setSurfaceType("volcanicAndMetallicAndMore");

        // Directly compare the expected value with the actual truncated value
        assertEquals("volcanicAndMetallic", planet.getSurfaceType());
    }

    @Test
    public void testAverageTemperatureBounds() {
        PlanetarySystem system = new PlanetarySystem("Beta System", "BetaStar");

        TestPlanet coldPlanet = new TestPlanet("Freezer", 1e22, 10000, system, -500, "icy", false);
        assertEquals(0, coldPlanet.getAverageTemperature());  // should default

        TestPlanet hotPlanet = new TestPlanet("Inferno", 1e22, 10000, system, 500, "lava", false);
        assertEquals(0, hotPlanet.getAverageTemperature());  // should default

        TestPlanet validPlanet = new TestPlanet("Comfy", 1e22, 10000, system, 25, "grassland", true);
        assertEquals(25, validPlanet.getAverageTemperature());
    }

    @Test
    public void testGravityCalculation() {
        PlanetarySystem system = new PlanetarySystem("Solar System", "Sun");

        TestPlanet earth = new TestPlanet("Earth", 5.972e24, 12742, system, 15, "rocky", true);

        // Gravity = (mass * G) / (radius^2)
        double expectedGravity = (5.972e24 * 6.67430e-11) / Math.pow(12742 / 2.0, 2);
        assertEquals(expectedGravity, earth.calculateGravity(), 1e-10);
    }

    @Test
    public void testToStringIncludesFields() {
        PlanetarySystem system = new PlanetarySystem("Gamma System", "GammaStar");
        TestPlanet planet = new TestPlanet("Tester", 1.23e22, 7000, system, 50, "dusty", true);

        String result = planet.toString();
        assertTrue(result.contains("Tester"));
        assertTrue(result.contains("dusty"));
        assertTrue(result.contains("50.0°C"));
        assertTrue(result.contains("true"));
    }
}

