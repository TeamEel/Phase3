/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icarus.operatingsoftware;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.FixAlreadyUnderwayException;
import icarus.exceptions.InvalidComponentException;
import icarus.exceptions.InvalidPumpException;
import icarus.exceptions.InvalidRodsException;
import icarus.exceptions.InvalidValveException;
import icarus.exceptions.NoFixNeededException;
import icarus.util.FileInput;
import icarus.util.FileOutput;
import icarus.util.SaveState;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author James
 */
public class OperatingSoftware extends Observable implements PlantControl {

    private final int pumpCount = 2;
    private final int valveCount = 2;
    private final int maxControlRodPosition = 100;
    private ProbabilitySource probability;
    private Plant plant;
    private int score;

    public OperatingSoftware(Plant plant, ProbabilitySource probability) {
        this.plant = plant;
        this.probability = probability;
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
        if (!softwareFailure()) {
            plant.turnOff(pumpNum);
        }
        updateObservers();
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
        if (!softwareFailure()) {
            plant.turnOn(pumpNum);
        }
        updateObservers();
    }

    /**
     * Move the control rods in the reactor to the amount specified
     *
     * @param amount The amount to lower the control rods by
     *
     * @return The new height of the control rods
     *
     * @throws InvalidRodsException     Thrown when amount specified is negative
     * @throws ComponentFailedException Thrown if method is called when component is failed.
     */
    @Override
    public void movecontrolrods(int amount) throws InvalidRodsException, ComponentFailedException {
        if (!softwareFailure()) {
            plant.movecontrolrods(amount);
        }
        updateObservers();
    }

    /**
     * Open a SteamValve in the system
     *
     * @param valveNum The id of the valve to close
     *
     * @throws InvalidValveException   Thrown when a bad valve ID is specified
     * @throws AlreadyAtStateException Thrown if the specified valve is already closed
     */
    @Override
    public void open(int amount) throws InvalidValveException, AlreadyAtStateException {
        if (!softwareFailure()) {
            plant.open(amount);
        }
        updateObservers();
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
        if (!softwareFailure()) {
            plant.close(valveNum);
        }
        updateObservers();
    }

    /**
     * Begins a fix on any non-pump fixable component
     *
     * @param component The component to begin the fix upon
     *
     * @throws InvalidComponentException   Thrown when a bad component is specified
     * @throws FixAlreadyUnderwayException Thrown when a fix is already occurring in the system
     * @throws NoFixNeededException
     */
    @Override
    public void fix(Components component) throws InvalidComponentException, FixAlreadyUnderwayException,
                                                 NoFixNeededException {
        if (!softwareFailure()) {
            plant.fix(component);
        }
        updateObservers();
    }

    /**
     * Begins fixes for pumps
     *
     * @param component Must be set to WaterPump, used as a check to ensure desired functionality
     * @param pumpNum   The id of the pump to be fixed
     *
     * @throws InvalidComponentException   Thrown when a non-pump component is specified
     * @throws FixAlreadyUnderwayException Thrown when a fix is already occurring in the system
     * @throws NoFixNeededException
     */
    @Override
    public void fix(Components component, int pumpNum) throws InvalidPumpException, InvalidComponentException,
                                                              FixAlreadyUnderwayException, NoFixNeededException {
        if (!softwareFailure()) {
            plant.fix(component, pumpNum);
        }
        updateObservers();
    }

    private boolean softwareFailure() {
        /*
         * A 1 in 8 chance of a software failure occuring
         */
        if (probability.trueOnceIn(8)) {
            /*
             * Do nothing or invoke a random command
             */
            if (probability.trueOnceIn(2)) {
                randomCommand();
            }
            return true;
        }
        return false;
    }

    private void randomCommand() {
        /*
         * Pick a random command and execute it.
         */
        try {
            switch (probability.choiceFromZeroTo(5)) {
                case 0:
                    plant.turnOn(probability.choiceFromZeroTo(pumpCount));
                    break;
                case 1:
                    plant.turnOff(probability.choiceFromZeroTo(pumpCount));
                    break;
                case 2:
                    plant.open(probability.choiceFromZeroTo(valveCount));
                    break;
                case 3:
                    plant.close(probability.choiceFromZeroTo(valveCount));
                    break;
                case 4:
                    plant.movecontrolrods(probability.choiceFromZeroTo(maxControlRodPosition + 1));
                    break;
            }
        } catch (Exception e) {/*Do nothing*/

        }
        updateObservers();
    }

    /**
     * sets the name of the player
     *
     * @param String The name of the player
     */
    @Override
    public void setPlayerName(String name) {
        plant.setPlayerName(name);
        updateObservers();
    }

    /**
     * get the name of the player
     *
     * @returns String name
     */
    @Override
    public String getPlayerName() {
        return plant.getPlayerName();
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
        return plant.isWaterPumpActive(pumpNum);
    }

    /**
     * Check if a condenser pump is active
     *
     * @returns whether pump is active
     */
    @Override
    public boolean isCondenserPumpActive() {
        return plant.isCondenserPumpActive();
    }

    /**
     * Check if a valve is opened
     *
     * @returns whether valve is open
     * @throws InvalidValveException Thrown when bad ID is specified
     */
    @Override
    public boolean isValveOpened(int valveNum) {
        return plant.isValveOpened(valveNum);
    }

    /**
     *
     * @return the total power generated
     */
    @Override
    public int getPower() {
        return plant.getPower();
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
        return plant.waterLevel(component);
    }

    /**
     * Returns the temperature of a requested component
     *
     * @param component Value of the enum of which component to retrieve the temperature.
     *
     * @return double containing the temperature of the requested component
     *
     * @throws InvalidComponentException Thrown when a non-reactor/-condenser component is specified
     */
    @Override
    public double temperature(Components component) {
        return plant.temperature(component);
    }

    /**
     * Returns the pressure of a requested component
     *
     * @param component Value of the enum of which component to retrieve the pressure.
     *
     * @return double containing the pressure of the requested component
     *
     * @throws InvalidComponentException Thrown when a non-reactor/-condenser component is specified
     */
    @Override
    public double pressure(Components component) {
        return plant.pressure(component);
    }

    /**
     * Returns the rod height
     *
     * @return double containing the rod height
     */
    @Override
    public int rodHeight() {
        return plant.rodHeight();
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
        return plant.functional(component);
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
    public boolean functional(Components component, int pumpID) {
        return plant.functional(component, pumpID);
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
        return plant.isRepairing(component);
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
    public boolean isRepairing(Components component, int pumpID) {
        return plant.isRepairing(component, pumpID);
    }

    /**
     *
     * @return Whether there is currently a fix underway in the system
     */
    @Override
    public boolean fixUnderway() {
        return plant.fixUnderway();
    }

    /**
     * Gets the number of timesteps remaining for the current fix to complete. Does not specify what component is being
     * fixed.
     *
     * @return The fix time on a current fix in the system
     */
    @Override
    public int getFixTime() {
        return plant.getFixTime();
    }

    /**
     * Check if game over
     *
     * @returns true or false
     */
    @Override
    public boolean checkIfGameOver() {
        return plant.checkIfGameOver();
    }

    /**
     * Calculates the player's score relative to how much power they produced
     */
    private void calculateScore() {
        score = 0;
        score = (int)plant.getPower() * 5;
    }

    /**
     *
     * @return the score earned by the player
     */
    public int getScore() {
        calculateScore();
        return score;
    }

    /**
     * the achievementResponse() method calculates what medal the player achieves when they hit a game over scenario
     *
     * @param playerName the name of the player
     *
     * @return response INdicates the response a player receives based on how highly they scored in the game
     */
    public String achievementResponse(String playerName) {

        String response;

        if (score > 250000) {
            if (score > 500000) {
                if (score > 950000) {
                    // gold medal awarded if bigger than all of the score tiers
                    response = String
                            .format("You're a hero %s! you have been awarded the Medal of Honour by the Barack Obama himself." +
                                    '\n' +
                                    "Awarded for \"Gallantry and intrepidity at risk of life above and beyond the call of duty\"." +
                                    '\n' +
                                    "Your power led to the destruction of ALL the alien ships!!", playerName);
                } else {
                    // if above bronze tier but doesn't reach gold awarded
                    // silver medal
                    response = String
                                    .format("Congratulations %s! You've been awarded a Silver Star for \"Gallantry in action\"!" +
                                            '\n' + "You destroyed nearly all", playerName);
                }
            } else {
                // bronze reponse
                response = String
                        .format(
                        "Well done %s! You have been awarded a Bronze Star for your efforts against the aliens." + '\n' +
                        "You took out a good number with your party powered superweapon, maybe play again to take out some more?",
                        playerName);
            } // under 3000 but over 1000 awarde bronze medal
        } else {
            // player did not reach any of the goals, gains no medal
            response = "Unlucky this time, you did not manage to kill enough aliens to earn a medal..." + '\n' +
                       "Maybe you'd like to try again, the human race depends on you! (and the president is a bit embarassed he called you...)";
        }
        return response;
    }

    /**
     * The next() method just calls next in PowerPlant (calculations are done here)
     */
    @Override
    public void next() {
        plant.next();
        updateObservers();
    }

    /**
     * Progresses current repairs in the system
     *
     * @return Whether a repair has finished.
     */
    @Override
    public boolean doFix() {
        boolean value = plant.doFix();
        updateObservers();
        return value;
    }

    /**
     * Save the reactor objects to a file
     *
     * @param fileName specifies the name of the file
     */
    public void saveToFile(String fileName) {
        FileOutput.saveObjectToFile(plant.getGameState(), fileName);
    }

    /**
     * Load reactor objects from a file
     *
     * @param fileName specifies the name of the file
     *
     * @return Hashtable containing the reactor objects
     */
    public boolean loadFromFile(String fileName) {
        try {
            SaveState s = FileInput.loadObjectFromFile(fileName);
            if (s == null) {
                return false;
            }
            plant = new PowerPlant(s);
            updateObservers();
            return true;
        } catch (Exception e) {
            System.out.println("Error loading from file");
            return false;
        }
    }

    /**
     * Resets the plant
     *
     */
    public void resetPlant() {
        plant = new PowerPlant();
        updateObservers();
    }

    private void updateObservers() {
        setChanged();
        notifyObservers();
    }
}
