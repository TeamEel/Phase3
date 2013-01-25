package icarus.operatorsoftware;

/**
 * Enum of Components that can fail in the system. Planned future use for keeping track of software failures.
 *
 * @author Team Haddock
 *
 */
public enum Component {

    REACTOR("reactor"),
    CONDENSER("condenser"),
    TURBINE("turbine"),
    WATERPUMP("waterpump"),
    CONDENSERPUMP("condenserpump"),
    GENERATOR("generator");
    private String componentString;

    private Component(String componentString) {

        this.componentString = componentString;
    }

    /**
     * @return a string version of the component
     */
    public String toString() {
        return componentString;
    }
}
