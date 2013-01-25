package icarus.operatorsoftware;

import icarus.exceptions.*;
import icarus.operatorsoftware.Components;
import icarus.reactor.*;
import icarus.util.SaveState;

/**
 * Emulates the software component of the nuclear reactor. In addition, it owns and manages the hardware components of
 * the reactor. All interaction with the system from the user goes through this level.
 *
 * @author Team Haddock
 *
 */
public class OperatorSoftware {

    Reactor reactor;
    Condenser condenser;
    Turbine turbine;
    Generator generator;
    SteamValve steamValve;
    WaterPump[] waterPump;
    CondenserPump condenserPump;
    Components currentFix;
    Player player;

    /**
     * Default constructor for OperatorSoftware, typically used upon starting a new game.
     */
    public OperatorSoftware() {
        condenserPump = new CondenserPump();

        reactor = new Reactor();
        condenser = new Condenser(condenserPump);

        turbine = new Turbine();
        generator = new Generator(turbine);

        steamValve = new SteamValve(reactor, turbine, condenser);

        waterPump = new WaterPump[2];
        waterPump[0] = new WaterPump(condenser, reactor);
        waterPump[1] = new WaterPump(condenser, reactor);
        try {
            waterPump[1].turnOff();
        } catch (AlreadyAtStateException e) {
            e.printStackTrace();
        } catch (ComponentFailedException e) {
            e.printStackTrace();
        }
        player = new Player();
    }

    /**
     * Constructor used when loading the game from a save state.
     *
     * @param HashTable with Reactor objects
     */
    public OperatorSoftware(SaveState state) {
        reactor = state.reactor;
        condenser = state.condenser;
        turbine = state.turbine;
        generator = state.generator;
        waterPump = state.waterPumps;
        steamValve = state.steamValve;
        condenserPump = state.condenserPump;
        player = state.player;
    }

    /**
     * sets the name of the player
     *
     * @param String name
     */
    public void setPlayerName(String name) {
        player.setName(name);
    }

    /**
     * get the name of the player
     *
     * @returns String name
     */
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Move the control rods up in the reactor by the amount specified
     *
     * @param amount The amount to raise the control rods by
     *
     * @return The new height of the control rods
     *
     * @throws InvalidRodsException Thrown when amount specified is negative
     * @throws MaximumRodsException Thrown when the rods attempt to exceed the maximum height
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    public int raise(int amount) throws InvalidRodsException, MaximumRodsException, ComponentFailedException {

        reactor.raise(amount);
        return reactor.getRodHeight();
    }

    /**
     * Move the control rods down in the reactor by the amount specified
     *
     * @param amount The amount to lower the control rods by
     *
     * @return The new height of the control rods
     *
     * @throws InvalidRodsException Thrown when amount specified is negative
     * @throws MinimumRodsException Thrown when the rods attempt to exceed the minimum height
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    public int lower(int amount) throws InvalidRodsException, MinimumRodsException, ComponentFailedException {
        reactor.lower(amount);

        return reactor.getRodHeight();
    }

    /**
     * Opens a SteamValve in the system
     *
     * @param valveNum The id of the valve to open
     *
     * @throws InvalidValveException Thrown when a bad valve ID is specified
     * @throws AlreadyAtStateException Thrown if the specified valve is already open
     */
    public void open(int valveNum) throws InvalidValveException, AlreadyAtStateException {
        steamValve.open(valveNum);
    }

    /**
     * Close a SteamValve in the system
     *
     * @param valveNum The id of the valve to close
     *
     * @throws InvalidValveException Thrown when a bad valve ID is specified
     * @throws AlreadyAtStateException Thrown if the specified valve is already closed
     */
    public void close(int valveNum) throws InvalidValveException, AlreadyAtStateException {
        steamValve.close(valveNum);
    }

    /**
     * Turn on a Pump in the system
     *
     * @param pumpNum The id of the pump to turn on
     *
     * @throws InvalidPumpException Thrown when a bad pump ID is specified
     * @throws AlreadyAtStateException Thrown if the specified pump is already on
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    public void turnOn(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        if (pumpNum == 0 || pumpNum == 1) {
            waterPump[pumpNum].turnOn();
        } else if (pumpNum == 2) {
            condenserPump.turnOn();
        } else {
            throw new InvalidPumpException(pumpNum);
        }
    }

    /**
     * Turn off a Pump in the system
     *
     * @param pumpNum The id of the pump to turn off
     *
     * @throws InvalidPumpException Thrown when a bad pump ID is specified
     * @throws AlreadyAtStateException Thrown if the specified pump is already on
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    public void turnOff(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        if (pumpNum == 0 || pumpNum == 1) {
            waterPump[pumpNum].turnOff();
        } else if (pumpNum == 2) {
            condenserPump.turnOff();
        } else {
            throw new InvalidPumpException(pumpNum);
        }
    }

    /**
     * Check if a water pump is active
     *
     * @param pumpNum The id of the pump
     *
     * @returns whether pump of ID is active
     */
    public boolean isWaterPumpActive(int pumpNum) {
        return waterPump[pumpNum].isActive();
    }

    /**
     * Check if a condenser pump is active
     *
     * @returns whether pump is active
     */
    public boolean isCondenserPumpActive() {
        return condenserPump.isActive();
    }

    /**
     * Check if a valve is opened
     *
     * @returns whether valve is open
     * @throws InvalidValveException Thrown when bad ID is specified
     */
    public boolean isValveOpened(int valveNum) throws InvalidValveException {
        return steamValve.isOpen(valveNum);
    }

    /**
     * Returns the temperature of a requested component
     *
     * @param component Value of the enum of which component to retrieve the temperature
     *
     * @return double containing the temperature of the requested component
     *
     * @throws InvalidComponentException Thrown when a non-reactor/-condenser component is specified
     */
    public double temperature(Components component) throws InvalidComponentException {
        switch (component) {
            case CONDENSER:
                return condenser.getTemperature();
            case REACTOR:
                return reactor.getTemperature();
            default:
                throw new InvalidComponentException(component.toString());
        }
    }

    /**
     * Returns the water level of a requested component
     *
     * @param component Value of the enum of which component to retrieve the water level
     *
     * @return double containing the water level of the requested component
     *
     * @throws InvalidComponentException Thrown when a non-reactor/-condenser component is specified
     */
    public double waterLevel(Components component) throws InvalidComponentException {
        switch (component) {
            case CONDENSER:
                return condenser.getWaterLevel();
            case REACTOR:
                return reactor.getWaterLevel();
            default:
                throw new InvalidComponentException(component.toString());
        }
    }

    /**
     * Returns whether a specified component is working correctly
     *
     * @param component The component to inspect
     *
     * @return whether the specified component is functional
     *
     * @throws InvalidComponentException Thrown when a bad component is specified
     */
    public boolean functional(Components component) throws InvalidComponentException {
        switch (component) {
            case CONDENSER:
                return condenser.getFunctional();
            case REACTOR:
                return reactor.getFunctional();
            case CONDENSERPUMP:
                return condenserPump.getFunctional();
            case TURBINE:
                return turbine.getFunctional();
            default:
                throw new InvalidComponentException(component.toString());
        }
    }

    /**
     * Variation of functional(Components) for pumps, returns the functionality of a specified pump
     *
     * @param component Required to be set to Components.WATERPUMP, specifies the component to inspect
     * @param pumpNum The id of the pump to inspect
     *
     * @return whether the specified pump is functional
     *
     * @throws InvalidComponentException Thrown when a bad component is specified
     */
    public boolean functional(Components component, int pumpNum) throws InvalidComponentException {
        if (component == Components.WATERPUMP) {
            return waterPump[pumpNum].getFunctional();
        } else {
            throw new InvalidComponentException(component.toString());
        }
    }

    /**
     * Returns the rod height
     *
     * @return double containing the rod height
     */
    public int rodHeight() {
        return reactor.getRodHeight();
    }

    /**
     * Returns the pressure of a requested component
     *
     * @param component Value of the enum of which component to retrieve the pressure
     *
     * @return double containing the pressure of the requested component
     *
     * @throws InvalidComponentException Thrown when a non-reactor/-condenser component is specified
     */
    public double pressure(Components component) throws InvalidComponentException {
        switch (component) {
            case CONDENSER:
                return condenser.getPressure();
            case REACTOR:
                return reactor.getPressure();
            default:
                throw new InvalidComponentException(component.toString());
        }
    }

    /**
     * Returns the total power generated
     */
    public int getPower() {
        return generator.getPower();
    }

    /**
     * Begins a fix on any non-pump fixable component
     *
     * @param component The component to begin the fix upon
     *
     * @throws InvalidComponentException Thrown when a bad component is specified
     * @throws FixAlreadyUnderwayException Thrown when a fix is already occurring in the system
     * @throws NoFixNeededException Thrown when attempting to fix a functional component
     */
    public void fix(Components component) throws InvalidComponentException, FixAlreadyUnderwayException,
                                                NoFixNeededException {
        if (!fixUnderway()) {
            switch (component) {
                case CONDENSER:
                    condenser.beginFix();
                    break;
                case REACTOR:
                    reactor.beginFix();
                    break;
                case TURBINE:
                    turbine.beginFix();
                    break;
                case CONDENSERPUMP:
                    condenserPump.beginFix();
                    break;
                default:
                    throw new InvalidComponentException(component.toString());
            }
        } else {
            throw new FixAlreadyUnderwayException();
        }
    }

    /**
     * Begins fixes for pumps
     *
     * @param component Must be set to WaterPump, used as a check to ensure desired functionality
     * @param pumpNum The id of the pump to be fixed
     *
     * @throws InvalidComponentException Thrown when a non-pump component is specified
     * @throws FixAlreadyUnderwayException Thrown when a fix is already occurring in the system
     * @throws NoFixNeededException Thrown when attempting to fix a functional component
     */
    public void fix(Components component, int pumpNum) throws InvalidComponentException, FixAlreadyUnderwayException,
                                                             InvalidPumpException, NoFixNeededException {
        if (!fixUnderway()) {
            if (component == Components.WATERPUMP) {
                if (pumpNum == 0 || pumpNum == 1) {
                    waterPump[pumpNum].beginFix();
                } else {
                    throw new InvalidPumpException(pumpNum);
                }
            } else {
                throw new InvalidComponentException(component.toString());
            }
        } else {
            throw new FixAlreadyUnderwayException();
        }
    }

    /**
     * Progresses time in the system. All time dependent activities occur such as changes in temperature, pressure,
     * water levels, power output and equiptment failure and fixes.
     */
    public void next() {
        reactor.calculateActivity();

        waterPump[0].pumpWater();
        waterPump[1].pumpWater();

        reactor.calculateTemperature();

        reactor.evaporate();
        condenser.condense();

        steamValve.equalise();

        condenser.calculateTemperature();

        reactor.calculatePressure();
        condenser.calculatePressure();

        // turbine.calculateRPM(); note: removed, this is now done as part of
        // SteamValve.equalise()
        generator.calculatePower();

        checkFailures();
        // UPDATE DISPLAY
    }

    /**
     * Rolls for and checks if any component fails
     *
     * @return Whether a component has failed
     */
    public boolean checkFailures() {
        //if a functional component fails, it will return true, leaving checkFailures
        //without calling checkFail() on any other components
        for (Component c : failableComponents()) {
            if (c.getFunctional() && c.checkFail()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Progresses current repairs in the system
     *
     * @return Whether a repair has finished.
     */
    public boolean doFix() {
        for (Component c : failableComponents()) {
            if (c.fixCycle()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Whether there is currently a fix underway in the system
     */
    public boolean fixUnderway() {
        for (Component c : failableComponents()) {
            if (c.getRepairing()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the number of timesteps remaining for the current fix to complete. Does not specify what component is being
     * fixed.
     *
     * @return The fix time on a current fix in the system
     */
    public int getFixTime() {
        for (Component c : failableComponents()) {
            if (c.getRepairing()) {
                return c.getFixRemaining();
            }
        }
        return 0; // Should probably throw an exception
    }

    /**
     * Returns whether a specified component is currently being repaired
     *
     * @param component The component to inspect
     *
     * @return Whether the component is currently being repaired
     */
    public boolean isRepairing(Components component) {
        switch (component) {
            case CONDENSER:
                return condenser.getRepairing();
            case REACTOR:
                return reactor.getRepairing();
            case TURBINE:
                return turbine.getRepairing();
            case CONDENSERPUMP:
                return condenserPump.getRepairing();
            default:
                return false;
        }
    }

    /**
     * The pump variant of isRepairing(Components). Returns whether a specified pump is currently being repaired.
     *
     * @param component required to be set to Components.WATERPUMP, specifies the component to inspect
     * @param id The pump ID to inspect
     *
     * @return Whether the specified pump is being repaired
     */
    public boolean isRepairing(Components component, int id) {
        if (component == Components.WATERPUMP && id == 0) {
            return waterPump[0].getRepairing();
        }
        if (component == Components.WATERPUMP && id == 1) {
            return waterPump[1].getRepairing();
        }
        return false;
    }

    /**
     * Checks whether the game has ended - if the temperature in the reactor has exceeded the max
     *
     * @return Whether the game over scenario has been reached
     */
    public boolean checkIfGameOver() {
        return (reactor.getTemperature() > 6000);
    }

    /**
     * Places all objects in the reactor + Player in a hashtable, for saving purposes
     *
     * @return a hashtable
     */
    public SaveState getGameState() {
        SaveState s = new SaveState();
        s.reactor = reactor;
        s.condenser = condenser;
        s.generator = generator;
        s.turbine = turbine;
        s.steamValve = steamValve;
        s.waterPumps = waterPump;
        s.condenserPump = condenserPump;
        s.player = player;
        return s;
    }
    
    private Component[] failableComponents() {
        Component[] result = {
            reactor,
            condenser,
            turbine,
            waterPump[0],
            waterPump[1],
            condenserPump
        };
        return result;
    }
}
