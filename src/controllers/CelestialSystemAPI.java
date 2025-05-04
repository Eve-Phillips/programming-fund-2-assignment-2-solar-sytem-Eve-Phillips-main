package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import models.*;
import utils.ISerializer;

/**
 * CelestialSystemAPI manages a collection of celestial bodies
 * such as stars, gas planets, and ice planets.
 * Provides methods for CRUD operations, reporting, sorting, and persistence.
 */
public class CelestialSystemAPI implements ISerializer {

    // ================================
    // Fields
    // ================================
    private static ArrayList<CelestialBody> celestialList;
    private final File file = new File("celestialBody.xml"); // Always save/load to "celestialBody.xml"

    // ================================
    // Constructor
    // ================================

    /**
     * Constructs a CelestialSystemAPI with an empty celestial body list.
     * The celestial bodies will be saved to and loaded from "celestialBody.xml".
     */
    public CelestialSystemAPI(File file) {
        this.celestialList = new ArrayList<>();
    }

    // ================================
    // CRUD Methods
    // ================================

    /**
     * Adds a celestial object to the system.
     *
     * @param body the celestial object to add
     * @return true if added successfully, otherwise false
     */
    public boolean addCelestialObject(CelestialBody body) {
        return celestialList.add(body);
    }

    /**
     * Deletes a celestial object by index.
     *
     * @param index the index to remove
     * @return the removed celestial object, or null if the index is invalid
     */
    public CelestialBody deleteCelestialIndex(int index) {
        if (index >= 0 && index < celestialList.size()) {
            return celestialList.remove(index);
        }
        return null;
    }

    /**
     * Deletes a celestial object by its ID.
     *
     * @param id the ID of the celestial body to remove
     * @return the removed celestial object, or null if not found
     */
    public CelestialBody deleteCelestialId(int id) {
        for (int i = 0; i < celestialList.size(); i++) {
            CelestialBody body = celestialList.get(i);
            if (body.getId() == id) {
                celestialList.remove(i);
                return body;
            }
        }
        return null;
    }

    /**
     * Gets a celestial body by its index.
     *
     * @param index the index of the celestial body
     * @return the celestial body at the specified index, or null if invalid index
     */
    public CelestialBody getCelestialBodyByIndex(int index) {
        if (index >= 0 && index < celestialList.size()) {
            return celestialList.get(index);
        }
        return null;
    }

    /**
     * Gets a celestial body by its ID.
     *
     * @param id the ID of the celestial body
     * @return the celestial body with the specified ID, or null if not found
     */
    public CelestialBody getCelestialBodyById(int id) {
        for (CelestialBody body : celestialList) {
            if (body.getId() == id) {
                return body;
            }
        }
        return null;
    }

    /**
     * Gets the list of all celestial bodies.
     *
     * @return the list of celestial bodies
     */
    public ArrayList<CelestialBody> getCelestialList() {
        return celestialList;
    }

    /**
     * Gets the file used for saving and loading celestial bodies.
     *
     * @return the file used for persistence
     */
    public File getFile() {
        return file;
    }

    // ================================
    // Reporting Methods
    // ================================

    /**
     * Lists all celestial bodies in the system.
     *
     * @return a string representing all celestial bodies, or a message if none exist
     */
    public String listAllCelestialBodies() {
        if (celestialList.isEmpty()) return "No Celestial Bodies";
        String result = "";
        for (int i = 0; i < celestialList.size(); i++) {
            result += i + ": " + celestialList.get(i).displayInfo() + "\n";
        }
        return result.trim();
    }

    /**
     * Lists all gas planets in the system.
     *
     * @return a string representing all gas planets, or a message if none exist
     */
    public String listAllGasPlanets() {
        return listByType("GasPlanet", "Gas Planets");
    }

    /**
     * Lists all ice planets in the system.
     *
     * @return a string representing all ice planets, or a message if none exist
     */
    public String listAllIcePlanets() {
        return listByType("IcePlanet", "Ice Planets");
    }

    /**
     * Lists all stars in the system.
     *
     * @return a string representing all stars, or a message if none exist
     */
    public String listAllStars() {
        return listByType("Star", "Stars");
    }

    /**
     * Lists celestial bodies by their type.
     *
     * @param typeName the class name of the type to filter by
     * @param displayName the name to display in the report
     * @return a string representing the filtered celestial bodies, or a message if none exist
     */
    private String listByType(String typeName, String displayName) {
        String result = "";
        int count = 0;
        for (int i = 0; i < celestialList.size(); i++) {
            CelestialBody cb = celestialList.get(i);
            if (cb.getClass().getSimpleName().equals(typeName)) {
                result += i + ": " + cb.displayInfo() + "\n";
                count++;
            }
        }
        return count == 0 ? "No " + displayName : result.trim();
    }

    /**
     * Lists all stars with a specific spectral type.
     *
     * @param spectralType the spectral type of the stars
     * @return a string representing all stars of the given spectral type, or a message if none exist
     */
    public String listAllStarsForSpectralType(char spectralType) {
        String result = "";
        int count = 0;
        for (CelestialBody cb : celestialList) {
            if (cb instanceof Star star && star.getSpectralType() == spectralType) {
                result += cb.displayInfo() + "\n";
                count++;
            }
        }
        return count == 0 ? "No stars for spectral type " + spectralType : result.trim();
    }

    /**
     * Lists all celestial objects heavier than a specified mass.
     *
     * @param mass the mass threshold
     * @return a string representing all celestial objects heavier than the given mass
     */
    public String listAllCelestialObjectsHeavierThan(double mass) {
        return filterByMass(mass, true);
    }

    /**
     * Lists all celestial objects smaller than a specified diameter.
     *
     * @param diameter the diameter threshold
     * @return a string representing all celestial objects smaller than the given diameter
     */
    public String listAllCelestialObjectsSmallerThan(double diameter) {
        return filterByDiameter(diameter, true);
    }

    /**
     * Filters celestial objects by mass, either heavier or lighter than the specified threshold.
     *
     * @param threshold the mass threshold
     * @param isHeavier if true, filter for objects heavier than the threshold; otherwise for lighter
     * @return a string representing the filtered celestial objects
     */
    private String filterByMass(double threshold, boolean isHeavier) {
        String result = "";
        int count = 0;
        for (CelestialBody cb : celestialList) {
            if ((isHeavier && cb.getMass() > threshold) ||  // changed from >= to >, now passing all tests
                    (!isHeavier && cb.getMass() < threshold)) {
                result += cb.displayInfo() + "\n";
                count++;
            }
        }
        return count == 0 ? "No celestial body matching the criteria" : result.trim();
    }

    /**
     * Filters celestial objects by diameter, either smaller or larger than the specified threshold.
     *
     * @param threshold the diameter threshold
     * @param isSmaller if true, filter for objects smaller than the threshold; otherwise for larger
     * @return a string representing the filtered celestial objects
     */
    private String filterByDiameter(double threshold, boolean isSmaller) {
        String result = "";
        int count = 0;
        for (CelestialBody cb : celestialList) {
            if ((isSmaller && cb.getDiameter() <= threshold) ||
                    (!isSmaller && cb.getDiameter() > threshold)) {
                result += cb.displayInfo() + "\n";
                count++;
            }
        }
        return count == 0 ? "No celestial body matching the criteria" : result.trim();
    }

    /**
     * Lists all celestial objects belonging to a specific planetary system.
     *
     * @param system the planetary system to filter by
     * @return a string representing the celestial objects belonging to the specified planetary system
     */
    public String listAllCelestialObjectsForGivenPlanetary(PlanetarySystem system) {
        if (system == null) return "Invalid Planetary System";

        String result = "";
        int count = 0;
        for (CelestialBody cb : celestialList) {
            if (cb.getPlanetarySystem() != null &&
                    cb.getPlanetarySystem().equals(system)) {
                result += cb.displayInfo() + "\n";
                count++;
            }
        }
        return count == 0 ? "No celestial body in the " + system : result.trim();
    }

    // Count methods

    /**
     * Returns the number of celestial bodies in the system.
     *
     * @return the number of celestial bodies
     */
    public int numberOfCelestialBodies() {
        return celestialList.size();
    }

    /**
     * Returns the number of stars in the system.
     *
     * @return the number of stars
     */
    public int numberOfStars() {
        return countType("Star");
    }

    /**
     * Returns the number of ice planets in the system.
     *
     * @return the number of ice planets
     */
    public int numberOfIcePlanets() {
        return countType("IcePlanet");
    }

    /**
     * Returns the number of gas planets in the system.
     *
     * @return the number of gas planets
     */
    public int numberOfGasPlanets() {
        return countType("GasPlanet");
    }

    /**
     * Returns the number of celestial bodies belonging to a specific planetary system.
     *
     * @param system the planetary system to count celestial bodies for
     * @return the number of celestial bodies in the specified planetary system
     */
    public int numberOfCelestialBodyByChosenPlanetarySystem(PlanetarySystem system) {
        if (system == null) return 0;

        int count = 0;
        for (CelestialBody cb : celestialList) {
            if (cb.getPlanetarySystem() != null &&
                    cb.getPlanetarySystem().equals(system)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of celestial bodies of a specific type.
     *
     * @param className the name of the class type to count
     * @return the number of celestial bodies of the specified type
     */
    private int countType(String className) {
        int count = 0;
        for (CelestialBody cb : celestialList) {
            if (cb.getClass().getSimpleName().equals(className)) {
                count++;
            }
        }
        return count;
    }

    // ================================
    // Update Methods
    // ================================

    /**
     * Updates the details of a star.
     *
     * @param id the ID of the star to update
     * @param updatedDetails the updated details of the star
     * @return true if the star was updated successfully, otherwise false
     */
    public boolean updateStar(int id, Star updatedDetails) {
        return updateCelestial(id, updatedDetails);
    }

    /**
     * Updates the details of a gas planet.
     *
     * @param id the ID of the gas planet to update
     * @param updatedDetails the updated details of the gas planet
     * @return true if the gas planet was updated successfully, otherwise false
     */
    public boolean updateGasPlanet(int id, GasPlanet updatedDetails) {
        return updateCelestial(id, updatedDetails);
    }

    /**
     * Updates the details of an ice planet.
     *
     * @param id the ID of the ice planet to update
     * @param updatedDetails the updated details of the ice planet
     * @return true if the ice planet was updated successfully, otherwise false
     */
    public boolean updateIcePlanet(int id, IcePlanet updatedDetails) {
        return updateCelestial(id, updatedDetails);
    }

    /**
     * Updates the details of a celestial body (generic method).
     *
     * @param id the ID of the celestial body to update
     * @param updatedDetails the updated details of the celestial body
     * @return true if the celestial body was updated successfully, otherwise false
     */
    public boolean updateCelestial(int id, CelestialBody updatedDetails) {
        for (int i = 0; i < celestialList.size(); i++) {
            if (celestialList.get(i).getId() == id &&
                    celestialList.get(i).getClass().equals(updatedDetails.getClass())) {
                celestialList.set(i, updatedDetails);
                return true;
            }
        }
        return false;
    }

    // ================================
    // Validation Methods
    // ================================

    /**
     * Checks if a celestial object with a given ID exists.
     *
     * @param id the ID of the celestial body
     * @return the index of the celestial body if found, otherwise -1
     */
    public int isValidId(int id) {
        for (int i = 0; i < celestialList.size(); i++) {
            if (celestialList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Validates if an index is within the bounds of the celestial list.
     *
     * @param index the index to validate
     * @return true if the index is valid, otherwise false
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < celestialList.size();
    }

    // ================================
    // Sorting Methods
    // ================================

    /**
     * Sorts celestial objects by mass in descending order.
     */
    public void sortByMassDescending() {
        int n = celestialList.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (celestialList.get(j).getMass() > celestialList.get(maxIndex).getMass()) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                swapCelestialBody(celestialList, i, maxIndex);
            }
        }
    }

    /**
     * Sorts celestial objects by diameter in ascending order.
     */
    public void sortByDiameterAscending() {
        int n = celestialList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (celestialList.get(j).getDiameter() < celestialList.get(minIndex).getDiameter()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swapCelestialBody(celestialList, i, minIndex);
            }
        }
    }

    /**
     * Swaps two celestial bodies at specified indices in the list.
     *
     * @param list the list of celestial bodies
     * @param currentIndex the index of the first celestial body
     * @param otherIndex the index of the second celestial body
     */
    private void swapCelestialBody(List<CelestialBody> list, int currentIndex, int otherIndex) {
        CelestialBody temp = list.get(currentIndex);
        list.set(currentIndex, list.get(otherIndex));
        list.set(otherIndex, temp);
    }

    /**
     * Sorts celestial objects by name in ascending order.
     */
    public void sortByNameAscending() {
        int n = celestialList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (celestialList.get(j).getName().compareTo(celestialList.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swapCelestialBody(celestialList, i, minIndex);
            }
        }
    }

    /**
     * Sorts celestial objects by orbitting star name in ascending order.
     */
    public void sortByOrbittingStarNameAscending() {
        int n = celestialList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (celestialList.get(j).getPlanetarySystem().getOrbittingStarName()
                        .compareTo(celestialList.get(minIndex).getPlanetarySystem().getOrbittingStarName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swapCelestialBody(celestialList, i, minIndex);
            }
        }
    }

    // ================================
    // Other Methods
    // ================================

    /**
     * Returns a list of the top 5 gas planets with the highest radiation levels.
     *
     * @return a list of the top 5 gas planets
     */
    public List<GasPlanet> topFiveHighestRadiationGasPlanet() {
        List<GasPlanet> gasPlanets = new ArrayList<>();

        // Collect all GasPlanet objects from celestialList
        for (CelestialBody body : celestialList) {
            if (body instanceof GasPlanet) {
                gasPlanets.add((GasPlanet) body);
            }
        }

        // Sort gas planets by radiation level descending (highest first)
        for (int i = 0; i < gasPlanets.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < gasPlanets.size(); j++) {
                if (gasPlanets.get(j).getRadiationLevel() > gasPlanets.get(maxIndex).getRadiationLevel()) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                GasPlanet temp = gasPlanets.get(i);
                gasPlanets.set(i, gasPlanets.get(maxIndex));
                gasPlanets.set(maxIndex, temp);
            }
        }

        // Return top 5 (or fewer if not enough)
        return gasPlanets.subList(0, Math.min(5, gasPlanets.size()));
    }

    /**
     * Checks if a celestial body belongs to a specific planetary system.
     *
     * @param cb the celestial body to check
     * @param ps the planetary system to check for
     * @return true if the celestial body belongs to the planetary system, otherwise false
     */
    public boolean bodyHasAsPlanetarySystem(CelestialBody cb, PlanetarySystem ps) {
        if (cb == null || ps == null) {
            return false;
        }
        return ps.equals(cb.getPlanetarySystem());
    }

    /**
     * Searches for a celestial body by its name.
     *
     * @param name the name of the celestial body to search for
     * @return the celestial body with the specified name, or null if not found
     */
    public static CelestialBody searchCelestialBodyByName(String name) {
        for (CelestialBody body : celestialList) {
            if (body.getName().equalsIgnoreCase(name)) {
                return body;  // Return the first match (you can change this to return a list if needed)
            }
        }
        return null; // If not found, return null
    }

    /**
     * Searches for a celestial body by its mass.
     *
     * @param mass the mass of the celestial body to search for
     * @return the celestial body with the specified mass, or null if not found
     */
    public static CelestialBody searchCelestialBodyByMass(double mass) {
        for (CelestialBody body : celestialList) {
            if (body.getMass() == mass) {
                return body;  // Return the first match
            }
        }
        return null; // If not found, return null
    }

    /**
     * Searches for a celestial body by its diameter.
     *
     * @param diameter the diameter of the celestial body to search for
     * @return the celestial body with the specified diameter, or null if not found
     */
    public static CelestialBody searchCelestialBodyByDiameter(double diameter) {
        for (CelestialBody body : celestialList) {
            if (body.getDiameter() == diameter) {
                return body;  // Return the first match
            }
        }
        return null; // If not found, return null
    }

    // ================================
    // Persistence Methods
    // ================================

    /**
     * Saves the celestial bodies to a file using the XStream serialization.
     */
    @Override
    public void save() {
        try {
            XStream xstream = new XStream();
            xstream.allowTypes(new Class[] {
                    CelestialBody.class, GasPlanet.class, IcePlanet.class, Star.class, Planet.class, PlanetarySystem.class
            });

            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
            out.writeObject(celestialList);
            out.close();
        } catch (Exception e) {
            System.err.println("Error saving celestial bodies: " + e.getMessage());
        }
    }

    /**
     * Loads the celestial bodies from a file using the XStream serialization.
     */
    @Override
    public void load() {
        if (file.exists()) {
            try {
                XStream xstream = new XStream();
                xstream.allowTypes(new Class[] {
                        CelestialBody.class, GasPlanet.class, IcePlanet.class, Star.class, Planet.class, PlanetarySystem.class
                });

                ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
                celestialList = (ArrayList<CelestialBody>) in.readObject();
                in.close();
            } catch (Exception e) {
                System.err.println("Error loading celestial bodies: " + e.getMessage());
            }
        } else {
            System.out.println("No celestialBody.xml file found. Starting with empty list.");
        }
    }

    /**
     * Returns the file name used for persistence.
     *
     * @return the file name for saving and loading celestial bodies
     */
    @Override
    public String fileName() {
        return file.getName(); // Returns "celestialBody.xml"
    }
}
