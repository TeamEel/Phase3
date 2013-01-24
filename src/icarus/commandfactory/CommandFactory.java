package icarus.commandfactory;

import icarus.exceptions.*;
import icarus.operatorsoftware.Component;
import icarus.operatorsoftware.OperatorSoftware;
import icarus.util.FileInput;
import icarus.util.FileOutput;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Handles commands specified by the UI, including calls to operatorSoftware,
 * saving and loading and calculating player's score
 * 
 * @author Team Haddock
 **/
public class CommandFactory {

	private OperatorSoftware op;
	private int score; // stores the score of the player
	private FileInput fileInput = new FileInput();
	private FileOutput fileOutput = new FileOutput();

	/**
	 * Default constructor for CommandFactory
	 */
	public CommandFactory() {
		op = new OperatorSoftware();
		// commands = new CommandWords();
	}

	/**
	 * sets the name of the player
	 * 
	 * @param String The name of the player
	 */
	public void setPlayerName(String name) {
		op.setPlayerName(name);
	}

	/**
	 * get the name of the player
	 * 
	 * @returns String name
	 */
	public String getPlayerName() {
		return op.getPlayerName();
	}

	/**
	 * Check if a water pump is active
	 * 
	 * @param pumpNum
	 *            The id of the pump
	 * @returns whether pump of ID is active
	 */
	public boolean isWaterPumpActive(int pumpNum) {
		return op.isWaterPumpActive(pumpNum);
	}

	/**
	 * Check if a condenser pump is active
	 * 
	 * @returns whether pump is active
	 */
	public boolean isCondenserPumpActive() {
		return op.isCondenserPumpActive();
	}

	/**
	 * Check if a valve is opened
	 * 
	 * @returns whether valve is open
	 * @throws InvalidValveException
	 *             Thrown when bad ID is specified
	 */
	public boolean isValveOpened(int valveNum) throws InvalidValveException {
		return op.isValveOpened(valveNum);
	}

	/**
	 * 
	 * @return the total power generated
	 */
	public int getPower() {
		return op.getPower();
	}

	/**
	 * Returns the water level of a requested component
	 * 
	 * @param component
	 *            Value of the enum of which component to retrieve the water
	 *            level
	 * @return double containing the water level of the requested component
	 * @throws InvalidComponentException
	 *             Thrown when a non-reactor/-condenser component is specified
	 */
	public double waterLevel(Component component) throws InvalidComponentException {
		return op.waterLevel(component);
	}

	/**
	 * Returns the temperature of a requested component
	 * 
	 * @param component
	 *            Value of the enum of which component to retrieve the
	 *            temperature.
	 * @return double containing the temperature of the requested component
	 * @throws InvalidComponentException
	 *             Thrown when a non-reactor/-condenser component is specified
	 */
	public double temperature(Component component) throws InvalidComponentException {
		return op.temperature(component);
	}

	/**
	 * Returns the pressure of a requested component
	 * 
	 * @param component
	 *            Value of the enum of which component to retrieve the
	 *            pressure.
	 * @return double containing the pressure of the requested component
	 * @throws InvalidComponentException
	 *             Thrown when a non-reactor/-condenser component is specified
	 */
	public double pressure(Component component) throws InvalidComponentException {
		return op.pressure(component);
	}

	/**
	 * Returns the rod height
	 * 
	 * @return double containing the rod height
	 */
	public int rodHeight() {
		return op.rodHeight();
	}

	/**
	 * Returns whether a specified component is working correctly
	 * 
	 * @param component
	 *            The component to inspect
	 * @return whether the specified component is functional
	 * @throws InvalidComponentException
	 *             Thrown when a bad component is specified
	 */
	public boolean functional(Component component) throws InvalidComponentException {
		return op.functional(component);
	}

	/**
	 * Variation of functional(Component) for pumps, returns the functionality
	 * of a specified pump
	 * 
	 * @param component
	 *            Required to be set to Component.WATERPUMP, specifies the
	 *            component to inspect
	 * @param pumpNum
	 *            The id of the pump to inspect
	 * @return whether the specified pump is functional
	 * @throws InvalidComponentException
	 *             Thrown when a bad component is specified
	 */
	public boolean functional(Component component, int pumpID) throws InvalidComponentException {
		return op.functional(component, pumpID);
	}

	/**
	 * Returns whether a specified component is currently being repaired
	 * 
	 * @param component
	 *            The component to inspect
	 * @return Whether the component is currently being repaired
	 */
	public boolean isRepairing(Component component) {
		return op.isRepairing(component);
	}

	/**
	 * The pump variant of isRepairing(Component). Returns whether a specified
	 * pump is currently being repaired.
	 * 
	 * @param component
	 *            required to be set to Component.WATERPUMP, specifies the
	 *            component to inspect
	 * @param id
	 *            The pump ID to inspect
	 * @return Whether the specified pump is being repaired
	 */
	public boolean isRepairing(Component component, int pumpID) {
		return op.isRepairing(component, pumpID);
	}

	/**
	 * 
	 * @return Whether there is currently a fix underway in the system
	 */
	public boolean fixUnderway() {
		return op.fixUnderway();
	}

	/**
	 * Gets the number of timesteps remaining for the current fix to complete.
	 * Does not specify what component is being fixed.
	 * 
	 * @return The fix time on a current fix in the system
	 */
	public int getFixTime() {
		return op.getFixTime();
	}

	/**
	 * Check if game over
	 * 
	 * @returns true or false
	 */
	public boolean checkIfGameOver() {
		return op.checkIfGameOver();
	}

	/**
	 * Calculates the player's score relative to how much power they produced
	 */
	private void calculateScore() {
		score = 0;
		score = (int) op.getPower() * 5;

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
	 * the achievementResponse() method calculates what medal the player
	 * achieves when they hit a game over scenario
	 * 
	 * @param playerName
	 *            the name of the player
	 * @return response INdicates the response a player receives based on how
	 *         highly they scored in the game
	 */
	public String achievementResponse(String playerName) {

		String response = null;

		if (score > 250000) {
			if (score > 500000) {
				if (score > 950000) {
					// gold medal awarded if bigger than all of the score tiers
					response = String.format("You're a hero %s! you have been awarded the Medal of Honour by the Barack Obama himself." + '\n' + "Awarded for \"Gallantry and intrepidity at risk of life above and beyond the call of duty\"." + '\n'
							+ "Your power led to the destruction of ALL the alien ships!!", playerName);
				} else {
					// if above bronze tier but doesn't reach gold awarded
					// silver medal
					response = String.format("Congratulations %s! You've been awarded a Silver Star for \"Gallantry in action\"!" + '\n' + "You destroyed nearly all", playerName);
				}
			} else {
				// bronze reponse
				response = String.format("Well done %s! You have been awarded a Bronze Star for your efforts against the aliens." + '\n' + "You took out a good number with your party powered superweapon, maybe play again to take out some more?",
						playerName);
			} // under 3000 but over 1000 awarde bronze medal
		} else {
			// player did not reach any of the goals, gains no medal
			response = "Unlucky this time, you did not manage to kill enough aliens to earn a medal..." + '\n' + "Maybe you'd like to try again, the human race depends on you! (and the president is a bit embarassed he called you...)";
		}
		return response;
	}

	/**
	 * The next() method just calls next in OperatorSoftware (calculations are
	 * done here)
	 */
	public void next() {
		op.next();
	}

	/**
	 * Progresses current repairs in the system
	 * 
	 * @return Whether a repair has
	 *         finished.
	 */
	public boolean doFix() {
		return op.doFix();
	}

	/**
	 * Turn off a Pump in the system
	 * 
	 * @param pumpNum
	 *            The id of the pump to turn off
	 * @throws InvalidPumpException
	 *             Thrown when a bad pump ID is specified
	 * @throws AlreadyAtStateException
	 *             Thrown if the specified pump is already on
	 * @throws ComponentFailedException
	 *             Thrown if method is called when component is failed.
	 */
	public void turnOff(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
		op.turnOff(pumpNum);
	}

	/**
	 * Turn on a Pump in the system
	 * 
	 * @param pumpNum
	 *            The id of the pump to turn on
	 * @throws InvalidPumpException
	 *             Thrown when a bad pump ID is specified
	 * @throws AlreadyAtStateException
	 *             Thrown if the specified pump is already on
	 * @throws ComponentFailedException
	 *             Thrown if method is called when component is failed.
	 */
	public void turnOn(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
		op.turnOn(pumpNum);
	}

	/**
	 * Move the control rods up in the reactor by the amount specified
	 * 
	 * @param amount
	 *            The amount to raise the control rods by
	 * @return The new height of the control rods
	 * @throws InvalidRodsException
	 *             Thrown when amount specified is negative
	 * @throws MaximumRodsException
	 *             Thrown when the rods attempt to exceed the maximum height
	 * @throws ComponentFailedException
	 *             Thrown if method is called when component is failed.
	 */
	public int raise(int amount) throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
		return op.raise(amount);
	}

	/**
	 * Move the control rods down in the reactor by the amount specified
	 * 
	 * @param amount
	 *            The amount to lower the control rods by
	 * @return The new height of the control rods
	 * @throws InvalidRodsException
	 *             Thrown when amount specified is negative
	 * @throws MinimumRodsException
	 *             Thrown when the rods attempt to exceed the minimum height
	 * @throws ComponentFailedException
	 *             Thrown if method is called when component is failed.
	 */
	public int lower(int amount) throws InvalidRodsException, MinimumRodsException, ComponentFailedException {
		return op.lower(amount);
	}

	/**
	 * Opens a SteamValve in the system
	 * 
	 * @param valveNum
	 *            The id of the valve to open
	 * @throws InvalidValveException
	 *             Thrown when a bad valve ID is specified
	 * @throws AlreadyAtStateException
	 *             Thrown if the specified valve is already open
	 */
	public void open(int amount) throws InvalidValveException, AlreadyAtStateException {
		op.open(amount);
	}

	/**
	 * Close a SteamValve in the system
	 * 
	 * @param valveNum
	 *            The id of the valve to close
	 * @throws InvalidValveException
	 *             Thrown when a bad valve ID is specified
	 * @throws AlreadyAtStateException
	 *             Thrown if the specified valve is already closed
	 */
	public void close(int valveNum) throws InvalidValveException, AlreadyAtStateException {
		op.close(valveNum);
	}

	/**
	 * Begins a fix on any non-pump fixable component
	 * 
	 * @param component
	 *            The component to begin the fix upon
	 * @throws InvalidComponentException
	 *             Thrown when a bad component is specified
	 * @throws FixAlreadyUnderwayException
	 *             Thrown when a fix is already occurring in the system
	 * @throws NoFixNeededException
	 */
	public void fix(Component component) throws InvalidComponentException, FixAlreadyUnderwayException, NoFixNeededException {
		op.fix(component);
	}

	/**
	 * Begins fixes for pumps
	 * 
	 * @param component
	 *            Must be set to WaterPump, used as a check to ensure desired
	 *            functionality
	 * @param pumpNum
	 *            The id of the pump to be fixed
	 * @throws InvalidComponentException
	 *             Thrown when a non-pump component is specified
	 * @throws FixAlreadyUnderwayException
	 *             Thrown when a fix is already occurring in the system
	 * @throws NoFixNeededException
	 */
	public void fix(Component component, int pumpNum) throws InvalidPumpException, InvalidComponentException, FixAlreadyUnderwayException, NoFixNeededException {
		op.fix(component, pumpNum);
	}

	/**
	 * Save the reactor objects to a file
	 * 
	 * @param fileName
	 *            specifies the name of the file
	 */
	public void saveToFile(String fileName) {
		fileOutput.saveObjectToFile(op.getGameState(), fileName);
	}

	/**
	 * Load reactor objects from a file
	 * 
	 * @param fileName
	 *            specifies the name of the file
	 * @return Hashtable containing the reactor objects
	 */
	public boolean loadFromFile(String fileName) {
		Hashtable<String, Serializable> h = new Hashtable<String, Serializable>();
		try {
			h = fileInput.LoadObjectFromFile(fileName);
			if (h.get("reactor") != null) {
				op = new OperatorSoftware(h);
				return true;
			}
			return false;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		}
	}
}
