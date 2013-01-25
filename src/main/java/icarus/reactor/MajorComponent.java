package icarus.reactor;

import java.io.Serializable;

/**
 * MajorComponent abstract class, used for components that can carry water/steam and have pressure and temperature
 *
 * @author Team Haddock
 *
 */
public abstract class MajorComponent extends Component implements Serializable {

    protected int steamLevel;
    protected double temperature;
    protected double pressure;
    protected double waterLevel;
    private double radius;
    protected double waterSurfaceArea;
    protected int height;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     *
     * @param steamLevel The level of steam stored in the MajorComponent
     * @param temperature The temperature in Kelvin
     * @param pressure The pressure in Pascals
     * @param waterLevel The water level in cm
     * @param failProb The (maximum) probability of a component failing
     * @param fixTime The number of timesteps required to fix a component
     */
    public MajorComponent(int steamLevel, double temperature, double pressure, double waterLevel, double failProb,
                          int fixTime) {
        super(failProb, fixTime);
        this.waterLevel = waterLevel;
        this.steamLevel = steamLevel;
        this.temperature = temperature;
        this.pressure = pressure;
        radius = 200; // in cm
        height = 600;
        waterSurfaceArea = Math.PI * radius * radius;
    }

    /**
     * Constructor used when testing or loading a MajorComponent from a saved state.
     *
     * @param steamLevel the amount of steam in the component
     * @param temperature temperature of the steam in the component
     * @param pressure is the pressure of the steam in the component
     * @param waterLevel the amount of water in the component
     * @param radius is the radius of the container
     * @param volume is the volume of the container
     * @param waterSurfaceArea is the surface area of the water, used for calculations
     * @param height the the hight of the container
     * @param failProb The (maximum) probability of a component failing
     * @param fix The number of timesteps required to fix a component
     * @param functioning Whether or not the Component is currently functioning
     * @param repairal Whether or not the Component is currently being repaired
     * @param fixingProgress The progress on a current fix
     */
    public MajorComponent(int steamLevel, double temperature, double pressure, double waterLevel, double radius,
                          double waterSurfaceArea, int height, double failProb, int fix, boolean functioning,
                          boolean repairal, int fixingProgress) {
        super(failProb, fix, functioning, repairal, fixingProgress);
        this.steamLevel = steamLevel;
        this.temperature = temperature;
        this.pressure = pressure;
        this.waterLevel = waterLevel;
        this.radius = radius;
        this.waterSurfaceArea = waterSurfaceArea;
        this.height = height;
    }

    /**
     * Calculates the temperature of the MajorComponent for the timestep.
     */
    abstract public void calculateTemperature();

    /**
     * Calculates the pressure in the MajorComponent for the timestep.
     */
    public void calculatePressure() {
        pressure = ((steamLevel / (height - waterLevel)) * temperature);
        if (pressure > 6000) {
            pressure = 6000;
        }
        if (pressure < 50) {
            pressure = 50;
        }
    }

    /**
     * This method calculates whether a Component has failed, called on next();.
     *
     * @return Returns whether the Component has failed or not (ie !functional)
     */
    public boolean checkFail() {
        double failureChance = failureProbability * (temperature / 4500) * (pressure / 6000);
        if (Math.random() < failureChance) {
            functional = false;
            fixTime++;
        }
        return !functional;
    }

    /**
     * Adds water of the specified amount in cm.
     *
     * @param amount The amount of water to be added.
     */
    public void addWater(double amount) {
        waterLevel += amount;
    }

    /**
     * Removes water of the specified amount.
     *
     * @param amount The amount of water to be drained in cm.
     *
     * @return The amount drained, this is different from the specified amount if the MajorComponent contains less than
     *         the amount specified.
     */
    public double drainWater(double amount) {
        // stops more water being taken out than there is water remaining
        if (waterLevel >= amount) {
            waterLevel -= amount;
        } else {
            amount = waterLevel;
            waterLevel = 0;
        }
        // returns the steam to be added to another component
        return amount;
    }

    /**
     * Adds steam of the specified amount.
     *
     * @param amount The amount of steam to be added in cm.
     * @param temperature of the team being added
     */
    public void addSteam(double amount, double temperature) {
        steamLevel += amount;
    }

    /**
     * Removes steam of the specified amount.
     *
     * @param amount The amount of steam to be drained in cm.
     *
     * @return The amount drained, this is different from the specified amount if the
     *
     * @return MajorComponent contains less than the amount specified.
     */
    public double drainSteam(double amount) {
        if (steamLevel >= amount) {
            steamLevel -= amount;
        } else {
            amount = steamLevel;
            steamLevel = 0;
        }
        return amount;
    }

    /**
     *
     * @return The current amount of steam in the MajorComponent in cm.
     */
    public int getSteamLevel() {
        return steamLevel;
    }

    /**
     * @return The current temperature of the MajorComponent in Kelvin.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * @return The current pressure of the MajorComponent in Pascals.
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @return The current water level in the MajorComponent in cm.
     */
    public double getWaterLevel() {
        return waterLevel;
    }

    /**
     * @return The height of the MajorComponent in cm.
     */
    public double getHeight() {
        return height;
    }
}
