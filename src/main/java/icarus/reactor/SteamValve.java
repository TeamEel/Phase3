package icarus.reactor;

import icarus.exceptions.*;

import java.io.Serializable;

/**
 * Steam Valve system that controls the flow of steam between the reactor, turbine and condenser. Consists of two
 * valves: the first flows via the turbine, the second does not.
 *
 * @author Team Haddock
 *
 */
public class SteamValve implements Serializable {

    private MajorComponent mCompIn;
    private Turbine turbine;
    private MajorComponent mCompOut;
    private boolean open1, open2;
    private static final long serialVersionUID = 1;

    /**
     * Primary constructor for use when initialising a new game
     *
     * @param mComponentIn The MajorComponent pushing steam into the one-way valve system
     * @param turbine The turbine which the steam passes over
     * @param mComponentOut The MajorComponent receiving steam out of the valve system
     */
    public SteamValve(MajorComponent mComponentIn, Turbine turbine, MajorComponent mComponentOut) {
        open1 = open2 = true;
        mCompIn = mComponentIn;
        this.turbine = turbine;
        mCompOut = mComponentOut;
    }

    /**
     * Moves steam from the in component to the out component according to pressure.
     */
    public void equalise() {
        turbine.calculateRPM(0);
        if ((turbine.getFunctional() && open1) || open2) {
            double pressureDiff = mCompIn.getPressure() - mCompOut.getPressure();
            if (pressureDiff > 0) {
                //amount works out the steam amount to drain, worked out using the opposite method to getPressure
                double amount = mCompIn
                                                   .drainSteam((mCompIn.getHeight() - mCompIn.getWaterLevel()) *
                                                               (pressureDiff / 2) / mCompIn.getTemperature());
                // works out how much steam to pass to turbine to work out rpm
                if (turbine.getFunctional() && open1 && open2) {
                    turbine.calculateRPM(amount / 2);
                }
                if (turbine.getFunctional() && open1 && !open2) {
                    turbine.calculateRPM(amount);
                }
                mCompOut.addSteam(amount, mCompIn.getTemperature());
            }
        }
    }

    /**
     * Opens the valve with the specified id.
     *
     * @param valveID ID of the valve to open
     *
     * @throws InvalidValveException Thrown when a bad ID is given
     * @throws AlreadyAtStateException Thrown if valve is already open
     */
    public void open(int valveID) throws InvalidValveException, AlreadyAtStateException {
        switch (valveID) {
            case 0:
                if (open1) {
                    throw new AlreadyAtStateException("open");
                }
                open1 = true;
                break;
            case 1:
                if (open2) {
                    throw new AlreadyAtStateException("open");
                }
                open2 = true;
                break;
            default:
                throw new InvalidValveException(valveID);
        }
    }

    /**
     * Closes the valive with the specified id.
     *
     * @param valveID ID of the valve to close
     *
     * @throws InvalidValveException Thrown when a bad ID is given
     * @throws AlreadyAtStateException Thrown if valve is already closed
     */
    public void close(int valveID) throws InvalidValveException, AlreadyAtStateException {
        switch (valveID) {
            case 0:
                if (!open1) {
                    throw new AlreadyAtStateException("closed");
                }
                open1 = false;
                break;
            case 1:
                if (!open2) {
                    throw new AlreadyAtStateException("closed");
                }
                open2 = false;
                break;
            default:
                throw new InvalidValveException(valveID);
        }
    }

    /**
     * Checks whether the specified valve is open.
     *
     * @param valveID ID of valve open status to return
     *
     * @return open status of specified valve
     *
     * @throws InvalidValveException Thrown when a bad ID is given
     */
    public boolean isOpen(int valveID) throws InvalidValveException {
        switch (valveID) {
            case 0:
                return open1;
            case 1:
                return open2;
            default:
                throw new InvalidValveException(valveID);
        }
    }
}
