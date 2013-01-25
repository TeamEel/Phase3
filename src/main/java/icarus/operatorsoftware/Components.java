package icarus.operatorsoftware;

/**
 * Enum of Components that can fail in the system. Planned future use for keeping track of software failures.
 *
 * @author Team Haddock
 *
 */
public enum Components {

    REACTOR("reactor"),
    CONDENSER("condenser"),
    TURBINE("turbine"),
    WATERPUMP("waterpump"),
    CONDENSERPUMP("condenserpump"),
    GENERATOR("generator");
    private String componentString;

    private Components(String componentString) {

        this.componentString = componentString;
    }

    /**
     * @return a string version of the component
     */
    @Override
    public String toString() {
        return componentString;
    }
}
