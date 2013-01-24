package icarus.exceptions;

/**
 * Thrown when a call to an invalid pump ID is issued
 *
 *@author Team Haddock
 */
public class InvalidValveException extends Exception {

	private int intValue;

	public InvalidValveException(int value) {
		this.intValue = value;
	}

	public String toString() {
		return "Invalid Valve entered: " + intValue + " Please enter a value of 0 or 1";
	}

}
