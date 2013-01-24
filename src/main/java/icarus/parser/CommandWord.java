package icarus.parser;

/**
 * 
 * This class holds information about the valid commands accepted by the parser.
 * Representations for all the valid command words for the game along with a
 * string in a particular language.
 * 
 * @author Team Haddock
 * @version 2012/2013
 */

public enum CommandWord {
	// A value for each command word along with its
	// corresponding user interface string.
	RAISE("raise"), LOWER("lower"), TURNOFF("turn off"), TURNON("turn on"), OPEN("open"), CLOSE("close"), SAVE("save"), LOAD("load"), FIX("fix"), NEXT("next"), QUIT("quit"), HELP("help"), UNKNOWN("?");

	// The command string.
	/**
	 * @uml.property name="commandString"
	 */
	private String commandString;

	/**
	 * Initialise with the corresponding command string.
	 * 
	 * @param commandString
	 *            The command string.
	 */
	CommandWord(String commandString) {

		this.commandString = commandString.toLowerCase().replaceAll(" ", "");
	}

	/**
	 * @return The command word as a string.
	 */
	public String toString() {
		return commandString;
	}

}
