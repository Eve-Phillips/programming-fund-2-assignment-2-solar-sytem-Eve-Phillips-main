package controllers;

import models.PlanetarySystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlanetarySystemAPITest {

    PlanetarySystem planetarySystem1, planetarySystem2, planetarySystemEmpty;
    private PlanetarySystemAPI populatedDevices = new PlanetarySystemAPI(new File("planetarySystemsTest.xml"));
    private PlanetarySystemAPI emptyDevices = new PlanetarySystemAPI(new File("planetary.xml"));

    @BeforeEach
    void setUp() {
        planetarySystem1 = new PlanetarySystem("Galaxy Far, far away", "something bright");
        planetarySystem2 = new PlanetarySystem("Solar System", "Sun");
        planetarySystemEmpty = new PlanetarySystem("Empty Galaxy", "No star");

        try {
            populatedDevices.load();  // Load existing data for testing populated devices
            emptyDevices.load();      // Load existing data for testing empty devices
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterEach
    void tearDown() {
        // Optionally clear the data if needed after each test (if not using persistent storage)
    }

    @Nested
    class CRUDMethods {
        @Test
        void addNewPlanetarySystemToEmpty() {
            assertEquals(0, emptyDevices.getPlanetarySystems().size());
            emptyDevices.addPLanetSystem(planetarySystem1);
            assertEquals(1, emptyDevices.getPlanetarySystems().size());
        }

        @Test
        void addNewPlanetarySystemToPopulated() {
            assertEquals(4, populatedDevices.getPlanetarySystems().size());
            populatedDevices.addPLanetSystem(planetarySystem1);
            assertEquals(5, populatedDevices.getPlanetarySystems().size());
        }

        @Test
        void addDuplicatePlanetarySystemFails() {
            populatedDevices.addPLanetSystem(planetarySystem2); // Add Solar System
            assertFalse(populatedDevices.addPLanetSystem(planetarySystem2)); // Try adding again, should fail due to duplicate
        }

        @Test
        void removePlanetarySystemByObject() {
            assertTrue(populatedDevices.getPlanetarySystems().contains(planetarySystem2));
            populatedDevices.removePlanetarySystem(planetarySystem2);
            assertFalse(populatedDevices.getPlanetarySystems().contains(planetarySystem2));
        }

        @Test
        void removePlanetarySystemByName() {
            assertTrue(populatedDevices.getPlanetarySystems().contains(planetarySystem2));
            populatedDevices.removePlanetarySystemByName("Solar System");
            assertFalse(populatedDevices.getPlanetarySystems().contains(planetarySystem2));
        }
    }

    @Nested
    class ListingMethods {
        @Test
        void listAllReturnsNoPlanetarySystemsWhenEmpty() {
            assertEquals(0, emptyDevices.getPlanetarySystems().size());
            assertTrue(emptyDevices.listPlanetarySystems().toLowerCase().contains("no planetary systems"));
        }

        @Test
        void listAllReturnsPlanetarySystemsWhenNotEmpty() {
            assertEquals(4, populatedDevices.getPlanetarySystems().size());
            String populatedDeviceStr = populatedDevices.listPlanetarySystems();
            assertTrue(populatedDeviceStr.contains("Solar System"));
            assertTrue(populatedDeviceStr.contains("GE345"));
            assertTrue(populatedDeviceStr.contains("Generic_234"));
            assertTrue(populatedDeviceStr.contains("orbits around: SUN"));
        }

        @Test
        void listByNameWhereNoneExist() {
            assertEquals(4, populatedDevices.getPlanetarySystems().size());
            String populatedDeviceStr = populatedDevices.listAllByPlanetarySystemName("Solar Doesn't exist");
            assertTrue(populatedDeviceStr.contains("No Planetary Systems"));
        }

        @Test
        void listSortedPlanetarySystems() {
            populatedDevices.sortPlanetarySystemsByName();
            String sortedDeviceStr = populatedDevices.listSortedPlanetarySystems();
            assertTrue(sortedDeviceStr.contains("Solar System"));
            // Further assertions can be added to check sorting
        }

        @Test
        void listSortedByStarName() {
            populatedDevices.sortPlanetarySystemsByStarName();
            String sortedByStarNameStr = populatedDevices.listSortedPlanetarySystems();
            assertTrue(sortedByStarNameStr.contains("orbits around: SUN"));
            // Add more checks based on the sorted list
        }
    }

    @Nested
    class UpdateMethods {
        @Test
        void updatePlanetarySystemStarName() {
            String newStarName = "Alpha Centauri";
            populatedDevices.updatePlanetarySystem("Solar System", newStarName);
            assertEquals("Alpha Centauri", populatedDevices.getPlanetarySystemByName("Solar System").getOrbittingStarName());
        }

        @Test
        void updatePlanetarySystemFailsWhenNotFound() {
            String newStarName = "Unknown Star";
            assertFalse(populatedDevices.updatePlanetarySystem("NonExistent System", newStarName));
        }
    }

    @Nested
    class PersistenceMethods {
        @Test
        void saveAndLoadPlanetarySystems() throws Exception {
            // Save and load from file to verify persistence
            populatedDevices.save();
            PlanetarySystemAPI newDeviceAPI = new PlanetarySystemAPI(new File("planetarySystemsTest.xml"));
            newDeviceAPI.load();
            assertEquals(4, newDeviceAPI.getPlanetarySystems().size());
        }

        @Test
        void loadEmptyPlanetarySystems() throws Exception {
            // Save and load from an empty system file
            emptyDevices.save();
            PlanetarySystemAPI newDeviceAPI = new PlanetarySystemAPI(new File("planetary.xml"));
            newDeviceAPI.load();
            assertEquals(0, newDeviceAPI.getPlanetarySystems().size());  // Check if no systems are loaded from empty file
        }
    }
}
