package models;

/**
 * Represents a gas planet, a type of planet primarily composed of gases. Gas planets
 * are known for their thick atmospheres and often large sizes. They may have a gaseous
 * or molten core and can emit high levels of radiation.
 */
public class GasPlanet extends Planet {

    // Private fields for gas composition, core composition, and radiation level
    private String gasComposition;
    private String coreComposition;
    private double radiationLevel;

    /**
     * Constructor to initialize a GasPlanet object.
     *
     * @param name the name of the gas planet
     * @param mass the mass of the gas planet (in kilograms)
     * @param diameter the diameter of the gas planet (in kilometers)
     * @param planetarySystem the planetary system the gas planet belongs to
     * @param averageTemperature the average temperature of the gas planet (in Celsius)
     * @param surfaceType the type of surface (e.g., gaseous, rocky, etc.)
     * @param hasLiquidWater indicates whether the gas planet has liquid water
     * @param gasComposition the composition of the gas in the atmosphere of the gas planet
     * @param coreComposition the composition of the gas planet's core
     * @param radiationLevel the radiation level emitted by the gas planet (in arbitrary units)
     */
    public GasPlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem,
                     double averageTemperature, String surfaceType, boolean hasLiquidWater,
                     String gasComposition, String coreComposition, double radiationLevel) {
        // Call the superclass constructor (Planet class)
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);

        // Initialize the fields for gas composition, core composition, and radiation level
        this.gasComposition = gasComposition;
        this.coreComposition = coreComposition;
        this.radiationLevel = radiationLevel;
    }

    /**
     * Gets the gas composition of the gas planet.
     *
     * @return the gas composition of the gas planet (e.g., hydrogen, helium, methane, etc.)
     */
    public String getGasComposition() {
        return gasComposition;
    }

    /**
     * Sets the gas composition of the gas planet.
     *
     * @param gasComposition the new gas composition to set (e.g., hydrogen, helium, methane, etc.)
     */
    public void setGasComposition(String gasComposition) {
        this.gasComposition = gasComposition;
    }

    /**
     * Gets the core composition of the gas planet.
     *
     * @return the core composition of the gas planet (e.g., rocky, gaseous, molten, etc.)
     */
    public String getCoreComposition() {
        return coreComposition;
    }

    /**
     * Sets the core composition of the gas planet.
     *
     * @param coreComposition the new core composition to set (e.g., rocky, gaseous, molten, etc.)
     */
    public void setCoreComposition(String coreComposition) {
        this.coreComposition = coreComposition;
    }

    /**
     * Gets the radiation level of the gas planet.
     *
     * @return the radiation level emitted by the gas planet (in arbitrary units)
     */
    public double getRadiationLevel() {
        return radiationLevel;
    }

    /**
     * Sets the radiation level of the gas planet.
     *
     * @param radiationLevel the new radiation level to set (in arbitrary units)
     */
    public void setRadiationLevel(double radiationLevel) {
        this.radiationLevel = radiationLevel;
    }

    /**
     * Displays detailed information about the gas planet, including its gas composition,
     * core composition, and radiation level.
     *
     * @return a string with the gas composition, core composition, and radiation level
     */
    @Override
    public String displayInfo() {
        // Return a string with gas composition, core composition, and radiation level
        return "Name: " + getName() + // For test
                ", Gas Composition: " + gasComposition +
                ", Core Composition: " + coreComposition +
                ", Radiation Level: " + radiationLevel;
    }

    /**
     * Classifies the celestial body as a Gas Planet.
     *
     * @return a string representing the classification of the celestial body as a "Gas Planet"
     */
    @Override
    public String classifyBody() {
        // Return the classification of the body as a Gas Planet
        return "Gas Planet";
    }

    /**
     * Returns a string representation of the GasPlanet.
     * This method is overridden from the Planet class to include gas composition, core composition,
     * and radiation level information.
     *
     * @return a string with the name, mass, diameter, planetary system, surface type,
     *         average temperature, liquid water status, gas composition, core composition,
     *         and radiation level of the gas planet
     */
    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Mass: " + getMass() + "kg\n" +
                "Diameter: " + getDiameter() + "km\n" +
                getPlanetarySystem().toString() + "\n" +  // Correct format for planetary system
                "Surface Type: " + getSurfaceType() + "\n" +
                "Avg Temp: " + getAverageTemperature() + "°C\n" +
                "Has Liquid Water: " + hasLiquidWater() + "\n" +
                "Gas Composition: " + gasComposition + "\n" +
                "Core Composition: " + coreComposition + "\n" +
                "Radiation Level: " + radiationLevel;
    }
}