package icarus.reactor;

import java.io.Serializable;

/**
 * A turbine that converts steam passed over it to rotation, used in conjunction with a generator
 *
 * @author Team Haddock
 *
 */
public class Turbine extends Component implements Serializable {

    private double rpm;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     */
    public Turbine() {
        super(.08, 1);
        rpm = 0;
    }

    /**
     * Calculates the new RPM of the turbine dependent on a volume of steam passed over it
     *
     * @param steamAmount The amount of steam passing over the turbine
     */
    public void calculateRPM(double steamAmount) {
        if (functional) {
            rpm = 10 * steamAmount;
        } else {
            rpm = 0;
        }
    }

    /**
     *
     * @return The current RPM of the turbine
     */
    public int getRPM() {
        return (int)rpm;
    }

    /**
     * This method calculates whether a Component has failed this timestep.
     *
     * @return Returns whether the Component has failed or not (ie !functional)
     */
    public boolean checkFail() {
        double failureChance = failureProbability * (rpm / 4000);
        if (Math.random() < failureChance) {
            functional = false;
            fixTime++;
        }
        return !functional;
    }
}
