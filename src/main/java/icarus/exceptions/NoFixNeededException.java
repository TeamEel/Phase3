package icarus.exceptions;

/**
 * Thrown when a fix command is issued to functional component
 *
 * @author Team Haddock
 */
public class NoFixNeededException extends Exception {

    public NoFixNeededException() {
    }

    @Override
    public String toString() {
        return ("Component does not need fixing");
    }
}
