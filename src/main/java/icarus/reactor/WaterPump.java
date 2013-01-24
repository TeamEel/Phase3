package icarus.reactor;

import icarus.exceptions.*;

import java.io.Serializable;

/**
 * WaterPump which moves water from one MajorComponent to another
 * 
 * @author Team Haddock
 * 
 */
public class WaterPump extends Component implements Serializable {

	private MajorComponent mcomp1;
	private MajorComponent mcomp2;
	private boolean active;
	private static final long serialVersionUID = 1;

	/**
	 * Primary constructor for use when initialising a new game
	 * 
	 * @param mcomp1
	 *            The MajorComponent to draw water from
	 * @param mcomp2
	 *            The MajorComponent to push water to
	 */
	public WaterPump(MajorComponent mcomp1, MajorComponent mcomp2) {
		super(0.2, 1);
		this.mcomp1 = mcomp1;
		this.mcomp2 = mcomp2;
		active = true;
	}

	/**
	 * This method calculates whether a Component has failed this timestep.
	 * 
	 * @return Returns whether the Component has failed or not (ie !functional)
	 */
	public boolean checkFail() {
		double failureChance = failureProbability * (0.9 * (Math.abs(mcomp1.getPressure() - mcomp2.getPressure()) / 10000) + 0.1);
		if (Math.random() < failureChance && active) {
			functional = false;
			fixTime++;
			active = false;
		}
		return !functional;
	}

	/**
	 * Enables the pump, causing it to move water on timestep
	 * 
	 * @throws AlreadyAtStateException
	 *             Thrown if pump is already on
	 * @throws ComponentFailedException
	 *             Thrown if method is called when component is failed.
	 */
	public void turnOn() throws AlreadyAtStateException, ComponentFailedException {
		if (functional) {
			if (active)
				throw new AlreadyAtStateException("on");
			active = true;
		} else
			throw new ComponentFailedException();
	}

	/**
	 * Disables the pump
	 * 
	 * @throws AlreadyAtStateException
	 *             Thrown if pump is already off
	 * @throws ComponentFailedException
	 *             Thrown if method is called when component is failed.
	 */
	public void turnOff() throws AlreadyAtStateException, ComponentFailedException {
		if (functional) {
			if (!active)
				throw new AlreadyAtStateException("off");
			active = false;
		} else
			throw new ComponentFailedException();
	}

	/**
	 * Moves water from the first MajorComponent to the second, if the pump is
	 * on and working
	 */
	public void pumpWater() {
		if (functional && active)
			mcomp2.addWater(mcomp1.drainWater(25));
		if (mcomp2.getWaterLevel() > 590) {
			mcomp1.addWater(mcomp2.drainWater(mcomp2.getWaterLevel() - 590));
		}
	}

	/**
	 * 
	 * @return Whether the current pump is currently on.
	 */
	public boolean isActive() {
		return active;
	}
}
