package models;

/**
 * Represents a planet, a type of celestial body that orbits a star. A planet typically has
 * a defined surface type, an average temperature, and may or may not have liquid water.
 * Planets can have various compositions, and this class serves as a blueprint for specific
 * types of planets (e.g., gas planets, ice planets, etc.).
 */
public abstract class Planet extends CelestialBody {

 // Private fields
 private String surfaceType;  // Max 20 characters
 private double averageTemperature;  // Range -400 to 400
 private boolean hasLiquidWater;

 /**
  * Constructor to initialize a Planet object.
  *
  * @param name the name of the planet
  * @param mass the mass of the planet (in kilograms)
  * @param diameter the diameter of the planet (in kilometers)
  * @param planetarySystem the planetary system to which the planet belongs
  * @param averageTemperature the average temperature of the planet (in Celsius)
  * @param surfaceType the type of surface (e.g., rocky, gaseous, icy, etc.)
  * @param hasLiquidWater indicates whether the planet has liquid water on its surface
  */
 public Planet(String name, double mass, double diameter, PlanetarySystem planetarySystem,
               double averageTemperature, String surfaceType, boolean hasLiquidWater) {
  // Call the superclass constructor (CelestialBody class)
  super(name, mass, diameter, planetarySystem);

  // Validate and set surfaceType (max 19 characters)
  if (surfaceType != null) {
   this.surfaceType = surfaceType.length() > 19 ? surfaceType.substring(0, 19) : surfaceType;
  } else {
   this.surfaceType = surfaceType;
  }

  // Validate averageTemperature within bounds [-400, 400]
  if (averageTemperature < -400 || averageTemperature > 400) {
   this.averageTemperature = 0;  // Default to 0 if out of bounds
  } else {
   this.averageTemperature = averageTemperature;
  }

  this.hasLiquidWater = hasLiquidWater;
 }

 /**
  * Gets the surface type of the planet.
  *
  * @return the surface type of the planet (e.g., rocky, gaseous, icy, etc.)
  */
 public String getSurfaceType() {
  return surfaceType;
 }

 /**
  * Sets the surface type of the planet.
  * The surface type is truncated to 19 characters if it exceeds the maximum length.
  *
  * @param surfaceType the new surface type to set (e.g., rocky, gaseous, icy, etc.)
  */
 public void setSurfaceType(String surfaceType) {
  if (surfaceType != null) {
   // Truncate to 19 characters, no extra characters should be added
   this.surfaceType = surfaceType.length() > 19 ? surfaceType.substring(0, 19) : surfaceType;
  }
 }

 /**
  * Gets the average temperature of the planet.
  *
  * @return the average temperature of the planet (in Celsius)
  */
 public double getAverageTemperature() {
  return averageTemperature;
 }

 /**
  * Sets the average temperature of the planet.
  * The temperature is validated to ensure it is within the bounds of -400 to 400 Celsius.
  *
  * @param averageTemperature the new average temperature to set (in Celsius)
  */
 public void setAverageTemperature(double averageTemperature) {
  if (averageTemperature >= -400 && averageTemperature <= 400) {
   this.averageTemperature = averageTemperature;
  }
 }

 /**
  * Determines whether the planet has liquid water.
  *
  * @return true if the planet has liquid water, otherwise false
  */
 public boolean hasLiquidWater() {
  return hasLiquidWater;
 }

 /**
  * Sets whether the planet has liquid water.
  *
  * @param hasLiquidWater true if the planet has liquid water, otherwise false
  */
 public void setHasLiquidWater(boolean hasLiquidWater) {
  this.hasLiquidWater = hasLiquidWater;
 }

 /**
  * Calculates the gravity of the planet using the formula:
  * <br> gravity = (mass * gravitational constant) / (radius^2)
  * <br> The radius is calculated by dividing the diameter by 2.
  *
  * @return the calculated gravity of the planet (in m/s²)
  */
 @Override
 public double calculateGravity() {
  double radius = getDiameter() / 2.0;
  return (getMass() * 6.67430e-11) / (radius * radius);
 }

 /**
  * Returns a string representation of the Planet.
  * This method is overridden from the CelestialBody class to include planet-specific fields
  * such as surface type, average temperature, and whether the planet has liquid water.
  *
  * @return a string containing the name, mass, diameter, planetary system, surface type,
  *         average temperature, and liquid water status of the planet
  */
 @Override
 public String toString() {
  return super.toString() +
          ", Surface Type: " + surfaceType +
          ", Avg Temp: " + averageTemperature + "°C" +
          ", Has Liquid Water: " + hasLiquidWater;
 }

 /**
  * Abstract method that should be implemented by subclasses to display specific information
  * about the planet.
  *
  * @return a string with detailed information about the planet
  */
 @Override
 public abstract String displayInfo();

 /**
  * Abstract method that should be implemented by subclasses to classify the planet.
  *
  * @return a string representing the classification of the planet (e.g., Gas Planet, Ice Planet)
  */
 @Override
 public abstract String classifyBody();
}