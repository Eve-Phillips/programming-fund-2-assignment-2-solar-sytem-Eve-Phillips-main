package models;

/**
 * Represents a celestial body (e.g., planet, moon, asteroid) in a planetary system.
 * This abstract class provides common attributes and behaviors shared by all celestial bodies.
 */
public abstract class CelestialBody {

    // Private fields
    private int id;
    private String name;
    private double mass;
    private double diameter;
    private PlanetarySystem planetarySystem;

    // Static field for generating unique ids
    private static int nextId = 1000;

    /**
     * Constructor to initialize a CelestialBody object.
     *
     * @param name the name of the celestial body
     * @param mass the mass of the celestial body (in kilograms)
     * @param diameter the diameter of the celestial body (in kilometers)
     * @param planetarySystem the planetary system the celestial body belongs to
     */
    public CelestialBody(String name, double mass, double diameter, PlanetarySystem planetarySystem) {
        this.id = nextId++; // Increment the ID each time a new celestial body is created
        this.name = name.length() > 30 ? name.substring(0, 30) : name;  // Truncate name to 30 chars
        this.mass = mass > 0.1 ? mass : 0.1;  // Default mass to 0.1 if invalid
        this.diameter = diameter > 0.5 ? diameter : 0.5;  // Default diameter to 0.5 if invalid
        this.planetarySystem = planetarySystem;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the celestial body.
     *
     * @return the ID of the celestial body
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the celestial body.
     *
     * @return the name of the celestial body
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the celestial body.
     *
     * @param name the new name to set (must be 30 characters or less)
     */
    public void setName(String name) {
        // Only update the name if it's valid (<= 30 characters)
        if (name != null && name.length() <= 30) {
            this.name = name;
        }
    }

    /**
     * Gets the mass of the celestial body.
     *
     * @return the mass of the celestial body in kilograms
     */
    public double getMass() {
        return mass;
    }

    /**
     * Sets the mass of the celestial body.
     *
     * @param mass the new mass to set (must be greater than 0.1 kg)
     */
    public void setMass(double mass) {
        // Only update the mass if it's valid (> 0.1)
        if (mass > 0.1) {
            this.mass = mass;
        }
    }

    /**
     * Gets the diameter of the celestial body.
     *
     * @return the diameter of the celestial body in kilometers
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Sets the diameter of the celestial body.
     *
     * @param diameter the new diameter to set (must be greater than 0.5 km)
     */
    public void setDiameter(double diameter) {
        // Only update the diameter if it's valid (> 0.5)
        if (diameter > 0.5) {
            this.diameter = diameter;
        }
    }

    /**
     * Gets the planetary system to which the celestial body belongs.
     *
     * @return the planetary system of the celestial body
     */
    public PlanetarySystem getPlanetarySystem() {
        return planetarySystem;
    }

    /**
     * Sets the planetary system of the celestial body.
     *
     * @param planetarySystem the new planetary system to set
     */
    public void setPlanetarySystem(PlanetarySystem planetarySystem) {
        this.planetarySystem = planetarySystem;
    }

    // Abstract methods

    /**
     * Displays the information of the celestial body.
     * This method should be implemented by subclasses to provide specific details.
     *
     * @return a string representing the details of the celestial body
     */
    public abstract String displayInfo();

    /**
     * Calculates the gravitational force of the celestial body.
     * This method should be implemented by subclasses to provide the specific calculation.
     *
     * @return the gravitational force of the celestial body (in m/s²)
     */
    public abstract double calculateGravity();

    /**
     * Classifies the celestial body (e.g., planet, moon, asteroid).
     * This method should be implemented by subclasses to return a specific classification.
     *
     * @return a string representing the classification of the celestial body
     */
    public abstract String classifyBody();

    /**
     * Returns a string representation of the CelestialBody.
     *
     * @return a string with the ID, name, mass, diameter, and planetary system of the celestial body
     */
    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Mass: " + mass + "kg\n" +
                "Diameter: " + diameter + "km\n" +
                "PlanetarySystem: " + (planetarySystem != null ? planetarySystem : "null");
    }
}