package icarus.reactor;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import java.io.Serializable;

/**
 * Pump used to move coolant through the condenser.
 *
 * @author Team Haddock
 *
 */
public class CondenserPump extends Component implements Serializable {

    private boolean active;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     */
    public CondenserPump() {
        super(0.02, 2);
        try {
            turnOn();
        } catch (AlreadyAtStateException e) {
            e.printStackTrace();
        } catch (ComponentFailedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calculates whether a Component has failed, called on next();.
     *
     * @return Returns whether the Component has failed or not (ie !functional)
     */
    @Override
    public boolean checkFail() {
        if (Math.random() < failureProbability && active) {
            functional = false;
            active = false;
            fixTime++;
        }
        return !functional;
    }

    /**
     * Enables the pump
     *
     * @throws AlreadyAtStateException  Thrown if pump is already on
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    public void turnOn() throws AlreadyAtStateException, ComponentFailedException {
        if (functional) {
            if (active) {
                throw new AlreadyAtStateException("on");
            }
            active = true;
        } else {
            throw new ComponentFailedException();
        }
    }

    /**
     * Disables the pump
     *
     * @throws AlreadyAtStateException  Thrown if pump is already off
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    public void turnOff() throws AlreadyAtStateException, ComponentFailedException {
        if (functional) {
            if (!active) {
                throw new AlreadyAtStateException("off");
            }
            active = false;
        } else {
            throw new ComponentFailedException();
        }
    }

    /**
     *
     * @return Whether the pump is currently running
     */
    public boolean isActive() {
        return active;
    }
}