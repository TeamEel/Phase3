package icarus.reactor;

import java.io.Serializable;

/**
 * Generates power from an attached turbine. The bit that makes a nuclear power station a power station.
 *
 * @author Team Haddock
 *
 */
public class Generator implements Serializable {

    private Turbine turbine;
    private int power;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     *
     * @param turbine
     */
    public Generator(Turbine turbine) {
        power = 0;
        this.turbine = turbine;
    }

    /**
     * Calculates the power output of the generator
     */
    public void calculatePower() {
        int rpm = turbine.getRPM();
        power = power + (5 * rpm);
    }

    /**
     *
     * @return Total power output of reactor
     */
    public int getPower() {
        return power;
    }
}