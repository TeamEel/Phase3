package icarus.exceptions;

/**
 * Thrown when an incorrect component is passed to a method
 *
 * @author Team Haddock
 */
public class InvalidComponentException extends Exception {

    String strValue;

    public InvalidComponentException(String value) {
        this.strValue = value;
    }

    @Override
    public String toString() {
        return "Invalid component entered: " + strValue;
    }
}
