package controllers;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CelestialSystemAPITest {

    private CelestialSystemAPI api;
    private Star testStar;
    private GasPlanet testGasPlanet;
    private IcePlanet testIcePlanet;
    private PlanetarySystem testSystem;

    @BeforeEach
    void setUp() {
        File testFile = new File("testCelestialBody.xml");
        api = new CelestialSystemAPI(testFile);

        testSystem = new PlanetarySystem("TestSystem", "Alpha");

        // Creating sample celestial bodies with required parameters
        testStar = new Star("Sun", 1.0e30, 1.4e6, testSystem, 'G', 1.0);
        testGasPlanet = new GasPlanet("Jupiter", 1.9e27, 1.42e5, testSystem,
                -108, "Gas Surface", false,
                "Hydrogen, Helium", "Rocky", 75.0);
        testIcePlanet = new IcePlanet("Neptune", 1.0e26, 4.9e4, testSystem,
                -200, "Icy Surface", false, "Methane Ice");

        api.addCelestialObject(testStar);
        api.addCelestialObject(testGasPlanet);
        api.addCelestialObject(testIcePlanet);
    }

    @Test
    void testAddCelestialObject() {
        CelestialBody newStar = new Star("Alpha Centauri", 2.0e30, 1.5e6, testSystem, 'A', 1.2);
        int initialSize = api.numberOfCelestialBodies();
        assertTrue(api.addCelestialObject(newStar));
        assertEquals(initialSize + 1, api.numberOfCelestialBodies());
    }

    @Test
    void testDeleteCelestialIndex() {
        CelestialBody removed = api.deleteCelestialIndex(0);
        assertNotNull(removed);
        assertEquals(2, api.numberOfCelestialBodies());
    }

    @Test
    void testDeleteCelestialId() {
        int idToRemove = testGasPlanet.getId();
        CelestialBody removed = api.deleteCelestialId(idToRemove);
        assertNotNull(removed);
        assertNull(api.getCelestialBodyById(idToRemove));
    }

    @Test
    void testGetCelestialBodyByIndex() {
        assertEquals(testStar.getName(), api.getCelestialBodyByIndex(0).getName());
        assertNull(api.getCelestialBodyByIndex(10));
    }

    @Test
    void testGetCelestialBodyById() {
        assertEquals(testIcePlanet.getName(), api.getCelestialBodyById(testIcePlanet.getId()).getName());
        assertNull(api.getCelestialBodyById(9999));
    }

    @Test
    void testListAllCelestialBodies() {
        String result = api.listAllCelestialBodies();
        assertTrue(result.contains("Sun"));
        assertTrue(result.contains("Jupiter"));
        assertTrue(result.contains("Neptune"));
    }

    @Test
    void testListAllGasPlanets() {
        String result = api.listAllGasPlanets();
        assertTrue(result.contains("Gas Composition:"));
    }

    @Test
    void testListAllIcePlanets() {
        String result = api.listAllIcePlanets();
        assertTrue(result.contains("Ice Composition:"));
    }

    @Test
    void testListAllStars() {
        String result = api.listAllStars();
        assertTrue(result.contains("Spectral Type:"));
    }

    @Test
    void testListByMass() {
        String result = api.listAllCelestialObjectsHeavierThan(1e26);
        assertTrue(result.contains("Sun"));
        assertTrue(result.contains("Jupiter"));
        assertFalse(result.contains("Neptune"));
    }

    @Test
    void testSortByMassDescending() {
        api.sortByMassDescending();
        List<CelestialBody> sorted = api.getCelestialList();
        assertTrue(sorted.get(0).getMass() >= sorted.get(1).getMass());
    }

    @Test
    void testSearchCelestialBodyByName() {
        CelestialBody found = CelestialSystemAPI.searchCelestialBodyByName("sun");
        assertNotNull(found);
        assertEquals("Sun", found.getName());
    }

    @Test
    void testTopFiveGasPlanets() {
        List<GasPlanet> top = api.topFiveHighestRadiationGasPlanet();
        assertEquals(1, top.size());
        assertEquals(testGasPlanet.getRadiationLevel(), top.get(0).getRadiationLevel());
    }

    @Test
    void testIsValidIndex() {
        assertTrue(api.isValidIndex(0));
        assertFalse(api.isValidIndex(99));
    }

    @Test
    void testIsValidId() {
        assertNotEquals(-1, api.isValidId(testStar.getId()));
        assertEquals(-1, api.isValidId(9999));
    }
}

