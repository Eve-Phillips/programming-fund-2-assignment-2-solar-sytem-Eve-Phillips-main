package models;

/**
 * Represents a dwarf planet, which is a type of celestial body that orbits the Sun,
 * but does not meet all the criteria to be considered a full-fledged planet. A dwarf planet
 * can have a unique surface composition and characteristics.
 */
public class DwarfPlanet extends Planet {

    // Private field for the surface composition
    private String surfaceComposition;

    /**
     * Constructor to initialize a DwarfPlanet object.
     *
     * @param name the name of the dwarf planet
     * @param mass the mass of the dwarf planet (in kilograms)
     * @param diameter the diameter of the dwarf planet (in kilometers)
     * @param planetarySystem the planetary system the dwarf planet belongs to
     * @param averageTemperature the average temperature of the dwarf planet (in Celsius)
     * @param surfaceType the type of surface (e.g., rocky, icy, etc.)
     * @param hasLiquidWater indicates whether the dwarf planet has liquid water
     * @param surfaceComposition the surface composition of the dwarf planet (e.g., ice, rock, etc.)
     */
    public DwarfPlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem,
                       double averageTemperature, String surfaceType, boolean hasLiquidWater, String surfaceComposition) {
        // Call the superclass constructor (Planet class)
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);

        // Initialize the surfaceComposition field
        this.surfaceComposition = surfaceComposition;
    }

    /**
     * Gets the surface composition of the dwarf planet.
     *
     * @return the surface composition of the dwarf planet (e.g., ice, rock, etc.)
     */
    public String getSurfaceComposition() {
        return surfaceComposition;
    }

    /**
     * Sets the surface composition of the dwarf planet.
     *
     * @param surfaceComposition the new surface composition to set (e.g., ice, rock, etc.)
     */
    public void setSurfaceComposition(String surfaceComposition) {
        this.surfaceComposition = surfaceComposition;
    }

    /**
     * Displays information about the dwarf planet.
     * This method is implemented to provide specific details for the DwarfPlanet class.
     *
     * @return a string with the surface composition information of the dwarf planet
     */
    @Override
    public String displayInfo() {
        // Return a string with surface composition information
        return "Surface Composition: " + surfaceComposition;
    }

    /**
     * Classifies the body as a Dwarf Planet.
     *
     * @return a string representing the classification of the celestial body as a "Dwarf Planet"
     */
    @Override
    public String classifyBody() {
        // Return the classification of the body as a Dwarf Planet
        return "Dwarf Planet";
    }

    /**
     * Returns a string representation of the DwarfPlanet.
     * This method is overridden from the Planet class to include surface composition.
     *
     * @return a string with all the details of the DwarfPlanet, including surface composition
     */
    @Override
    public String toString() {
        return super.toString() + ", Surface Composition: " + surfaceComposition;
    }
}

