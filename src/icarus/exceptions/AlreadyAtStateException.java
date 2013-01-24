package icarus.exceptions;

/**
 * Thrown when a component's state is changed to a state it is already at
 *@author Team Haddock
 */
public class AlreadyAtStateException extends Exception {

	private String state;

	public AlreadyAtStateException(String state) {
		this.state = state;
	}
	
	public String toString() {
		return "Component is already at state: " + state;
	}
}
