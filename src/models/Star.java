package models;

/**
 * Represents a star in a planetary system. A star is a luminous celestial body that generates
 * light and heat through nuclear fusion. This class extends the {@link StellarObject} class.
 * It provides functionality to calculate the star's gravity, display its information, and classify it as a star.
 */
public class Star extends StellarObject {

    /**
     * Constructs a new Star object with the specified properties.
     * This constructor calls the superclass constructor ({@link StellarObject}) to initialize
     * the star with its name, mass, diameter, planetary system, spectral type, and luminosity.
     *
     * @param name the name of the star
     * @param mass the mass of the star in kilograms
     * @param diameter the diameter of the star in kilometers
     * @param system the planetary system that the star is part of
     * @param spectralType the spectral type of the star (e.g., 'G' for a G-type star)
     * @param luminosity the luminosity of the star in solar units
     */
    public Star(String name, double mass, double diameter, PlanetarySystem system, char spectralType, double luminosity) {
        super(name, mass, diameter, system, spectralType, luminosity);
    }

    /**
     * Calculates the gravitational pull of the star using the formula:
     * (mass * G) / (radius^2), where G is the gravitational constant and radius is half of the star's diameter.
     *
     * @return the gravity of the star in meters per second squared
     */
    @Override
    public double calculateGravity() {
        double G = 6.67430e-11; // Gravitational constant
        double radius = getDiameter() / 2.0; // Radius of the star
        return (getMass() * G) / (radius * radius); // Gravity calculation
    }

    /**
     * Displays information about the star, including its name, spectral type, and luminosity.
     *
     * @return a string representation of the star's key properties
     */
    @Override
    public String displayInfo() {
        return "Star: " + getName() +
                ", Spectral Type: " + getSpectralType() +
                ", Luminosity: " + getLuminosity();
    }

    /**
     * Classifies the celestial body as a star.
     *
     * @return the classification of the body as a "Star"
     */
    @Override
    public String classifyBody() {
        return "Star";
    }

    /**
     * Returns a string representation of the star, including all relevant properties
     * such as its name, mass, diameter, spectral type, luminosity, and gravity. The gravity
     * is formatted in scientific notation.
     *
     * @return a formatted string representing the star's details
     */
    @Override
    public String toString() {
        // Ensuring the format matches the test expectations
        return "Star: " + super.toString() +
                ", SpectralType: " + getSpectralType() +  // Corrected to match the test expectation (no space)
                ", luminosity: " + getLuminosity() +  // Corrected to match the test expectation (lowercase 'l')
                ", Gravity: " + String.format("%.3e", calculateGravity()); // Gravity in scientific notation
    }
}
