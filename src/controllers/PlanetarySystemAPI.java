package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.PlanetarySystem;
import utils.ISerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static utils.Utilities.isValidIndex;

/**
 * This class manages a collection of planetary systems.
 * It provides methods to create, read, update, delete, and persist planetary system data.
 */
public class PlanetarySystemAPI implements ISerializer {

    // List to hold planetary systems
    private List<PlanetarySystem> planetarySystemList = new ArrayList<>();

    // File where planetary system data is stored
    private File file;

    /**
     * Constructor to initialize the PlanetarySystemAPI with a file.
     *
     * @param file the file where planetary system data will be saved or loaded from
     */
    public PlanetarySystemAPI(File file) {
        this.file = file;
    }

    //---------------------
    // Create methods
    //---------------------

    /**
     * Adds a new planetary system to the list.
     *
     * @param planetarySystem the planetary system to add
     * @return true if the planetary system was added successfully, false if the system name is already taken
     */
    public boolean addPLanetSystem(PlanetarySystem planetarySystem) {
        if (isValidPlanetSys(planetarySystem.getSystemName())) {
            return false; // System name already exists
        }
        return planetarySystemList.add(planetarySystem);
    }

    //---------------------
    // Read methods
    //---------------------

    /**
     * Retrieves a planetary system by its index in the list.
     *
     * @param index the index of the planetary system in the list
     * @return the planetary system at the specified index, or null if the index is invalid
     */
    public PlanetarySystem getPlanetarySystemByIndex(int index) {
        if (isValidIndex(planetarySystemList, index)) {
            return planetarySystemList.get(index);
        } else {
            return null; // Invalid index
        }
    }

    /**
     * Retrieves a planetary system by its name.
     *
     * @param pName the name of the planetary system to search for
     * @return the planetary system with the specified name, or null if not found
     */
    public PlanetarySystem getPlanetarySystemByName(String pName) {
        int index = retrievePlanetarySystemIndex(pName);
        if (index != -1) {
            return planetarySystemList.get(index);
        }
        return null; // Planetary system not found
    }

    /**
     * Lists all planetary systems in the collection.
     *
     * @return a string representation of all planetary systems, or a message indicating there are none
     */
    public String listPlanetarySystems() {
        String listPlanetarySystems = "";
        for (PlanetarySystem planetarySystem : planetarySystemList) {
            listPlanetarySystems += planetarySystemList.indexOf(planetarySystem) + ": " + planetarySystem + "\n";
        }
        if (listPlanetarySystems.equals("")) {
            return "No Planetary Systems"; // No planetary systems in the list
        } else {
            return listPlanetarySystems;
        }
    }

    /**
     * Lists all planetary systems that match the given system name.
     *
     * @param pName the name of the planetary system to search for
     * @return a string representation of matching planetary systems, or a message indicating none were found
     */
    public String listAllByPlanetarySystemName(String pName) {
        if (!planetarySystemList.isEmpty()) {
            String listPlanetarySystems = "";
            for (PlanetarySystem planetarySystem : planetarySystemList) {
                if (planetarySystem.getSystemName().equalsIgnoreCase(pName)) {
                    listPlanetarySystems += planetarySystemList.indexOf(planetarySystem) + ": " + planetarySystem + "\n";
                }
            }
            if (listPlanetarySystems.equals("")) {
                return "No Planetary Systems of that name"; // No matching planetary systems
            } else {
                return listPlanetarySystems;
            }
        } else {
            return "There are no Planetary Systems in the list."; // No planetary systems in the list
        }
    }

    //---------------------
    // Update methods
    //---------------------

    /**
     * Updates the star name of a planetary system.
     *
     * @param pSysName the name of the planetary system to update
     * @param starName the new star name
     * @return true if the planetary system was updated successfully, false if the system name was not found
     */
    public boolean updatePlanetarySystem(String pSysName, String starName) {
        if (isValidPlanetSys(pSysName)) {
            PlanetarySystem planetarySystemByName = getPlanetarySystemByName(pSysName);
            planetarySystemByName.setOrbittingStarName(starName);
            return true;
        }
        return false; // System name not found
    }

    //---------------------
    // Delete methods
    //---------------------

    /**
     * Removes a planetary system from the list.
     *
     * @param planetarySystem the planetary system to remove
     * @return true if the planetary system was removed, false if it was not in the list
     */
    public boolean removePlanetarySystem(PlanetarySystem planetarySystem) {
        if (planetarySystemList.contains(planetarySystem)) {
            return planetarySystemList.remove(planetarySystem);
        }
        return false; // Planetary system not found
    }

    /**
     * Removes a planetary system by its name.
     *
     * @param planetarySystemName the name of the planetary system to remove
     * @return the removed planetary system, or null if not found
     */
    public PlanetarySystem removePlanetarySystemByName(String planetarySystemName) {
        int index = retrievePlanetarySystemIndex(planetarySystemName);
        if (index != -1) {
            return planetarySystemList.remove(index);
        }
        return null; // Planetary system not found
    }

    //---------------------
    // Validation Methods
    //---------------------

    /**
     * Checks if a planetary system name is valid (i.e., it does not already exist in the list).
     *
     * @param planSysName the name of the planetary system to check
     * @return true if the planetary system name is valid (does not exist), false otherwise
     */
    public boolean isValidPlanetSys(String planSysName) {
        for (PlanetarySystem planetarySystem : planetarySystemList) {
            if (planetarySystem.getSystemName().equalsIgnoreCase(planSysName)) {
                return true; // Planetary system name exists
            }
        }
        return false; // Planetary system name does not exist
    }

    /**
     * Retrieves the index of a planetary system by its name.
     *
     * @param planetarySystemName the name of the planetary system to search for
     * @return the index of the planetary system, or -1 if not found
     */
    public int retrievePlanetarySystemIndex(String planetarySystemName) {
        for (PlanetarySystem planetarySystem : planetarySystemList) {
            if (planetarySystem.getSystemName().equalsIgnoreCase(planetarySystemName)) {
                return planetarySystemList.indexOf(planetarySystem);
            }
        }
        return -1; // Planetary system not found
    }

    //---------------------
    // Getters/Setters
    //---------------------

    /**
     * Retrieves the list of all planetary systems.
     *
     * @return the list of planetary systems
     */
    public List<PlanetarySystem> getPlanetarySystems() {
        return planetarySystemList;
    }

    //---------------------
    // Persistence Methods
    //---------------------

    /**
     * Returns the file name where the planetary systems are saved or loaded from.
     *
     * @return the file name
     */
    @Override
    public String fileName() {
        return String.valueOf(file);
    }

    /**
     * Saves the list of planetary systems to the specified file.
     *
     * @throws Exception if an error occurs during serialization
     */
    public void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(planetarySystemList);
        os.close();
    }

    //---------------------
    // For Sorting
    //---------------------

    /**
     * Swaps two planetary systems in the list.
     *
     * @param systems the list of planetary systems
     * @param first   the index of the first planetary system to swap
     * @param second  the index of the second planetary system to swap
     */
    private void swapPlanetarySystems(List<PlanetarySystem> systems, int first, int second) {
        PlanetarySystem temp = systems.get(first);
        systems.set(first, systems.get(second));
        systems.set(second, temp);
    }

    /**
     * Sorts the planetary systems by their names in ascending order using selection sort.
     */
    public void sortPlanetarySystemsByName() {
        int n = planetarySystemList.size();

        // Selection Sort: Sorting by system name
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (planetarySystemList.get(j).getSystemName().compareTo(planetarySystemList.get(minIndex).getSystemName()) < 0) {
                    minIndex = j;
                }
            }
            // Swap the elements
            swapPlanetarySystems(planetarySystemList, i, minIndex);
        }
    }

    /**
     * Sorts the planetary systems by the star they orbit in ascending order using selection sort.
     */
    public void sortPlanetarySystemsByStarName() {
        int n = planetarySystemList.size();

        // Selection Sort: Sorting by orbitting star name
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (planetarySystemList.get(j).getOrbittingStarName().compareTo(planetarySystemList.get(minIndex).getOrbittingStarName()) < 0) {
                    minIndex = j;
                }
            }
            // Swap the elements
            swapPlanetarySystems(planetarySystemList, i, minIndex);
        }
    }

    /**
     * Lists all planetary systems sorted by name.
     *
     * @return a string representation of all sorted planetary systems, or a message indicating none exist
     */
    public String listSortedPlanetarySystems() {
        String listPlanetarySystems = "";
        for (PlanetarySystem planetarySystem : planetarySystemList) {
            listPlanetarySystems += planetarySystemList.indexOf(planetarySystem) + ": " + planetarySystem + "\n";
        }
        return listPlanetarySystems.isEmpty() ? "No Planetary Systems" : listPlanetarySystems;
    }

    /**
     * Loads the list of planetary systems from the specified file.
     *
     * @throws Exception if an error occurs during deserialization
     */
    public void load() throws Exception {
        // List of classes to include in the serialization
        Class<?>[] classes = new Class[]{PlanetarySystem.class};

        // Set up the xstream object with default security and the specified classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        // Perform the actual deserialization from the XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        planetarySystemList = (List<PlanetarySystem>) in.readObject();
        in.close();
    }
}