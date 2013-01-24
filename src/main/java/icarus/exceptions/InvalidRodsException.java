package icarus.exceptions;

/**
 * Thrown when a value outside the allowed boundaries is passed to the reactor
 * @author Team Haddock
 */

public class InvalidRodsException extends Exception {

	private int intValue;

	public InvalidRodsException(int value) {
		this.intValue = value;
	}

	public String toString() {
		return "Invalid ammount entered: " + intValue + " Please enter a value between 0 and 100.";
	}

}
