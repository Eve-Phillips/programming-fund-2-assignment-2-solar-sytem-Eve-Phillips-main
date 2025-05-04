package models;

import utils.Utilities;

import java.util.Objects;

/**
 * Represents a planetary system, which consists of a system name and the star around which
 * the planets orbit. A planetary system may contain multiple celestial bodies, including
 * planets, dwarf planets, moons, asteroids, etc.
 */
public class PlanetarySystem {

    // Private fields for the system name and the star around which it orbits
    private String systemName; // e.g., TRAPPIST-1 or Solar System (max 50 chars)
    private String orbittingStarName; // e.g., Sun for Solar System (max 30 chars)
    // Additional attributes can be added as needed

    /**
     * Constructs a new PlanetarySystem object with the specified system name and the star it orbits.
     * The system name is truncated to 50 characters, and the star name is truncated to 30 characters
     * using utility methods.
     *
     * @param systemName the name of the planetary system (e.g., "TRAPPIST-1", "Solar System")
     * @param orbittingStarName the name of the star that the system's planets orbit (e.g., "Sun")
     */
    public PlanetarySystem(String systemName, String orbittingStarName) {
        this.systemName = Utilities.truncateString(systemName, 50); // Truncate system name to max 50 chars
        this.orbittingStarName = Utilities.truncateString(orbittingStarName, 30); // Truncate star name to max 30 chars
    }

    /**
     * Gets the name of the planetary system.
     *
     * @return the system name (e.g., "TRAPPIST-1", "Solar System")
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Sets the name of the planetary system. The system name is validated to ensure it does not exceed 50 characters.
     *
     * @param systemName the new system name to set
     */
    public void setSystemName(String systemName) {
        if (Utilities.validStringlength(systemName, 50)) {
            this.systemName = systemName;
        }
    }

    /**
     * Gets the name of the star that the planetary system orbits.
     *
     * @return the name of the star (e.g., "Sun")
     */
    public String getOrbittingStarName() {
        return orbittingStarName;
    }

    /**
     * Sets the name of the star that the planetary system orbits. The star name is validated to ensure it does not exceed 30 characters.
     *
     * @param orbittingStarName the new star name to set
     */
    public void setOrbittingStarName(String orbittingStarName) {
        if (Utilities.validStringlength(orbittingStarName, 30)) {
            this.orbittingStarName = orbittingStarName;
        }
    }

    /**
     * Compares the current PlanetarySystem object to another object for equality. Two planetary systems are considered equal
     * if they have the same system name and the same star name they orbit.
     *
     * @param o the object to compare with
     * @return true if the two planetary systems are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetarySystem that = (PlanetarySystem) o;
        return Objects.equals(systemName, that.systemName) && Objects.equals(orbittingStarName, that.orbittingStarName);
    }

    /**
     * Returns a string representation of the PlanetarySystem. The string includes the system name
     * and the name of the star it orbits.
     *
     * @return a string with the system name and the name of the star
     */
    @Override
    public String toString() {
        return "PlanetarySystem Name: " + systemName +
                ", orbits around: " + orbittingStarName.toUpperCase();
    }
}

/*
Extra Information about this class
Our solar system is just one specific planetary system—a star with planets orbiting around it.
Our planetary system is the only one officially called “solar system,” but astronomers have discovered more than 3,200
other stars with planets orbiting them in our galaxy. That’s just how many we’ve found so far.
There are likely to be many more planetary systems out there waiting to be discovered!

 */