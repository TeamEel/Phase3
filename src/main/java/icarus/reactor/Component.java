package icarus.reactor;

import java.io.Serializable;
import icarus.exceptions.NoFixNeededException;

/**
 * Component abstract class, used for all components that can fail.
 *
 * @author Team Haddock
 *
 */
public abstract class Component implements Serializable {

    protected boolean functional;
    private boolean repairing;
    protected double failureProbability;
    protected int fixTime;
    private int fixProgress;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     *
     * @param failProb The (maximum) probability of a component failing
     * @param fix The number of timesteps required to fix a component
     */
    public Component(double failProb, int fix) {
        functional = true;
        repairing = false;
        failureProbability = failProb;
        fixTime = fix;
        fixProgress = 0;
    }

    /**
     * Constructor used when loading a Component from a saved state.
     *
     * @param failProb The (maximum) probability of a component failing
     * @param fix The number of timesteps required to fix a component
     * @param functioning Whether or not the Component is currently functioning
     * @param repairal Whether or not the Component is currently being repaired
     * @param fixingProgress The progress on a current fix
     */
    public Component(double failProb, int fix, boolean functioning, boolean repairal, int fixingProgress) {
        functional = functioning;
        repairing = repairal;
        failureProbability = failProb;
        fixTime = fix;
        fixProgress = fixingProgress;
    }

    /**
     * This method calculates whether a Component has failed this timestep.
     *
     * @return Returns whether the Component has failed or not (ie !functional)
     */
    public abstract boolean checkFail();

    /**
     * This method progresses a repair on a component that is currently undergoing repair.
     *
     * @return True on completion of repair.
     */
    public boolean fixCycle() {
        if (repairing) {
            fixProgress += 1;
            if (fixTime == fixProgress) {
                functional = true;
                repairing = false;
                fixProgress = 0;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Whether or not Component is functional
     */
    public boolean getFunctional() {
        return functional;
    }

    /**
     *
     * @return Whether or not Component is repairing
     */
    public boolean getRepairing() {
        return repairing;
    }

    /**
     * Begins a fix on a failed component
     *
     * @return True if fix has begun successfully
     *
     * @throws NoFixNeededException
     */
    public boolean beginFix() throws NoFixNeededException {
        if (!functional) {
            if (!repairing) {
                repairing = true;
                return true;
            } else {
                return false;
            }
        } else {
            throw new NoFixNeededException();
        }
    }

    /**
     * Calculates and returns remaining time on a repair
     *
     * @return The number of timesteps until the repair is completed.
     */
    public int getFixRemaining() {
        return fixTime - fixProgress;
    }
}
