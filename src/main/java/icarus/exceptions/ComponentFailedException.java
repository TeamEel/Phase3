package icarus.exceptions;

/**
 * Thrown when a command is given to a failed component
 *
 * @author Team Haddock
 */
public class ComponentFailedException extends Exception {

    public ComponentFailedException() {
    }

    @Override
    public String toString() {
        return "Component failed.";
    }
}
