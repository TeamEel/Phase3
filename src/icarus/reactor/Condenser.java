package icarus.reactor;

import icarus.reactor.CondenserPump;

import java.io.Serializable;

/**
 * Condenser that converts steam back to water.
 * 
 * @author Team Haddock
 * 
 */
public class Condenser extends MajorComponent implements Serializable {

	private double steamIn;
	private double steamInTemperature;
	double condensation;
	private CondenserPump condenserPump;
	private static final long serialVersionUID = 1;

	/**
	 * Primary constructor for use when initialising a new game
	 * 
	 * @param pump
	 *            The CondenserPump associated with the coolant for this
	 *            condenser
	 */
	public Condenser(CondenserPump pump) {
		super(300, 400, 400, 290, .8, 2);
		steamInTemperature = 0;
		condensation = 0;
		condenserPump = pump;
	}

	/**
	 * Constructor used when testing or loading a Reactor from a saved state.
	 * 
	 * @param steamInamount
	 * @param pump
	 *            The CondenserPump associated with the coolant for this
	 *            condenser
	 * @param steamLevel
	 *            the amount of steam in the component
	 * @param temperature
	 *            temperature of the steam in the component
	 * @param pressure
	 *            is the pressure of the steam in the component
	 * @param waterLevel
	 *            the amount of water in the component
	 * @param radius
	 *            is the radius of the container
	 * @param volume
	 *            is the volume of the container
	 * @param waterSurfaceArea
	 *            is the surface area of the water, used for calculations
	 * @param height
	 *            the the hight of the container
	 * @param failProb
	 *            The (maximum) probability of a component failing
	 * @param fix
	 *            The number of timesteps required to fix a component
	 * @param functioning
	 *            Whether or not the Component is currently functioning
	 * @param repairal
	 *            Whether or not the Component is currently being repaired
	 * @param fixingProgress
	 *            The progress on a current fix
	 */
	public Condenser(double steamIn, CondenserPump pump, int steamLevel, double temperature, double pressure, double waterLevel, double radius, double waterSurfaceArea, int height, double failProb, int fix, boolean functioning, boolean repairal,
			int fixingProgress) {
		super(steamLevel, temperature, pressure, waterLevel, radius, waterSurfaceArea, height, failProb, fix, functioning, repairal, fixingProgress);
		this.steamIn = steamIn;
		this.condenserPump = pump;
	}

	/**
	 * Converts stored steam back to water for the timestep
	 */
	public void condense() {
		// Checks if the condenser pump is on and working
		if (functional && condenserPump.isActive() && condenserPump.getFunctional()) {
			condensation = 30;
		} else
			condensation = 0;
		
		// stops the condenser overfilling
		if ((condensation + waterLevel) > 590) {
			condensation = 590 - waterLevel - 1;
			if (condensation < 0){ condensation = 0;}
		}
		
		// making sure condensation doesn't try to take more water than there
		// is steam in the reactor
		if (steamLevel >= condensation) {
			steamLevel -= condensation;
		} else {
			condensation = steamLevel;
			steamLevel = 0;
		}
		// converts steam to water
		waterLevel += condensation;
	}

	/**
	 * Increases the steamLevel by the specified amount.
	 */
	public void addSteam(double amount, double temperature) {
		steamInTemperature = temperature;
		steamLevel += amount;
		steamIn = amount;
	}

	/**
	 * Calculates the Condenser's temperature for the timestep
	 */
	public void calculateTemperature() {
		temperature = (((steamLevel - steamIn) * temperature) + (steamIn * steamInTemperature)) / steamLevel;
		temperature -= (condensation);
		steamIn = 0;
		if (temperature < 300)
			temperature = 300;
		if (temperature > 6000)
			temperature =6000;
	}

	
}
