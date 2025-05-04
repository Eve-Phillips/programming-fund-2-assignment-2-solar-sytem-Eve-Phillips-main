package models;

/**
 * Represents an ice planet, a type of planet primarily composed of ice. Ice planets
 * are typically colder and have surfaces or atmospheres rich in frozen substances like water, methane, or ammonia.
 */
public class IcePlanet extends Planet {

    // Private field for ice composition
    private String iceComposition;

    /**
     * Constructor to initialize an IcePlanet object.
     *
     * @param name the name of the ice planet
     * @param mass the mass of the ice planet (in kilograms)
     * @param diameter the diameter of the ice planet (in kilometers)
     * @param planetarySystem the planetary system the ice planet belongs to
     * @param averageTemperature the average temperature of the ice planet (in Celsius)
     * @param surfaceType the type of surface (e.g., icy, rocky, etc.)
     * @param hasLiquidWater indicates whether the ice planet has liquid water
     * @param iceComposition the composition of the ice on the surface of the planet (e.g., water ice, methane ice, etc.)
     */
    public IcePlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem,
                     double averageTemperature, String surfaceType, boolean hasLiquidWater, String iceComposition) {
        // Call the superclass constructor (Planet class)
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);

        // Initialize the iceComposition field
        this.iceComposition = iceComposition;
    }

    /**
     * Gets the ice composition of the ice planet.
     *
     * @return the ice composition of the ice planet (e.g., water ice, methane ice, etc.)
     */
    public String getIceComposition() {
        return iceComposition;
    }

    /**
     * Sets the ice composition of the ice planet.
     *
     * @param iceComposition the new ice composition to set (e.g., water ice, methane ice, etc.)
     */
    public void setIceComposition(String iceComposition) {
        this.iceComposition = iceComposition;
    }

    /**
     * Displays detailed information about the ice planet, including its ice composition.
     *
     * @return a string with the ice composition information of the ice planet
     */
    @Override
    public String displayInfo() {
        // Return a string with ice composition information
        return "Name: " + getName() + // For test
                ", Ice Composition: " + iceComposition;
    }

    /**
     * Classifies the celestial body as an Ice Planet.
     *
     * @return a string representing the classification of the celestial body as an "Ice Planet"
     */
    @Override
    public String classifyBody() {
        // Return the classification of the body as an Ice Planet
        return "Ice Planet";
    }

    /**
     * Returns a string representation of the IcePlanet.
     * This method is overridden from the Planet class to include ice composition information.
     *
     * @return a string with all the details of the IcePlanet, including ice composition
     */
    @Override
    public String toString() {
        return super.toString() + ", Ice Composition: " + iceComposition;
    }
}
