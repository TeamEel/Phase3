package icarus.exceptions;

/**
 * Thrown when a fix command is issued to a component which is already going through a fix
 *
 * @author Team Haddock
 */
public class FixAlreadyUnderwayException extends Exception {

    public FixAlreadyUnderwayException() {
    }
}
