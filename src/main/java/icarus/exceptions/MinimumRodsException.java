package icarus.exceptions;

/**
 * Thrown when the control rods of the reactor are lowered below their minimum value
 *
 * @author Team Haddock
 */
public class MinimumRodsException extends Exception {

    public String toString() {
        return "The minimum of 0 has been reached!";
    }
}
