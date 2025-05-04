package models;

public class TestPlanet extends Planet {

    public TestPlanet(String name, double mass, double diameter, PlanetarySystem planetarySystem,
                      double averageTemperature, String surfaceType, boolean hasLiquidWater) {
        super(name, mass, diameter, planetarySystem, averageTemperature, surfaceType, hasLiquidWater);
    }

    @Override
    public String displayInfo() {
        return "TestPlanet: " + getName() + ", Surface Type: " + getSurfaceType();
    }

    @Override
    public String classifyBody() {
        return "Test Classification";
    }
}

