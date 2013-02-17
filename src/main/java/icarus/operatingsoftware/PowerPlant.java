package icarus.operatingsoftware;

import icarus.exceptions.*;
import icarus.operatingsoftware.Components;
import icarus.reactor.*;
import icarus.util.SaveState;

/**
 * Emulates the software component of the nuclear reactor. In addition, it owns and manages the hardware components of
 * the reactor. All interaction with the system from the user goes through this level.
 *
 * @author Team Haddock
 *
 */
public class PowerPlant implements Plant {

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
     * Default constructor for PowerPlant, typically used upon starting a new game.
     */
    public PowerPlant() {
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
     * @param HfashTable with Reactor objects
     */
    public PowerPlant(SaveState state) {
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
    @Override
    public void setPlayerName(String name) {
        player.setName(name);
    }

    /**
     * get the name of the player
     *
     * @returns String name
     */
    @Override
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Opens a SteamValve in the system
     *
     * @param valveNum The id of the valve to open
     *
     * @throws InvalidValveException   Thrown when a bad valve ID is specified
     * @throws AlreadyAtStateException Thrown if the specified valve is already open
     */
    @Override
    public void open(int valveNum) throws InvalidValveException, AlreadyAtStateException {
        steamValve.open(valveNum);
    }

    /**
     * Close a SteamValve in the system
     *
     * @param valveNum The id of the valve to close
     *
     * @throws InvalidValveException   Thrown when a bad valve ID is specified
     * @throws AlreadyAtStateException Thrown if the specified valve is already closed
     */
    @Override
    public void close(int valveNum) throws InvalidValveException, AlreadyAtStateException {
        steamValve.close(valveNum);
    }

    /**
     * Turn on a Pump in the system
     *
     * @param pumpNum The id of the pump to turn on
     *
     * @throws InvalidPumpException     Thrown when a bad pump ID is specified
     * @throws AlreadyAtStateException  Thrown if the specified pump is already on
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    @Override
    public void turnOn(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        if (validPumpNum(pumpNum)) {
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
     * @throws InvalidPumpException     Thrown when a bad pump ID is specified
     * @throws AlreadyAtStateException  Thrown if the specified pump is already on
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    @Override
    public void turnOff(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        if (validPumpNum(pumpNum)) {
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
    @Override
    public boolean isWaterPumpActive(int pumpNum) {
        if(pumpNum==2)
        {
            return isCondenserPumpActive();
        }
        return waterPump[pumpNum].isActive();
    }

    /**
     * Check if a condenser pump is active
     *
     * @returns whether pump is active
     */
    @Override
    public boolean isCondenserPumpActive() {
        return condenserPump.isActive();
    }

    /**
     * Check if a valve is opened
     *
     * @returns whether valve is open
     * @throws InvalidValveException Thrown when bad ID is specified
     */
    @Override
    public boolean isValveOpened(int valveNum) {
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
    @Override
    public double temperature(Components component) {
        switch (component) {
            case CONDENSER:
                return condenser.getTemperature();
            case REACTOR:
                return reactor.getTemperature();
            default:
                throw new IllegalArgumentException("'" + component.toString() +
                                                   "' is not a valid component for this method.");
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
    @Override
    public double waterLevel(Components component) {
        switch (component) {
            case CONDENSER:
                return condenser.getWaterLevel();
            case REACTOR:
                return reactor.getWaterLevel();
            default:
                throw new IllegalArgumentException("'" + component.toString() +
                                                   "' is not a valid component for this method.");
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
    @Override
    public boolean functional(Components component) {
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
                throw new IllegalArgumentException("This method cannot be called with '" + component.toString() + "'");
        }
    }

    /**
     * Variation of functional(Components) for pumps, returns the functionality of a specified pump
     *
     * @param component Required to be set to Components.WATERPUMP, specifies the component to inspect
     * @param pumpNum   The id of the pump to inspect
     *
     * @return whether the specified pump is functional
     *
     * @throws InvalidComponentException Thrown when a bad component is specified
     */
    @Override
    public boolean functional(Components component, int pumpNum) {
        if (component != Components.WATERPUMP) {
            throw new IllegalArgumentException("This method cannot be called with '" + component.toString() + "'");
        }
        if (pumpNum < 0 || pumpNum >= waterPump.length) {
            if(pumpNum ==2)
            {
                return functional(Components.CONDENSERPUMP);
            }
            throw new IllegalArgumentException("'" + pumpNum + "' is not a valid pump number.");
        }
        return waterPump[pumpNum].getFunctional();
    }

    /**
     * Returns the rod height
     *
     * @return double containing the rod height
     */
    @Override
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
    @Override
    public double pressure(Components component) {
        switch (component) {
            case CONDENSER:
                return condenser.getPressure();
            case REACTOR:
                return reactor.getPressure();
            default:
                throw new IllegalArgumentException("'" + component.toString() +
                                                   "' is not a valid component for this method.");
        }
    }

    /**
     * Returns the total power generated
     */
    @Override
    public int getPower() {
        return generator.getPower();
    }

    /**
     * Begins a fix on any non-pump fixable component
     *
     * @param component The component to begin the fix upon
     *
     * @throws InvalidComponentException   Thrown when a bad component is specified
     * @throws FixAlreadyUnderwayException Thrown when a fix is already occurring in the system
     * @throws NoFixNeededException        Thrown when attempting to fix a functional component
     */
    @Override
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
     * @param pumpNum   The id of the pump to be fixed
     *
     * @throws InvalidComponentException   Thrown when a non-pump component is specified
     * @throws FixAlreadyUnderwayException Thrown when a fix is already occurring in the system
     * @throws NoFixNeededException        Thrown when attempting to fix a functional component
     */
    @Override
    public void fix(Components component, int pumpNum) throws InvalidComponentException, FixAlreadyUnderwayException,
                                                              InvalidPumpException, NoFixNeededException {
        if (!fixUnderway()) {
            if (component == Components.WATERPUMP) {
                if (validPumpNum(pumpNum)) {
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
    @Override
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
        doFixes();
        
    }

    private void doFixes() {
        waterPump[0].fixCycle();
        waterPump[1].fixCycle();
        condenserPump.fixCycle();
        reactor.fixCycle();
        turbine.fixCycle();
        condenser.fixCycle();
    }

    /**
     * Rolls for and checks if any component fails
     *
     * @return Whether a component has failed
     */
    boolean checkFailures() {
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
     * @param id        The pump ID to inspect
     *
     * @return Whether the specified pump is being repaired
     */
    @Override
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
    @Override
    public boolean checkIfGameOver() {
        return (reactor.getTemperature() > 6000);
    }

    /**
     * Places all objects in the reactor + Player in a hashtable, for saving purposes
     *
     * @return a hashtable
     */
    @Override
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

    /**
     * Move the control rods to the specified position
     *
     * @param amount The position to move the control rods to, from 1-100
     *
     * @throws InvalidRodsException     Thrown when amount has an invalid value
     * @throws ComponentFailedException Thrown when the reactor has failed and the control rods cannot be moved
     */
    @Override
    public void movecontrolrods(int amount) throws InvalidRodsException, ComponentFailedException {
        reactor.movecontrolrods(amount);
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

    private boolean validPumpNum(int pumpNum) {
        return pumpNum == 0 || pumpNum == 1;
    }
}
