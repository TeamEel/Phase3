package icarus.exceptions;

/**
 * Thrown when a call to an invalid pump is issued
 *@author Team Haddock
 */
public class InvalidPumpException extends Exception {

	private int intValue;

	public InvalidPumpException(int value) {
		this.intValue = value;
	}

	public String toString() {
		return "Invalid pump entered: " + intValue + " Please enter a value of 0, 1 or 2";
	}

}
