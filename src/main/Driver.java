package main;

import controllers.CelestialSystemAPI;

import controllers.PlanetarySystemAPI;

import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;
import java.util.Scanner;

/**
 * The main driver class for the Space Place application.
 * This class handles the user interface and interaction with the CelestialSystemAPI and PlanetarySystemAPI.
 */
public class Driver {


    private CelestialSystemAPI celestialAPI;
    private PlanetarySystemAPI planetarySystemAPI;


    /**
     * The main method of the application. Creates an instance of the Driver and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            new Driver().start();  // Instance of Driver called to run the logic
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the Space Place application. Initializes the APIs, loads data, and runs the main menu.
     */
    public void start() {
        celestialAPI = new CelestialSystemAPI(new File("celestialSystems.xml"));
        planetarySystemAPI = new PlanetarySystemAPI(new File("planetarySystems.xml"));

        loadAllData();  // Load all data once the serializers are set up
        runMainMenu();
    }


    /**
     * Displays the main menu options to the user.
     *
     * @return The integer choice selected by the user.
     */
    private int mainMenu() {

        System.out.println("""
                 -------Space Place -------------
                |  1) Planetary Systems CRUD MENU|
                |  2) Celestial CRUD MENU        |
                |  3) Reports MENU               |
                |--------------------------------|
                |  4) Search Planetary Systems   |
                |  5) Search Planetary Objects   |  
                |  6) Sort Planetary Objects     | 
                |--------------------------------|
                |  10) Save all                  |
                |  11) Load all                  |
                |--------------------------------|
                |  0) Exit                       |
                 --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }

    //// search todo by different criteria i.e. look at the list methods and give options based on that.
// sort todo (and give a list of options - not a recurring menu thou)

    /**
     * Runs the main application loop, handling user input and navigating between menus.
     */
    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runPlanetaryMenu();
                case 2 -> runCelestialAPIMenu();
                case 3 -> runReportsMenu();
                case 4 -> System.out.println(planetarySystemAPI.listPlanetarySystems());
                case 5 -> searchPlanetaryObjects();  // Implemented Case 5
                case 6 -> sortPlanetaryObjects();    // Implemented Case 6
                case 10 -> saveAllData();
                case 11 -> loadAllData();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    // Case 5

    /**
     * Displays options for searching planetary objects and calls the appropriate search method.
     */
    private void searchPlanetaryObjects() {
        System.out.println("Search Planetary Objects by:");
        System.out.println("1) Name");
        System.out.println("2) Mass");
        System.out.println("3) Diameter");
        System.out.print("Enter the option number: ");

        int option = ScannerInput.readNextInt("==>> ");

        switch (option) {
            case 1 -> searchPlanetaryObjectByName();
            case 2 -> searchPlanetaryObjectByMass();
            case 3 -> searchPlanetaryObjectByDiameter();
            default -> System.out.println("Invalid search option");
        }
    }

    /**
     * Searches for a planetary object by its name.
     */
    private void searchPlanetaryObjectByName() {
        String name = ScannerInput.readNextLine("Enter the name of the planetary object: ");
        CelestialBody result = CelestialSystemAPI.searchCelestialBodyByName(name);  // Ensure you are using an instance of CelestialSystemAPI
        if (result != null) {
            System.out.println("Planetary Object Found: " + result);
        } else {
            System.out.println("No planetary object found with the name: " + name);
        }
    }

    /**
     * Searches for a planetary object by its mass.
     */
    private void searchPlanetaryObjectByMass() {
        double mass = ScannerInput.readNextDouble("Enter the mass of the planetary object: ");
        CelestialBody result = CelestialSystemAPI.searchCelestialBodyByMass(mass);  // Ensure you are using an instance of CelestialSystemAPI
        if (result != null) {
            System.out.println("Planetary Object Found: " + result);
        } else {
            System.out.println("No planetary object found with the mass: " + mass);
        }
    }

    /**
     * Searches for a planetary object by its diameter.
     */
    private void searchPlanetaryObjectByDiameter() {
        double diameter = ScannerInput.readNextDouble("Enter the diameter of the planetary object: ");
        CelestialBody result = CelestialSystemAPI.searchCelestialBodyByDiameter(diameter);  // Ensure you are using an instance of CelestialSystemAPI
        if (result != null) {
            System.out.println("Planetary Object Found: " + result);
        } else {
            System.out.println("No planetary object found with the diameter: " + diameter);
        }
    }

    // Case 6

    /**
     * Displays options for sorting planetary systems and performs the selected sort.
     */
    private void sortPlanetaryObjects() {
        System.out.println("Sort Planetary Systems by:");
        System.out.println("1) System Name");
        System.out.println("2) Star Name");
        System.out.println("3) Planet Count");
        System.out.print("Enter the option number: ");

        int option = ScannerInput.readNextInt("==>> ");  // Assuming ScannerInput.readNextInt is a method you already have

        switch (option) {
            case 1 -> planetarySystemAPI.sortPlanetarySystemsByName();       // Sort by system name
            case 2 -> planetarySystemAPI.sortPlanetarySystemsByStarName();   // Sort by star name
            default -> System.out.println("Invalid sort option");
        }
        // Display the sorted list of planetary systems
        System.out.println("Sorted Planetary Systems:");
        System.out.println(planetarySystemAPI.listSortedPlanetarySystems());
    }


    /**
     * A generic method to run a menu with given options and actions.
     *
     * @param menuTitle The title of the menu.
     * @param options   An array of strings representing the menu options.
     * @param actions   An array of Runnable objects, where each Runnable corresponds to an action for an option.
     */
    private void runMenu(String menuTitle, String[] options, Runnable[] actions) {
        int option = ScannerInput.readNextInt(menuTitle);
        while (option != 0) {
            if (option > 0 && option <= actions.length) {
                actions[option - 1].run();
            } else {
                System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = ScannerInput.readNextInt(menuTitle);
        }
    }


    /**
     * Exits the application after saving all data.
     */
    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //----------------------
    //  Developer Menu Items
    //----------------------

    /**
     * Displays the Planetary Systems CRUD menu.
     *
     * @return The integer choice selected by the user.
     */
    private int planetarySystemsMenu() {
        System.out.println("""
                 --------Planetary Menu---------
                |  1) Add a planetary systems           |
                |  2) Delete a planetary systems        |
                |  3) Update planetary systems details  |
                |  4) List all planetary systems       |
                |  5) Find a planetary systems          |
                |  0) Return to main menu          |
                 ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * Runs the Planetary Systems CRUD menu loop.
     */
    private void runPlanetaryMenu() {
        int option = planetarySystemsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addPlanetary();
                case 2 -> deletePlanetary();
                case 3 -> updatePlanetary();
                case 4 -> System.out.println(planetarySystemAPI.listPlanetarySystems());
                case 5 -> findPlanetary();
                case 6 -> listByPlanetaryName();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = planetarySystemsMenu();
        }
    }

    /**
     * Adds a new planetary system based on user input.
     */
    private void addPlanetary() {
        String planetarysystemsName = ScannerInput.readNextLine("Please enter the planetary systems name: ");
        String orbittingStar = ScannerInput.readNextLine("Please enter the name of the start that it orbits: ");

        if (planetarySystemAPI.addPLanetSystem(new PlanetarySystem(planetarysystemsName, orbittingStar))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    /**
     * Deletes a planetary system based on its name provided by the user.
     */
    private void deletePlanetary() {
        String planetarysystemsName = ScannerInput.readNextLine("Please enter the planetary systems name: ");
        if (planetarySystemAPI.removePlanetarySystemByName(planetarysystemsName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    /**
     * Updates the details of an existing planetary system based on user input.
     */
    private void updatePlanetary() {
        PlanetarySystem pSys = getPlanetaryByName();
        if (pSys != null) {
            String name = pSys.getSystemName();

            String orbittingStar = ScannerInput.readNextLine("Please enter the name of the star that it orbits: ");
            if (planetarySystemAPI.updatePlanetarySystem(name, orbittingStar))
                System.out.println("Star name Updated");
            else
                System.out.println("Star Name NOT Updated");
        } else
            System.out.println("Planetary System name is NOT valid");
    }

    /**
     * Finds and displays the details of a planetary system based on its name provided by the user.
     */
    private void findPlanetary() {
        PlanetarySystem pSys = getPlanetaryByName();
        if (pSys == null) {
            System.out.println("No such planetary systems exists");
        } else {
            System.out.println(pSys);
        }
    }

    /**
     * Lists all celestial objects belonging to a specific planetary system based on user input.
     */
    private void listByPlanetaryName() {
        String planetarysystems = ScannerInput.readNextLine("Enter the planetary systems's name:  ");

        System.out.println(planetarySystemAPI.listAllByPlanetarySystemName(planetarysystems));
    }


    //---------------------
    //  App Store Menu
    //---------------------

    /**
     * Displays the Celestial Object CRUD menu.
     *
     * @return The integer choice selected by the user.
     */
    private int celestialAPIMenu() {
        System.out.println(""" 
                 -----Celestial Object Menu----- 
                | 1) Add a Celestial Object           |
                | 2) Delete a Celestial Object        |
                | 3) List all Celestial Object       |
                | 4) Update Celestial Object          |
                | 0) Return to main menu         |
                 ----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * Runs the Celestial Object CRUD menu loop.
     */
    private void runCelestialAPIMenu() {
        int option = celestialAPIMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addCelestial();
                case 2 -> deleteCelestial();
                case 3 -> System.out.println(celestialAPI.listAllCelestialBodies());
                case 4 -> updateCelestialBody();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = celestialAPIMenu();
        }
    }

    /**
     * Updates the details of an existing celestial body based on user input.
     */
    private void updateCelestialBody() {
        // Step 1: Ask for the ID of the celestial object to update
        int id = ScannerInput.readNextInt("Please enter the ID of the celestial object you want to update: ");

        // Step 2: Check if the celestial object with this ID exists
        CelestialBody celestialObject = celestialAPI.getCelestialBodyById(id);
        if (celestialObject == null) {
            System.out.println("No celestial object found with the given ID.");
            return;
        }

        // Step 3: Display the current details of the celestial object
        System.out.println("Current details of the celestial object:");
        System.out.println(celestialObject); // assuming the CelestialBody class has a toString() method for display

        // Step 4: Ask the user which property they want to update
        int choice = ScannerInput.readNextInt("""
        Which property would you like to update?
        1) Name
        2) Mass
        3) Diameter
        4) Planetary System
        5) Star-Specific Attributes (e.g., spectral type, luminosity)
        6) Gas Planet-Specific Attributes (e.g., gas composition)
        7) Ice Planet-Specific Attributes (e.g., ice composition)
        0) Cancel
        ==>>""");

        CelestialBody updatedCelestialObject = null;

        switch (choice) {
            case 1 -> {
                String newName = ScannerInput.readNextLine("Enter the new name: ");
                celestialObject.setName(newName);
                updatedCelestialObject = celestialObject;  // Update object reference
                System.out.println("Name updated to: " + newName);
            }
            case 2 -> {
                double newMass = ScannerInput.readNextDouble("Enter the new mass: ");
                celestialObject.setMass(newMass);
                updatedCelestialObject = celestialObject;  // Update object reference
                System.out.println("Mass updated to: " + newMass);
            }
            case 3 -> {
                double newDiameter = ScannerInput.readNextDouble("Enter the new diameter: ");
                celestialObject.setDiameter(newDiameter);
                updatedCelestialObject = celestialObject;  // Update object reference
                System.out.println("Diameter updated to: " + newDiameter);
            }
            case 4 -> {
                // Update planetary system - similar to how the user adds a new system
                String newPlanetarySystemName = ScannerInput.readNextLine("Enter the new planetary system name: ");
                PlanetarySystem newPlanetarySystem = planetarySystemAPI.getPlanetarySystemByName(newPlanetarySystemName);
                if (newPlanetarySystem != null) {
                    celestialObject.setPlanetarySystem(newPlanetarySystem);
                    updatedCelestialObject = celestialObject;  // Update object reference
                    System.out.println("Planetary system updated to: " + newPlanetarySystemName);
                } else {
                    System.out.println("No planetary system found with the given name.");
                }
            }
            case 5 -> {
                if (celestialObject instanceof Star) {
                    Star star = (Star) celestialObject;
                    char newSpectralType = ScannerInput.readNextLine("Enter the new spectral type (OBAFGKM): ").charAt(0);
                    double newLuminosity = ScannerInput.readNextDouble("Enter the new luminosity: ");
                    star.setSpectralType(newSpectralType);
                    star.setLuminosity(newLuminosity);
                    updatedCelestialObject = star;  // Update object reference
                    System.out.println("Star updated: Spectral type = " + newSpectralType + ", Luminosity = " + newLuminosity);
                } else {
                    System.out.println("This object is not a star, cannot update star-specific attributes.");
                }
            }
            case 6 -> {
                if (celestialObject instanceof GasPlanet) {
                    GasPlanet gasPlanet = (GasPlanet) celestialObject;
                    String newGasComposition = ScannerInput.readNextLine("Enter the new gas composition: ");
                    gasPlanet.setGasComposition(newGasComposition);
                    updatedCelestialObject = gasPlanet;  // Update object reference
                    System.out.println("Gas composition updated to: " + newGasComposition);
                } else {
                    System.out.println("This object is not a gas planet, cannot update gas planet-specific attributes.");
                }
            }
            case 7 -> {
                if (celestialObject instanceof IcePlanet) {
                    IcePlanet icePlanet = (IcePlanet) celestialObject;
                    String newIceComposition = ScannerInput.readNextLine("Enter the new ice composition: ");
                    icePlanet.setIceComposition(newIceComposition);
                    updatedCelestialObject = icePlanet;  // Update object reference
                    System.out.println("Ice composition updated to: " + newIceComposition);
                } else {
                    System.out.println("This object is not an ice planet, cannot update ice planet-specific attributes.");
                }
            }
            case 0 -> System.out.println("Update canceled.");
            default -> System.out.println("Invalid option. Update canceled.");
        }

        // Step 5: Save the updated object back using the CelestialSystemAPI
        if (updatedCelestialObject != null) {
            boolean updateSuccessful = celestialAPI.updateCelestial(id, updatedCelestialObject);
            if (updateSuccessful) {
                System.out.println("Celestial object updated successfully.");
            } else {
                System.out.println("Failed to update celestial object. Please try again.");
            }
        }
    }

    /**
     * Deletes a celestial body based on its ID provided by the user.
     */
    private void deleteCelestial() {
        int id = ScannerInput.readNextInt("Please enter id number to delete: ");

        if (celestialAPI.isValidId(id) != -1) {
            CelestialBody t = celestialAPI.deleteCelestialId(id);
            if (t != null)
                System.out.println("Sucessful delete : " + t);
            else System.out.println("No Celestial Object was deleted");
        }

    }

    // public Vehicle(String regNumber, String  model, float cost, Planetary planetary systems, int  year) {

    /**
     * Adds a new celestial body based on user input, allowing the user to specify the type of celestial body.
     */
    private void addCelestial() {
        int celestialType = ScannerInput.readNextInt("""
                Which type of celestial object do you wish to add? 
                1) Star
                2) Gas Planet
                3) Ice Planet""");

        String name = ""; // Max 30 chars
        double mass = 0.1; // Measured in ronnagrams; earth is approx 6.0rg must be > 0.1, default to 0.1
        double diameter = 0.5; // Measured in kilometers, must be > 0.5, default to 0.5
        PlanetarySystem planetarySystem = null;

        switch (celestialType) {
            case 2, 3 -> { // Planet
                double averageTemperature = 0; // Avg surface temperature in °C; must be between -400 and 400, default 0
                String surfaceType = "";
                boolean hasLiquidWater = false;
                switch (celestialType) {
                    case 2 -> { // Gas
                        String gasComposition = ""; // Max 20 chars
                        boolean hasStorms = true; // Default true
                        double windSpeed = 1; // Max wind speed in km/h, min 1, max 200, default 1
                        String coreComposition = ""; // "rocky core", "metallic hydrogen core", max 40 chars
                        double radiationLevel = 0;

                        celestialAPI.addCelestialObject(new GasPlanet(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater, gasComposition, coreComposition, radiationLevel));
                    }
                    case 3 -> { // Ice
                        String iceComposition = "";  // Max 30 chars
                        celestialAPI.addCelestialObject(new IcePlanet(name, mass, diameter, planetarySystem,
                                averageTemperature, surfaceType, hasLiquidWater, iceComposition));
                    }
                }
            }
            case 1 -> { // Star
                char spectralType = 'M'; // Must be one of OBAFGKM, default to M
                double luminosity = 0;
                celestialAPI.addCelestialObject(new Star(name, mass, diameter, planetarySystem, spectralType, luminosity));
            }
            default -> {
                System.out.println("Invalid celestial type selected. Please try again.");
            }
        }
    }


    /**
     * Runs the main Reports menu loop.
     */
    public void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runPlanetaryReports();
                case 2 -> runCelestialReportsMenu();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }

    /**
     * Displays the main Reports menu.
     *
     * @return The integer choice selected by the user.
     */
    private int reportsMenu() {
        System.out.println(""" 
                 --------Reports Menu ---------
                | 1) Planetarys Overview    | 
                | 2) Celestials Overview         |
                | 0) Return to main menu       | 
                  -----------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * Runs the Celestial Reports menu loop.
     */
    public void runCelestialReportsMenu() {
        int option = celestialReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(celestialAPI.listAllCelestialBodies());
                case 2 -> System.out.println(celestialAPI.listAllGasPlanets());
                case 3 -> System.out.println(celestialAPI.listAllIcePlanets());
                case 4 -> System.out.println(celestialAPI.listAllStars());
                case 5 -> listAllCelestialHeavierThan();
                case 6 -> listAllCelestialSmallerThan();
                case 7 -> listAllStarsForSpectralType();
                case 8 -> System.out.println(celestialAPI.topFiveHighestRadiationGasPlanet());

                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = celestialReportsMenu();
        }
    }


    /**
     * Displays the Celestial Reports menu.
     *
     * @return The integer choice selected by the user.
     */
    private int celestialReportsMenu() {
        System.out.println(""" 
                 ---------- Celestial Reports Menu  ---------------------
                | 1) List all Celestial Objects                                 | 
                | 2) List all Gas Planet                                 |
                | 3) List all Ice Planets                               |
                | 4) List all Stars                                    |
                | 5) List all objects heavier than                      |
                | 6) List all ojects smaller than                      |
                | 7) List all stars for a spectral type                |
                | 8) List the top five gas planets by radiation levels      |
                | 0) Return to main menu                                 | 
                  ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * Displays the Planetary Systems Reports menu.
     *
     * @return The integer choice selected by the user.
     */
    private int planetarysystemsReportsMenu() {
        System.out.println(""" 
                 ---------- Planetarys Reports Menu  -------------
                | 1) List All Planetarys Systems                              | 
                | 2) List Celestial Objects from a given planetary systems    |
                | 3) List Planetary Systems by a given name              |
                | 0) Return to main menu                             | 
                  ---------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * Runs the Planetary Systems Reports menu loop.
     */
    public void runPlanetaryReports() {
        int option = planetarysystemsReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(planetarySystemAPI.listPlanetarySystems());
                case 2 -> listAllCelestialFromaGivenPlanetary();
                case 3 -> listPlanetarySystemCount();  // List the total count of planetary systems
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = planetarysystemsReportsMenu();
        }
    }

    /**
     * Displays the total count of planetary systems.
     */
    private void listPlanetarySystemCount() {
        int count = planetarySystemAPI.getPlanetarySystems().size();
        System.out.println("Total number of planetary systems: " + count);
    }

    /**
     * Lists all stars of a specific spectral type based on user input.
     */
    private void listAllStarsForSpectralType() {
        char type = ScannerInput.readNextChar("Enter Spectral Type: ");
        System.out.println(celestialAPI.listAllStarsForSpectralType(type));
    }

    /**
     * Lists all celestial objects smaller than a given diameter based on user input.
     */
    private void listAllCelestialSmallerThan() {
        double diam = ScannerInput.readNextDouble("Enter diameter of Celestial: ");
        System.out.println(celestialAPI.listAllCelestialObjectsSmallerThan(diam));
    }

    /**
     * Lists all celestial objects heavier than a given mass based on user input.
     */
    private void listAllCelestialHeavierThan() {
        double w = ScannerInput.readNextDouble("Enter weight of Celestial: ");
        System.out.println(celestialAPI.listAllCelestialObjectsHeavierThan(w));
    }

    /**
     * Lists all celestial objects belonging to a specific planetary system based on user input.
     */
    public void listAllCelestialFromaGivenPlanetary() {
        String manu = ScannerInput.readNextLine("What planetary systems you want a list of objects for?  : ");
        PlanetarySystem m = planetarySystemAPI.getPlanetarySystemByName(manu);
        if (!(m == null))
            System.out.println(celestialAPI.listAllCelestialObjectsForGivenPlanetary(m));
        else
            System.out.println("No planetary systems with tha name exists");
    }


    //---------------------
    //  General Menu Items
    //---------------------

    /**
     * Saves all data from both APIs to their respective files.
     */
    private void saveAllData() {
        System.out.println("Storing all data....");
        try {
            celestialAPI.save();
            planetarySystemAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    /**
     * Loads all data into both APIs from their respective files.
     */
    private void loadAllData() {
        System.out.println("Loading all data....");
        try {
            celestialAPI.load();
            planetarySystemAPI.load();
        } catch (Exception e) {
            System.err.println("Error loading from this file:  " + e);
        }
    }

    //---------------------
    //  Helper Methods
    //---------------------

    /**
     * Prompts the user for a unique ID and validates it.
     *
     * @return The valid unique ID entered by the user, or -1 if the ID is not valid or already exists.
     */
    private int getValidId() {
        int id = ScannerInput.readNextInt("\tID Number (must be unique): ");
        if (celestialAPI.isValidId(id) != 1) {
            return id;
        } else {
            System.err.println("\tId already exists / is not valid.");
            return -1;
        }
    }

    /**
     * Prompts the user for a planetary system name and retrieves the corresponding PlanetarySystem object.
     * Validates if a planetary system with the given name exists.
     *
     * @return The PlanetarySystem object if found and valid, otherwise null.
     */
    private PlanetarySystem getPlanetaryByName() {
        String planetarySystemName = ScannerInput.readNextLine("Please enter the planetary system's name: ");
        if (planetarySystemAPI.isValidPlanetSys(planetarySystemName)) {
            return planetarySystemAPI.getPlanetarySystemByName(planetarySystemName);
        } else {
            System.out.println("Invalid planetary system name. Please try again.");
            return null;
        }
    }
}