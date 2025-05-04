package models;

import utils.SpectralTypeUtility;

/**
 * Represents a stellar object, which is a type of celestial body that emits light and heat due to nuclear fusion.
 * This class extends the {@link CelestialBody} class and adds properties specific to stars, such as spectral type and luminosity.
 */
public abstract class StellarObject extends CelestialBody {

    // Private fields
    private char spectralType; // Spectral type of the stellar object (e.g., 'O', 'G', 'M')
    private double luminosity; // Luminosity of the stellar object in solar units

    /**
     * Constructs a new StellarObject with the specified properties, initializing the celestial body
     * with its name, mass, diameter, planetary system, spectral type, and luminosity.
     * If the spectral type is invalid, it defaults to 'M', and if the luminosity is out of the valid range,
     * it defaults to 1000.
     *
     * @param name the name of the stellar object
     * @param mass the mass of the stellar object in kilograms
     * @param diameter the diameter of the stellar object in kilometers
     * @param planetarySystem the planetary system that the stellar object belongs to
     * @param spectralType the spectral type of the stellar object (e.g., 'O', 'G', 'M')
     * @param luminosity the luminosity of the stellar object in solar units
     */
    public StellarObject(String name, double mass, double diameter, PlanetarySystem planetarySystem, char spectralType, double luminosity) {
        super(name, mass, diameter, planetarySystem);

        // Set the spectralType and luminosity with validation
        this.spectralType = isValidStellarType(spectralType) ? spectralType : 'M';  // Default to 'M' if invalid
        this.luminosity = (luminosity >= 1000 && luminosity <= 200000) ? luminosity : 1000;  // Default to 1000 if out of range
    }

    /**
     * Gets the spectral type of the stellar object.
     *
     * @return the spectral type (e.g., 'O', 'G', 'M')
     */
    public char getSpectralType() {
        return spectralType;
    }

    /**
     * Sets the spectral type of the stellar object, ensuring that the provided spectral type is valid.
     *
     * @param spectralType the spectral type to set (e.g., 'O', 'G', 'M')
     */
    public void setSpectralType(char spectralType) {
        if (isValidStellarType(spectralType)) {
            this.spectralType = spectralType;
        }
    }

    /**
     * Gets the luminosity of the stellar object.
     *
     * @return the luminosity in solar units
     */
    public double getLuminosity() {
        return luminosity;
    }

    /**
     * Sets the luminosity of the stellar object, ensuring that it is within the valid range (1000 to 200000 solar units).
     *
     * @param luminosity the luminosity to set in solar units
     */
    public void setLuminosity(double luminosity) {
        if (luminosity >= 1000 && luminosity <= 200000) {
            this.luminosity = luminosity;
        }
    }

    /**
     * Validates the spectral type using the {@link SpectralTypeUtility} class to check if the provided
     * spectral type is a valid type (e.g., 'O', 'B', 'G', 'M').
     *
     * @param spectralType the spectral type to validate
     * @return true if the spectral type is valid, false otherwise
     */
    public boolean isValidStellarType(char spectralType) {
        return SpectralTypeUtility.isValidSpectralType(spectralType);
    }

    /**
     * Returns a string representation of the stellar object's key properties: spectral type and luminosity.
     *
     * @return a string with the spectral type and luminosity of the stellar object
     */
    public String displayInfo() {
        return "Spectral Type: " + spectralType + " Luminosity: " + luminosity;
    }

    /**
     * Returns a string representation of the stellar object, including its properties inherited from
     * the {@link CelestialBody} class as well as its spectral type and luminosity.
     *
     * @return a formatted string representing the stellar object
     */
    @Override
    public String toString() {
        return super.toString() + ", Spectral Type: " + spectralType + ", Luminosity: " + luminosity;
    }
}

/*
More Info
https://lweb.cfa.harvard.edu/~pberlind/atlas/htmls/note.html - spectral type
One fundamental property of a star is the total amount of energy it radiates each second. This energy output is called luminosity or absolute brightness.
https://www.teachastronomy.com/textbook/Properties-of-Stars/Stellar-Luminosity/#:~:text=A%20slightly%20modified%20version%20of,called%20luminosity%20or%20absolute%20brightness.

 */