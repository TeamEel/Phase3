package icarus.exceptions;

/**
 * Thrown when the control rods of the reactor are raised above their maximum value
 *
 * @author Team Haddock
 */
public class MaximumRodsException extends Exception {

    public String toString() {
        return "The maximum of 100 has been reached!";
    }
}
