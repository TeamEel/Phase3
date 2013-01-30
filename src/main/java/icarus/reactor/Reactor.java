package icarus.reactor;

import icarus.exceptions.*;
import java.io.Serializable;

/**
 * The nuclear reactor, evaporates water to steam as part of the boiling water reactor model.
 *
 * @author Team Haddock
 *
 */
public class Reactor extends MajorComponent implements Serializable {

    private int rodHeight;
    private double activity;
    double waterIn;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     */
    public Reactor() {
        super(300, 500, 900, 290, .8, 2);
        rodHeight = 50;
        activity = 600;
        waterIn = 0;
    }

    /**
     * Constructor used when testing or loading a Reactor from a saved state.
     *
     * @param rodHeight is the height the rods are at, out of 100
     * @param activity is the level of activity in the reactor
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
    public Reactor(int rodHeight, int activity, int steamLevel, double temperature, double pressure, double waterLevel,
                   double radius, double waterSurfaceArea, int height, double failProb, int fix, boolean functioning,
                   boolean repairal,
                   int fixingProgress) {
        super(steamLevel, temperature, pressure, waterLevel, radius, waterSurfaceArea, height, failProb, fix,
              functioning, repairal, fixingProgress);
        this.rodHeight = rodHeight;
        this.activity = activity;
        waterIn = 0;
    }

    /**
     * Calculates the radioactivity of the Reactor for the timestep.
     */
    public void calculateActivity() {
        activity = activity / 10;
        activity = activity + rodHeight;
        activity = activity * 5;
    }

    /**
     * Evaporates water to steam in the reactor
     */
    public void evaporate() {
        double evaporation = 0;
        if (temperature > 400) {
            // gives a value between 0 and 80	
            evaporation = Math.log(temperature);
            evaporation -= 5.5;
            evaporation = evaporation * 25;
        }
        // stops evaporation from going below 0
        if (evaporation < 0) {
            evaporation = 0;
        }
        // evaporates water to steam
        if (waterLevel >= evaporation) {
            waterLevel -= evaporation;
        } else {
            evaporation = waterLevel;
            waterLevel = 0;
        }
        steamLevel += evaporation;
        // simulates heat loss due to evaporation
        temperature -= evaporation;
    }

    /**
     * Calculates the current temperature of the Reactor in Kelvin
     */
    @Override
    public void calculateTemperature() {
        // stops divide by zero error
        if (waterLevel < 1) {
            temperature = temperature + ((activity / 1) * 100);
        } else {
            temperature = temperature + ((activity / waterLevel) * 100);
        }
        // cools temperature proportional to the water being put in
        temperature += (waterIn * 4);
        // resets waterIn so it can be used again during the next timestep 
        waterIn = 0;
        // stops the temperature from going below zero
        if (temperature < 300) {
            temperature = 300;
        }

    }

    
    
    public void movecontrolrods(int amount) throws InvalidRodsException, ComponentFailedException {
        if (functional) {
            // stops inputs outside of the 0 - 100 range
            if (amount < 0 || amount > 100) {
                throw new InvalidRodsException(amount);
            } else {
                rodHeight =  amount;
            }
        } else {
            throw new ComponentFailedException();
        }
    }
    

    /**
     *
     * @return The height of the reactor control rods as a value between 0 (fully down) and 100 (fully up)
     */
    public int getRodHeight() {
        return rodHeight;
    }

    /**
     * Adds water of the specified amount in cm.
     *
     * @param amount The amount of water to be added.
     */
    @Override
    public void addWater(double amount) {
        waterLevel += amount;
        waterIn += amount;
    }

    /**
     *
     * @return activity
     */
    public double getActivity() {
        return activity;
    }

    /**
     * Removes water of the specified amount.
     *
     * @param amount The amount of water to be drained in cm.
     *
     * @return The amount drained, this is different from the specified amount if the
     *
     * @return MajorComponent contains less than the amount specified.
     */
    @Override
    public double drainWater(double amount) {
        if (waterLevel >= amount) {
            waterLevel -= amount;
        } else {
            amount = waterLevel;
            waterLevel = 0;
        }
        waterIn -= amount;
        return amount;
    }
}
