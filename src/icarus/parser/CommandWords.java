package icarus.parser;

/**  
 *
 * The CommandWords class holds a mapping between a commands and the CommandWord 
 * associated with it.
 * 
 * @author  Team Haddock
 * @version 2012/2013
 */

import java.util.HashMap;

public class CommandWords {

	/**
	 * @uml.property name="validCommands"
	 * @uml.associationEnd 
	 *                     qualifier="aString:java.lang.String InputParser.CommandWord"
	 */
	private HashMap<String, CommandWord> validCommands;

	/**
	 * Constructor - initialise the command words.
	 */
	public CommandWords() {
		validCommands = new HashMap<String, CommandWord>();
		for (CommandWord command : CommandWord.values()) {
			if (command != CommandWord.UNKNOWN) {
				validCommands.put(command.toString(), command);
			}
		}
	}

	/**
	 * Find the CommandWord associated with a command word.
	 * 
	 * @param commandWord
	 *            The word to look up.
	 * @return The CommandWord correspondng to commandWord, or UNKNOWN if it is
	 *         not a valid command word.
	 */
	public CommandWord getCommandWord(String commandWord) {
		CommandWord command = validCommands.get(commandWord);
		if (command != null) {
			return command;
		} else {
			return CommandWord.UNKNOWN;
		}
	}

	/**
	 * Check whether a given String is a valid command word.
	 * 
	 * @return true if it is, false if it isn't.
	 */
	public boolean isCommand(String aString) {
		return validCommands.containsKey(aString);
	}

	/**
	 * Print all valid commands to System.out.
	 */
	public void showAll() {
		int counter = 1;
		for (String command : validCommands.keySet()) {
			if (command.equals("turnon")) {
				System.out.print(String.format("%-15s", "turn on "));
			} else if (command.equals("turnoff")) {
				System.out.print(String.format("%-15s", "turn off "));
			} else
				System.out.print(String.format("%-15s", command + "  "));
			if ((counter % 4) == 0) {
				System.out.println();
			}
			counter++;
		}
		System.out.println();
	}

}
